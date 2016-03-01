/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Alex Parks
 */
public class Snake {

    private final CellDataProviderIntf cellData;

    public void move() {
        Point newHead = new Point(getHead());

        //adjust the newHead location, depending on direction
        if (direction == Direction.UP) {
            newHead.y--;
        } else if (direction == Direction.DOWN) {
            newHead.y++;
        } else if (direction == Direction.RIGHT) {
            newHead.x++;
        } else if (direction == Direction.LEFT) {
            newHead.x--;
        }

        // put the newHead into the body
        body.add(HEAD_POSITION, newHead);

        if (growthCounter > 0) {
            growthCounter--;
        } else {
            body.remove(body.size() - 1);
        }
    }

    private int growthCounter;

    public void grow(int value) {
        growthCounter += value;
    }

    public void draw(Graphics graphics) {
        graphics.setColor(bodyColor);
        for (Point cellLocation : getBodyCopy()) {
            graphics.fill3DRect(cellData.getCellSystemCoordinateX(cellLocation),
                    cellData.getCellSystemCoordinateY(cellLocation), cellData.getCellWidth(), cellData.getCellHeight(), true);
        }
    }

    public Snake(Direction direction, Color bodyColor, Point startLocation, 
            CellDataProviderIntf cellData) {
        this.direction = direction;
        this.bodyColor = bodyColor;
        this.cellData = cellData;

        body = new ArrayList<>();
        body.add(startLocation);
        for (int i = 1; i < 1; i++) {
            body.add(new Point(startLocation.x - i, startLocation.y));
        }
        alive = true;
    }

    public boolean selfHit(){
        return getTail().contains(getHead());
    }
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Point> body;
    private Direction direction;
    private Color bodyColor;
    private boolean alive;

    public static int HEAD_POSITION = 0;

    public Point getHead() {
        return body.get(HEAD_POSITION);
    }

    private ArrayList<Point> getTail(){
        ArrayList<Point> tail = new ArrayList<>();
        for (int i = 1; i < body.size(); i++) {
            tail.add(body.get(i));
        }
        return tail;
    }
    
    private ArrayList<Point> getBodyCopy() {
        return (ArrayList<Point>) body.clone();
    }

    /**
     * @return the body
     */
    public ArrayList<Point> getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(ArrayList<Point> body) {
        this.body = body;
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return the bodyColor
     */
    public Color getBodyColor() {
        return bodyColor;
    }

    /**
     * @param bodyColor the bodyColor to set
     */
    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

//</editor-fold>

}
