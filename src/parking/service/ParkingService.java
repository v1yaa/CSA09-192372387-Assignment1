package parking.service;
import parking.model.*;
import parking.pricing.PricingService;
import java.time.LocalDateTime;
import java.util.*;
public class ParkingService {
 private final ParkingFacility facility; private final PricingService pricing;
 private final Map<String,ParkingTicket> active=new HashMap<>();
 public ParkingService(ParkingFacility f,PricingService p){facility=f;pricing=p;}
 public ParkingTicket enter(Vehicle v,LocalDateTime time){
  String r=v.getRegNo();if(active.containsKey(r))throw new IllegalStateException("Duplicate active vehicle");
  int[] pos=facility.allocate(r);if(pos==null)throw new IllegalStateException("Facility full");
  ParkingTicket t=new ParkingTicket(v,pos[0],pos[1],time);active.put(r,t);return t;
 }
 public double exit(String r,LocalDateTime time,Pass pass,double kWh){
  String key=r.trim().toUpperCase();ParkingTicket t=active.get(key);
  if(t==null)throw new IllegalArgumentException("No active ticket");
  double amount=pricing.fee(t.vehicle(),t.entry(),time,pass,kWh);
  t.close(time,amount);facility.release(t.floor(),t.slot());active.remove(key);return amount;
 }
 public int activeCount(){return active.size();}
}
