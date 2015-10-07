/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rayTracing;

/**
 *
 * @author Langhalsdino
 */

public class GetColor {
    private int[] backColor;
    private double intersection;
    private double newIntersection;
    private int objects;
    private int planes;
    private Ray ray;
    private Scene3D scene;
    private boolean fullReflection;
    
    /**  
    *   This Class contains all the Object and functions
    *   a Pixel needs to calculated its Color 
    *
    *   @param  x       X coordinate of the pixel
    *   @param  y       Y coordinate of the pixel
    *   @param  camera  Set the camera that has the pixel
    *   @param  scene3D Set the scene in order to get the objects in the scene
    */
    public GetColor(int x, int y, Camera camera, Scene3D scene3D){
        backColor = scene3D.getBackgroundColor();
        scene = scene3D;
        ray = camera.calcRay(x, y);
        
        // Calculate amount of Objects and planes in the scene
        objects = scene.getPlanes().size() + scene.getSpheres().size();
        planes = scene.getPlanes().size();
    }
    
    /**
    *   This function calculates the color of the Pixel defined in the class
    *   
    *   @param depth    Depth defines the durations of regression
    *   @param limit    Limit defines the minimal distance that each object has to be in order to see it. Integer as amounts of Ray vectors.
    *   @return         Return the pixels color as an in array {R,G,B}
    */
    public int[] getColor(int depth, int limit){
        // Some preperations 
        intersection = 0;
        newIntersection = -1;
        int[] color = backColor;
        
        // Look for the first object hit, by calculate the distance to each Object
        for(int i = 0; i < objects; i++){
            // Look first for the planes
            if(i < planes){
                // Get the intersection with the current plane
                Plane plane = (Plane) scene.getPlanes().get(i);
                newIntersection = plane.intersectionPointOfRay(ray);
                
                /*  If the previous intersection is 0 (So there was no previous intersection with any object) 
                *   and the new intersection is behinde the limit
                *   Or
                *   The new intersection is closer to the limit but still bigger than the limit
                */
                if((intersection == 0 && newIntersection > limit) || (intersection > newIntersection && newIntersection > limit)){
                    
                    // If the plane is reflectiv at all and the depth of regressions has not reached the max
                    if(plane.getReflectionIndex() > 0.0 && depth < 10){
                        
                        // Calculate the reflection ray, therefore get the vectror of intersection at first.
                        Vector3D pointOfIntersction = Vector3D.add
                            (ray.getOrigin(), Vector3D.scale(newIntersection, ray.getDirection()));
                        // Get the actural reflection ray
                        Reflection reflection = new Reflection(plane.getSurfaceNormal(), ray, pointOfIntersction);
                        ray = reflection.getRefelctedRay();
                        
                        // If the ray is at least 100% refelctive, get the color of the reflectiv ray,
                        // by searching for the closest hit object
                        if(plane.getReflectionIndex() > 1.0){
                            color = getColor(depth++, 0);
                        }
                        // Otherewise mix the color according to the refelction index 
                        else{
                            
                            // If plane has a texture
                            if(plane.hasTexture()){
                                color = plane.getColor(pointOfIntersction);
                            }
                            // If the plane has no texture get the color of the plane
                            else{
                                color = plane.getColor();
                            }
                            //Mix the color
                            color[0] = (int) (color[0] * plane.getReflectionIndex() + scene.getBackgroundColor()[0] * (1 - plane.getReflectionIndex()));
                            color[1] = (int) (color[1] * plane.getReflectionIndex() + scene.getBackgroundColor()[1] * (1 - plane.getReflectionIndex()));
                            color[2] = (int) (color[2] * plane.getReflectionIndex() + scene.getBackgroundColor()[2] * (1 - plane.getReflectionIndex()));
                        }
                    }
                    
                    // If the plane is not reflectiv
                    else{
                        // If plane has a texture
                        if(plane.hasTexture()){
                            // Get Point of intersection and ask vor color at the point of intersection
                            Vector3D pointOfIntersction = Vector3D.add
                                (ray.getOrigin(), Vector3D.scale(newIntersection, ray.getDirection()));
                            color = plane.getColor(pointOfIntersction);
                        }
                        else{
                            // Get color of the plane
                            color = plane.getColor();
                        }
                    }
                    
                    // convert the new intersection into the old intersection for the next round
                    intersection = newIntersection;
                }
            }
            
            // After last plane choosen try the spheres 
            else{
                // Get the intersection with the current sphere
                Sphere sphere;
                sphere = (Sphere) scene.getSpheres().get(i-planes);
                newIntersection = sphere.intersectionPointOfRay(ray);
                
                /*  If the previous intersection is 0 (So there was no previous intersection with any object) 
                *   and the new intersection is behinde the limit
                *   Or
                *   The new intersection is closer to the limit but still bigger than the limit
                */
                if((intersection == 0 && newIntersection > limit) || (intersection > newIntersection && newIntersection > limit)){
                    
                    // If the sphere is reflectiv at all and the depth of regressions has not reached the max
                    if(sphere.getReflectionIndex() > 0.0 && depth < 10){
                        
                        // Calculate the reflection ray, therefore get the vectror of intersection at first.
                        Vector3D pointOfIntersction = Vector3D.add
                            (ray.getOrigin(), Vector3D.scale(newIntersection, ray.getDirection()));
                        // Deduce the actual refflection ray from point of intersection, surface normal and the actual ray
                        Reflection reflection = new Reflection(sphere.getSurfaceNormal(pointOfIntersction), ray, pointOfIntersction);
                        ray = reflection.getRefelctedRay();
                        
                        // If the ray is at least 100% refelctive, get the color of the reflectiv ray,
                        // by searching for the closest hit object
                        if(sphere.getReflectionIndex() > 1.0){
                            color = getColor(depth++, 0);
                        }
                        // Otherwise mix the color according to the reflection index
                        else{
                            color = getColor(depth++, 0);
                            color[0] = (int) (color[0] * sphere.getReflectionIndex() + scene.getBackgroundColor()[0] * (1 - sphere.getReflectionIndex()));
                            color[1] = (int) (color[1] * sphere.getReflectionIndex() + scene.getBackgroundColor()[1] * (1 - sphere.getReflectionIndex()));
                            color[2] = (int) (color[2] * sphere.getReflectionIndex() + scene.getBackgroundColor()[2] * (1 - sphere.getReflectionIndex()));
                        }
                    }
                    // If the Sphere is not reflectiv get the color of the sphere
                    else{
                        color = sphere.getColor();
                    }
                    
                    // convert the new intersection into the old intersection for the next round
                    intersection = newIntersection;
                }
            }
        }
        // return the color of the first object hit by ray
        return color;
    }
}