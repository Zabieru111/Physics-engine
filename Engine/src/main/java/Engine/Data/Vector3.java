package Engine.Data;

public class Vector3 extends Vector {
    public float x;
    public float y;
    public float z;
    public Vector3(float x,float y,float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = 3;
    }

    public Vector3() {
        this.x=0;
        this.y=0;
        this.z=0;
        this.type=3;
    }

    @Override
    public Vector3 add(Vector other) {
        if(other.type==this.type){
            Vector3 temp = (Vector3) other;
            this.x+=temp.x;
            this.y+=temp.y;
            this.z+=temp.z;
        }
        return this;
    }

    @Override
    public Vector3 sub(Vector other) {
        if(other.type==this.type){
            Vector3 temp = (Vector3) other;
            this.x-=temp.x;
            this.y-=temp.y;
            this.z-=temp.z;
        }
        return this;
    }

    @Override
    public Vector3 mul(Vector other) {
        if(other.type==this.type){
            Vector3 temp = (Vector3) other;
            this.x*=temp.x;
            this.y*=temp.y;
            this.z*=temp.z;
        }
        return this;
    }

    @Override
    public Vector3 div(Vector other) {
        if(other.type==this.type){
            Vector3 temp = (Vector3) other;
            if (temp.x != 0 && temp.y != 0 && temp.z != 0) {
                this.x /= temp.x;
                this.y /= temp.y;
                this.z /= temp.z;
            }
        }
        return this;
    }

    @Override
    public float length() {
        return (float)Math.sqrt(this.x*this.x+this.y*this.y+this.z*this.z);
    }

    @Override
    public float dot(Vector other) {
        if (this.type==other.type){
            Vector3 temp = (Vector3) other;
            return this.x* temp.x+this.y*temp.y+this.z*temp.z;
        }
        return -1;
    }

    @Override
    public Vector3 normalize() {
        float length = this.length();
        if (Math.abs(length)>Constant.EPSILON) {
            this.x /= length;
            this.y /= length;
            this.z /= length;
        }
        return this;
    }

    @Override
    public Vector3 add_scalar(float scalar) {
        this.x += scalar;
        this.y += scalar;
        this.z += scalar;
        return this;
    }

    @Override
    public Vector3 sub_scalar(float scalar) {
        this.x -= scalar;
        this.y -= scalar;
        this.z -= scalar;
        return this;
    }

    @Override
    public Vector3 mul_scalar(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        return this;
    }

    @Override
    public Vector3 div_scalar(float scalar) {
        if( scalar!=0){
            this.x/=scalar;
            this.y/=scalar;
            this.z/=scalar;
        }
        return this;
    }

    @Override
    public boolean equals(Vector other) {
        if (this.type==other.type){
            Vector3 temp = (Vector3) other;

            return Math.abs(temp.x - this.x) < Constant.EPSILON &&
                    Math.abs(temp.y - this.y) < Constant.EPSILON &&
                    Math.abs(temp.z - this.z) < Constant.EPSILON;
        }
        return false;
    }

    @Override
    public Matrix toMatrix() {
        return new Matrix(3,1,new float[]{this.x,this.y,this.z});
    }

    @Override
    public String toString() {
        return String.format("Vector3(x=%.4f, y=%.4f, z=%.4f)", this.x, this.y, this.z);
    }

    @Override
    public Vector3 cross(Vector other) {
        if (other.type!=3){
            throw new IllegalArgumentException("the vectors should both be Vector3");
        }
        Vector3 temp = (Vector3) other;
        Vector3 w = new Vector3();
        w.x = this.y*temp.z - this.z*temp.y;
        w.y = this.z*temp.x-this.x*temp.z;
        w.z = this.x*temp.y-this.y*temp.x;
        return w.normalize();
    }
    public void set(float x,float y,float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
