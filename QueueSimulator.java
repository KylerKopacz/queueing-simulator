public class QueueSimulator {
    /* This is where the actual simulator will run. I plan on adding arguments to the command line so we can run various tests without changing code. I will work on this after I finish the structure of the Queue system. */
    public static void main(String[] args) {
        SimQueue sim = new SimQueue(0.99, 0.5, 0.5, 0.5);                          
        sim.runSimulation(10000);
    }
}
