package pl.gpietrzyk.ship;

import pl.gpietrzyk.board.Field;

public interface Ship {

    int getDecksCount();
    void hit();
    boolean isSunk();
    void setOnField(Field field, int deckNumber);
    Orientation getOrientation();
}
