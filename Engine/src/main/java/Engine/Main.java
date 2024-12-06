package Engine;

import Engine.Data.*;
import Engine.Geometry.*;
import Engine.Renderer.Camera;
import Engine.Renderer.Renderer;


public class Main {
    public static void main(String[] args) {
        PlaneGeometry planeGeometry = new PlaneGeometry(1000,1000,2,2);
        SphereGeometry sphereGeometry = new SphereGeometry(1000,64,64);
        sphereGeometry.findVertices();
        System.out.println("---DONE---");
    }
}
