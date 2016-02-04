package multiinheritance;

import java.util.Arrays;

/**
 * GKislin
 * 02.10.2015.
 */
public class Main {
    public static void main(String[] args) {
        IVehicle[] vehicles = new Vehicle[3];
        ILandVehicle[] vehicles2 = new LandVehicle[5];
        System.out.println(vehicles.getClass().equals(vehicles2.getClass()));

        System.out.println(vehicles.length + " " + vehicles.getClass());
        LandVehicle landVehicle = new LandVehicle(170, 100, 4);
        WaterVehicle waterVehicle = new WaterVehicle(40, 300, 300);
        Amphibian amphibian = new Amphibian(50, 150, landVehicle, waterVehicle);
        vehicles[0] = amphibian;
        vehicles[1] = landVehicle;
        vehicles[2] = waterVehicle;
        for (IVehicle vehicle : vehicles) {
            if (vehicle instanceof IWaterVehicle) {
                System.out.println("Displacement" + ((IWaterVehicle) vehicle).getDisplacement());
            }
            if (ILandVehicle.class.isInstance(vehicle)) {
                System.out.println("WheelNumber" + ((ILandVehicle) vehicle).getWheelNumber());
            }
            vehicle.move();
        }
    }
}
