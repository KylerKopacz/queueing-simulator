import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Random;


public class TwoServerSimQueue{
  int time; /* time-stamp for the current state of the queue */
  int nextArrivalTime; /* Arrival time is the time that the job enters service */
  int nextDepartureTimeTwo;
  int nextDepartureTimeOne; /* Departure time is the time that the job will leave the server */
  Queue<Integer> queueOne;
  Queue<Integer> queueTwo;
  Exponential exp;
  Hyperexponential hexp;
  boolean hasJobOne;
  boolean hasJobTwo;
  Random rand;



  public TwoServerSimQueue(double hexpLambdaOne, double hexpLambdaTwo, double hexpP, double expLambda) {
    time = 0;
    nextArrivalTime = 0;
    nextDepartureTimeOne = 0;
    nextDepartureTimeTwo = 0;
    queueOne = new LinkedList<Integer>();
    queueTwo = new LinkedList<Integer>();
    exp = new Exponential(expLambda);
    hasJobOne = false;
    hasJobTwo = false;
    hexp = new Hyperexponential(hexpLambdaOne, hexpLambdaTwo, hexpP);
    rand=new Random();
  }



  public void generateJob(){
    if(rand.nextBoolean()){
      queueOne.add(hexp.next());
      hasJobOne=true;
      if(queueOne.size() == 1) nextDepartureTimeOne= time+ queueOne.peek();
    }
    else{
      queueTwo.add(hexp.next());
      hasJobTwo=true;
      if(queueTwo.size() == 1) nextDepartureTimeTwo= time+ queueTwo.peek();
    }
  }
  public void finishJobOne(){
    queueOne.poll();
    if(queueOne.isEmpty()) hasJobOne =false;
    else nextDepartureTimeOne = time + queueOne.peek();
  }
  public void finishJobTwo(){
    queueTwo.poll();
    if(queueTwo.isEmpty()) hasJobTwo=false;
    else nextDepartureTimeTwo= time + queueTwo.peek();
  }
  public int getJobsInService(){
    return queueOne.size() + queueTwo.size();
  }


  public double runSimulation(int n){
    ArrayList<Integer> snapshots = new ArrayList<Integer>();

    while(n>0){

      snapshots.add(getJobsInService());

      if((nextArrivalTime < nextDepartureTimeOne && nextArrivalTime < nextDepartureTimeTwo)|| (!hasJobOne && !hasJobTwo)){
        time = nextArrivalTime;
        generateJob();
        nextArrivalTime+= exp.next();
      }
      else if(nextDepartureTimeOne<nextDepartureTimeTwo){
        if(hasJobOne){
          time = nextDepartureTimeOne;
          finishJobOne();
        }
      }
      else if(hasJobTwo){
        time=nextDepartureTimeTwo;
        finishJobTwo();
      }
    }
    int sum = 0;
    for(Integer i: snapshots) {
      sum += i;
    }
    double averageJobs = sum/snapshots.size();

    return averageJobs;
  }
}
