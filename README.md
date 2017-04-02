# Langhalsdinos Ray Tracing
The goal of the whole project is to create a Ray Tracing engine with some basic functionality.
First, the main idea of ray tracing is to identify the color of each pixel by reconstructing the path that the light took, from the source of the light to the eye. 
Therefore, we can create a photorealistic image with a perspective projection of the actual scene. 
The development of the project started during the mathematical exploration of ray tracing in school, therefore I have to mention, that I wrote this piece of software just for fun and it is not finished, so feel free to expand the functionality of this project.

## Licence & acknowledgment
Langhalsdinos Ray Tracing is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or any later version.
Langhalsdinos Ray Tracing is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Langhalsdinos Ray Tracing. If not, see <http://www.gnu.org/licenses/>.

The developed code only depends on the libraries of the Java development kit 
and a class for the Gaussian elimination by Sebastian Peuser.
Additionally I found some inspiration for the vector class from Leonard McMillans work. 
The rest was done by myself and maybe you, if you participate in this github project.

## Structure of Langhalsdinos Ray Tracing
Since I created the project with Netbeans, it has the structure of a Netbeans project.
You can finde the source code under **Ray-Tracing -> scr**.

Langhalsdinos Ray Tracing contains two pakages:

1. RayTracing (contains the Main)
  1. The containing classes inherited a lot of different function in order to execute Ray Tracing.
  2. Currently supported objects:
    1. Plane 
    2. Sphere
  3. Currently supported types of surfaces:
    1. Reflective surface (with from 0 to 100%)
    2. Colored surfaces (one color)
    3. Textured surfaces
      1. Image as texture
      2. Mathematical function as texture, currently only chees pattern (Still work to do)
2. TagFileReader (verry early and not implemented correctly)
  1. This package should open a file, with the discription of a scene and certain object (Similar to XML stuff)

Additional I will publish a modified paper about ray tracing, the current version is not updated and has some errors.
You can find my paper under **Paper_Ray-Tracing**, additionally I will upload some pictures of processed images
**Ohh and the software got multi core support :)**
