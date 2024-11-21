package Engine.Data;

public abstract class Vector {
    protected int type;
    public abstract Vector add(Vector other);
    public abstract Vector sub(Vector other);
    public abstract Vector mul(Vector other);
    public abstract Vector div(Vector other);
    public abstract float length();
    public abstract float dot(Vector other);
    public abstract Vector normalize();

    public abstract Vector add_scalar(float scalar);
    public abstract Vector sub_scalar(float scalar);
    public abstract Vector mul_scalar(float scalar);
    public abstract Vector div_scalar(float scalar);

    public abstract boolean equals(Vector other);

    public abstract Matrix toMatrix();

    public abstract String toString();

    public abstract Vector cross(Vector other);
}
