public class TestEnviroment{
  public static void main(String[] args) {

    // Parameters
    Double hexpLambda1;
    Double hexpLambda2;
    Double expLambda;
    Double hexpProb = 0.5; // Probability of the "coinflip"
    int tests;
    SimQueue sim;


    for (expLambda=0.1; expLambda <1.0; expLambda= expLambda + 0.1){
          sim = new SimQueue(1,1,0.5,expLambda);
          System.out.println("Variance: 1 Mean Responce Time: " + sim.runSimulation()/expLambda);
          sim = new SimQueue(1.904,0.096,0.952,expLambda);
          System.out.println("Variance: 10 Mean Responce Time: " + sim.runSimulation()/expLambda);
          sim = new SimQueue(1.952,0.048,0.976,expLambda);
          System.out.println("Variance: 20 Mean Responce Time: " + sim.runSimulation()/expLambda);
          sim = new SimQueue(1.98,0.02,0.99,expLambda);
          System.out.println("Variance: 10 Mean Responce Time: " + sim.runSimulation()/expLambda);
    }

  }
}
