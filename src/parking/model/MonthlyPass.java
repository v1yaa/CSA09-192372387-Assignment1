package parking.model;
import java.time.*;
public class MonthlyPass implements Pass {
 private final YearMonth month; private final String holder;
 public MonthlyPass(YearMonth m,String h){
  if(m==null||(!"STUDENT".equalsIgnoreCase(h)&&!"STAFF".equalsIgnoreCase(h)))
   throw new IllegalArgumentException("Monthly pass requires student/staff and month");
  month=m; holder=h.toUpperCase();
 }
 public boolean isValid(LocalDateTime in,LocalDateTime out){
  return YearMonth.from(in).equals(month)&&YearMonth.from(out).equals(month);
 }
}
