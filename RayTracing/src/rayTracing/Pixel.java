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

import javafx.scene.paint.Color;

/**
 *
 * @author Langhalsdino
 * 
 * Class include a point on the screen with a color
 */
public class Pixel {
    private final int xPos;
    private final int yPos;
    private int[] color;
    private boolean hasNull = true;
    
    /**
     * Creates pixel with coordinates and a color
     * 
     * @param   posX        position in X-dimension
     * @param   posY        position in Y-dimension
     * @param   colorRGB    color {R,G,B}
     */
    public Pixel(int posX, int posY, int[] colorRGB){
        xPos = posX;
        yPos = posY;
        color = colorRGB;
        hasNull = false;
    }
    
    /**
     * Get position of the pixel in X-dimension
     * 
     * @return  return position in X-dimension
     */
    public int getPosX(){
        return xPos;
    }
    
    /**
     * Get position of the pixel in Y-dimension
     * 
     * @return  return position in Y-dimension
     */
    public int getPosY(){
        return yPos;
    }
    
    /**
     * Get the color of a pixel
     * 
     * @return  the color of the pixel
     */
    public int[] getRGB(){
        return color;
    }
    
    /**
     * Set the color of this pixel
     * 
     * @param newRGBcolor   color in {R,G,B}
     */
    public void setRGB(int[] newRGBcolor){
        color = newRGBcolor;
        hasNull = false;
    }
    
    /**
     * Get the Color of a pixel
     * 
     * @return Color as javafx.scene.paint.Color;
     */
    public Color getColor(){
        if(color.length == 3){
            return Color.rgb(color[0], color[1], color[2]);
        }
        else{
            return Color.BLACK;
        }
    }
    
    /**
     * Has the color been proccessed (got a color)
     * 
     * @return if proccessed return false
     */
    public boolean isNull(){
        return hasNull;
    }
}
