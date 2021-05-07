package pl.gpietrzyk.ship;

public class Submarine extends WarShip {
    public Submarine() {
        super(Orientation.HORIZONTAL);
    }

    public Submarine(Orientation orientation) {
        super(Orientation.HORIZONTAL);
    }

    @Override
    public int getDecksCount() {
        return 1;
    }
}
