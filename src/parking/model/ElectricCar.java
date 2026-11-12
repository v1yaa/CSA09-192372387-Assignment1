package parking.model;
public class ElectricCar extends Car implements Chargeable {
 public ElectricCar(String r){super(r);}
 @Override public String category(){return "ElectricCar";}
 public double chargingFee(double kWh){
  if(kWh<0) throw new IllegalArgumentException("Negative kWh");
  return kWh*12;
 }
}
