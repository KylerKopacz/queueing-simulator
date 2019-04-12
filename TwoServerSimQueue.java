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
    rand = new Random();
  }
  
  
  
  public void generateJob(){
    int job = hexp.next();
    if(rand.nextBoolean()){
      queueOne.add(job);
      hasJobOne = true;
      if(queueOne.size() == 1) nextDepartureTimeOne = time + queueOne.peek();
    } else {
      queueTwo.add(job);
      hasJobTwo=true;
      if(queueTwo.size() == 1) nextDepartureTimeTwo= time + queueTwo.peek();
    }
  }
  
  public void finishJobOne(){
    queueOne.poll();
    if(queueOne.isEmpty()) {
      hasJobOne = false;
      nextDepartureTimeOne = 0;
    } else {
      nextDepartureTimeOne = time + queueOne.peek();
    }
  }
  
  public void finishJobTwo(){
    queueTwo.poll();
    if(queueTwo.isEmpty()) {
      hasJobTwo = false;
      nextDepartureTimeTwo = 0;
    } else {
      nextDepartureTimeTwo = time + queueTwo.peek();
    } 
  }

  public int getJobsInService(){
    return queueOne.size() + queueTwo.size();
  }
  
  public double runSimulation(int numJobs){
    int n = numJobs;
    ArrayList<Integer> snapshots = new ArrayList<Integer>();
    
    while(n > 0){
      
      snapshots.add(getJobsInService());

      System.out.println(" Time: " + this.time + " Ariv: " + this.nextArrivalTime  + " Departure One: " + this.nextDepartureTimeOne  + " Departure Two: " + this.nextDepartureTimeTwo);
      
      if(n == numJobs) {
        generateJob();
        nextArrivalTime = time + exp.next();
        n--;
      } else if((nextArrivalTime < nextDepartureTimeOne || nextArrivalTime < nextDepartureTimeTwo)) {
        time = nextArrivalTime;
        generateJob();
        nextArrivalTime += exp.next();
        n--;
      } else if(nextArrivalTime == nextDepartureTimeOne || nextArrivalTime == nextDepartureTimeTwo) {
        time = nextArrivalTime;
        if(nextDepartureTimeOne == nextDepartureTimeTwo) {
          finishJobOne();
          finishJobTwo();
          generateJob();
          n--;
        } else if(nextArrivalTime == nextDepartureTimeOne) {
          finishJobOne();
          generateJob();
          n--;
        } else {
          finishJobTwo();
          generateJob();
          n--;
        }
        nextArrivalTime += exp.next();
      } else if(nextDepartureTimeOne != 0 && nextDepartureTimeTwo == 0) {
        time = nextDepartureTimeOne;
        finishJobOne();
      } else if(nextDepartureTimeOne == 0 && nextDepartureTimeTwo != 0) {
        time = nextDepartureTimeTwo;
        finishJobTwo();
      } else if(nextDepartureTimeOne != 0 && nextDepartureTimeTwo != 0) {
        if(nextDepartureTimeOne < nextDepartureTimeTwo) {
          time = nextDepartureTimeOne;
          finishJobOne();
        }
        if(nextDepartureTimeOne > nextDepartureTimeTwo) {
          time = nextDepartureTimeTwo;
          finishJobTwo();
        }
        if(nextDepartureTimeOne == nextDepartureTimeTwo) {
          finishJobOne();
          finishJobTwo();
        }
      } else if(nextDepartureTimeOne == 0 && nextDepartureTimeTwo == 0) {
        time = nextArrivalTime;
        generateJob();
        nextArrivalTime += exp.next();
        n--;
      }
    }

    int sum = 0;
    for(Integer i: snapshots) {
      sum += i;
    }
    double averageJobs = sum/snapshots.size();

    System.out.println("The average number of jobs in the queue was: " + averageJobs);
    
    return averageJobs;
  }
}
