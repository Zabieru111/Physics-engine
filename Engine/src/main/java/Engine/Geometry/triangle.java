package Engine.Geometry;

import Engine.Data.Vector3;

public class triangle {
    public Vector3[] vertices;

    public triangle(Vector3 x,Vector3 x2,Vector3 x3) {
        this.vertices = new Vector3[]{x,x2,x3};
    }
}
