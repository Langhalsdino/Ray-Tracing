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

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Langhalsdino
 * 
 * The texture class contains a special texture 
 * and can return the color of a point on this texture
 */
public class Texture {
    private BufferedImage image;
    private ColorFunction colorFunction;
    private boolean isFunction;
    private double scale;
    private Vector3D rotation;
    
    /**
     * The texture class contains a texture (image) with a certain rotation and maginfication
     * 
     * @param   magnification       A value that represents the magnification of the pixel -> 1.0=100%
     * @param   rotationDirection   Represents the direction of the X-Axis of the image 
     * @param   picture             A image as texture 
     */
    public Texture(double magnification, Vector3D rotationDirection, BufferedImage picture) {
        //Define private vars
        isFunction = false;
        image = picture;
        scale = magnification;
        rotation = rotationDirection;
    }
    
    /**
     * The texture class contains a texture (function) with a certain rotation and maginfication
     * 
     * @param magnification     A value that represents the magnification of the pixel -> 1.0=100%
     * @param rotationDirection Represents the direction of the X-Axis of the function 
     * @param function          Function used as a texture       
     */
    public Texture(double magnification, Vector3D rotationDirection, ColorFunction function) {
        //Define private vars
        isFunction = true;
        scale = magnification;
        rotation = rotationDirection;
        colorFunction = function;
    }
    
    /**
     * Calculates the color of a point on a sphere
     *  -> Currently not implemented, returns black
     * 
     * @param point     A vector that represents a point on the sphere
     * @param sphere    Sphere that is painted with the texture
     * @return          Color of the point on the sphere
     */
    public int[] colorOfPoint(Vector3D point, Sphere sphere) {
        int[] color = {255,255,255};
        // Currently not implemented return black
        return color;
    }
    
    /**
     * Calculates the color of a point on a plane with the texture
     * 
     * @param point A vector that represets a point on the sphere 
     * @param plane Plane that is painted with the texture
     * @return      Color of the point on the plane
     */
    public int[] colorOfPoint(Vector3D point, Plane plane) {
        int[] color = {255,255,255};
        
        // get coordinates of the point on surface (X,Y)
        double[] mappedPoint = mappPointOnSurface(point, plane);
        int a = (int) (mappedPoint[0]/scale);
        int b = (int) (mappedPoint[1]/scale);
        
        // If the texture is a function calculate the color and return it
        if (isFunction) {
            color = colorFunction.getRGB(a,b);
        }
        // Otherwise look into the image and return the color
        else {
            // get a point on the image by using the modulus (image is looped)
            int x = (int) Math.abs(a)%image.getWidth();
            int y = (int) Math.abs(b)%image.getHeight();
            
            // Some how the modulus did not work :(( sad
            if (x >= image.getWidth() || x < 0) {
                x = 0;
                 System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!----------------error----------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n');
            }
            if (y >= image.getHeight() || y < 0) {
                y = 0;
                 System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!----------------error----------------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n'+ '\n');
            }
           
            // get the color from the image
            Color rgb = new Color(image.getRGB(x, y));
            color[0] = rgb.getRed();
            color[1] = rgb.getGreen();
            color[2] = rgb.getBlue();
        }
        
        // return the color
        return color;
    }
    
    /**
     * Mapp a point on the surface of a plane
     * 
     * @param   point   A vector that defines the point
     * @param   plane   The plane painted with the texture
     * @return  the mapped point as {x,y} coordinates
     */
    private double[] mappPointOnSurface(Vector3D point, Plane plane) {
        Vector3D distanceToZero = Vector3D.subtract(point, plane.getZeroPoint());
        
        Vector3D vectorU;
        Vector3D vectorV;
        Vector3D vectorW;
        
        
        double[] mappedPoint = {0,0};
        double[] result;
        
        // generate new coordinate system with respect to the origin of the texture
        vectorU = Vector3D.scale(-1.0, plane.getSurfaceNormal());
        vectorU.normalize();
        vectorV = Vector3D.cross(vectorU, rotation);
        vectorV.normalize();
        vectorW = Vector3D.cross(vectorU, vectorV);
        vectorW.normalize();
        
        double[][] matrix = {{vectorV.x, vectorW.x, vectorU.x},{vectorV.y, vectorW.y, vectorU.y},{vectorV.z, vectorW.z, vectorU.z}};
        double[] vector = {distanceToZero.x, distanceToZero.y, distanceToZero.z};
        
        // use gause allgorithm to get te x and y coordinates of the point
        // {x,y,z} -> z is irrelevant, since it allways 0 if the point is on the surface
        result = Gauss.getSolution(matrix, vector, false);
        mappedPoint[0] = result[0];
        mappedPoint[1] = result[1];
        
        // return the mapped point {x,y}
        return mappedPoint;
    }
    
    /**
     * Mapp point on the surface of a sphere
     *  -> Currently not implemented
     * 
     * @param point     Vector that defines the point on the surface of the sphere
     * @param sphere    Sphere painted with the texture
     * @return          Return the {x,y} coordinates of the point
     */
    private double[] mappPointOnSurface(Vector3D point, Sphere sphere) {
        double[] mappedPoint = {0.0,0.0};
        
        // Currently not implemented!!!
        return mappedPoint;
    }
}
