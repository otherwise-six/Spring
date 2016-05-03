package RoboRace;

import COSC3P40.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Player extends JFrame {
	private String name;
	private Board board;
	private BoardCanvas boardCanvas;
	private CardPane cardPane;
	
        public Player(String n) {
            name = n;
        }
        
        public void recieveBoard(Board b) {
            board = b;
            boardCanvas = new BoardCanvas(board);
            cardPane = new CardPane();
            getContentPane().add("North",boardCanvas);
            getContentPane().add("South",cardPane);
            pack();
            setResizable(false);
            setVisible(true);
            boardCanvas.start();  
        }
        
        public void recieveEvents(EventList e) {
            //some stuff
        }
        
        public CardList selectCards(CardList c) {
          return cardPane.selectCards(c);
        }
        
        //Close the board
        public void close() {
            boardCanvas.stop();
        }
        
	/* some code that will be needed for display
		
		boardCanvas = new BoardCanvas(board);
		cardPane = new CardPane();
		getContentPane().add("North",boardCanvas);
		getContentPane().add("South",cardPane);
		pack();
		setResizable(false);
		setVisible(true);
		boardCanvas.start();
        */
}