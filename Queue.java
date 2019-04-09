import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SimQueue {
    int time; /* time-stamp for the current state of the queue */
    int nextArrivalTime; /* Arrival time is the time that the job enters service */
    int nextDepartureTime; /* Departure time is the time that the job will leave the server */
    int jobCount; /* count of the number of jobs that have arrived */
    Queue<Integer> queue;

    
    public SimQueue() {
        time = 0;
        nextArrivalTime = 0;
        nextDepartureTime = 0;
        queue = new LinkedList<Integer>();
    }

    public int getNextArrivalTime() {

    }
}