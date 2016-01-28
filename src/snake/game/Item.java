/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.game;

import java.awt.Point;

/**
 *
 * @author Alex Parks
 */
public class Item {
    
 
    public Item(int x, int y, String type, CellDataProviderIntf cellData){
        this.x = x;
        this.y = y;
        this.type = type;
        this.cellData = cellData;
    }
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private int x, y;
    private String type;
    private CellDataProviderIntf cellData;
    
    /**
     * @return the x
     */
    public Point getLocation() {
        return new Point(x, y);
    }
    
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }
    
    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
//</editor-fold>
    
}
