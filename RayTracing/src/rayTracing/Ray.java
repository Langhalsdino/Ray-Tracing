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
 * This class discribes a ray as a parametric line equation:
 * Ray(t)=origin+t*direction
 */
public class Ray {
    public static final double MAX_T = Float.MAX_VALUE;
    private Vector3D origin;
    private Vector3D direction;
    
    /**
     * Creates ray with an origin and a direction
     * 
     * @param eye   origin of the ray
     * @param dir   direction of the ray
     */
    public Ray(Vector3D eye, Vector3D dir) {
        origin = new Vector3D(eye);
        direction = dir;
    }
    
    /**
     * Returns the origin of the ray
     * 
     * @return origin of the ray
     */
    public Vector3D getOrigin() {
        return origin;
    }
    
    /**
     * Returns the direction of the ray
     * 
     * @return direction of ray
     */
    public Vector3D getDirection() {
        return direction;
    }
}
