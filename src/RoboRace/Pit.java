package RoboRace;

public class Pit implements Tile {
	
        //Destroy a robot that steps on this pit
        @Override
	public void effect(EventCounter counter, EventList events, Robot robot, Board board) {
            events.add(new DestroyedEvent(counter, robot.getLocation()));
            robot.destroyed();
	}
	
        @Override
	public String toXMLString() {
		return "<pit/>";
	}
	
}