package pl.gpietrzyk.ship;

public class Destroyer extends WarShip {

    public Destroyer(Orientation orientation) {
        super(orientation);
    }

    @Override
    public int getDecksCount() {
        return 2;
    }
}
