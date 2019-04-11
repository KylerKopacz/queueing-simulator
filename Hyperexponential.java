import java.util.Random;
import java.lang.Math;
public class Hyperexponential {
    double lOne;
    double lTwo; /* the two rates of our distributions */
    double p;
    Random rand = new Random();


    public Hyperexponential(double lamdaOne, double lamdaTwo, double parameter) {
      lOne=lamdaOne;
      lTwo=lamdaTwo;
      p=parameter;
    }

    public int next() {
      // generate the next instance of a Hyperexponential, using the Inverse transform method on an Exponential distibution with either lamdaOne or lamdaTwo as the parameters
       // formula x = -(1/lamda)(ln(1- (# from uniform 1-0)))
      if(rand.nextDouble() < p){ //go with exp distro 1
        return (int)((-1.0)*(1.0/lOne)* Math.log(1-rand.nextDouble())+1);
      }
      else{ // go with exp distro 2
        return (int)((-1.0)*(1.0/lTwo)* Math.log(1-rand.nextDouble())+1);
      }
    }
}
