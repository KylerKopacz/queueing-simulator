
public class Exponential {
    private double lambda;

    public Exponential(double lambda) {
        this.lambda = lambda;
    }

    public int next() {
        return (int)((-1.0)*(1.0/lambda)* Math.log(1-Math.random())+1);
    }
}
