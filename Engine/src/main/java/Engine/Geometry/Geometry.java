package Engine.Geometry;

import Engine.Data.Matrix;
import Engine.Data.Vector;
import Engine.Data.Vector3;

public abstract class Geometry {
    public Matrix matrix;
    public Vector3 position;
    protected Vector3[]orientation;


    public void translate(float dx,float dy,float dz){
        this.matrix.matrix[0][3]+=dx;
        this.matrix.matrix[1][3]+=dy;
        this.matrix.matrix[2][3]+=dz;

        this.position = this.matrix.dot(this.position);
    }
    public void scale(float dx,float dy,float dz){
        this.matrix.matrix[0][0]*=dx;
        this.matrix.matrix[1][1]*=dy;
        this.matrix.matrix[2][2]*=dz;
    }
    public void rotateX(float angle){
        float cos = (float)Math.cos(angle);
        float sin = (float)Math.sin(angle);
        this.matrix = this.matrix.dot(new Matrix(4,4,new float[]{
                1,0,0,0,
                0,cos,-sin,0,
                0,sin,cos,0,
                0,0,0,1
        }));
    }
    public void rotateY(float angle){
        float cos = (float)Math.cos(angle);
        float sin = (float)Math.sin(angle);
        this.matrix = this.matrix.dot(new Matrix(4,4,new float[]{
                cos,0,sin,0,
                0,1,0,0,
                -sin,0,cos,0,
                0,0,0,1
        }));
    }
    public void rotateZ(float angle){
        float cos = (float)Math.cos(angle);
        float sin = (float)Math.sin(angle);
        this.matrix = this.matrix.dot(new Matrix(4,4,new float[]{
                cos,-sin,0,0,
                sin,cos,0,0,
                0,0,1,0,
                0,0,0,1
        }));
    }
    public void rotateAxis(Vector3 axis,float angle){
        float cos = (float)Math.cos(angle);
        float sin = (float)Math.sin(angle);
        Matrix K = new Matrix(3,3,new float[]{
                0,-axis.z, axis.y,
                axis.z,0,-axis.x,
                -axis.y, axis.x,0
        });
        Matrix R = new Matrix(3).add(K.mul_Scalar(sin)).add(K.dot(K).mul_Scalar((1-cos)));
        R.resize(4,4);
        this.matrix = this.matrix.dot(R);
    }

}
