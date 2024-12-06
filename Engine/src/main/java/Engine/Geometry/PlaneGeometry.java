package Engine.Geometry;


public class PlaneGeometry extends Geometry{
    public float width;
    public float height;

    public int widthSegment;

    public int heigthSegment;

    public float[]triangles;

    public PlaneGeometry(float width, float height,int widthSegment,int heightSegment) {
        this.width = width;
        this.height = height;
        this.widthSegment = widthSegment;
        this.heigthSegment = heightSegment;
        int numVertices = (this.widthSegment+1)*(this.heigthSegment+1);
        this.triangles = new float[numVertices*9];
        findTriangles();
    }
    public PlaneGeometry(){
        this(1,1,1,1);
    }

    //@Override
    public void findTriangles() {
        long start = System.nanoTime();
        float heightDisplacement = this.height/this.heigthSegment;
        float widthDisplacement = this.width/this.widthSegment;
        float halfHeight = this.height/2;
        float halfWidth = this.width/2;
        float[] xs = new float[this.widthSegment+1];
        for (int i = 0;i<=this.widthSegment;++i){
            xs[i] = -halfWidth+i*widthDisplacement;
        }
        int index = 0;
        for (int i=0;i<=this.heigthSegment;++i){
            float z = (-halfHeight+i*heightDisplacement);
            for (int j=0;j<=this.widthSegment;++j){
                triangles[index] = xs[j];
                triangles[index+2] = z;
                triangles[index+3] = (xs[j]+halfWidth)/this.width;
                triangles[index+4] = (z+halfHeight)/this.height;
                triangles[index+6] = 1;
                triangles[index+8] = index/9;
                index+=9;
            }
        }
        System.out.println((float)(System.nanoTime()-start)/1000000);
    }
}
