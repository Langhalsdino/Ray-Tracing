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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Langhalsdino
 * 
 * This class discribes a 3D scene with spheres and planes in it
 */
public class Scene3D {
    private List<Sphere> Spheres = new ArrayList<>();
    private List<Plane> Planes = new ArrayList<>();
    private List<Texture> Textures = new ArrayList<>();
    private int[] backgroundColor = {255,255,255};
    
    /**
     * Add a sphere to the scene (list of objects)
     * 
     * @param c                 center of the sphere
     * @param r                 radius of the sphere
     * @param color             color of the sphere {R,G,B}
     * @param reflectionIndex   reflectivness of the sphere -> 1.0 -> 100%
     */
    public void addObject(Vector3D c, double r, int[] color, double reflectionIndex) {
        // add a new sphere to the list spheres
        if (reflectionIndex > 1.0) {
            reflectionIndex = 1.0;
        }
        Sphere sphere = new Sphere(c, r, color, reflectionIndex);
        Spheres.add(sphere);
    }
    
    /**
     * Add a plane to the scene (list of objects)
     * 
     * @param normal            surface normal of the plane
     * @param distance          distance of a point in the plane to the origin
     * @param color             color of the plane {R,G,B}
     * @param reflectionIndex   reflectivness of the plane -> 1.0 = 100%
     */
    public void addObject(Vector3D normal, Vector3D distance, int[] color, double reflectionIndex) {
        // add a new plane to the list planes
        if (reflectionIndex > 1.0) {
            reflectionIndex = 1.0;
        }
        Plane plane = new Plane(normal, distance, color, reflectionIndex);
        Planes.add(plane);
    }
    
    /**
     * Add a plane to the scene (list of objects) with texture (image)
     * 
     * @param normal            surface normal of the plane
     * @param distance          distance of a point in the plane to the origin
     * @param color             color of the plane {R,G,B}
     * @param reflectionIndex   reflectivness of the plane -> 1.0 = 100%
     * @param rot               rotation of the x-axis from the texture
     * @param magnification     magnification of the texture
     * @param image             image as texture
     */
    public void addObject(Vector3D normal, Vector3D distance, int[] color, double reflectionIndex, Vector3D rot, double magnification, BufferedImage image) {
        // add a new plane to the list planes
        if (reflectionIndex > 1.0) {
            reflectionIndex = 1.0;
        }
        Plane plane = new Plane(normal, distance, color, reflectionIndex, rot, magnification, image);
        Planes.add(plane);
    }
    
    /**
     * Add a plane to the scene (list of objects) with texture (image)
     * 
     * @param normal            surface normal of the plane
     * @param distance          distance of a point in the plane to the origin
     * @param color             color of the plane {R,G,B}
     * @param reflectionIndex   reflectivness of the plane -> 1.0 = 100%
     * @param rot               rotation of the x-axis from the texture
     * @param magnification     magnification of the texture
     * @param function          function as texture
     */
    public void addObject(Vector3D normal, Vector3D distance, int[] color, double reflectionIndex, Vector3D rot, double magnification, ColorFunction function) {
        // add a new plane to the list planes
        if (reflectionIndex > 1.0) {
            reflectionIndex = 1.0;
        }
        Plane plane = new Plane(normal, distance, color, reflectionIndex, rot, magnification, function);
        Planes.add(plane);
    }
    
    /**
     * Set background color of the scene 
     * 
     * @param newBackgroundColor new background color {R,G,b}
     */
    public void setBackgroundColor(int[] newBackgroundColor) {
        backgroundColor = newBackgroundColor;
    }
    
    /**
     * Get list of spheres in scene 
     * 
     * @return list of spheres
     */
    public List getSpheres() {
        // returns the list of spheres
        return Spheres;
    }
    
    /**
     * Get list of planes in scene
     * 
     * @return list of planes
     */
    public List getPlanes() {
        // returns the list of planes
        return Planes;
    }
    
    /**
     * Get list of textures
     * 
     * @return list of textures
     */
    public List getTextures() {
        return Textures;
    }
    
    /**
     * Get background color of this scene
     * 
     * @return backgound color {R,G,B}
     */
    public int[] getBackgroundColor() {
        return backgroundColor;
    }
}
