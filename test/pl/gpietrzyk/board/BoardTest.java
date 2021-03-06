package pl.gpietrzyk.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.gpietrzyk.IllegalMoveException;
import pl.gpietrzyk.ship.Orientation;
import pl.gpietrzyk.ship.BattleShip;
import pl.gpietrzyk.ship.Destroyer;
import pl.gpietrzyk.ship.Submarine;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;
    private Field field;

    @BeforeEach
    void setUp() {
        board = new Board();
        field = board.getField(0,0);
    }

    @Test
    void testShouldBoardFill() {
        boolean result = board.fieldIsEmpty(board.getBoard());
        assertEquals(result,true);
    }

    @Test
    void testShouldAddShip() throws IllegalMoveException {
        board.addShip(0,0, new Submarine());
        assertEquals(State.SHIP, field.getState());
    }

    @Test
    void testShouldAddDestroyerOnFields() throws IllegalMoveException {
        board.addShip(0,0, new Destroyer(Orientation.HORIZONTAL));
        field = board.getField(1,0);
        assertEquals(State.SHIP, field.getState());
    }

    @Test
    void testShouldNotBeAbleToAddFiveWarships() throws IllegalMoveException {
        board.addShip(3,3, new Submarine());
        board.addShip(2,1, new Submarine());
        board.addShip(7,7, new Submarine());
        board.addShip(9,9, new Submarine());

        IllegalMoveException exception = assertThrows(
                IllegalMoveException.class,
                () -> board.addShip(0,0, new Submarine()));

        assertEquals("You have all ship set!",exception.getMessage());
    }

    @Test
    void testShouldNotBeAbleToGetOutside() throws  IllegalMoveException{
       IllegalMoveException exception = assertThrows(
                IllegalMoveException.class, ()
                        -> board.addShip(9,0, new Destroyer(Orientation.HORIZONTAL)));

        assertEquals("Ship set outside board!", exception.getMessage());
    }

    @Test
    void testShouldNotBeAbleToAddTwoBattleShips() throws IllegalMoveException {
        board.addShip(0,0,new BattleShip(Orientation.HORIZONTAL));

        IllegalMoveException exception = assertThrows(
                IllegalMoveException.class, ()
                        -> board.addShip(6,0,new BattleShip(Orientation.HORIZONTAL)));

        assertEquals("You have all ship set!", exception.getMessage());
    }

    @Test
    void testShootMarkAsMiss() throws IllegalMoveException {
        board.shoot(0,0);
        assertEquals(board.getField(0,0).getState(), State.MISS);
    }

    @Test
    void testShootMarkAsHit() throws IllegalMoveException {
        board.addShip(0,0, new Destroyer(Orientation.HORIZONTAL));
        board.shoot(0,0);
        assertEquals(board.getField(0,0).getState(),State.HIT);
    }

    @Test
    void testShootMarkAsSunk() throws IllegalMoveException {
        board.addShip(0,0, new Destroyer(Orientation.HORIZONTAL));
        board.shoot(0,0);
        board.shoot(1,0);
        assertEquals(board.getField(0,0).getState(),State.SUNK);
        assertEquals(board.getField(1,0).getState(),State.SUNK);
    }

    @Test
    void testShouldDecreaseShipsOnBoard() throws IllegalMoveException {
        board.addShip(0,0, new Destroyer(Orientation.HORIZONTAL));
        board.shoot(0,0);
        board.shoot(1,0);
        assertEquals(0,board.getShipsCount());
    }

    @Test
    void testShouldNotBeAbleToShootTwice() throws IllegalMoveException {
        board.shoot(0,0);

       IllegalMoveException exception = assertThrows(
               IllegalMoveException.class, ()-> board.shoot(0,0));

       assertEquals("You have already shot here!", exception.getMessage());
    }

    @Test
    void testShouldHaveAllShipsGenerated() {
        board.fillBoard();
        assertEquals(10, board.getShipsCount());
    }
}
