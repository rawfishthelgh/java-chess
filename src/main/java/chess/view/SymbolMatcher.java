package chess.view;

import java.util.Arrays;

public enum SymbolMatcher {
    KING('k'),
    QUEEN('q'),
    ROOK('r'),
    KNIGHT('n'),
    BISHOP('b'),
    PAWN('p'),
    EMPTY('e');

    private final char symbol;

    SymbolMatcher(char symbol) {
        this.symbol = symbol;
    }

    public static char symbolOf(SymbolMatcher symbolMatcher){
        return symbolMatcher.symbol;
    }

}
