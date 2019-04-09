public class HyperExponentialTest {
    public static void main(String[] args) {
        Hyperexponential h = new Hyperexponential(.5, .5, .5);
        int i =0;
        while( i < 100) {
            System.out.println(h.next());
            i++;
        }
    }
}