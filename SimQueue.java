import java.util.LinkedList;
import java.util.Queue;

public class SimQueue {
    int time; /* time-stamp for the current state of the queue */
    int nextArrivalTime; /* Arrival time is the time that the job enters service */
    int nextDepartureTime; /* Departure time is the time that the job will leave the server */
    int jobCount; /* count of the number of jobs that have arrived */
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
        hasJob = false;
        hexp = new Hyperexponential(.5, .5, .5);
    }

    public int getNextArrivalTime() {
        return time + exp.next();
    }

    public int getNextDepartureTime() {
        if(!hasJob) {
            return time + queue.peek();
        } else {
            return nextDepartureTime;
        }
    }

    public int generateJob() {
        return hexp.next();
    }
}