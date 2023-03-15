package chess.piece;

import chess.piece.coordinate.Coordinate;

public class King extends Piece {
    public King(Team team, Coordinate coordinate) {
        super(team, coordinate);
    }
    
    public char symbol() {
        return 'k';
    }
}
