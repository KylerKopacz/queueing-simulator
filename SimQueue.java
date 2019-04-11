import java.util.LinkedList;
import java.util.Queue;

public class SimQueue {
  int time; /* time-stamp for the current state of the queue */
  int nextArrivalTime; /* Arrival time is the time that the job enters service */
  int nextDepartureTime; /* Departure time is the time that the job will leave the server */
  Queue<Integer> queue;
  Exponential exp;
  Hyperexponential hexp;
  boolean hasJob;
  
  
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
  
  public int getNextArrivalTime() {
    nextArrivalTime = time + exp.next();
    return nextArrivalTime;
  }
  
  public int getNextDepartureTime() {
    return nextDepartureTime;
  }
  
  public int generateJob() {
    queue.add(hexp.next());//generate & add a job to the queue
    
    if(queue.size() == 1) { // job directly enters service
      nextDepartureTime= time + queue.peek();
    }
    hasJob=true;
    return queue.peek();
  }
  
  public void finishJob(){
    //remove first element
    queue.poll();
    if(queue.isEmpty()) hasJob= false;
    else{
      nextDepartureTime = time + queue.peek();
    }
  }
  
  public int getJobsInService(){
    return queue.size();
  }
  
  public void runSimulation(int numJobs) {
    int n = numJobs;// number of jobs
    
    while (n>0){
      
      System.out.println("Ariv: " + this.nextArrivalTime  + " Dep: " + this.nextDepartureTime + " Time: " + this.time);
      
      
      // if the queue is empty, or the next arrival time is first
      if(this.nextArrivalTime < this.nextDepartureTime || !this.hasJob){
        this.time = this.nextArrivalTime; // advance system time to next arrival
        this.generateJob();
        this.nextArrivalTime= this.getNextArrivalTime(); // set new arrival time
        n--;
      }
      // if the queue is non-empty and a departure is sooner.
      else{
        this.time = this.nextDepartureTime; //advance system time to departure time
        this.finishJob();
        this.nextDepartureTime= this.getNextDepartureTime();// get new departure time
        if(this.time == this.nextArrivalTime){
          this.generateJob();
          this.nextArrivalTime= this.getNextArrivalTime();
          n--;
        }
      }
    }
    System.out.println("Jobs In Service after n steps: " + this.getJobsInService());
  }
}