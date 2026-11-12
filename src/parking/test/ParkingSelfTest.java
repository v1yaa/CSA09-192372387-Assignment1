
package parking.test;

import parking.model.*;
import parking.service.*;
import parking.pricing.*;
import java.time.*;

public class ParkingSelfTest {

    static void ok(boolean condition, String description) {
        if (!condition) {
            throw new AssertionError("FAIL " + description);
        }
        System.out.println("PASS " + description);
    }

    public static void main(String[] args) {

        LocalDateTime t = LocalDateTime.of(2026, 11, 12, 10, 0);

        ParkingFacility f =
                new ParkingFacility(new int[]{1, 2, 1, 3, 1});

        ParkingService p =
                new ParkingService(f, new PricingService());

        // 1. Jagged allocation
        var ticket = p.enter(new TwoWheeler("TN01AA1111"), t);
        ok(ticket.floor() == 0 && ticket.slot() == 0,
                "jagged allocation");

        // 2. Duplicate registration
        boolean dup = false;
        try {
            p.enter(new Car("TN01AA1111"), t);
        } catch (IllegalStateException e) {
            dup = true;
        }
        ok(dup, "duplicate registration rejected");

        // 3. 61-minute parking fee
        ok(p.exit("TN01AA1111", t.plusMinutes(61), null, 0) == 20,
                "61 minute started-hour fee");

        PricingService ps = new PricingService();

        // 4. Car for 130 minutes
        ok(ps.fee(new Car("TN02BB2222"), t,
                t.plusMinutes(130), null, 0) == 90,
                "car 130 minutes");

        // 5. Electric car and charging
        ok(ps.fee(new ElectricCar("TN03CC3333"), t,
                t.plusMinutes(130), null, 10) == 210,
                "EV plus charging");

        // 6. E-bike flat fee
        ok(ps.fee(new EBike("TN04DD4444"), t,
                t.plusHours(4), null, 0) == 15,
                "e-bike flat fee");

        // 7. Delivery van daytime rate
        ok(ps.fee(new DeliveryVan("TN05EE5555"), t,
                t.plusHours(1), null, 0) == 60,
                "van daytime double rate");

        // 8. One-day pass
        ok(ps.fee(new Car("TN06FF6666"), t,
                t.plusHours(1),
                new OneDayPass(t.toLocalDate()), 0) == 0,
                "one-day pass");

        // 9. Monthly pass
        ok(ps.fee(new Car("TN07GG7777"), t,
                t.plusHours(1),
                new MonthlyPass(YearMonth.from(t), "STAFF"), 0) == 0,
                "monthly pass");

        // 10. Invalid floor
        ok(!f.release(99, 0),
                "invalid floor release");

        // 11. Zero-minute parking
        ok(ps.fee(new Car("TN08HH8888"), t,
                t, null, 0) == 0,
                "zero-minute parking fee");

        // 12. Exactly 60 minutes
        ok(ps.fee(new Car("TN09JJ9999"), t,
                t.plusMinutes(60), null, 0) == 30,
                "exactly 60-minute parking fee");

        // 13. Invalid slot
        ok(!f.release(0, 5),
                "invalid slot release");

        // 14. Already empty slot
        ok(!f.release(0, 0),
                "already empty slot release");

        // 15. Last available slot and full facility
        ParkingFacility small = new ParkingFacility(new int[]{1});

        int[] first = small.allocate("TN10KK1111");
        ok(first != null && first[0] == 0 && first[1] == 0,
                "last available slot allocated");

        ok(small.allocate("TN11LL2222") == null,
                "full facility rejects allocation");

        // 16. Negative floor capacity
        boolean invalidCapacity = false;
        try {
            new ParkingFacility(new int[]{2, -1});
        } catch (IllegalArgumentException e) {
            invalidCapacity = true;
        }

        ok(invalidCapacity,
                "negative floor capacity rejected");

        System.out.println("All tests passed.");
    }
}

