/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.game;

import environment.Environment;
import grid.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Alex Parks
 */
public class Grove extends Environment implements LocationValidatorInt, CellDataProviderIntf {
    
    private Grid grid;
    private Snake snake;
    
    
    public Grove(){
        
        this.grid = new Grid(50, 30, 50, 50, new Point(100, 100), Color.DARK_GRAY);
        this.setBackground(Color.BLACK);
        
        snake = new Snake(Direction.RIGHT, Color.WHITE, new Point(grid.getColumns() / 2, grid.getRows() / 2), this);
    }
    

    @Override
    public void initializeEnvironment() {

    }

    @Override
    public void timerTaskHandler() {
        
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        
        
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        graphics.setColor(Color.darkGray);
        graphics.fillRect(0, 0, 100, 1700);
        graphics.fillRect(0, 0, 2700, 100);
        graphics.fillRect(2600, 0, 100, 1700);
        graphics.fillRect(0, 1600, 2700, 100);


        
     
        
        if (grid != null) {
            grid.paintComponent(graphics);
        }
        
        if (snake != null) {
            snake.draw(graphics);
        }
        
    }

    @Override
    public Point validate(Point proposedLocation) {

        
        return proposedLocation;
    }

    //<editor-fold defaultstate="collapsed" desc="CellDataProviderIntf">
    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }
    
    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }
    
    @Override
    public int getCellSystemCoordinateX(Point cellLocation) {
        return grid.getCellSystemCoordinate(cellLocation).x;
    }
    
    @Override
    public int getCellSystemCoordinateY(Point cellLocation) {
        return grid.getCellSystemCoordinate(cellLocation).y;
    }
//</editor-fold>
    
}
