/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import environment.Environment;
import grid.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Alex Parks
 */
public class Grove extends Environment implements LocationValidatorInt, CellDataProviderIntf {

    private Grid grid;
    private Snake snake;
    
    private ArrayList<Item> items;

    public Grove() {
        this.grid = new Grid(50, 30, 50, 50, new Point(100, 100), Color.DARK_GRAY);
        this.setBackground(Color.BLACK);

        snake = new Snake(Direction.RIGHT, Color.WHITE, new Point(grid.getColumns() / 2, grid.getRows() / 2), this);

        items = new ArrayList<>();
        items.add(new Item(22, 3, Item.TYPE_FOOD, this));
        items.add(new Item(2, 33, Item.TYPE_FOOD, this));
        items.add(new Item(5, 23, Item.TYPE_FOOD, this));
    }

    @Override
    public void initializeEnvironment() {

    }

    private int counter;
    private int counterLimit = 5;

    @Override
    public void timerTaskHandler() {
        // if counter >= counterLimit
        //   - move the snake
        //   - reset the counter to 0
        // else (it is less than counterLImit)
        //   - increase teh counter by one

        if (counter >= counterLimit) {
            if (snake != null) {
                snake.move();
                checkIntersections();
            }
            counter = 0;
        } else {
            counter ++;
        }
    }

    public void checkIntersections(){
         if (items != null) {
            for (Item item : items){
                if (item.getLocation().equals(snake.getHead())) {
                    //Add a body block
                    snake.grow(2);
                }
            }
        }
    }
    
    
    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            snake.setDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            snake.setDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            snake.setDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            snake.setDirection(Direction.RIGHT);
        }
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
        
        if (items != null) {
            for (Item item : items){
                item.draw(graphics);
            }
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
