public class QueueSimulator {
    /* This is where the actual simulator will run. I plan on adding arguments to the command line so we can run various tests without changing code. I will work on this after I finish the structure of the Queue system. */
    public static void main(String[] args) {
        //TODO - make changes for different ns, rates

        int n =100;// number of jobs

        SimQueue simque = new SimQueue();

        while (n>0){

          System.out.println("Ariv: " + simque.nextArrivalTime  + " Dep: " + simque.nextDepartureTime + "time: " + simque.time);


          // if the queue is empty, or the next arrival time is first
          if(simque.nextArrivalTime < simque.nextDepartureTime || !simque.hasJob){
            simque.time = simque.nextArrivalTime; // advance system time to next arrival
            simque.generateJob();
            simque.nextArrivalTime= simque.getNextArrivalTime(); // set new arrival time
            n--;
          }
            // if the queue is non-empty and a departure is sooner.
            else{
              simque.time = simque.nextDepartureTime; //advance system time to departure time
              simque.finishJob();
              simque.nextDepartureTime= simque.getNextDepartureTime();// get new departure time
              if(simque.time == simque.nextArrivalTime){
                simque.generateJob();
                simque.nextArrivalTime= simque.getNextArrivalTime();
                n--;
              }
            }
          }
        System.out.println("Jobs In Service after n steps: " + simque.getJobsInService());
    }
}
