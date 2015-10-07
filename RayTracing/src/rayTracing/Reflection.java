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
 * Class does reflection rays
 */
public class Reflection {
    private Vector3D n;
    private Ray ray;
    private Vector3D point;
    private Ray reflectedRay;
    private Vector3D reflectionVector;
    
    /**
     * Calculates a refelctiv ray from normal, original ray and point of intersection
     * 
     * @param normal                surface normal of the object that reflects the ray
     * @param originalRay           ray that should be reflected
     * @param pointOfIntersection   point at whicht the ray should be reflected
     */
    public Reflection(Vector3D normal, Ray originalRay, Vector3D pointOfIntersection){
        n = normal;
        ray = originalRay;
        point = pointOfIntersection;
        calcReflectionVector();
    }
    
    /**
     * Actually calculates the reflected ray
     */
    private void calcReflectionVector(){
        // calculate the new reflectionVector
        
        double factor = 2 * Vector3D.dot(ray.getDirection(), n);
        Vector3D invertedReflectionVector = Vector3D.add(ray.getDirection(), Vector3D.scale(factor, n));
        reflectionVector = Vector3D.scale(-1.0, invertedReflectionVector);
        reflectionVector.normalize();
        reflectedRay = new Ray(point, reflectionVector);
    }
    
    /**
     * Get the reflected Ray
     * 
     * @return reflected Ray
     */
    public Ray getRefelctedRay(){
        return reflectedRay;
    }
}
