package RoboRace;

public class Crusher implements Decoration {
	
	private int phase1;
	private int phase2;
	
	public Crusher(int phase1, int phase2) {
		this.phase1 = phase1;
		this.phase2 = phase2;
	}
	
	public Crusher(int phase1) {
		this(phase1,0);
	}
	
        //see if the crusher activates and destroys the robot
        @Override
	public void effect(EventCounter counter, EventList events, int phase, Robot robot, Board board) {
            if (phase == phase1 || phase == phase2) {
                events.add(new DestroyedEvent(counter, robot.getLocation()));
                robot.destroyed();
            }
	}
	
	public String toXMLString() {
		String result = "<crusher phase1=\"" + phase1 + "\"";
		if (phase2 != 0) result += " phase2=\"" + phase2 +"\""; 
		result += "/>";
		return result;
	}
	
}