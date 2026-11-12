public class WrapperDemo {
    public static void main(String[] args) {

        int duration = 60;

        // Autoboxing
        Integer boxedDuration = duration;

        // Unboxing
        int actualDuration = boxedDuration;

        Integer a = 200;
        Integer b = 200;

        System.out.println("Primitive value: " + duration);
        System.out.println("Wrapper value: " + boxedDuration);
        System.out.println("Unboxed value: " + actualDuration);

        System.out.println("Using == : " + (a == b));
        System.out.println("Using equals(): " + a.equals(b));
    }
}