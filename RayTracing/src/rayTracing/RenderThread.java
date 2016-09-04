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

import java.util.concurrent.BlockingQueue;
import javafx.scene.paint.Color;

/**
 *
 * @author Langhalsdino
 * 
 * The render thread renders pixel from a task Blocking Queue and saves them in final Blcoking Queue
 */
public class RenderThread implements Runnable {
    private final BlockingQueue qTask;
    private final BlockingQueue qFinal;
    private final Camera currentCamera;
    private final Scene3D currentScene;
    
    /**
     * Create a greate render thread class, that performes stuff like a pro
     * 
     * @param queueTask     Blocking queue with the tasks (filled with pixels)
     * @param queueFinal    Blocking queue as destination for the rendered pixels
     * @param camera        Cameara of the scene
     * @param scene         Scene with the virtual screne
     */
    RenderThread(BlockingQueue queueTask, BlockingQueue queueFinal, Camera camera, Scene3D scene) {
        qFinal = queueFinal;
        qTask = queueTask;
        currentCamera = camera;
        currentScene = scene;
    }

    /**
     * renders a pixel
     * 
     * @param pixelToRender pixel it should render
     * @return              rendered pixel
     */
    private Pixel renderPixel(Pixel pixelToRender) {
        Pixel pixel = pixelToRender;
        GetColor calcColor = new GetColor(pixel.getPosX(), pixel.getPosY(), currentCamera, currentScene);
        pixel.setRGB(calcColor.getColor(0, 1));
        return pixel;
    }
   
    /**
     * run the render thread
     */
    public void run() {
        try {
            while (!qTask.isEmpty()) { 
                Pixel pixel = (Pixel) qTask.take();
                qFinal.put(renderPixel(pixel));
            }
        } 
        catch (InterruptedException ex) {
            System.out.println("Couln't put finished Pixel into queue");
        }
    }
}
