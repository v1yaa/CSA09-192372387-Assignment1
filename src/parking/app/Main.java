package parking.app;
import parking.model.*;import parking.service.*;import parking.pricing.*;
import java.time.*;
public class Main {
 public static void main(String[] args){
  ParkingService p=new ParkingService(new ParkingFacility(new int[]{2,3,2,4,1}),new PricingService());
  LocalDateTime t=LocalDateTime.of(2026,11,12,10,0);
  p.enter(new TwoWheeler("TN01AB1111"),t);
  p.enter(new Car("TN02CD2222"),t);
  p.enter(new ElectricCar("TN03EF3333"),t);
  System.out.println("Two-wheeler: Rs "+p.exit("TN01AB1111",t.plusMinutes(61),null,0));
  System.out.println("Car: Rs "+p.exit("TN02CD2222",t.plusMinutes(130),null,0));
  System.out.println("Electric car incl. 10 kWh: Rs "+p.exit("TN03EF3333",t.plusMinutes(130),null,10));
  System.out.println("Active vehicles: "+p.activeCount());
 }
}
