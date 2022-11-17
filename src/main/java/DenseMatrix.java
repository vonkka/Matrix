import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.concurrent.ThreadLocalRandom;

public class DenseMatrix implements Matrix{
    private final double DELTA = 0.001;
    private final int lHash = 2;
    private final int cHash = 3;
    private double hash = 0;
    private double[][] data;
    private int w, h;
    DenseMatrix(){
        this.data = null;
        this.w = 0;
        this.h = 0;
    }
    DenseMatrix(int height, int width) {
        this.h = height;
        this.w = width;
        this.data = new double[height][width];
    }
    DenseMatrix(double[][] arr) {
        if (arr != null) {
            this.h = arr.length;
            this.w = arr[0].length;
            this.data = new double[this.h][this.w];
            for (int i = 0; i < this.h; ++i) {
                for (int j = 0; j < this.w; ++j) {
                    this.data[i][j] = arr[i][j];
                    this.hash += arr[i][j] * Math.pow(lHash, i) * Math.pow(cHash, j);
                }
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
            if ((Double.isInfinite(this.hash) && Double.isInfinite(((Matrix) m).getHash()))
                || (Math.abs(this.hash - ((Matrix) m).getHash()) < DELTA)) {
                if (this.h == ((Matrix) m).getH() && this.w == ((Matrix) m).getW()) {
                    for (int i = 0; i < this.h; ++i) {
                        for (int j = 0; j < this.w; ++j) {
                            if (Math.abs(this.data[i][j] - ((Matrix) m).getElem(i, j)) > DELTA) {
                                System.out.println("Elements on place [" + i + ", " + j + "] - " +
                                        this.data[i][j] + " and " + ((Matrix) m).getElem(i, j) + " are not equal");
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
    public void display() {
        if (this.data != null) {
            int[] columnWidth = new int[this.w];
            int[][] elemLength = new int[this.h][this.w];
            for (int i = 0; i < this.h; ++i) {
                for (int j = 0; j < this.w; ++j) {
                    int k = Double.toString(this.data[i][j]).length();
                    elemLength[i][j] = k;
                    columnWidth[j] = Math.max(columnWidth[j], k);
                }
            }
            for (int i = 0; i < this.h; ++i) {
                for (int j = 0; j < this.w; ++j) {
                    System.out.print(this.data[i][j]);
                    for (int n = elemLength[i][j]; n < columnWidth[j] + 2; ++n) System.out.print(' ');
                }
                System.out.println();
            }
        }
        else System.out.println("Empty matrix");
    }
    @Override
    public String toString() {
        this.display();
        return "Height - " + this.h + ", width - " + this.w + "\nHash: " + this.hash;
    }
    public int getH() {
        return this.h;
    }
    public int getW() {
        return this.w;
    }
    public double getElem(int i, int j){
        if (i < this.h && j < this.w) {
            return this.data[i][j];
        }
        return 0;
    }
    public double getHash() {
        return hash;
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
                res[i][j] = this.data[i][j];
            }
        }
        return res;
    }
    void addElem(double elem, int i, int j) {
        if (i >= 0 && j >= 0) {
            if (i <= this.h && j <= this.w) {
                this.data[i][j] = elem;
                this.hash += elem * Math.pow(lHash, i) * Math.pow(cHash, j);
            }
        }
    }
    public DenseMatrix negMatrix() {
        if (this.data != null) {
            DenseMatrix res = new DenseMatrix(this.h, this.w);
            res.hash -= this.hash;
            for (int i = 0; i < this.h; ++i) {
                for (int j = 0; j < this.w; ++j) {
                    res.data[i][j] = -this.data[i][j];
                }
            }
            return res;
        }
        return this;
    }
    public DenseMatrix transposedMatrix() {
        if (this.data != null) {
            DenseMatrix res = new DenseMatrix(this.w, this.h);
            for (int i = 0; i < this.h; ++i) {
                for (int j = 0; j < this.w; ++j) {
                    res.addElem(this.data[i][j], j, i);
                }
            }
            return res;
        }
        return this;
    }
    DenseMatrix expandMatrix(int newh, int neww) {
        if (newh >= this.h && neww >= this.w) {
            DenseMatrix res = new DenseMatrix(newh, neww);
            res.hash = this.hash;
            for (int i = 0; i < this.h; ++i) {
                System.arraycopy(this.data[i], 0, res.data[i], 0, this.w);
            }
            return res;
        }
        return this;
    }
    // Copies data from existing matrix to the new one
    // not including ends
    public DenseMatrix copy(int begh, int endh, int begw, int endw) {
        if (begh >= 0 && begw >= 0) {
            if (endh - begh > 0 && endw - begw > 0 && this.data != null) {
                int toh = Math.min(this.h, endh);
                int tow = Math.min(this.w, endw);
                DenseMatrix res = new DenseMatrix(toh - begh, tow - begw);
                for (int i = begh; i < toh; ++i) {
                    for (int j = begw; j < tow; ++j) {
                        res.data[i - begh][j - begw] = this.data[i][j];
                        res.hash += res.data[i - begh][j - begw] * Math.pow(lHash, i - begh) * Math.pow(cHash, j - begw);
                    }
                }
                return res;
            }
        }
        return new DenseMatrix();
    }
    // Copies data to existing matrix
    void insert(DenseMatrix m, int begh, int endh, int begw, int endw) {
        if (begh >= 0 && begw >= 0) {
            if (endh - begh > 0 && endw - begw > 0 && m.data != null) {
                int toh = Math.min(this.h, endh);
                int tow = Math.min(this.w, endw);
                for (int i = begh; i < toh; ++i) {
                    for (int j = begw; j < tow; ++j) {
                        this.hash += (m.data[i % m.h][j % m.w] - this.data[i][j]) * Math.pow(lHash, i) * Math.pow(cHash, j);
                        this.data[i][j] = m.data[i % m.h][j % m.w];
                    }
                }
            }
        }
    }

    private DenseMatrix add(DenseMatrix m) {
        if (m != null) {
            if (this.data == null) return m;
            if (m.data == null) return this;
            int toh = Math.min(this.h, m.h);
            int tow = Math.min(this.w, m.w);
            DenseMatrix res = new DenseMatrix(Math.max(this.h, m.h), Math.max(this.w, m.w));
            res.insert(this,0, this.h, 0, this.w);
            for (int i = 0; i < toh; ++i) {
                for (int j = 0; j < tow; ++j) {
                    res.data[i][j] += m.data[i][j];
                    res.hash += m.data[i][j] * Math.pow(lHash, i) * Math.pow(cHash, j);
                }
            }
            for (int i = toh; i < m.h; ++i) {
                for (int j = 0; j < m.w; ++j) {
                    res.data[i][j] = m.data[i][j];
                    res.hash += m.data[i][j] * Math.pow(lHash, i) * Math.pow(cHash, j);
                }
            }
            for (int i = tow; i < m.w; ++i) {
                for (int j = 0; j < m.h; ++j) {
                    res.data[j][i] = m.data[j][i];
                    res.hash += m.data[j][i] * Math.pow(lHash, j) * Math.pow(cHash, i);
                }
            }
            return res;
        }
        return this;
    }
    private DenseMatrix sub(DenseMatrix m) {
        if (m != null) {
            if (this.data == null) return m.negMatrix();
            if (m.data == null) return this;
            int toh = Math.min(this.h, m.h);
            int tow = Math.min(this.w, m.w);
            DenseMatrix res = new DenseMatrix(Math.max(this.h, m.h), Math.max(this.w, m.w));
            res.insert(this,0, this.h, 0, this.w);
            for (int i = 0; i < toh; ++i) {
                for (int j = 0; j < tow; ++j) {
                    res.data[i][j] -= m.data[i][j];
                    res.hash -= m.data[i][j] * Math.pow(lHash, i) * Math.pow(cHash, j);
                }
            }
            for (int i = toh; i < m.h; ++i) {
                for (int j = 0; j < m.w; ++j) {
                    res.data[i][j] -= m.data[i][j];
                    res.hash -= m.data[i][j] * Math.pow(lHash, i) * Math.pow(cHash, j);
                }
            }
            for (int i = tow; i < m.w; ++i) {
                for (int j = 0; j < m.h; ++j) {
                    res.data[j][i] -= m.data[j][i];
                    res.hash -= m.data[j][i] * Math.pow(lHash, j) * Math.pow(cHash, i);
                }
            }
            return res;
        }
        return this;
    }
    private DenseMatrix mulSq(DenseMatrix m) {
        DenseMatrix res = new DenseMatrix();
        if (m != null) {
            if (this.data != null && m.data != null) {
                // determine sizes of 2^p x 2^p separated matrices
                int k = 0, n = 0;
                while ((1 << k) < this.h || (1 << k) < this.w) ++k;
                while ((1 << n) < m.h || (1 << n) < m.w) ++n;
                int p = Math.max(k, n) - 1;
                res = new DenseMatrix(this.h, m.w);
                if (p > 11) {
                    int partition = (int) Math.pow(2, p);
                    // "this" matrix separation
                    DenseMatrix a1 = this.copy(0, partition, 0, partition);
                    DenseMatrix a2 = this.copy(0, partition, partition, this.w);
                    DenseMatrix a3 = this.copy(partition, this.h, 0, partition);
                    DenseMatrix a4 = this.copy(partition, this.h, partition, this.w);
                    // "m" matrix separation
                    DenseMatrix b1 = m.copy(0, partition, 0, partition);
                    DenseMatrix b3 = m.copy(0, partition, partition, m.w);
                    DenseMatrix b2 = m.copy(partition, m.h, 0, partition);
                    DenseMatrix b4 = m.copy(partition, m.h, partition, m.w);
                    // supporting matrices
                    DenseMatrix a1b1 = a1.mulSq(b1);
                    DenseMatrix u = ((a3.sub(a1))).mulSq(b3.sub(b4));
                    DenseMatrix v = a3.add(a4).mulSq(b3.sub(b1));
                    DenseMatrix w = a1b1.add((a3.add(a4).sub(a1)).mulSq(b1.add(b4).sub(b3)));
                    // getting the result matrix
                    res.insert(a1b1.add(a2.mulSq(b2)), 0, partition, 0, partition);
                    res.insert((w.add(v).add((a1.add(a2).sub(a3).sub(a4)).mulSq(b4))), 0, partition, partition, res.w);
                    res.insert((w.add(u).add(a4.mulSq(b2.add(b3).sub(b1).sub(b4)))), partition, res.h, 0, partition);
                    res.insert(w.add(u).add(v), partition, res.h, partition, res.w);
                }
                else {
                    int toh = Math.min(this.w, m.h);
                    DenseMatrix transposed = m.transposedMatrix();
                    for (k = 0; k < res.h; ++k) {
                        for (int i = 0; i < res.w; ++i) {
                            for (int j = 0; j < toh; ++j) {
                                res.data[k][i] += this.data[k][j] * transposed.data[i][j];
                            }
                            res.hash += res.data[k][i] * Math.pow(lHash, k) * Math.pow(cHash, i);
                        }
                    }
                }
                return res;
            }
        }
        res.data = null;
        return res;
    }
    public Matrix mul(Matrix m){
        if (m instanceof DenseMatrix) {
            if (this.w == m.getH()) {
                return this.mulSq((DenseMatrix) m);
            }
        }
        else if (m instanceof SparseMatrix) {
            if (this.w == m.getH()) {
                return (m.transposedMatrix()).mul(this.transposedMatrix()).transposedMatrix();
            }
        }
        return null;
    }
    public boolean isSparse() {
        if (this.h > 0 && this.w > 0) {
            int nonZeroes = 0;
            for (int i = 0; i < this.h; ++i) {
                for (int j = 0; j < this.w; ++j) {
                    if (this.getElem(i, j) != 0) ++nonZeroes;
                }
            }
            return ((float) nonZeroes / (this.h * this.w)) < 0.2;
        }
        return false;
    }
    public void writeMatrix(String fName) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(fName));
        for (int i = 0; i < this.h; ++i) {
            for (double x : this.data[i]) {
                out.write(Double.toString(x));
                out.write(" ");
            }
            out.write('\n');
        }
        out.flush();
        out.close();
    }
}
