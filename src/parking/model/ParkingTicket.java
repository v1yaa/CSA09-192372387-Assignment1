package parking.model;
import java.time.*;
public class ParkingTicket {
 private static long sequence=1000;
 private final String id="P-"+(++sequence);
 private final Vehicle vehicle; private final int floor,slot;
 private final LocalDateTime entry; private LocalDateTime exit;
 private double fee=-1;
 public ParkingTicket(Vehicle v,int f,int s,LocalDateTime e){
  if(v==null||e==null)throw new IllegalArgumentException();
  vehicle=v;floor=f;slot=s;entry=e;
 }
 public String id(){return id;} public Vehicle vehicle(){return vehicle;}
 public int floor(){return floor;} public int slot(){return slot;}
 public LocalDateTime entry(){return entry;}
 public void close(LocalDateTime x,double amount){
  if(x==null||x.isBefore(entry)||exit!=null||amount<0)throw new IllegalArgumentException("Invalid close");
  exit=x;fee=amount;
 }
}
