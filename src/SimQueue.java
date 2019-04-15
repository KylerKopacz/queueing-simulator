import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

/**
 * A Class representing a Queueing System Server (M/H^2/1)
 * @author Kyler Kopacz
 * @author https://github.com/KylerKopacz/queueing-server
 */
public class SimQueue {
  int time; /* time-stamp for the current state of the queue */
  int nextArrivalTime; /* Arrival time is the time that the job enters service */
  int nextDepartureTime; /* Departure time is the time that the job will leave the server */

  //a linked list to actually hold the queued jobs
  Queue<Integer> queue;

  //Exponential to draw from
  Exponential exp;

  //Hyperexponential to draw from
  Hyperexponential hexp;

  //whether there is currently a job being processed by the server
  boolean hasJob;
  
  /**
   * Constructor for a SimQueue System
   * @param hexpLambdaOne Rate for first Exponential in the Hyperexponential
   * @param hexpLambdaTwo Rate for the second Exponential in the Hyperexponential
   * @param hexpP The probability for the Hyperexponential (see Hyperexponential)
   * @param expLambda The job inter-arrival rate
   */
  public SimQueue(double hexpLambdaOne, double hexpLambdaTwo, double hexpP, double expLambda) {
    time = 0;
    nextArrivalTime = 0;
    nextDepartureTime = 0;
    queue = new LinkedList<Integer>();
    exp = new Exponential(expLambda);
    hasJob = true;
    hexp = new Hyperexponential(hexpLambdaOne, hexpLambdaTwo, hexpP);
    queue.add(hexp.next());
  }
  
  /**
   * Calculates and sets the next arrival time
   * @return The calculated next arrival time
   */
  public int getNextArrivalTime() {
    nextArrivalTime = time + exp.next();
    return nextArrivalTime;
  }
  
  /**
   * Get the next job departure time
   * @return the next job departure time
   */
  public int getNextDepartureTime() {
    return nextDepartureTime;
  }
  
  /**
   * Generates a job and adds it to the queue, and sets flags
   */
  public void generateJob() {
    queue.add(hexp.next());//generate & add a job to the queue
    
    if(queue.size() == 1) { // job directly enters service
      nextDepartureTime= time + queue.peek();
    }
    hasJob=true;
  }
  
  /**
   * Takes a job out of the server because it is done
   */
  public void finishJob(){
    //remove first element
    queue.poll();
    if(queue.isEmpty()) hasJob= false;
    else{
      nextDepartureTime = time + queue.peek();
    }
  }
  
  /**
   * Gets the number of jobs in service 
   * @return Gets the current size of the queue
   */
  public int getJobsInService(){
    return queue.size();
  }
  
  /**
   * Runs the actual simulation
   * @return The average number of jobs in the queue throughout the simulation
   */
  public int runSimulation() {
    int n = 0;// number of jobs
    ArrayList<Integer> snapshots = new ArrayList<Integer>();
    
    while (n < 10000000) {
      
      //System.out.println("Ariv: " + this.nextArrivalTime  + " Dep: " + this.nextDepartureTime + " Time: " + this.time);

      if(n > 100000)
        snapshots.add(queue.size());     
      
      // if the queue is empty, or the next arrival time is first
      if(this.nextArrivalTime < this.nextDepartureTime || !this.hasJob){
        this.time = this.nextArrivalTime; // advance system time to next arrival
        this.generateJob();
        this.nextArrivalTime = this.getNextArrivalTime(); // set new arrival time
        n++;
      }
      // if the queue is non-empty and a departure is sooner.
      else{
        this.time = this.nextDepartureTime; //advance system time to departure time
        this.finishJob();
        this.nextDepartureTime= this.getNextDepartureTime();// get new departure time
        if(this.time == this.nextArrivalTime){
          this.generateJob();
          this.nextArrivalTime= this.getNextArrivalTime();
          n++;
        }
      }
    }
    //System.out.println("Jobs In Service after n steps: " + this.getJobsInService());

    //calculate the average number of jobs in the queue
    int sum = 0;
    for(Integer i: snapshots) {
      sum += i;
    }
    int averageJobs = (int)sum/snapshots.size();
    //System.out.println("The average number of jobs in the queue was: " + averageJobs);
    
    return averageJobs;
  }
}