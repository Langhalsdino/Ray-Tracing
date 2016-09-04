/*
 * This file is part of Langhalsdinos Ray Tracing.
 * Langhalsdinos Ray Tracing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Langhalsdinos Ray Tracing is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Langhalsdinos Ray Tracing. If not, see <http://www.gnu.org/licenses/>.
 */
package rayTracing;

/**
 *
 * @author Langhalsdino
 * 
 * This class contains functions that define the change in color on a surface
 * 
 * The following functions are currently implemented:
 *  - Chess pattern, name: "chess"
 * 
 */
public class ColorFunction {
    private String functionName;
    
    /**
     * Creates a ColorFunction class with a certain function type -> defined by there name
     * @param   name    The name of the function 
     */
    public ColorFunction(String name) {
        functionName = name;
    }
    
    /**
     * Returns the RGB value of a point in the function
     * 
     * @param   x   x coordinate of the point
     * @param   y   y coordinate of the point
     * @return      returns a array of integers as the RGB color of the point
     */
    public int[] getRGB(int x, int y) {
        // if no function type exists, is the point black
        int[] color = {255,255,255};
        
        // If the function type is chess the function will get the color from a chess pattern
        if (functionName == "chess") {
            // get modulus of coordinates with respect to the modulus (2)
            x = Math.abs(x+100)%2;
            y = Math.abs(y+100)%2;
            x = x * x;
            y = y * y;
            
            // White tile if x == y
            if (x == y) {
                color[0] = 0;
                color[1] = 0;
                color[2] = 0;
            }
            
            // Otherwise return black, since it is a black tile
            else {
                color[0] = 255;
                color[1] = 255;
                color[2] = 255;
            }
        }
        // add further functions here
        
        
        // return the calculated color
        return color;
    }
}
