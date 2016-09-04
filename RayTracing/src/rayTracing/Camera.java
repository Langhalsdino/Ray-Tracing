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
 * This class is an camera object, that should be defined by the eye, 
 * viewing specifications and dimensions of a virtual screen.
 */
public class Camera {
    //prepare the class
    private Vector3D origin;
    private Vector3D distance;
    private Vector3D top;
    private Vector3D vectorU;
    private Vector3D vectorV;
    private Vector3D vectorW;
    private double l;
    private double r;
    private double t;
    private double b;
    private int nx;
    private int ny;
    private double height;
    private double width;
    
    /**
     * This class creats an camera Object from the eye, viewing specifications and dimensions of a virtual screen
     * 
     * @param   eye     The vector eye defines the origin of the rays that run through the virtual screen
     * @param   dis     The distance vector defines the distance between the eye and the center of the screen 
     * @param   up      The up vector devides the screen in a left and right half
     * @param   maxX    The max amount of pixels in the X dimension 
     * @param   maxY    The max amount of pixels in the Y dimension
     * @param   xLenght The max lenght in the X dimension of the screen in coordinate units 
     * @param   yLenght The max lenght in the Y dimension of the screen in coordinate units 
     */
    public Camera(Vector3D eye, Vector3D dis, Vector3D up, int maxX, int maxY, double xLenght, double yLenght) {
        // Define private vars
        origin = eye;
        distance = dis;
        top = up;
        nx = maxX;
        ny = maxY;
        height = yLenght;
        width = xLenght;
        
        // Calculate distance from the boorder of the screen to the center
        double screenCenterX = yLenght/2.0;
        double screenCenterY = xLenght/2.0;
        
        //Define the u,v,w coordinates of the camera and the screen
        defineCameraCoordinates();
        defineScreen(screenCenterX, screenCenterY);
    }
    
    /**
     * Calculate a Ray that goes through the eye and a defined pixel
     * 
     * @param pixelX    X Pixel through which the ray goes
     * @param pixelY    Y Pixel through which the ray goes
     * @return          The Ray that goes through the camera and pixel x&y
     */
    public Ray calcRay(int pixelX, int pixelY) {
        Vector3D direction = new Vector3D();
        
        // if pixel is on the screen
        if (pixelX < nx && pixelY < ny) {
            // The d,v,w coordinates represents the direction vector
            double d = distance.length();
            double v;
            double w;
            
            // Calculate the length of the vector in v and w direction
            v = l + (r - l) * ((pixelX  + 0.5)/ (nx * (nx/width)));
            w = b + (t - b) * ((pixelY + 0.5) / (ny * (ny/height)));
            
            // Create real direction vector from d,v,w and the camer coordinate system (Vector U,V,W)
            direction = Vector3D.add(Vector3D.scale(-d, vectorU),
                                     Vector3D.add(Vector3D.scale(v,vectorV), 
                                     Vector3D.scale(w, vectorW)));
        }
        else {
            // Otherwise is the pixel not on the screen
            System.out.println("ERROR, pixel is out of bound!!");
            System.out.println("X,Y: " + pixelX + ", " + pixelY);   
        }
        
        // Create the ray from the origin of the camera and the calculated direction vector
        // Then return it
        Ray ray = new Ray(origin, direction);
        return ray;
    }
    
    /** 
     *  Defines the camera coordinate System (origin at the eye)
     *  containing the vectors U, V and W
     */
    private void defineCameraCoordinates() {
        // Create normalized vectors from previous data
        vectorU = Vector3D.scale(-1.0, distance);
        vectorU.normalize();
        vectorV = distance.cross(top);
        vectorV.normalize();
        vectorW = vectorU.cross(vectorV);
        vectorW.normalize();
    }
    
    /**
     *  Functions defines the virtual Screen, containing the pixels of the image from the camera
     * 
     * @param   centerX The distance from the boorder of the screen to the center in the X-dimension 
     * @param   centerY The distance from the boorder of the screen to the center in the Y-dimension 
     */
    private void defineScreen(double centerX, double centerY) {
        
        // Calculate length (left and right) and height (bottom and top)
        l = - centerX;
        r = nx - (centerX + 1);
        b = - centerY;
        t = ny - (centerY + 1);
    }
    
    /**
     * This functions changes the perspective by shifting the center of thes screen to another pixel
     * 
     * @param   centerX The distance from the left boorder of the screen to the center in the X-dimension
     * @param   centerY The distance from the bottom boorder of the screen to the center in the X-dimension
     */
    public void changePerspective(int centerX, int centerY) {
        // redefines the the perspective by changing the center of the screen
        defineScreen(centerX, centerY);
    }
}
