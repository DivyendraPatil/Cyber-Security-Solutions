package busSimulationService;

import java.util.LinkedList;

/**
 * @author Lancelot
 * Main Class from where the program runs
 */

public class MainJavaSimulator {
		
	public static Double timer = 0.0; // Current time in the simulation
	public static LinkedList<Event> events = new LinkedList<Event>(); // Current stack of the events to occur in future
	private static LinkedList<BusStop> busStops = new LinkedList<BusStop>(); // List of current bus stops and their statuses
	private static LinkedList<Bus> buses = new LinkedList<Bus>(); // List of buses and their statuses

	public static void main(String[] args) {
		//Initialization variables for Simulation
		Double simulationTime = 8 * 60 * 60.0;
		Integer noOfBuses = 5;
		Bus.timeToTravelToNextStop = 5 * 60.0;
		Integer noOfBusStops = 15;
		PassengerInfo.BoardingTime = 2.0;
		BusStop.meanInterArrivalRate = 12.0; // Mean arrival time of passengers is 5 per minute, that is per passenger in 12 seconds.
		
		// Creation of buses and their stops
		createBusStops(noOfBusStops);
		createBus(noOfBuses, noOfBusStops);
		
		long checkpoint = 0;
		System.out.println("\\\\\\ Bus Service Starts //////");
		while (timer <= simulationTime) {
			Event currentEvent = events.removeFirst();
			timer = currentEvent.getTimeOfEvent();
			currentEvent.generateEvent();
			if (timer >= checkpoint) {
				System.out.println(getStatus()); // Run as getStatus for Buses & getStatusB for bus stops
				checkpoint += 60 * 60;
			}
		}
		System.out.println("\\\\\\\\\\\\ Bus service stops ///////////");
	}
	//Creating buses
	private static void createBus(Integer busCount, Integer stopCount) {
		for (int counter = 1; busCount >= counter; counter++) {
			buses.add(new Bus(busStops, counter, stopCount/busCount));
			buses.getLast().generateEvent();//Generating the first event for it
		}
	}
	// Creating Bus Stops
	private static void createBusStops(Integer count) {
		for (int counter = 1; count >= counter; counter++) {
			busStops.add(new BusStop(counter));
			busStops.getLast().generateEvent(); // Generating first event for it i.e a passenger arrival
		}
	}
	
	
	public static String getStatus() {
		StringBuilder status = new StringBuilder();
		buses.forEach((B) -> {status.append(B.getStatus() + "\t\t");});
		return status.toString();
	}
	
	public static String getStatusB() {
		StringBuilder status = new StringBuilder();
		busStops.forEach((B) -> {status.append(B.getStatusB() + "\t\t");});
		return status.toString();
	}
}