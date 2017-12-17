package busSimulationService;

import java.util.LinkedList;

/**
 * @author Lancelot
 *
 */
public class BusStop extends Event {
	// waiting queue of passengers for buses at the stop
	private LinkedList<PassengerInfo> passengers = new LinkedList<PassengerInfo>(); 
	public static Double meanInterArrivalRate = 0.0;
	private Integer busStopNo;
	// List of buses at current stop by their order of arrival
	private LinkedList<Bus> busAtStop = new LinkedList<Bus>();
	
	private BusStop() {	}
	
	public BusStop(Integer busStopNo) {
		this();
		this.busStopNo = busStopNo;
	}

	public String getStatus() {
		return "S#" + busStopNo + "\tP#" + passengers.size();
	}
	
	public StringBuilder getStatusB() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("{S#" + busStopNo + "\tP#" + passengers.size());
		busAtStop.forEach((B)->{sBuilder.append(B.getStatusB());});
		sBuilder.append("}");
		return sBuilder;
	}
	
	public Integer getPassengersCount(){
		return passengers.size();
	}
	// Handling event generating i.e arrival of passengers & putting new event in the queue for future
	public void generateEvent() {
		passengers.add(new PassengerInfo()); // Adding New passenger
		eventPerformed(); 
		// Setting the time for next passenger to arrive
		setTimeOfEvent(MainJavaSimulator.timer + meanInterArrivalRate * RandomNumberGenerator.random());
		// Adding the event into the event list..
		addEvent(this);
	}
	
	public void passengerBoardBus() {
		passengers.removeFirst();
	}
	
	public boolean passengersAvailable() {
		if (passengers.size() > 0) {
			return true;
		}
		return false;
	}
	
	public void busArrived(Bus bus) {
		busAtStop.add(bus);
	}
	
	// Buses will depart as per their arrival order
	public Bus busDeparting(Bus bus) {
		if (busAtStop.peekFirst().equals(bus)){
			busAtStop.remove(bus);
			return null;
		}
		return busAtStop.get(busAtStop.indexOf(bus) - 1);
	}
	
	
	public boolean isThisBusHere(Bus bus) {
		return busAtStop.contains(bus);
	}
}