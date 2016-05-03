package RoboRace;

import java.awt.*;
import COSC3P40.graphics.ImageManager;

public class CardTurn extends Card {
	
	private boolean clockwise;

	public CardTurn(int priority, boolean clockwise) {
		super(priority);
		this.clockwise = clockwise;
		if (clockwise)
			image = ImageManager.getInstance().loadImage("Cards/TurnClockwise.png");
		else
			image = ImageManager.getInstance().loadImage("Cards/TurnCounterClockwise.png");
		Graphics g = image.getGraphics();
		g.drawString("Priority: "+ getPriority(),15,15);
		g.dispose();
	}
	
        //Turn the robot 90 degrees cw/ccw
        @Override
	public void execute(EventCounter counter, EventList events, Robot robot, Board board) {
            events.add(new TurnEvent(counter,robot.getLocation(), clockwise));
            robot.turn(clockwise);
	}
	
        @Override
	public String toXMLString() {
		return "<turn priority=\"" + getPriority() + "\" clockwise=\"" + clockwise + "\"/>";
	}
	
}