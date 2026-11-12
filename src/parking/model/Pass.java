package parking.model;
import java.time.LocalDateTime;
public interface Pass {
 boolean isValid(LocalDateTime in, LocalDateTime out);
}
