package RoboRace;

public class Goal implements Tile {
	
        //A robot is a winner!!
        @Override
	public void effect(EventCounter counter, EventList events, Robot robot, Board board) {
            events.add(new VictoryEvent(counter, robot.getLocation(), robot.getName()));
	}
	
        @Override
	public String toXMLString() {
		return "<goal/>";
	}
	
}