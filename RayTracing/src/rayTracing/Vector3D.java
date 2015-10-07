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
 * @author Langhalsdino and Leonard McMillan
 * I (Langhalsdino)s found some instparation in Leonard McMillans work
 * 
 * This class discribes vector in the 3D and can calculate with this vectors
 */
public class Vector3D {
    double x, y, z;

    /**
     * Creates an emty Vector with 3 dimensions
     */
    public Vector3D( ) {
    }

    /**
     * Creates a Vector with 3 dimensions
     * 
     * @param x vector length in X-dimension
     * @param y vector length in Y-dimension
     * @param z vector length in Y-dimension
     */
    public Vector3D(double x, double y, double z) {
        this.x = x; this.y = y; this.z = z;
    }

    /**
     * Creates a Vector from a vector
     * 
     * @param v vector with x,y,z length
     */
    public Vector3D(Vector3D v) {
        x = v.x;
        y = v.y;
        z = v.z;
    }

    //Start of the methodes
    
    /**
     * Calculates the dot product vorm current vector and another vector
     * 
     * @param B Other vector to calculate the dot product
     * @return the dot product
     */
    public final double dot(Vector3D B) {
        return (x*B.x + y*B.y + z*B.z);
    }

    /**
     * Calculates the dot product vorm current vector and another vector
     * 
     * @param Bx    vector length in X-dimension of the other vector 
     * @param By    vector length in Y-dimension of the other vector
     * @param Bz    vector length in Z-dimension of the other vector
     * @return      return the dot product
     */
    public final double dot(double Bx, double By, double Bz) {
        return (x*Bx + y*By + z*Bz);
    }

    /**
     * Calculates the dot product vorm two vectors
     * 
     * @param A vector a
     * @param B vector b
     * @return  the dot product of vector a and b
     */
    public static final double dot(Vector3D A, Vector3D B) {
        return (A.x*B.x + A.y*B.y + A.z*B.z);
    }

    /**
     * Calculate the cross product of current vector and another vector
     * 
     * @param B other vector
     * @return  cross product 
     */
    public final Vector3D cross(Vector3D B) {
        return new Vector3D(y*B.z - z*B.y, z*B.x - x*B.z, x*B.y - y*B.x);
    }

    /**
     * Calculate the cross product of current vector and another vector
     * 
     * @param Bx    vector length in X-dimension of the other vector 
     * @param By    vector length in Y-dimension of the other vector
     * @param Bz    vector length in Z-dimension of the other vector
     * @return      cross product of the two vectors
     */
    public final Vector3D cross(double Bx, double By, double Bz) {
        return new Vector3D(y*Bz - z*By, z*Bx - x*Bz, x*By - y*Bx);
    }

    /**
     * Calculate the cross product of two vector
     * 
     * @param A vector A
     * @param B vector B 
     * @return  cross product of the two vectors
     */
    public final static Vector3D cross(Vector3D A, Vector3D B) {
        return new Vector3D(A.y*B.z - A.z*B.y, A.z*B.x - A.x*B.z, A.x*B.y - A.y*B.x);
    }
    
    /**
     * Add the current vector with another vector
     * 
     * @param B other vector
     * @return  return the sum
     */
    public final Vector3D add(Vector3D B) {
        return new Vector3D(x+B.x, y+B.y,z+B.z);
    }

    /**
     * Add the current vector with another vector
     * 
     * @param Bx    vector length in X-dimension of the other vector 
     * @param By    vector length in Y-dimension of the other vector
     * @param Bz    vector length in Z-dimension of the other vector
     * @return      return the sum
     */
    public final Vector3D add(double Bx, double By, double Bz) {
        return new Vector3D(x+Bx, y+By, z+Bz);
    }

    /**
     * Add two vectors
     * 
     * @param A vector a
     * @param B vector be
     * @return  return the sum
     */
    public final static Vector3D add(Vector3D A, Vector3D B) {
        return new Vector3D(A.x+B.x, A.y+B.y, A.z+B.z);
    }
    
    /**
     * Subrtract another Vector from the current vector
     * 
     * @param B second vectors
     * @return  Diffrence of the vectors
     */
    public final Vector3D subtract(Vector3D B) {
        return new Vector3D(x-B.x, y-B.y,z-B.z);
    }

    /**
     * Subrtract another Vector from the current vector
     * 
     * @param Bx    vector length in X-dimension of the other vector 
     * @param By    vector length in Y-dimension of the other vector
     * @param Bz    vector length in Z-dimension of the other vector
     * @return      return diffrence
     */
    public final Vector3D subtract(double Bx, double By, double Bz) {
        return new Vector3D(x-Bx, y-By, z-Bz);
    }

    /**
     * Subrtract two vectors
     * 
     * @param A vector a
     * @param B vector b
     * @return  return diffrece
     */
    public final static Vector3D subtract(Vector3D A, Vector3D B) {
        return new Vector3D(A.x-B.x, A.y-B.y, A.z-B.z);
    }
    
    /**
     * Scale this vactor by some value
     * 
     * @param B scale by value
     * @return  scaled vector
     */
    public final Vector3D scale(double B) {
        return new Vector3D(x*B, y*B,z*B);
    }

    /**
     * Scale a vactor by some value
     * 
     * @param A scale by value
     * @param B vector to scale
     * @return 
     */
    public final static Vector3D scale(double A, Vector3D B) {
        return new Vector3D(A*B.x, A*B.y, A*B.z);
    }
    
    /**
     * Get the length of this vector
     * 
     * @return  length of vector
     */
    public final double length( ) {
        return (double) Math.sqrt(x*x + y*y + z*z);
    }

    /**
     * Get the length of a vector
     * 
     * @param A vector
     * @return  length of vector
     */
    public final static double length(Vector3D A) {
        return (double) Math.sqrt(A.x*A.x + A.y*A.y + A.z*A.z);
    }

    /**
     * Normalize this vector
     */
    public final void normalize( ) {
        double t = x*x + y*y + z*z;
        if (t != 0 && t != 1) t = (double) (1 / Math.sqrt(t));
        x *= t;
        y *= t;
        z *= t;
    }

    /**
     * Normalize a vector
     * 
     * @param A vector
     * @return  nomalized vector
     */
    public final static Vector3D normalize(Vector3D A) {
        double t = A.x*A.x + A.y*A.y + A.z*A.z;
        if (t != 0 && t != 1) t = (double)(1 / Math.sqrt(t));
        return new Vector3D(A.x*t, A.y*t, A.z*t);
    }
    
    /**
     * Convert this vector to string
     * 
     * @return String
     */
    public String toString() {
        return "["+x+", "+y+", "+z+"]";
    }
    
    /**
     * Print vector to command line (System.out.println(Vector))
     */
    public void printVector3D(){
        System.out.println(("["+x+", "+y+", "+z+"]"));
    }
}
