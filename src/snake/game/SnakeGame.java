/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.game;

import environment.ApplicationStarter;
import java.awt.Dimension;

/**
 *
 * @author Alex Parks
 */
public class SnakeGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//         ApplicationStarter.run("Alex's Snake Game", new Grove());
         ApplicationStarter.run(new String[0], "Alex's Snake Game", new Dimension(1800, 3200), new Grove());
    }
    
}
