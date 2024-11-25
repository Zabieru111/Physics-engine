package Engine.Data;

public class Quaternion {
    public float w;
    public float x, y, z;

    public Quaternion(float w, float x, float y, float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
        this.normalize();
    }

    public Quaternion() {
        this(1, 0, 0, 0);
    }

    public Quaternion(float w, Vector3 vector3) {
        this.w = w;
        this.x = vector3.x;
        this.y = vector3.y;
        this.z = vector3.z;
        this.normalize();
    }
    public Quaternion multiplyQuaternion(Quaternion q2){
        float w = this.w*q2.w-this.x*q2.x-this.y*q2.y-this.z*q2.z;
        float x = this.w*q2.x+this.x*q2.w+this.y*q2.z-this.z*q2.y;
        float y = this.w*q2.y-this.x*q2.z+this.y*q2.w+this.z*q2.x;
        float z = this.w*q2.z+this.x*q2.y-this.y*q2.x+this.z*q2.w;
        return new Quaternion(w,x,y,z);
    }

    public void setFromAngle(float angle, Vector3 axis) {
        float half_angle = angle / 2;
        float cos = (float) Math.cos(half_angle);
        float sin = (float) Math.sin(half_angle);
        this.w = cos;
        this.x = axis.x*sin;
        this.y = axis.y*sin;
        this.z = axis.z*sin;
    }

    public void setFromAxis(Vector3 axis1, Vector3 axis2) {
        float angle = (float) Math.acos(axis1.dot(axis2));
        System.out.println(angle*180/Math.PI);

        this.setFromAngle(angle,axis1.cross(axis2));
    }

    public Vector3 rotateVector3(Vector3 vector3) {
        Quaternion qv = new Quaternion(0, vector3);
        Quaternion invQ = new Quaternion(this.w,-this.x,-this.y,-this.z);

        return this.multiplyQuaternion(qv.multiplyQuaternion(invQ)).xyz();
    }

    public Matrix getRotationMatrix() {
        float y2 = 2 * this.y * this.y;
        float x2 = 2 * this.x * this.x;
        float z2 = 2 * this.z * this.z;
        float xy = 2 * this.x * this.y;
        float xz = 2 * this.x * this.z;
        float wx = 2 * this.w * this.x;
        float yz = 2 * this.y * this.z;
        float wy = 2 * this.w * this.y;
        float wz = 2 * this.w * this.z;
        return new Matrix(new float[][]{
                {1 - y2 - z2, xy - wz, xz + wy, 0},
                {xy + wz, 1 - x2 - z2, yz - wx, 0},
                {xz - wy, yz + wx, 1 - x2 - y2, 0},
                {0, 0, 0, 1}
        });
    }
    public Vector3 xyz(){
        return new Vector3(this.x,this.y,this.z);
    }
    public void normalize() {
        float norm = (float) Math.sqrt(w * w + x * x + y * y + z * z);
        this.w /= norm;
        this.x /= norm;
        this.y /= norm;
        this.z /= norm;
    }

    @Override
    public String toString() {
        return String.format("Quaternion(w=%.4f,x=%.4f, y=%.4f, z=%.4f)",this.w,this.x,this.y,this.z);
    }
}
