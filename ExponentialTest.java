public class ExponentialTest {
    public static void main(String[] args) {
        Exponential e = new Exponential(.5);
        for(int i = 0; i < 100; i++) {
            System.out.println(e.next());
        }
    }
}