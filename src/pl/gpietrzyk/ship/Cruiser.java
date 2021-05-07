package pl.gpietrzyk.ship;

public class Cruiser extends WarShip {

    public Cruiser(Orientation orientation) {
        super(orientation);
    }

    @Override
    public int getDecksCount() {
        return 3;
    }
}
