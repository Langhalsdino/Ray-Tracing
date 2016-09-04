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
 * This class describes a sphere
 */
public class Sphere {
    private Vector3D center = new Vector3D();
    private double radius;
    private int[] colorRGB = new int[3];
    private double reflection = 0;
    
    /**
     * Creates a sphere with a center, radius, color and reflectionIndex
     * 
     * @param c             center of the sphere
     * @param r             radius of the sphere    
     * @param color         color of the sphere {R,G,B}
     * @param reflectiones  reflectiones of the sphere -> 1.0 = 100% reflection
     */
    public Sphere(Vector3D c, double r, int[] color, double reflectiones) {
        // Set vars
        center = c;
        radius = r;
        colorRGB = color;
        reflection = reflectiones;
    }
    
    /**
     * Get the color of the spheres
     * 
     * @return the color of the sphere {R,G,B}
     */
    public int[] getColor() {
        return colorRGB;
    }
    
    /**
     * Get the reflection index of the sphere
     * 
     * @return the reflectiones of the sphere -> 1.0 = 100%
     */
    public double getReflectionIndex() {
        return reflection;
    }
    
    /**
     * Get the surface normal of the sphere 
     * 
     * @param pointOnSurface    Point on the surface at which you need the surface normal
     * @return                  Surface normal
     */
    public Vector3D getSurfaceNormal(Vector3D pointOnSurface) {
        Vector3D normal = Vector3D.subtract(pointOnSurface, center);
        return normal;
    }
    
    /**
     * Get the intersection point of a ray and this sphere
     * 
     * @param light ray to calculate intersection
     * @return      return distance to intersection in ray units
     */
    public double intersectionPointOfRay(Ray light) {
        double intersection = calcIntersection(light);

        // return distance to intersection
        return intersection;
    }
    
    /**
     * Get the intersection point of a ray and this sphere
     * 
     * @param light ray to calculate intersection
     * @return      return distance to intersection in ray units
     */
    private double calcIntersection(Ray light) {
        double intersectionT = 0.0;
        
        // Calculate a,b,c from a*t^2+b*t+c
        double a = Vector3D.dot(light.getDirection(), light.getDirection());
        double b = Vector3D.dot(Vector3D.scale(2, light.getDirection()), Vector3D.subtract(light.getOrigin(), center));
        double c = Vector3D.dot(center, center) + Vector3D.dot(light.getOrigin(), light.getOrigin()) + (- 2.0 * Vector3D.dot(center, light.getOrigin())) - radius * radius;    
        
        // The discriminant describes the amount of solutions
        double  discriminant = b*b - 4.0*a*c;
        
        if (discriminant > 0.0 ) {
            // Two solutions, find smallest solution that is > 1
            double firstT = 0.0;
            double secondT = 0.0;
            
            // calc first solution
            firstT = (-b + Math.sqrt(discriminant)) / (2.0*a);
           
            // calc second solution
            secondT = (-b - Math.sqrt(discriminant)) / (2.0*a);
           
            if (firstT < secondT) {
                intersectionT = firstT;
            }
            
            else {
                intersectionT = secondT;
            }
        }
        
        if (discriminant == 0.0) {
            // Tangent one point of intersection
            intersectionT = -b / 2.0 * a;
        }
        
        if (discriminant < 0.0) {
            // no solution!! :)
        }
        return intersectionT;
    }
}
