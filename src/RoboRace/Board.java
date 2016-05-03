package RoboRace;

import COSC3P40.xml.*;
import java.awt.*;
import java.io.*;

public class Board implements XMLObject {

    private Factory factory;
    private int numberRobots;
    private Robot[] robots;

    public static Board read(Reader input) {
        XMLReader<Board> reader = new XMLReader<Board>();
        reader.setXMLSchema("board.xsd");
        reader.setXMLNodeConverter(new BoardReader());
        return reader.readXML(input);
    }

    public Board(Factory factory, int numberRobots, Robot[] robots) {
        this.factory = factory;
        this.numberRobots = numberRobots;
        this.robots = robots;
    }

    public Location getLocation(int x, int y) {
        return factory.getLocation(x, y);
    }

    public Location getLocation(Point p) {
        return factory.getLocation(p);
    }

    public Robot robotAt(int x, int y) {
        for (Robot robot : robots) {
            if (x == robot.getLocation().x && y == robot.getLocation().y) {
                return robot;
            }
        }
        return null;
    }

    public void step(EventCounter counter, EventList events, Robot robot, Direction direction) {
        //determine the point our robot wants to move to (if it's still alive)
        
            Point p = new Point(robot.getLocation());
            switch (direction) {
                case North:
                    p.setLocation(p.getX(), p.getY() - 1);
                    break;
                case East:
                    p.setLocation(p.getX() + 1, p.getY());
                    break;
                case South:
                    p.setLocation(p.getX(), p.getY() + 1);
                    break;
                case West:
                    p.setLocation(p.getX() - 1, p.getY());
                    break;
            }
            if (robot.isAlive()) {
            //if there's a wall in the way, we bump
                System.out.print(robot.getLocation());
            if (factory.hasWall(robot.getLocation(), direction)) {
                events.add(new BumpEvent(counter, robot.getLocation(), direction));
            } //if there's another robot we shove (recursively call step for that robot)
            else if (robotAt((int) p.getX(), (int) p.getY()) != null) {
                step(counter, events, robotAt((int) p.getX(), (int) p.getY()), direction);
                //if the way is still blocked, we bump
                if (robotAt((int) p.getX(), (int) p.getY()) != null) {
                    events.add(new BumpEvent(counter, robot.getLocation(), direction));
                } else {
                    events.add(new MoveEvent(counter, p, direction));
                    robot.move(direction);
                }
            } //if the way is clear and not deadly we can safely move our robot now
            else {
                events.add(new MoveEvent(counter, p, direction));
                robot.move(direction);
            }
            //if the robot moves off the board or hits a pit, destroy it
            if (p.getX() < 0 || p.getY() < 0 || p.getX() >= factory.getXSize()
                    || p.getY() >= factory.getYSize()
                    || getLocation((int) p.getX(), (int) p.getY()).isPit()) {
                    //events.add(new MoveEvent(counter, p, direction));
                //robot.move(direction);
                events.add(new DestroyedEvent(counter, p));
                robot.destroyed();
            }
        }
    }

    public void revitalize() {
        for (Robot robot : robots) {
            if (!robot.isAlive() && robotAt(0, robot.getStart()) == null) {
                robot.revitalize();
            }
        }
    }

    @Override
    public String toXMLString() {
        String result = "<board>\n";
        result += "<robots number=\"" + numberRobots + "\">\n";
        for (Robot robot : robots) {
            result += robot.toXMLString() + "\n";
        }
        result += "</robots>\n";
        result += factory.toXMLString() + "\n";
        return result + "</board>";
    }

    public Dimension getDisplaySize() {
        return factory.getDisplaySize();
    }

    public void start() {
        factory.start();
    }

    public void update(long delta) {
        factory.update(delta);
        for (Robot robot : robots) {
            robot.update(delta);
        }
    }

    public void draw(Graphics graphics) {
        factory.draw(graphics);
        for (Robot robot : robots) {
            graphics.drawImage(robot.getImage(), robot.getScreenX(), robot.getScreenY(), null);
        }
    }

    public void waitOnRobots() {
        for (Robot robot : robots) {
            robot.waitOnRobot();
        }

    }

}
