package RoboRace;

import java.util.*;
import java.io.*;

public class GameMaster {
    private Board board;
    private EventList events;
    private EventCounter counter;
    private CardFactory factory;
    private Factory f;
    private Player[] players;
    private Robot[] robots;
    private CardList newHand; //a randomly selected hand of 7 cards
    private CardList selected; //a player selected hand of 5 cards
    private Card[][] programs; //will hold player programs and associate cards with players
  
    private int numPlayers; //number of players

    public GameMaster(String[] names, int nHumans) {
        numPlayers = nHumans;
        f = Factory.load("factory.xml");
        factory = new CardFactory();
        events = new EventList();
        players = new Player[numPlayers];
        robots = new Robot[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            robots[i] = new Robot(names[i]+"-bot",1+i*2);  
        }
        board = new Board(f, numPlayers, robots);
        counter = new EventCounter();
        //events.execute(board);
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(names[i]);
            players[i].recieveBoard(board);
        }
        newHand = new CardList();
        selected = new CardList();
        programs = new Card[numPlayers][5];
    }
  
    public void run() {
        while(true) {
            //1. Bring back any dead robots
            board.revitalize();

            //2. Generate a new hand for each player and get their selections
            for (int i = 0; i < numPlayers; i++) {
                for (int j = 0; j < 7; j++) {
                    newHand.add(j, factory.createCard());
                }
                selected = players[i].selectCards(newHand);
                for (int k = 0; k < 5; k++) {
                    programs[i][k] = selected.get(k);
                }
                newHand.clear();
                selected.clear();
            }

            //3. Execute the programs
            for (int i = 0; i < numPlayers; i++) {
                for (int j = 0; j < 5; j++) {

                    //if (programs[i][j-1].compareTo(programs[i][j]) > 0)
                    //PRIORITY STUFF  
                    //1. One card of each player is executed 
                    programs[i][j].execute(counter, events, robots[i], board);
                    //2. Activate factory elements at the position of each robot.
                    board.getLocation(robots[i].getLocation()).effect(counter, events, j, robots[i], board);
                } 
            }
            events.clear();
            counter.reset();
        }
    }
}
		