package parking.model;
public class Car extends Vehicle {
 public Car(String r){super(r);}
 public String category(){return "Car";}
 public double hourlyRate(){return 30;}
}
