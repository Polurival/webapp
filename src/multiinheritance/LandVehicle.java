package multiinheritance;

/**
 * GKislin
 * 28.09.2015.
 */
public class LandVehicle extends Vehicle implements ILandVehicle {

    int wheelNumber;

    public LandVehicle(int maxSpeed, int power, int wheelNumber) {
        super(maxSpeed, power);
        this.wheelNumber = wheelNumber;
    }

    @Override
    public int getWheelNumber() {
        return wheelNumber;
    }

    @Override
    public void move() {
        System.out.println("Go");
    }

    @Override
    public String toString() {
        return "LandVehicle{" +
                "wheelNumber=" + wheelNumber +
                '}';
    }
}
