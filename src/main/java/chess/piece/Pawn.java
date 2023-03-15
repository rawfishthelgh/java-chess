package chess.piece;

import chess.piece.coordinate.Coordinate;

import java.util.List;

public class Pawn extends Piece {
    private static final int WHITE_PAWN_START_ROW = 7;
    private static final int BLACK_PAWN_START_ROW = 2;
    
    public Pawn(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    @Override
    public char symbol() {
        return 'p';
    }
    
    @Override
    public boolean isMovable(Piece targetPiece) {
        List<Integer> subtractCoordinate = subtractCoordinate(targetPiece);
        int subtractedRow = subtractCoordinate.get(ROW_INDEX);
        int subtractedColumn = subtractCoordinate.get(COLUMN_INDEX);
        return isPawnMovable(targetPiece, subtractedRow, subtractedColumn);
    }
    
    private boolean isPawnMovable(Piece targetPiece, int subtractedRow, int subtractedColumn) {
        if (isOutOfMovementRadius(targetPiece,subtractedRow, subtractedColumn)) {
            return false;
        }
        
        return isDifferentTeam(targetPiece);
    }
    
    private boolean isOutOfMovementRadius(Piece targetPiece, int subtractedRow, int subtractedColumn) {
        if (isSameTeam(Team.WHITE)) {
            return isWhitePawnImmovable(targetPiece, subtractedRow, subtractedColumn);
        }
        
        if (isSameTeam(Team.BLACK)) {
            return isBlackPawnMovable(targetPiece, subtractedRow, subtractedColumn);
        }
        return true;
    }
    
    private boolean isWhitePawnImmovable(Piece targetPiece, int subtractedRow, int subtractedColumn) {
        if (isWhitePawnMoveForwardTwoSpace(subtractedRow, subtractedColumn)){
            return isPawnImmovableForwardTwoSpace(targetPiece, WHITE_PAWN_START_ROW);
        }
        if (isWhitePawnOneDiagonalDirection(subtractedRow, subtractedColumn)) {
            return isPawnImmovableDiagonalOneSpace(targetPiece, Team.BLACK);
        }
        if (isWhitePawnOneStraightDirection(subtractedRow, subtractedColumn)) {
            return isPawnImmovableForwardOneSpace(targetPiece);
        }
        return true;
    }
    
    private boolean isWhitePawnMoveForwardTwoSpace(int subtractedRow, int subtractedColumn) {
        return subtractedRow == 2 && subtractedColumn == 0;
    }
    
    private boolean isWhitePawnOneDiagonalDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == 1 && Math.abs(subtractedColumn) == 1;
    }
    
    private boolean isWhitePawnOneStraightDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == 1 && subtractedColumn == 0;
    }
    
    private boolean isPawnImmovableForwardTwoSpace(Piece targetPiece, int startRow) {
        return !coordinate().isPawnStartRow(startRow) || !targetPiece.isSameTeam(Team.EMPTY);
    }
    
    private boolean isPawnImmovableDiagonalOneSpace(Piece targetPiece, Team team) {
        return !targetPiece.isSameTeam(team);
    }
    
    private boolean isPawnImmovableForwardOneSpace(Piece targetPiece) {
        return !targetPiece.isSameTeam(Team.EMPTY);
    }
    
    private boolean isBlackPawnMovable(Piece targetPiece, int subtractedRow, int subtractedColumn) {
        if (isBlackPawnMoveForwardTwoSpace(subtractedRow, subtractedColumn)) {
            return isPawnImmovableForwardTwoSpace(targetPiece, BLACK_PAWN_START_ROW);
        }
        if (isBlackPawnDiagonalDirection(subtractedRow, subtractedColumn)) {
            return isPawnImmovableDiagonalOneSpace(targetPiece, Team.WHITE);
        }
        if (isBlackPawnOneStraightDirection(subtractedRow, subtractedColumn)) {
            return isPawnImmovableForwardOneSpace(targetPiece);
        }
        return true;
    }
    
    private boolean isBlackPawnMoveForwardTwoSpace(int subtractedRow, int subtractedColumn) {
        return subtractedRow == -2 && subtractedColumn == 0;
    }
    
    private boolean isBlackPawnDiagonalDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == -1 && Math.abs(subtractedColumn) == 1;
    }
    
    private boolean isBlackPawnOneStraightDirection(int subtractedRow, int subtractedColumn) {
        return subtractedRow == -1 && subtractedColumn == 0;
    }
}
