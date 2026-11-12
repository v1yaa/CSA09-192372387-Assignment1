package parking.model;
import java.time.*;
public class OneDayPass implements Pass {
 private final LocalDate date;
 public OneDayPass(LocalDate d){if(d==null)throw new IllegalArgumentException();date=d;}
 public boolean isValid(LocalDateTime in,LocalDateTime out){
  return in.toLocalDate().equals(date)&&out.toLocalDate().equals(date);
 }
}
