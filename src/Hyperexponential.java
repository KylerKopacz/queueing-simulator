import java.util.Random;
import java.lang.Math;

/**
 * A class that represents a Hyperexponential Distibution.
 * @author Kyler Kopacz
 * @author https://github.com/KylerKopacz/queueing-simulator
 */
public class Hyperexponential {
    //first rate
    double lOne;

    //second rate
    double lTwo;

    //parameter
    double p;

    //for sampling calculations
    Random rand;

    /**
     * Constructor for a Hyperexponential Object
     * @param lamdaOne Rate of the first Exponential
     * @param lamdaTwo Rate of the second Exponential
     * @param parameter The probability that the a sample will be drawn from the first Exponential (1 - p probability for the second Exponential)
     */
    public Hyperexponential(double lamdaOne, double lamdaTwo, double parameter) {
      lOne=lamdaOne;
      lTwo=lamdaTwo;
      p=parameter;
      rand = new Random();
    }

    /**
     * Take a sample from a Hyperexponential Distribution
     * @return An int taken from the Hyperexponential Distribution, with a min value of 1.
     */
    public int next() {
      // generate the next instance of a Hyperexponential, using the Inverse transform method on an Exponential distibution with either lamdaOne or lamdaTwo as the parameters
       // formula x = -(1/lamda)(ln(1- (# from uniform 1-0)))
      if(rand.nextDouble() < p) { //go with exp distro 1
        return (int) ((-1.0) * (1.0 / lOne) * Math.log(1 - rand.nextDouble()) + 1);
      } else{ // go with exp distro 2
        return (int) ((-1.0) * (1.0 / lTwo) * Math.log(1 - rand.nextDouble()) + 1);
      }
    }
}
