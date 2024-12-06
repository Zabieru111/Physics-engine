package Engine.Renderer;

import Engine.Data.Quaternion;
import Engine.Data.Vector3;

public class Camera {
    public int fov;
    public float ratio;
    public Vector3 position;
    public float near;
    public float far;
    public Vector3 lookAt;
    public Vector3 forward;
    public Vector3 right;
    private Vector3 up;
    public Quaternion q = new Quaternion();

    public Camera(int fov, float ratio, Vector3 position, float near, float far, Vector3 lookAt) {
        this.fov = fov;
        this.ratio = ratio;
        this.position = position;
        this.near = near;
        this.far = far;
        this.lookAt = lookAt;
        this.up = new Vector3(0,1,0);
        this.forward = lookAt.sub(position).normalize();
        this.right = this.up.cross(this.forward).normalize();
        this.up = this.forward.cross(this.right).normalize();
        this.setOrientation(right,up,forward);
    }

    public Camera() {
        this.fov = 90;
        this.ratio = 1.33f;
        this.position = new Vector3(0,0,0);
        this.near = 0.1f;
        this.far = 1000f;
        this.lookAt = new Vector3(0,0,1);
        this.up = new Vector3(0,1,0);
        this.forward = lookAt.sub(position).normalize();
        this.right = this.up.cross(this.forward).normalize();
        this.up = this.forward.cross(this.right).normalize();
        this.setOrientation(right,up,forward);
    }
    public void setOrientation(Vector3 right, Vector3 up, Vector3 forward){
        float[][] m = new float[][]{
                {right.x,right.y,right.z},
                {up.x,up.y,up.z},
                {forward.x, forward.y, forward.z}
        };
        q.setFromMatrix(m);
    }
}
