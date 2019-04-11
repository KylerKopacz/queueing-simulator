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


    public SimQueue() {
        time = 0;
        nextArrivalTime = 0;
        nextDepartureTime = 0;
        queue = new LinkedList<Integer>();
        exp = new Exponential(0.5);
        hasJob = true;
        hexp = new Hyperexponential(.5, .5, .5);
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
}
