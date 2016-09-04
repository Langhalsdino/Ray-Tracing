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

import java.awt.image.BufferedImage;

/**
 *
 * @author Langhalsdino
 * 
 * Class discribes a plane
 */
public class Plane {
    private double d;
    private Vector3D normal;
    private int[] colorRGB;
    private Vector3D zeroPoint;
    private boolean boolTexture;
    private Texture textur;
    private double scale;
    private double reflectionIndex;
    
    /**
     * Creates a plane withe certain properties
     * 
     * @param normalVector  normal Vector of the plane
     * @param pointInPlane  any point in the plane
     * @param color         color of the plane
     * @param reflection    reflectiones of the plane -> 1.0 = 100%
     */
    public Plane(Vector3D normalVector, Vector3D pointInPlane, int[] color, double reflection) {
        normal = normalVector;
        normal.normalize();
        zeroPoint = pointInPlane;
        d = calcDistance(zeroPoint);
        colorRGB = color;
        reflectionIndex = reflection;
        boolTexture = false;
    }
    
    /**
     * Creates a plane with certain properties and an image as textur
     * 
     * @param normalVector  normal Vector of the plane
     * @param pointInPlane  any point in the plane
     * @param color         color of the plane
     * @param reflection    reflectiones of the plane -> 1.0 = 100%
     * @param rot           rotation of the image
     * @param magnification magnification of the image
     * @param imag          image (texture)
     */
    public Plane(Vector3D normalVector, Vector3D pointInPlane, int[] color, double reflection,  Vector3D rot, double magnification, BufferedImage imag) {
        normal = normalVector;
        normal.normalize();
        zeroPoint = pointInPlane;
        d = calcDistance(zeroPoint);
        colorRGB = color;
        reflectionIndex = reflection;
        boolTexture = true;
        scale = magnification;
        textur = new Texture(scale, rot, imag);    
    }
    
    /**
     * Creates a plane with certain properties and a function as textur
     * 
     * @param normalVector  normal vector of plane
     * @param pointInPlane  any point in plane 
     * @param color         color of plane {R,G,B}
     * @param reflection    reflectivness -> 1.0 = 100%
     * @param rot           rotation of the function, direction of the x-axis
     * @param magnification magnification of the function
     * @param function      function that should become the texture
     */
    public Plane(Vector3D normalVector, Vector3D pointInPlane, int[] color, double reflection, Vector3D rot, double magnification, ColorFunction function) {
        // Set vars
        normal = normalVector;
        normal.normalize();
        zeroPoint = pointInPlane;
        d = calcDistance(zeroPoint);
        colorRGB = color;
        reflectionIndex = reflection;
        boolTexture = true;
        scale = magnification;
        textur = new Texture(scale, rot, function);       
    }
    
    /**
     * Get the distance of a plane with a given surface normal and pointInPlane
     * 
     * @param pointInPlane  vector that discribes point in plane 
     * @return              distance
     */
    private double calcDistance(Vector3D pointInPlane) {
        double distance;
        
        // get the distance of a plane with a given surface normal and pointInPlane
        distance = Vector3D.dot(normal, pointInPlane);
        
        //return distance
        return distance;
    }
    
    /**
     * Get the oint of intersection from a ray and this plane
     * 
     * @param light ray that should intersect the plane
     * @return      return distance to intersection in ray units
     */
    public double intersectionPointOfRay(Ray light) {
        double intersection = calcIntersection(light);
        //return distance of the point ofintersection in ray units
        return intersection;
    }
    
    /**
     * Get the color of the plane
     * 
     * @return  the color of the plane {R,G,B}
     */
    public int[] getColor() {
        return colorRGB;
    }
    
    /**
     * Get color of a point on the sphere
     * 
     * @param point point on the sphere
     * @return      color of the point in {R,G,B}
     */
    public int[] getColor(Vector3D point) {
        int[] color = textur.colorOfPoint(point, this);
        return color;
    }
    
    /**
     * Get reflectivness of the plane -> 1.0 = 100%
     * 
     * @return reflectivness of the plane -> 1.0 = 100%
     */
    public double getReflectionIndex() {
        return reflectionIndex;
    }
    
    /**
     * Get surface normal of the plane
     * 
     * @return  surface normal of the plane
     */
    public Vector3D getSurfaceNormal() {
        return normal;
    }
    
    /**
     * Get zero,zero point of the plane
     * 
     * @return  returns the origin on the plane as vector
     */
    public Vector3D getZeroPoint() {
        return zeroPoint;
    }
    
    /**
     * Get the magnification of the texture
     * 
     * @return magnification of texture
     */
    public double getMagnification() {
        return scale;
    }
    
    /**
     * Get texture of plane
     * 
     * @return texture of plane
     */
    public Texture getTexture() {
        return textur;
    }
    
    /**
     * Has the plane a texture
     * 
     * @return true if plane has a textrure
     */
    public boolean hasTexture() {
        return boolTexture;
    }
    
    /**
     * Get the oint of intersection from a ray and this plane
     * 
     * @param light ray that should intersect the plane
     * @return      return distance to intersection in ray units
     */
    private double calcIntersection(Ray light) {
        double intersection = 0;
        
        double a = Vector3D.dot(light.getDirection(), normal) ;
        double b = Vector3D.dot(light.getOrigin(), normal);
        
        if (a!=0) {
            intersection = (d - b) / a;
        }
        else {
            // no solution, light ray ist parallel to plane
        }
        return intersection;
    }
}
