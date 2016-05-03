package RoboRace;

import java.awt.*;
import COSC3P40.graphics.ImageManager;

public class CardMove extends Card {

	private int steps;

	public CardMove(int priority, int steps) {
	    super(priority);
		this.steps = steps;
		image = ImageManager.getInstance().loadImage("Cards/Move.png");
		Graphics g = image.getGraphics();
		g.drawString("Priority: "+ getPriority(),15,15);
		g.drawString("Move: " +steps,25,120);
		g.dispose();
	}
	
        //for every step, call the move step 
        @Override
	public void execute(EventCounter counter, EventList events, Robot robot, Board board) {
            for (int i = 0; i < steps; i++) {
                counter.increaseStep();
                board.step(counter,events,robot,robot.getDirection());
            }
            counter.increase();
	}
	
        @Override
	public String toXMLString() {
		return "<move priority=\"" + getPriority() + "\" steps=\"" + steps + "\"/>";
	}
	
}