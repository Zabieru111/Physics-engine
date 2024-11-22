package Engine.Geometry;

import Engine.Data.Matrix;
import Engine.Data.Vector3;

public abstract class Geometry {
    protected Matrix matrix;
    public Vector3 position;
    protected Vector3[]orientation;


    public abstract void translate(float dx,float dy,float dz);

}
