import java.util.ArrayList;

public class Queue {
    int time; /* time-stamp for the current state of the queue */
    ArrayList<Double> jobArrivalTimes; /* list of all job arrival times */
    ArrayList<Double> jobDepartureTimes; /* list of all job departure times */
    int jobCount; /* count of the number of jobs that have arrived */

    
    public Queue() {
        time = 0;
        jobArrivalTimes = new ArrayList<Double>();
        jobDepartureTimes = new ArrayList<Double>();
    }
}