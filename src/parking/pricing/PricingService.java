package parking.pricing;
import parking.model.*;
import java.time.*;
public class PricingService {
 private static final double EBIKE_FLAT=15, CHARGE_RATE=12;
 public double fee(Vehicle v,LocalDateTime in,LocalDateTime out,Pass pass,double kWh){
  if(out.isBefore(in))throw new IllegalArgumentException("Exit before entry");
  if(pass!=null&&pass.isValid(in,out))return 0;
  double parking;
  if(v instanceof EBike) parking=EBIKE_FLAT;
  else if(v instanceof DeliveryVan) parking=vanFee(in,out);
  else parking=((Duration.between(in,out).toMinutes()+59)/60)*v.hourlyRate();
  double charging=v instanceof Chargeable c?c.chargingFee(kWh):0;
  return parking+charging;
 }
 // Minute-prorated: double car rate for overlap with [09:00,17:00).
 private double vanFee(LocalDateTime in,LocalDateTime out){
  long total=Duration.between(in,out).toMinutes(),day=0;
  LocalDateTime cur=in;
  while(cur.isBefore(out)){
   LocalDateTime end=cur.toLocalDate().plusDays(1).atStartOfDay();
   if(end.isAfter(out))end=out;
   LocalDateTime a=cur.toLocalDate().atTime(9,0),b=cur.toLocalDate().atTime(17,0);
   LocalDateTime x=cur.isAfter(a)?cur:a,y=end.isBefore(b)?end:b;
   if(y.isAfter(x))day+=Duration.between(x,y).toMinutes();
   cur=end;
  }
  return (total-day)*0.5+day*1.0; // normal 30/hr; daytime 60/hr
 }
}
