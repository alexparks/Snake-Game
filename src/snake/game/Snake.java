/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.game;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Alex Parks
 */
public class Snake {
    
    
    public Snake(Direction direction, Color bodyColor, Point startLocation){
        this.direction = direction;
        this.bodyColor = bodyColor;
        
        body.add(startLocation);
    }
    
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private ArrayList<Point> body;
    private Direction direction;
    private Color bodyColor;
    
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
//</editor-fold>
    
}
