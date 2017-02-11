package net.ngeor.t3.models;

/**
 * Represents the state of a tile on the game board.
 * Created by ngeor on 1/29/2017.
 */
public enum TileState {
    /**
     * The tile is empty.
     */
    EMPTY,

    /**
     * The tile is marked X.
     */
    X,

    /**
     * The tile is marked O.
     */
    O;

    public static TileState fromPlayer(PlayerSymbol playerSymbol) {
        switch (playerSymbol) {
            case X:
                return TileState.X;
            case O:
                return TileState.O;
            default:
                throw new IllegalArgumentException();
        }
    }
}
