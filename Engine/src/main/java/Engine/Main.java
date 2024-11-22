package Engine;

import Engine.Data.*;
import Engine.Geometry.Sphere;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Sphere sphere2 = new Sphere(1,32,16);
        sphere2.rotateAxis(new Vector3(1,1,1).normalize(),(float) 1);
        System.out.println(sphere2.matrix.toString());
        System.out.println("---DONE---");
    }
}
