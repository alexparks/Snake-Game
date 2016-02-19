/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Point;

/**
 *
 * @author Alex Parks
 */
public interface CellDataProviderIntf {
    
    public int getCellWidth();
    public int getCellHeight();
    public int getCellSystemCoordinateX(Point cellLocation);
    public int getCellSystemCoordinateY(Point cellLocation);
    
}
