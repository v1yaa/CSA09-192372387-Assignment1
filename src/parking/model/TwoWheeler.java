package parking.model;
public class TwoWheeler extends Vehicle {
 public TwoWheeler(String r){super(r);}
 public String category(){return "TwoWheeler";}
 public double hourlyRate(){return 10;}
}
