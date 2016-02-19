/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Alex Parks
 */
public class Item {
    
    public void draw(Graphics graphics){
        graphics.drawImage(image, 
                cellData.getCellSystemCoordinateX(getLocation()), 
                cellData.getCellSystemCoordinateY(getLocation()),
                cellData.getCellWidth(), cellData.getCellHeight(), null);
    }
    
//<editor-fold defaultstate="collapsed" desc="Constructor">
    public Item(int x, int y, String type, CellDataProviderIntf cellData){
        this.x = x;
        this.y = y;
        this.cellData = cellData;
        
        this.type = type;
        if (type.equals(TYPE_FOOD)) {
            image = ResourceTools.loadImageFromResource("snakegame/FOOD.png");
        } else if (type.equals(TYPE_BARRIER)) {
            image = ResourceTools.loadImageFromResource("snakegame/barrier.png");
        } else if (type.equals(TYPE_POWER_UP)) {
            image = ResourceTools.loadImageFromResource("snakegame/power_up.png");
        }
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    public static final String TYPE_FOOD = "FOOD";
    public static final String TYPE_BARRIER = "BARRIER";
    public static final String TYPE_POWER_UP = "POWER_UP";
    
    
    private int x, y;
    private final String type;
    private final CellDataProviderIntf cellData;
    private Image image;
    
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
