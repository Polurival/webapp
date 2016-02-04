package multiinheritance;

/**
 * GKislin
 * 28.09.2015.
 */
public abstract class Vehicle implements IVehicle {
    private final int maxSpeed;
    private final int power;

    public Vehicle(int maxSpeed, int power) {
        this.maxSpeed = maxSpeed;
        this.power = power;
    }

    @Override
    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public int getPower() {
        return power;
    }

    public abstract void move();
}
