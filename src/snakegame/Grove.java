/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import environment.Environment;
import grid.Grid;
import java.awt.Color;
import java.awt.Font;
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

//<editor-fold defaultstate="collapsed" desc="Properties">
    private Grid grid;
    private Snake snake;

    private ArrayList<Item> cherryFoodItems;
    private ArrayList<Item> powerUpItems;
    long timeToDisablePowerUps;
    private int score;

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;

        if ((score > 0) && ((score % 100) == 0)) {
            //enable the the powerups for some time...
            setPowerUpVisibilty(true);
            timeToDisablePowerUps = System.currentTimeMillis() + 5000;
        }
    }
//</editor-fold>

    public Grove() {
        this.grid = new Grid(50, 30, 50, 50, new Point(100, 100), Color.BLACK);
        this.setBackground(Color.BLACK);

        snake = new Snake(Direction.RIGHT, Color.WHITE, new Point(grid.getColumns() / 2, grid.getRows() / 2), this);

        cherryFoodItems = new ArrayList<>();
        cherryFoodItems.add(new Item(15, 20, Item.TYPE_FOOD, true, this));

        powerUpItems = new ArrayList<>();
        powerUpItems.add(new Item(10, 16, Item.TYPE_POWER_UP, false, this));
    }

    @Override
    public void initializeEnvironment() {

    }

    private int counter;
    private int counterLimit = 8;

    @Override
    public void timerTaskHandler() {
        // if counter >= counterLimit
        //   - move the snake
        //   - reset the counter to 0
        // else (it is less than counterLImit)
        //   - increase teh counter by one

        if (snake.isAlive()) {

            //check to see if we should disable the powerups
            if (timeToDisablePowerUps < System.currentTimeMillis()) {
                setPowerUpVisibilty(false);
            }

            if (counter >= counterLimit) {
                if (snake != null) {
                    snake.move();
                    if (snake.selfHit()) {
                        snake.setAlive(false);
                    }

                    checkIntersectionsF();
                    checkIntersectionsP();
                }
                counter = 0;
            } else {
                counter++;
            }
        }

    }

    public void checkIntersectionsF() {
        if (cherryFoodItems != null) {
            for (Item item : cherryFoodItems) {
                if (item.getLocation().equals(snake.getHead())) {
                    //Add a body block
                    setScore(getScore() + 10);
                    snake.grow(2);
                    item.setLocation(getRandomGridLocation());
                }
            }
        }
    }

    public Point getRandomGridLocation() {
        return new Point((int) (Math.random() * grid.getColumns()), (int) (Math.random() * grid.getRows()));
    }

    public void checkIntersectionsP() {
        if (powerUpItems != null) {
            for (Item item : powerUpItems) {
                if (item.getLocation().equals(snake.getHead())) {
                    setScore(getScore() + 20);
                    //slow down snake
                    counterLimit++;
                    //disable the powerups
                    setPowerUpVisibilty(false);
                    //move the position of all the powerups
                    for (Item itemA : powerUpItems) {
                        itemA.setLocation(getRandomGridLocation());
                    }
                }
            }
        }
    }

    private void setPowerUpVisibilty(boolean visible) {
        if (powerUpItems != null) {
            for (Item item : powerUpItems) {
                item.setVisible(visible);
            }
        }
    }

//    public boolean checkScore() {
//        return ((getScore() > 0) && ((getScore() % 100) == 0));
////        if (score + 1 % 101 == 0) {
////            return true;               
////          
////        } else {
////            return false;
////        }
//    }
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

        if (cherryFoodItems != null) {
            for (Item item : cherryFoodItems) {
                item.draw(graphics);
            }
        }

        for (Item item : powerUpItems) {
            item.draw(graphics);
        }

        graphics.setFont(new Font("Calibri", Font.BOLD, 50));
        graphics.drawString("Score : " + getScore(), 50, 50);

        if (!snake.isAlive()) {
            graphics.drawString("Game Over - You Suck!", 300, 300);           
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
