package busSimulationService;

public abstract class Event {
	private Double timeOfEvent;
	public abstract void generateEvent();
	private Integer position = -1;
	
	protected void setTimeOfEvent(Double timeOfEvent) {
		this.timeOfEvent = timeOfEvent;
	}
	
	public Double getTimeOfEvent() {
		return timeOfEvent;
	}
	
	public void eventPerformed() {
		position = 0;
	}
	
	public void PositionIncrementation() {
		position++;
	}
	
	public Integer getPosition() {
		return position;
	}
	
	// Adding event into the linked list by sorting on its time
	public void addEvent(Event event) {
		MainJavaSimulator.events.forEach((E) -> {if (E.getTimeOfEvent() <= event.getTimeOfEvent()) {
			event.PositionIncrementation();
		}});
		MainJavaSimulator.events.add(event.getPosition(), event);
	}
}
