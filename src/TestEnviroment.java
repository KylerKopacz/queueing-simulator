/**
 * Class that runs the simulation
 * @author Kyler Kopacz and Connor Haugh
 * @author https://github.com/KylerKopacz/queueing-simulator
 */
public class TestEnviroment {
  public static void main(String[] args) {
    
    // Parameters
    Double expLambda;
    SimQueue sim1;
    
    
    for (expLambda=0.1; expLambda <0.9; expLambda = expLambda + 0.1){
      sim1 = new SimQueue(1,1,0.5,expLambda);
      System.out.println("1, " + sim1.runSimulation()/expLambda + ", " + expLambda);
      sim1 = new SimQueue(1.904,0.096,0.952,expLambda);
      System.out.println("10, " + sim1.runSimulation()/expLambda + ", " + expLambda);
      sim1 = new SimQueue(1.952,0.048,0.976,expLambda);
      System.out.println("20, " + sim1.runSimulation()/expLambda + ", " + expLambda);
      sim1 = new SimQueue(1.98,0.02,0.99,expLambda);
      System.out.println("50, " + sim1.runSimulation()/expLambda + ", " + expLambda);
    }

    TwoServerSimQueue sim2;

    System.out.println("\n\nStarting Two Server with coinflip");
    for (expLambda=0.1; expLambda <0.9; expLambda= expLambda + 0.1){
      sim2 = new TwoServerSimQueue(1,1,0.5,expLambda);
      System.out.println("1, " + sim2.runSimulation(true)/expLambda + ", " + expLambda);
      sim2 = new TwoServerSimQueue(1.904,0.096,0.952,expLambda);
      System.out.println("10, " + sim2.runSimulation(true)/expLambda + ", " + expLambda);
      sim2 = new TwoServerSimQueue(1.952,0.048,0.976,expLambda);
      System.out.println("20, " + sim2.runSimulation(true)/expLambda + ", " + expLambda);
      sim2 = new TwoServerSimQueue(1.98,0.02,0.99,expLambda);
      System.out.println("50, " + sim2.runSimulation(true)/expLambda + ", " + expLambda);
    }

    System.out.println("\n\nStarting Two Server with smaller queue size picking");
    for (expLambda=0.1; expLambda <0.9; expLambda= expLambda + 0.1){
      //System.out.println("Lambda is : " + expLambda);
      sim2 = new TwoServerSimQueue(1,1,0.5,expLambda);
      System.out.println("1, " + sim2.runSimulation(false)/expLambda + ", " + expLambda);
      sim2 = new TwoServerSimQueue(1.904,0.096,0.952,expLambda);
      System.out.println("10, " + sim2.runSimulation(false)/expLambda + ", " + expLambda);
      sim2 = new TwoServerSimQueue(1.952,0.048,0.976,expLambda);
      System.out.println("20, " + sim2.runSimulation(false)/expLambda + ", " + expLambda);
      sim2 = new TwoServerSimQueue(1.98,0.02,0.99,expLambda);
      System.out.println("50, " + sim2.runSimulation(false)/expLambda + ", " + expLambda);
    }
  }
}
