package parking.model;
import java.util.Locale;
public abstract class Vehicle {
 private final String regNo;
 protected Vehicle(String regNo) {
  if(regNo==null||regNo.isBlank()) throw new IllegalArgumentException("Registration required");
  this.regNo=regNo.trim().toUpperCase(Locale.ROOT);
 }
 public final String getRegNo(){return regNo;}
 public abstract String category();
 public abstract double hourlyRate();
 @Override public final boolean equals(Object o){return o instanceof Vehicle v && regNo.equals(v.regNo);}
 @Override public final int hashCode(){return regNo.hashCode();}
}
