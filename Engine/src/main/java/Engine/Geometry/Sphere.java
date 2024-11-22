package Engine.Geometry;

import Engine.Data.Matrix;
import Engine.Data.Vector3;


public class Sphere extends Geometry{
    public float radius;
    private final int widthSegment;
    private final int heightSegment;



    public Sphere(float r,int width_segment,int height_segment) {
        this.position = new Vector3(0,0,0);
        this.radius = r;
        this.widthSegment = width_segment;
        this.heightSegment = height_segment;
        this.orientation = new Vector3[3];
        this.orientation[0] = new Vector3(1,0,0);
        this.orientation[1] = new Vector3(0,1,0);
        this.orientation[2] = new Vector3(0,0,1);
        this.matrix = new Matrix(4);
    }
    private Vector3[] findVertices(){
        Vector3[]vertices = new Vector3[2+(this.heightSegment-1)*this.widthSegment];
        long start = System.nanoTime();
        int index = 0;
        vertices[index++] = new Vector3(0,this.radius,0);
        float invWidth = 1.0f / this.widthSegment;
        float invHeight = 1.0f / this.heightSegment;
        for (int i = 1;i<this.heightSegment;++i){
            for (int j=0;j<this.widthSegment;++j){
                // Azimuthal angle (angle around the Y-axis)
                float angle1 = (float) (j * invWidth * 2 * Math.PI);
                // Polar angle (angle from the Z-axis)
                float angle2 = (float) (i * invHeight * Math.PI);
                float sinAngle = radius*(float)Math.sin(angle2);
                // Convert spherical to Cartesian coordinates
                float x = (float) (sinAngle * Math.cos(angle1));
                float y = (float) (this.radius * Math.cos(angle2));
                float z = (float) (sinAngle * Math.sin(angle1));

                // Store the vertex position
                vertices[index++] = new Vector3(x, y, z);
            }
        }
        vertices[index] = new Vector3(0,-this.radius,0);
        System.out.println((float)(System.nanoTime()-start)/1000000);
        return vertices;
    }

}
