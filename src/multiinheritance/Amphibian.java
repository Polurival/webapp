package multiinheritance;

import java.util.Random;

/**
 * GKislin
 * 28.09.2015.
 */
public class Amphibian extends Vehicle implements IWaterVehicle, ILandVehicle {
    private final ILandVehicle landVehicle;
    private final IWaterVehicle waterVehicle;
    private static final Random RANDOM = new Random();

    public Amphibian(int maxSpeed, int power, ILandVehicle landVehicle, IWaterVehicle waterVehicle) {
        super(maxSpeed, power);
        this.landVehicle = landVehicle;
        this.waterVehicle = waterVehicle;
    }

    private boolean isOnWater() {
        return RANDOM.nextBoolean();
    }

    @Override
    public int getWheelNumber() {
        return landVehicle.getWheelNumber();
    }

    @Override
    public int getDisplacement() {
        return waterVehicle.getDisplacement();
    }

    @Override
    public void move() {
        System.out.println(isOnWater() ? "Go" : "Flow");
    }
}
