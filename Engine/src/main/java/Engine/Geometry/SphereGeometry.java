package Engine.Geometry;

import Engine.Data.Matrix;
import Engine.Data.Vector3;


public class SphereGeometry extends Geometry{
    public float radius;
    private final int widthSegment;
    private final int heightSegment;

    public float[] vertices;


    public SphereGeometry(float r, int width_segment, int height_segment) {
        this.position = new Vector3(0,0,0);
        this.radius = r;
        this.widthSegment = width_segment;
        this.heightSegment = height_segment;
        this.orientation = new Vector3[3];
        this.orientation[0] = new Vector3(1,0,0);
        this.orientation[1] = new Vector3(0,1,0);
        this.orientation[2] = new Vector3(0,0,1);
        this.matrix = new Matrix(4);
        int numVertices = 2+(this.heightSegment-1)*this.widthSegment;
        this.vertices = new float[3*numVertices];
    }
    public void findVertices(){
        long start = System.nanoTime();
        int index = 0;
        vertices[index++] = 0;
        vertices[index++] = this.radius;
        vertices[index++] = 0;
        float invWidth = (float) (1.0f / this.widthSegment* 2 * Math.PI);
        float invHeight = (float) (1.0f / this.heightSegment * Math.PI);
        float[] xs = new float[this.widthSegment];
        float[] zs = new float[this.widthSegment];
        for (int j=0;j<this.widthSegment;++j) {
            float angle1 = j * invWidth;
            xs[j] = (float)Math.cos(angle1);
            zs[j] = (float)Math.sin(angle1);
        }
        for (int i = 1;i<this.heightSegment;++i){
            float angle2 = i * invHeight;
            float sinAngle = (float) (radius*Math.sin(angle2));
            float y = (float) (this.radius * Math.cos(angle2));
            for (int j=0;j<this.widthSegment;++j){
                float x = sinAngle * xs[j];
                float z = sinAngle * zs[j];

                // Store the vertex position
                vertices[index++] = x;
                vertices[index++] = y;
                vertices[index++] = z;
            }
        }
        vertices[index++] = 0;
        vertices[index++] = -this.radius;
        vertices[index] = 0;
        System.out.println((float)(System.nanoTime()-start)/1000000);
    }

}
