/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

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
         ApplicationStarter.run(new String[0], "Alex's Snake Game", new Dimension(3200, 1800), new Grove());
    }
    
}
