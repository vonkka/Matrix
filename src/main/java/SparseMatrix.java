import java.util.*;

public class SparseMatrix implements Matrix {
    private final double DELTA = 0.001;
    private final int lHash = 1;
    private final int cHash = 3;
    private double hash = 0;
    private HashMap<Integer, HashMap<Integer, Double>> data;
    private int w, h;

    SparseMatrix(){
        this.data = null;
        this.w = 0;
        this.h = 0;
    }
    SparseMatrix(int height, int width) {
        this.h = height;
        this.w = width;
        this.data = new HashMap<>(height + 1, 1);
    }
    SparseMatrix(double[][] arr) {
        if (arr != null) {
            this.h = arr.length;
            this.w = arr[0].length;
            this.data = new HashMap<>();
            for (int i = 0; i < this.h; ++i) {
                HashMap<Integer, Double> line = new HashMap<>();
                for (int j = 0; j < this.w; ++j) {
                    line.put(j ,arr[i][j]);
                    this.hash += arr[i][j] * Math.pow(lHash, i) * Math.pow(cHash, j);
                }
                this.data.put(i, line);
            }
        }
        else {
            this.data = null;
            this.w = 0;
            this.h = 0;
        }
    }
    @Override
    public boolean equals(Object m) {
        if (m instanceof Matrix) {
            if (m instanceof SparseMatrix) {
                if (Math.abs(this.hash - ((SparseMatrix) m).hash) < DELTA) {
                    if (this.h == ((SparseMatrix) m).h && this.w == ((SparseMatrix) m).w) {
                        for (int i = 0; i < this.h; ++i) {
                            for (int j = 0; j < this.w; ++j) {
                                if (Math.abs(this.data.get(i).get(j) - ((SparseMatrix) m).data.get(i).get(j)) > DELTA) {
                                    System.out.println("Elements on place [" + i + ", " + j + "] - " +
                                            this.data.get(i).get(j) + " and " + ((SparseMatrix) m).data.get(i).get(j) + " are not equal");
                                    return false;
                                }
                            }
                        }
                        return true;
                    }
                }
            }
            else if (m instanceof DenseMatrix) {
                if (Math.abs(this.hash - ((DenseMatrix) m).getHash()) < DELTA) {
                    if (this.h == ((DenseMatrix) m).getH() && this.w == ((DenseMatrix) m).getW()) {
                        for (int i = 0; i < this.h; ++i) {
                            for (int j = 0; j < this.w; ++j) {
                                if (Math.abs(this.data.get(i).get(j) - ((DenseMatrix) m).getElem(i, j)) > DELTA) {
                                    System.out.println("Elements on place [" + i + ", " + j + "] - " +
                                            this.data.get(i).get(j) + " and " + ((DenseMatrix) m).getElem(i, j) + " are not equal");
                                    return false;
                                }
                            }
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
    @Override
    public String toString() {
       this.display();
       return "Height - " + this.h + ", width - " + this.w + "\nHash: " + this.hash;
    }
    public void display() {
        if (this.data != null) {
            int[] columnWidth = new int[this.w];
            int[][] elemLength = new int[this.h][this.w];
            for (int i = 0; i < this.h; ++i) {
                for (int j = 0; j < this.w; ++j) {
                    int k = Double.toString(this.data.get(i).get(j)).length();
                    elemLength[i][j] = k;
                    columnWidth[j] = Math.max(columnWidth[j], k);
                }
            }
            for (int i = 0; i < this.h; ++i) {
                for (int j = 0; j < this.w; ++j) {
                    System.out.print(this.data.get(i).get(j));
                    for (int n = elemLength[i][j]; n < columnWidth[j] + 2; ++n) System.out.print(' ');
                }
                System.out.println();
            }
        }
        else System.out.println("Empty matrix");
    }
    public int getH() {
        return this.h;
    }
    public int getW() {
        return this.w;
    }
    public double getElem(int i, int j){
        if (i < this.h && j < this.w) {
            return this.data.get(i).get(j);
        }
        return 0;
    }
    double getHash() {
        return hash;
    }
    int getlHash() {
        return this.lHash;
    }
    int getcHash() {
        return this.cHash;
    }
    void setH(int height) {
        this.h = height;
    }
    void setW(int width) {
        this.w = width;
    }
    void addHash(double num) {
        this.hash += num;
    }
    public double[][] getData() {
        double[][] res = new double[this.h][this.w];
        for (int i = 0; i < this.h; ++i) {
            for (int j = 0; j < this.w; ++j) {
                res[i][j] = this.data.get(i).get(j);
            }
        }
        return res;
    }
    void addElem(double elem, int i, int j) {
        if (i >= 0 && j >= 0) {
            if (!this.data.containsKey(i)) this.data.put(i, new HashMap<Integer, Double>());
            if (i <= this.h && j <= this.w) {
                this.data.get(i).put(j, elem);
                this.hash += elem * Math.pow(lHash, i) * Math.pow(cHash, j);
            }
        }
    }
    public SparseMatrix negMatrix() {
        if (this.data != null) {
            SparseMatrix res = new SparseMatrix(this.h, this.w);
            res.hash -= this.hash;
            for (int i = 0; i < this.h; ++i) {
                HashMap<Integer, Double> line = new HashMap<>();
                for (int j = 0; j < this.w; ++j) {
                    line.put(j, -this.data.get(i).get(j));
                }
                res.data.put(i, line);
            }
            return res;
        }
        return this;
    }
    public SparseMatrix transposedMatrix() {
        if (this.data != null) {
            SparseMatrix res = new SparseMatrix(this.w, this.h);
            for (int i = 0; i < res.h; ++i) {
                HashMap<Integer, Double> line = new HashMap<>();
                for (int j = 0; j < res.w; ++j) {
                    double elem = this.getElem(j, i);
                    line.put(j, elem);
                    res.hash += elem * Math.pow(lHash, i) * Math.pow(cHash, j);
                }
                res.data.put(i, line);
            }
            return res;
        }
        return this;
    }
    // Copies data from existing matrix to the new one
    // not including ends
    public SparseMatrix copy(int begh, int endh, int begw, int endw) {
        if (begh >= 0 && begw >= 0) {
            if (endh - begh > 0 && endw - begw > 0 && this.data != null) {
                int toh = Math.min(this.h, endh);
                int tow = Math.min(this.w, endw);
                SparseMatrix res = new SparseMatrix(toh - begh, tow - begw);
                for (int i = begh; i < toh; ++i) {
                    HashMap<Integer, Double> line = new HashMap<>();
                    for (int j = begw; j < tow; ++j) {
                        double elem = this.getElem(i, j);
                        line.put(j - begw, elem);
                        res.hash += elem * Math.pow(lHash, i - begh) * Math.pow(cHash, j - begw);
                    }
                    res.data.put(i - begh, line);
                }
                return res;
            }
        }
        return new SparseMatrix();
    }
    // Copies data to existing matrix
    void insert(SparseMatrix m, int begh, int endh, int begw, int endw) {
        if (begh >= 0 && begw >= 0) {
            if (endh - begh > 0 && endw - begw > 0 && m.data != null) {
                int toh = Math.min(this.h, endh);
                int tow = Math.min(this.w, endw);
                for (int i = begh; i < toh; ++i) {
                    HashMap<Integer, Double> line = new HashMap<>();
                    for (int j = begw; j < tow; ++j) {
                        double elem = m.data.get(i % m.h).get(j % m.w);
                        this.hash += (elem - this.data.get(i).get(j)) * Math.pow(lHash, i) * Math.pow(cHash, j); // If evth is ok with hash
                        line.put(j, elem);
                    }
                    this.data.put(i, line);
                }
            }
        }
    }
    public Matrix mul(Matrix m) {
        if (this.h > 0 && this.w > 0 && m.getW() > 0 && this.w == m.getH()) {
            SparseMatrix res = new SparseMatrix(this.h, m.getW());
            Matrix mTransposed = m.transposedMatrix();
            for (int i = 0; i < res.h; ++i) {
                HashMap<Integer, Double> line = new HashMap<>();
                for (int j = 0; j < res.w; ++j) {
                    double sum = 0;
                    for (int k = 0; k < this.w; ++k) {
                        sum += this.getElem(i, k) * mTransposed.getElem(j, k);
                    }
                    line.put(j, sum);
                    res.hash += sum * Math.pow(lHash, i) * Math.pow(cHash, j);
                }
                res.data.put(i, line);
            }
            return res;
        }
        return null;
    }

    public static void main(String[] args) {
        double[][] mat = {{322, 2},
                {24.214, 32133},
                {3, 0}};
        SparseMatrix m = new SparseMatrix(mat);
        m.addElem(34, 2, 1);
//        SparseMatrix m2 = m.copy(1, 3, 0, 2);
//        System.out.println(m.toString());
//        System.out.println(m2.toString());
//        m2.insert(m, 0, 3, 0, 2);
//        System.out.println(m2.toString());
        SparseMatrix negm = m.transposedMatrix();
        DenseMatrix shitty = new DenseMatrix(negm.getData());
        System.out.println(negm.toString());
        System.out.println(m.mul(negm).toString());
        System.out.println(shitty.toString());
        System.out.println(m.mul(shitty).toString());
    }
}
