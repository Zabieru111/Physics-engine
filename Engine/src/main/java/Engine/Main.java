package Engine;

import Engine.Data.Matrix;
import Engine.Data.Vector3;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Matrix matrix1= new Matrix(4,4,new float[]{1,3,1,4,3,9,5,15,0,2,1,1,0,4,2,3});
        System.out.println(matrix1.det);
        System.out.println("---DONE---");
    }
}
