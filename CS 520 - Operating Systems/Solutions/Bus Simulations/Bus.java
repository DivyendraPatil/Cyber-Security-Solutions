package busSimulationService;

import java.util.LinkedList;

/**
 * @author Lancelot
 *
 */
public class Bus extends Event{
	// Order of the bus stop the bus will arrive at.
	private LinkedList<BusStop> busStops = new LinkedList<BusStop>();
	public static Double timeToTravelToNextStop = 0.0;
	private Integer busNo;

	private Bus() {}
	
	public Integer getBusNo() {
		return busNo;
	}
	
	public Bus(LinkedList<BusStop> busStops, Integer busNo, Integer interval) {
		this();
		this.busNo = busNo;
		this.busStops.addAll(busStops);
		// Initializing buses at bus stops at equal distances from each other.
		for (int count = 0; count < (busNo - 1) * interval; count++) {
			this.busStops.addLast(this.busStops.removeFirst());
		}
	}
	
	public String getStatus() {
		return "{B#" + busNo + "\t" + busStops.getFirst().getStatus() + "}";
	}
	
	public String getStatusB() {
		return "\tB#" + busNo;
	}
	/*
	 * Handling event for bus where in (if there are passengers on stop bus will wait for
	 * another boarding time of passengers) else bus will move to next stop. 
	 * 
	 * */
	public void generateEvent() {
		BusStop currentStop = busStops.getFirst(); //checking current stop where the bus is..
		
		
		// Was the bus already at this stop
		if (!currentStop.isThisBusHere(this)) {
			currentStop.busArrived(this);
		}
		
		// Are the passangers on stop
		if (currentStop.passengersAvailable()) {
			currentStop.passengerBoardBus();
			setTimeOfEvent(MainJavaSimulator.timer + PassengerInfo.BoardingTime);
		} else {
			
			// No passengers on stop, thus departing for the next stop
			Bus busAhead = currentStop.busDeparting(this);
			if (busAhead == null) {
				setTimeOfEvent(MainJavaSimulator.timer + timeToTravelToNextStop);
				busStops.removeFirst();
				busStops.addLast(currentStop);
			} else {
				setTimeOfEvent(busAhead.getTimeOfEvent());
			}
		}
		eventPerformed();
		addEvent(this);
	}
}
