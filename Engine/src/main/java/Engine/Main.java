package Engine;

import Engine.Data.Vector3;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Vector3 vector3 = new Vector3(1, 1, 1).add(new Vector3(1,5,4).normalize()).normalize();
        System.out.println(vector3.toString());
        System.out.println("---DONE---");
    }
}
