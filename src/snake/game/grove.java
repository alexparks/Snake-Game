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
public class Grove extends Environment implements LocationValidatorInt {
    
    private Grid grid;
   
    
    
    public Grove(){
        
        this.grid = new Grid(50, 30, 50, 50, new Point(100, 100), Color.YELLOW);
        this.setBackground(Color.BLACK);
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
        
    }

    @Override
    public Point validate(Point proposedLocation) {

        
        return proposedLocation;
    }
    
}
