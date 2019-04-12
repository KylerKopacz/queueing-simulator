public class TestEnviroment{
  public static void main(String[] args) {
    
    // Parameters
    Double expLambda;
    SimQueue sim1;
    
    
    for (expLambda=0.1; expLambda <0.7; expLambda= expLambda + 0.1){
      System.out.println("Lambda is : " + expLambda);
      sim1 = new SimQueue(1,1,0.5,expLambda);
      System.out.println("Variance: 1 Mean Response Time: " + sim1.runSimulation()/expLambda);
      sim1 = new SimQueue(1.904,0.096,0.952,expLambda);
      System.out.println("Variance: 10 Mean Response Time: " + sim1.runSimulation()/expLambda);
      sim1 = new SimQueue(1.952,0.048,0.976,expLambda);
      System.out.println("Variance: 20 Mean Response Time: " + sim1.runSimulation()/expLambda);
      sim1 = new SimQueue(1.98,0.02,0.99,expLambda);
      System.out.println("Variance: 50 Mean Response Time: " + sim1.runSimulation()/expLambda);
    }

    TwoServerSimQueue sim2;

    for (expLambda=0.1; expLambda <0.7; expLambda= expLambda + 0.1){
      System.out.println("Lambda is : " + expLambda);
      sim2 = new TwoServerSimQueue(1,1,0.5,expLambda);
      System.out.println("Variance: 1 Mean Response Time: " + sim2.runSimulation()/expLambda);
      sim2 = new TwoServerSimQueue(1.904,0.096,0.952,expLambda);
      System.out.println("Variance: 10 Mean Response Time: " + sim2.runSimulation()/expLambda);
      sim2 = new TwoServerSimQueue(1.952,0.048,0.976,expLambda);
      System.out.println("Variance: 20 Mean Response Time: " + sim2.runSimulation()/expLambda);
      sim2 = new TwoServerSimQueue(1.98,0.02,0.99,expLambda);
      System.out.println("Variance: 50 Mean Response Time: " + sim2.runSimulation()/expLambda);
    }
  }
}
