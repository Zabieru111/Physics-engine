package Engine.Data;

public class Vector4 extends Vector {
    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
        this.type = 4;
    }

    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        this.type = 4;
    }

    @Override
    public Vector4 add(Vector other) {
        if (other.type == this.type) {
            Vector4 temp = (Vector4) other;
            this.x += temp.x;
            this.y += temp.y;
            this.z += temp.z;
            this.w += temp.w;
        }
        return this;
    }

    @Override
    public Vector4 sub(Vector other) {
        if (other.type == this.type) {
            Vector4 temp = (Vector4) other;
            this.x -= temp.x;
            this.y -= temp.y;
            this.z -= temp.z;
            this.w -= temp.w;
        }
        return this;
    }

    @Override
    public Vector4 mul(Vector other) {
        if (other.type == this.type) {
            Vector4 temp = (Vector4) other;
            this.x *= temp.x;
            this.y *= temp.y;
            this.z *= temp.z;
            this.w *= temp.w;
        }
        return this;
    }

    @Override
    public Vector4 div(Vector other) {
        if (other.type == this.type) {
            Vector4 temp = (Vector4) other;
            if (temp.x != 0 && temp.y != 0 && temp.z != 0 && temp.w != 0) {
                this.x /= temp.x;
                this.y /= temp.y;
                this.z /= temp.z;
                this.w /= temp.w;
            }
        }
        return this;
    }

    @Override
    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
    }

    @Override
    public float dot(Vector other) {
        if (other.type == this.type) {
            Vector4 temp = (Vector4) other;
            return this.x * temp.x + this.y * temp.y + this.z * temp.z + this.w * temp.w;
        }
        return -1;
    }

    @Override
    public Vector4 normalize() {
        float length = this.length();
        if (Math.abs(length) > Constant.EPSILON) {
            this.x /= length;
            this.y /= length;
            this.z /= length;
            this.w /= length;
        }
        return this;
    }

    @Override
    public Vector4 add_scalar(float scalar) {
        this.x += scalar;
        this.y += scalar;
        this.z += scalar;
        this.w += scalar;
        return this;
    }

    @Override
    public Vector4 sub_scalar(float scalar) {
        this.x -= scalar;
        this.y -= scalar;
        this.z -= scalar;
        this.w -= scalar;
        return this;
    }

    @Override
    public Vector4 mul_scalar(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
        this.w *= scalar;
        return this;
    }

    @Override
    public Vector4 div_scalar(float scalar) {
        if (scalar != 0) {
            this.x/=scalar;
            this.y/=scalar;
            this.z/=scalar;
            this.w/=scalar;
        }
        return this;
    }

    @Override
    public boolean equals(Vector other) {
        if (this.type==other.type){
            Vector4 temp = (Vector4) other;

            return Math.abs(temp.x - this.x) < Constant.EPSILON &&
                    Math.abs(temp.y - this.y) < Constant.EPSILON &&
                    Math.abs(temp.z - this.z) < Constant.EPSILON &&
                    Math.abs(temp.w - this.w) < Constant.EPSILON;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Vector4(x=%.4f, y=%.4f, z=%.4f, w=%.4f)", this.x, this.y, this.z, this.w);
    }
}
