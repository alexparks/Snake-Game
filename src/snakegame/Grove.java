/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import audio.AudioPlayer;
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
    private ArrayList<Item> barriers;
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

        barriers = new ArrayList<>();
        barriers.add(new Item(5, 5, Item.TYPE_BARRIER, true, this));

        for (int i = 0; i < 30; i++) {
            barriers.add(new Item(getRandomGridLocation(), Item.TYPE_BARRIER, true, this));
        }
//        for (int row = 0; row < 5; row++) {
//            for (int column = 0; column < grid.getColumns(); column++) {
//                barriers.add(new Item(column, row, Item.TYPE_BARRIER, true, this));
//            }
//        }

        if (snake.isAlive()) {
            AudioPlayer.play("/snakegame/snake_music.wav", -1);
        }

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
                    checkIntersectionsB();
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
                    snake.grow(4);
                    item.setLocation(getRandomGridLocation());
                    playRandomRewardSound();

                }
            }
        }
    }

    public Point getRandomGridLocation() {
        // keep generating new locations until one does not match a barrier location
        boolean badLocation = true;
        Point location;

        do {
            location = new Point((int) (Math.random() * grid.getColumns()), (int) (Math.random() * grid.getRows()));
            badLocation = false;
            for (Item barrier : barriers){
                if (barrier.equals(location)) {
                    badLocation = true;
                    break;
                }
            }
            
//            badLocation = badLocation || snake.getHead().equals(location);
        } while (badLocation);

        return location;
    }

    public void checkIntersectionsP() {
        if (powerUpItems != null) {
            for (Item item : powerUpItems) {
                if ((item.getLocation().equals(snake.getHead())) && (item.isVisible())) {
                    setScore(getScore() + 20);
                    //slow down snake
                    counterLimit++;
                    //disable the powerups
                    setPowerUpVisibilty(false);
//                    random sounds
                    AudioPlayer.play("/snakegame/potion_sound1.wav");
                    //move the position of all the powerups
                    for (Item itemA : powerUpItems) {
                        itemA.setLocation(getRandomGridLocation());
                    }
                }
            }
        }
    }

    public void checkIntersectionsB() {
        if ((barriers != null) && (snake != null)) {
            for (Item barrier : barriers) {
                if (barrier.getLocation().equals(snake.getHead())) {
                    snake.setAlive(false);
                    AudioPlayer.play("/snakegame/deathSound.wav");
                }
            }
        }

        //check if the snake's head is outsid the boundary of the grid
        if (snake != null) {
            if ((snake.getHead().x <= -1)
                    || (snake.getHead().x >= grid.getColumns())
                    || (snake.getHead().y <= -1)
                    || (snake.getHead().y >= grid.getRows())) {
                snake.setAlive(false);
                AudioPlayer.play("/snakegame/deathSound.wav");
            }
        }
    }

    private void setPowerUpVisibilty(boolean visible) {
        if (powerUpItems != null) {
            // does not delete the item of the grid : visible method
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
            if (snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            if (snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            }

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (snake.getDirection() != Direction.UP) {
                snake.setDirection(Direction.DOWN);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            if (snake.getDirection() != Direction.UP) {
                snake.setDirection(Direction.DOWN);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.LEFT);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            if (snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.LEFT);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            if (snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_C) {
            for (Item food : cherryFoodItems){
                food.setLocation(getRandomGridLocation());
            }
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e
    ) {

    }

    @Override
    public void environmentMouseClicked(MouseEvent e
    ) {

    }

    @Override
    public void paintEnvironment(Graphics graphics
    ) {
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

        if (barriers != null) {
            for (Item item : barriers) {
                item.draw(graphics);
            }
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
    public int getCellSystemCoordinateX(Point cellLocation
    ) {
        return grid.getCellSystemCoordinate(cellLocation).x;
    }

    @Override
    public int getCellSystemCoordinateY(Point cellLocation
    ) {
        return grid.getCellSystemCoordinate(cellLocation).y;
    }
//</editor-fold>

    private void playRandomRewardSound() {
        // generate random number
        // if num < .
        double number = Math.random();
        if (number <= .01) {
            AudioPlayer.play("/snakegame/burp_rare.wav");
        } else if (number <= .33) {
            AudioPlayer.play("/snakegame/burp1.wav");
        } else if (number <= .5) {
            AudioPlayer.play("/snakegame/burp4.wav");
        } else if (number <= .7) {
            AudioPlayer.play("/snakegame/burp3.wav");
        } else {
            AudioPlayer.play("/snakegame/burp2.wav");
        }
    }
//                    if (cherrySoundInt() * 10 == ) {
//doubl
//                    }
//    public double cherrySoundInt() {
//        return Math.random();
//    }

}
