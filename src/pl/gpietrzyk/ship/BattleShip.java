package pl.gpietrzyk.ship;

public class BattleShip extends WarShip {

    public BattleShip(Orientation orientation) {
        super(orientation);
    }

    @Override
    public int getDecksCount() {
        return 4;
    }
}
