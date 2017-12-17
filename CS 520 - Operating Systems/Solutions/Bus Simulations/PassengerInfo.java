package busSimulationService;

/**
 * @author Lancelot
 *
 */
public class PassengerInfo {
	private Double Arrival_Time;
	public static Double BoardingTime = 0.0;
	
	public PassengerInfo() {
		Arrival_Time = MainJavaSimulator.timer;
	}
	
	public PassengerInfo(double time_of_arrival) {
		this();
		this.Arrival_Time = time_of_arrival;
	}
}
