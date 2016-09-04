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
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.*; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;


/**
 *
 * @author Langhalsdino
 */
public class RayTracing {
    private String path;

    /**
     * Creates emty buffered image
     * 
     * @param width     width of the image
     * @param height    height of the image
     * @return          returns emty image
     */
    private BufferedImage createImage(int width,int height) {
        // Create BufferedImage
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        return image;
    }
    
    /**
     * Creates a writable raster from buffered image
     * 
     * @param image buffered image
     * @return      writable raster from image
     */
    private WritableRaster createRaster(BufferedImage image) {
        // creat writable raster from BufferdImage
        WritableRaster raster = image.getRaster();
        return raster;
    }
    
    /**
     * Get Path of the desktop
     * 
     * @return returns path of the desktop
     */
    private String getPathDesktop() {
        //get Paht of the users home directory
        String userHomeFolder = System.getProperty("user.home");
        return userHomeFolder;
    }
    
    /**
     * Save a buffered image as png to a location   
     * 
     * @param image     buffered image to save
     * @param location  path to the new image
     */
    private void saveImage(BufferedImage image, String location) {
        // try to save BufferdImage at location
        try {
            ImageIO.write(image,"PNG",new File(location + "/rayTracingImage_multi_Thread.png"));
        }
        catch (IOException ioe) {
            System.out.println("Could not save file");
        }
    }
    
    /**
     * Set Pixels of the image, so performes the ray tracing.
     * Therefore the function has to manage diffrent threads, ...
     * 
     * @param image     image to fill with colore
     * @param raster    raster from image
     * @throws MalformedURLException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InterruptedException 
     */
    private void setPixels(BufferedImage image, WritableRaster raster) throws MalformedURLException, IOException, IllegalAccessException, InstantiationException, InterruptedException{
        // start raytracing here!!
        int[] color;
        
        int width = image.getWidth();
        int height = image.getHeight();
        
        double magnification = 1.5; //1 -> 100%
        
        //Create a camera
        Vector3D eye = new Vector3D(-146.45,-353.55,0.0);
        Vector3D distance = new Vector3D(-353.55,353.55,0.0); 
        Vector3D viewUp = new Vector3D(-1.0,1.0,2.0);
        int maxX = width;
        int maxY = height;
        double xLenght = height * magnification;
        double yLenght = width * magnification;
        
        Camera camera = new Camera(eye, distance, viewUp, maxX, maxY, xLenght, yLenght);
        Scene3D scene = new Scene3D();
        
        //Background Color
        int[] bgColor = {0,0,0};
        scene.setBackgroundColor(bgColor);
        
        //Add white sphere to scene
        int[] sphereRGB = {255,255,255};
        double radius = 70.0;
        double reflection = 0.8;
        Vector3D center = new Vector3D(-600.0,100.0,30.0);
        scene.addObject(center, radius, sphereRGB, reflection);
        
        /*
        //Add white sphere to scene
        center = new Vector3D(-600.0,-100.0,0.0);
        scene.addObject(center, radius, sphereRGB, reflection);
        
        //Add white sphere to scene
        center = new Vector3D(-600.0,0.0,-100.0);
        scene.addObject(center, radius, sphereRGB, reflection);
        
        //Add white sphere to scene
        center = new Vector3D(-600.0,0.0,100.0);
        scene.addObject(center, radius, sphereRGB, reflection);
        */
        
        //Add blue plane to scene
        Vector3D normalBP = new Vector3D(0.0,1.0,0.0);
        Vector3D pointInPlaneBP = new Vector3D(-600.0,100.0,0.0);
        double reflectiveBP = 0.0;
        int[] planeRGBBP = {0,0,255};
        double scale = 0.05;
        Vector3D rotation = new Vector3D(0.0, -1.0, 1.0);
        BufferedImage textureImage = ImageIO.read(new File(getPathDesktop() + "/Desktop/Ray-Tracing_private_images/" + "right_wall.jpg"));
        scene.addObject(normalBP, pointInPlaneBP, planeRGBBP, reflectiveBP, rotation, scale, textureImage);
        
        //Add red plane to scene
        Vector3D normalRP = new Vector3D(0.0,1.0,0.0);
        Vector3D pointInPlaneRP = new Vector3D(-600.0,-100.0,0.0);
        double reflectiveRP = 0.0;
        int[] planeRGBRP = {255,0,0};
        scale = 0.06;
        rotation = new Vector3D(0.0, 1.0, 1.0);
        textureImage = ImageIO.read(new File(getPathDesktop() + "/Desktop/Ray-Tracing_private_images/" + "left_wall.jpg"));
        scene.addObject(normalRP, pointInPlaneRP, planeRGBRP, reflectiveRP, rotation, scale, textureImage);
        
        //Add green (chess) plane to sceen
        Vector3D normalGP = new Vector3D(0.0,0.0,1.0);
        Vector3D pointInPlaneGP = new Vector3D(-600.0,0.0,100.0);
        double reflectiveGP = 0.0;
        int[] planeRGBGP = {0,255,0};
        scale = 50.0;
        rotation = new Vector3D(0.0, 1.0, 1.0);
        ColorFunction function = new ColorFunction("chess");
        scene.addObject(normalGP, pointInPlaneGP, planeRGBGP, reflectiveGP,  rotation, scale, function);
        
        //Add yellow plane to sceen
        Vector3D normalYP = new Vector3D(0.0,0.0,1.0);
        Vector3D pointInPlaneYP = new Vector3D(-600.0,0.0,-100.0);
        double reflectiveYP = 0.0;
        int[] planeRGBYP = {255,255,0};
        scale = 0.05;
        rotation = new Vector3D(-1.0, 0.0, -1.0);
        textureImage = ImageIO.read(new File(getPathDesktop() + "/Desktop/Ray-Tracing_private_images/" + "roof_wall.jpg"));
        scene.addObject(normalYP, pointInPlaneYP, planeRGBYP, reflectiveYP, rotation, scale, textureImage);
        
        // Deffines amount of Threads and Blocking Queue
        int maxThreads = 8;
        BlockingQueue qTask = new ArrayBlockingQueue(width * height);
        BlockingQueue qFinal = new ArrayBlockingQueue(width * height);      
        
        //Create Pixels for the image and put them into the Blocking Queue
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                Pixel currentPixel = new Pixel(w, h, scene.getBackgroundColor());
                qTask.put(currentPixel);
            }
        }
        
        // creates and starts the new threads
        for (int threadCount = 0; threadCount < maxThreads; threadCount++) {
            RenderThread renderThread = new RenderThread(qTask, qFinal, camera, scene);
            new Thread((Runnable) renderThread).start();
        }
        
        // wait threads to finish
        while(!qTask.isEmpty() && !(qFinal.size()>= height * width)) {
            // wait render threads to finish
        }
        
        // create new image
        while(!qFinal.isEmpty()) {
            Pixel currentPixel = (Pixel) qFinal.take();
            raster.setPixel(currentPixel.getPosX(), currentPixel.getPosY(), currentPixel.getRGB());
        }
    }
    
    /**
     * Main of the whole thing!! Does everything like a boss ;)
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // size of the picture
        /*
        int width = 1250;
        int height = 1250;
        */
        
        int width = 125;
        int height = 125;
        
        // creat Ray Tracing
        RayTracing rayTracing = new RayTracing();
        rayTracing.path = rayTracing.getPathDesktop() + "/Desktop/Ray-Tracing_private_images/";
        
        // creat image and write ray traced image
        BufferedImage image = rayTracing.createImage(width, height);
        WritableRaster raster = rayTracing.createRaster(image);
        
        try {
            rayTracing.setPixels(image, raster);
        } catch (MalformedURLException | IllegalAccessException | InstantiationException | InterruptedException ex) {
            Logger.getLogger(RayTracing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // print out the path of the image
        System.out.println(rayTracing.path);
        
        // save image at path
        rayTracing.saveImage(image, rayTracing.path);
    
    }
    
}
