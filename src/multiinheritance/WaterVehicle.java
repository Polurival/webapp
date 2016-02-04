package multiinheritance;

/**
 * GKislin
 * 28.09.2015.
 */
public class WaterVehicle extends Vehicle implements IWaterVehicle {
    final int displacement;

    public WaterVehicle(int maxSpeed, int power, int displacement) {
        super(maxSpeed, power);
        this.displacement = displacement;
    }

    @Override
    public int getDisplacement() {
        return displacement;
    }

    @Override
    public void move() {
        System.out.println("Flow");
    }
}
