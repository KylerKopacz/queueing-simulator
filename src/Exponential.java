/**
 * A class that represents an Exponential Distribution.
 * @author Kyler Kopacz
 * @author https://github.com/KylerKopacz/queueing-simulator
 */
public class Exponential {
    private double lambda;

    /**
     * Creates an Exponential Distribution.
     * @param lambda The rate for the distribution
     */
    public Exponential(double lambda) {
        this.lambda = lambda;
    }

    /**
     * Takes a sample from the distribution.
     * @return An int taken from an Exponential Distribution, with a min value of 1.
     */
    public int next() {
        return (int) ((-1.0) * (1.0 / lambda) * Math.log(1 - Math.random()) + 1);
    }
}
