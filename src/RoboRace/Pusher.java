package RoboRace;

public class Pusher implements Decoration {
	
	private Direction direction;
	private int phase1;
	private int phase2;
	private int phase3;
	
	public Pusher(Direction direction, int phase1, int phase2, int phase3) {
		this.direction = direction;
		this.phase1 = phase1;
		this.phase2 = phase2;
		this.phase3 = phase3;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public Pusher(Direction direction, int phase1, int phase2) {
		this(direction,phase1,phase2,0);
	}
	
        @Override
	public void effect(EventCounter counter, EventList events, int phase, Robot robot, Board board) {
	}
	
        @Override
	public String toXMLString() {
		String result = "<pusher direction=\"" + direction + "\" phase1=\"" + phase1 + "\" phase2=\"" + phase2 + "\"";
		if (phase3 != 0) result += " phase3=\"" + phase3 +"\""; 
		result += "/>";
		return result;
	}
}