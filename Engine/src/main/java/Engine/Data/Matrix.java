package Engine.Data;

public class Matrix {
    public int n;
    public int m;

    public float det;
    public float invDet;
    public float [][] matrix;

    public Matrix(float[][] matrix) {
        this.n = matrix.length;
        this.m = matrix[0].length;
        this.matrix = matrix;
        this.det = findDet(this.matrix);
        this.invDet = 1/this.det;
    }

    public Matrix(int n, int m, float[] values) {
        if(n * m == values.length) {
            this.n = n;
            this.m = m;
            this.matrix = new float[n][m];
            for (int i = 0; i < values.length; ++i) {
                this.matrix[i / m][i % m] = values[i];
            }
            long start = System.nanoTime();
            this.det = findDet(this.matrix);
            this.invDet = 1/this.det;
            System.out.println((System.nanoTime()-start)/1000);
        }
    }
    public Matrix transpose(){
        float[][] temp = new float[this.m][this.n];
        for (int i = 0; i< n;++i){
            for (int j = 0;j< m;++j){
                temp[j][i] = this.matrix[i][j];
            }
        }
        return new Matrix(temp);
    }
    public Matrix add(Matrix other){
        if (this.n!=other.n || this.m!= other.m){
            throw new IllegalArgumentException("Matrix dimension must match");
        }
        float[][] result = new float[n][m];
        for (int i = 0;i<this.n;++i){
            for (int j=0;j<this.m;++j){
               result[i][j] = this.matrix[i][j]+other.matrix[i][j];
            }
        }
        return new Matrix(result);
    }
    public Matrix sub(Matrix other){
        if (this.n!=other.n || this.m!= other.m){
            throw new IllegalArgumentException("Matrix dimension must match");
        }
        float[][] result = new float[n][m];
        for (int i = 0;i<this.n;++i){
            for (int j=0;j<this.m;++j){
                result[i][j] = this.matrix[i][j]-other.matrix[i][j];
            }
        }
        return new Matrix(result);
    }
    public Matrix mul(Matrix other){
        if (this.n!=other.n || this.m!= other.m){
            throw new IllegalArgumentException("Matrix dimension must match");
        }
        float[][] result = new float[n][m];
        for (int i = 0;i<this.n;++i){
            for (int j=0;j<this.m;++j){
                result[i][j] = this.matrix[i][j]*other.matrix[i][j];
            }
        }
        return new Matrix(result);
    }
    public Matrix div(Matrix other){
        if (this.n!=other.n || this.m!= other.m){
            throw new IllegalArgumentException("Matrix dimension must match");
        }
        float[][] result = new float[n][m];
        for (int i = 0;i<this.n;++i){
            for (int j=0;j<this.m;++j){
                float value = other.matrix[i][j];
                if (Math.abs(value)<Constant.EPSILON){
                    throw new ArithmeticException("Division by zero");
                }
                result[i][j] = this.matrix[i][j]/other.matrix[i][j];
            }
        }
        return new Matrix(result);
    }
    public Matrix dot(Matrix other){
        if (this.m != other.n){
            throw new IllegalArgumentException( String.format("Matrix multiplication requires A.columns == B.rows: [%dx%d] and [%dx%d]",
                    this.n, this.m, other.n, other.m));
        }
        float[][] result = new float[this.n][other.m];
        for (int i = 0;i<this.n;++i){
            for (int j = 0;j<other.m;++j){
                for(int k = 0;k<this.m;++k){
                    result[i][j] += this.matrix[i][k] * other.matrix[k][j];
                }
            }
        }
        return new Matrix(result);
    }
    public Matrix add_Scalar(float scalar){
        float[][] result = new float[n][m];
        for (int i = 0;i<this.n;++i){
            for (int j=0;j<this.m;++j){
                result[i][j] = this.matrix[i][j]+scalar;
            }
        }
        return new Matrix(result);
    }
    public Matrix sub_Scalar(float scalar){
        float[][] result = new float[n][m];
        for (int i = 0;i<this.n;++i){
            for (int j=0;j<this.m;++j){
                result[i][j] = this.matrix[i][j]-scalar;
            }
        }
        return new Matrix(result);
    }
    public Matrix mul_Scalar(float scalar){
        float[][] result = new float[n][m];
        for (int i = 0;i<this.n;++i){
            for (int j=0;j<this.m;++j){
                result[i][j] = this.matrix[i][j]*scalar;
            }
        }
        return new Matrix(result);
    }
    public Matrix div_Scalar(float scalar){
        if (Math.abs(scalar)<Constant.EPSILON){
            throw new ArithmeticException("Division by zero");
        }
        float[][] result = new float[n][m];
        for (int i = 0;i<this.n;++i){
            for (int j=0;j<this.m;++j){
                result[i][j] = this.matrix[i][j]/scalar;
            }
        }
        return new Matrix(result);
    }
    public boolean equals(Matrix other){
        if (this.n != other.n || this.m!=other.m){
            return false;
        }
        for (int i = 0 ; i<this.n;++i){
            for (int j = 0; j<this.m;++j){
                if (Math.abs(this.matrix[i][j]-other.matrix[i][j])>Constant.EPSILON){
                    return false;
                }
            }
        }
        return true;
    }
    private float find2x2Det(float[][]matrix){
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }
    private float find3x3Det(float[][]matrix){
        float part1 = matrix[0][0]*find2x2Det(new float[][]{
                {matrix[1][1],matrix[1][2]},
                {matrix[2][1],matrix[2][2]}});
        float part2 = matrix[0][1] * -find2x2Det(new float[][]{
                {matrix[1][0],matrix[1][2]},
                {matrix[2][0],matrix[2][2]}});
        float part3 = matrix[0][2] * find2x2Det(new float[][]{
                {matrix[1][0],matrix[1][1]},
                {matrix[2][0],matrix[2][1]}});
        return part1+part2+part3;
    }
    private float find4x4Det(float[][]matrix){
        float part1 = matrix[0][0] * find3x3Det(new float[][]{
                {matrix[1][1],matrix[1][2],matrix[1][3]},
                {matrix[2][1],matrix[2][2],matrix[2][3]},
                {matrix[3][1],matrix[3][2],matrix[3][3]}});
        float part2 = matrix[0][1] * - find3x3Det(new float[][]{
                {matrix[1][0],matrix[1][2],matrix[1][3]},
                {matrix[2][0],matrix[2][2],matrix[2][3]},
                {matrix[3][0],matrix[3][2],matrix[3][3]}});
        float part3 = matrix[0][2] * find3x3Det(new float[][]{
                {matrix[1][0],matrix[1][1],matrix[1][3]},
                {matrix[2][0],matrix[2][1],matrix[2][3]},
                {matrix[3][0],matrix[3][1],matrix[3][3]}});
        float part4 = matrix[0][3] * - find3x3Det(new float[][]{
                {matrix[1][0],matrix[1][1],matrix[1][2]},
                {matrix[2][0],matrix[2][1],matrix[2][2]},
                {matrix[3][0],matrix[3][1],matrix[3][2]}});
        return part1 + part2 + part3 + part4;
    }
    private float LU(float [][] matrix){
        //need to be implemented
        return -1;
    }
    public float findDet(float[][]matrix){
        if (matrix.length!=matrix[0].length){
            return -1;
        }
        if (matrix.length==1){
            return matrix[0][0];
        }
        if (matrix.length==2){
            return find2x2Det(matrix);
        }
        if (matrix.length==3){
            find3x3Det(matrix);
        }
        if (matrix.length==4){
            return find4x4Det(matrix);
        }
        return LU(matrix);
    }
}
