package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMatcher;
import chess.domain.piece.Point;
import chess.view.SymbolMatcher;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class RowPieces implements Comparable<RowPieces> {

    private static final int FIRST_PIECE_INDEX = 0;

    private final List<Piece> pieces;

    public RowPieces(String rowNum) {
        this(InitialPieces.from(rowNum));
    }

    private RowPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public BigDecimal sumPiecePoints() {
        Point firstPoint = pieces().get(0).point();
        Point secondPoint = pieces().get(1).point();
        BigDecimal sum = firstPoint.sum(secondPoint);
        for (int i = 2; i < pieces.size(); i++) {
            Point previousPoint = pieces.get(i - 1).point();
            Point presentPoint = pieces.get(i).point();
            sum = presentPoint.subtract(sum).add(presentPoint.sum(previousPoint));
        }
        return sum;
    }

    @Override
    public int compareTo(RowPieces o) {
        Piece firstPiece = this.pieces.get(FIRST_PIECE_INDEX);
        Piece otherPiece = o.pieces.get(FIRST_PIECE_INDEX);
        return firstPiece.compareTo(otherPiece);
    }

    public boolean isMovable(RowPieces targetRowPieces, Coordinate sourceCoordinate, Coordinate destinationCoordinate) {
        Piece sourcePiece = findPieceByCoordinate(this, sourceCoordinate);
        Piece destinationPiece = findPieceByCoordinate(targetRowPieces, destinationCoordinate);

        return sourcePiece.isMovable(destinationPiece);
    }

    private Piece findPieceByCoordinate(RowPieces rowPieces, Coordinate coordinate) {
        return rowPieces.pieces.stream()
            .filter(piece -> piece.hasCoordinate(coordinate))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 컬럼입니다"));
    }

    public boolean isPieceByColumnNotEmpty(Coordinate coordinate) {
        return !findPieceByCoordinate(this, coordinate).isSameTeam(Team.EMPTY);
    }

    public void move(
        RowPieces destinationRowPieces,
        Coordinate sourceCoordinate,
        Coordinate destinationCoordinate
    ) {
        Piece sourcePiece = findPieceByCoordinate(this, sourceCoordinate);
        Piece newPiece = sourcePiece.newSourcePiece(destinationCoordinate);

        destinationRowPieces.switchPiece(newPiece, destinationCoordinate);
        switchPiece(createEmpty(sourceCoordinate), sourceCoordinate);
    }

    private void switchPiece(Piece newPiece, Coordinate coordinate) {
        this.pieces.set(coordinate.columnIndex(), newPiece);
    }

    private Empty createEmpty(Coordinate coordinate) {
        return (Empty) PieceMatcher.of(SymbolMatcher.EMPTY, Team.EMPTY, coordinate);
    }

    public boolean isPieceKnight(Coordinate coordinate) {
        return findPieceByCoordinate(this, coordinate).isKnight();
    }

    public boolean hasCoordinate(Coordinate coordinate) {
        return this.pieces.stream().anyMatch(
            piece -> piece.hasCoordinate(coordinate)
        );
    }

    public List<Piece> pieces() {
        return pieces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RowPieces rowPieces = (RowPieces) o;
        return Objects.equals(pieces, rowPieces.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieces);
    }
}
