package chess.piece;

import chess.piece.coordinate.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceMatcherTest {

    private Coordinate coordinate;

    @BeforeEach
    void setUp() {
        coordinate = Coordinate.createCoordinate("1", "a");
    }

    @Test
    void King_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.sourceOf('k'), Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new King(Team.WHITE, coordinate));
    }

    @Test
    void Queen_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.sourceOf('q'), Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new Queen(Team.WHITE, coordinate));
    }

    @Test
    void Rook_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.sourceOf('r'), Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new Rook(Team.WHITE, coordinate));
    }

    @Test
    void Knight_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.sourceOf('n'), Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new Knight(Team.WHITE, coordinate));
    }

    @Test
    void Bishop_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.sourceOf('b'), Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new Bishop(Team.WHITE, coordinate));
    }

    @Test
    void Pawn_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.sourceOf('p'), Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new Pawn(Team.WHITE, coordinate));
    }

    @Test
    void Empty_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.sourceOf('e'), Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new Empty(Team.WHITE, coordinate));
    }
}
