package RoboRace;

public class Gear implements Tile {
	
	private boolean clockwise;
	
	public Gear(boolean clockwise) {
		this.clockwise = clockwise;
	}
	
	public boolean isClockwise() {
		return clockwise;
	}
	
        //Turn a robot in a specific direction
        @Override
	public void effect(EventCounter counter, EventList events, Robot robot, Board board) {
            events.add(new TurnEvent(counter,robot.getLocation(), clockwise));
            robot.turn(clockwise);
	}
	
        @Override
	public String toXMLString() {
		return "<gear clockwise=\"" + clockwise + "\"/>";
	}
}