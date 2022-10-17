import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

public class denseMatrix {
    private final int lHash = 1;
    private final int cHash = 2;
    private double[][] data;
    private double hash = 0;
    private int w, h;
    denseMatrix(){
        this.data = null;
        this.w = 0;
        this.h = 0;
    }
    denseMatrix(int height, int width) {
        this.h = height;
        this.w = width;
        this.data = new double[height][width];
    }
    denseMatrix(double[][] arr) {
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
        if (m != null) {
            if (this.hash == m.hash) {
                if (this.h == m.h && this.w == m.w) {
                    for (int i = 0; i < this.h; ++i) {
                        for (int j = 0; j < this.w; ++j) {
                            if (BigDecimal.valueOf(this.data[i][j]).setScale(2, RoundingMode.HALF_UP).doubleValue() !=
                                    BigDecimal.valueOf(m.data[i][j]).setScale(2, RoundingMode.HALF_UP).doubleValue()) {
                                System.out.println("Elements on place [" + i + ", " + j + "] - " +
                                        this.data[i][j] + " and " + m.data[i][j] + "are not equal");
                                return 3;
                            }
                        }
                    }
                    return 0;
                }
                return 2;
            }
            return 1;
        }
        return 4;
    }
    public String toString() {
        this.Display();
        return "Height - " + this.h + ", width - " + this.w + "\nHash: " + this.hash;
    }
    int getH() {
        return this.h;
    }
    int getW() {
        return this.w;
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
    double[][] getData() {
        double[][] res = new double[this.h][this.w];
        for (int i = 0; i < this.h; ++i) {
            for (int j = 0; j < this.w; ++j) {
                res[i][j] = this.data[i][j];
            }
        }
        return res;
    }
    denseMatrix negMatrix() {
        if (this.data != null) {
            denseMatrix res = new denseMatrix(this.h, this.w);
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
    denseMatrix transposedMatrix() {
        if (this.data != null) {
            denseMatrix res = new denseMatrix(this.w, this.h);
            for (int i = 0; i < this.h; ++i) {
                for (int j = 0; j < this.w; ++j) {
                    res.data[j][i] = this.data[i][j];
                    res.hash += this.data[i][j] * Math.pow(lHash, j) * Math.pow(cHash, i);
                }
            }
            return res;
        }
        return this;
    }
    void Display() {
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
    int addElem(double elem, int i, int j) {
        if (i >= 0 && j >= 0) {
            if (i <= this.h && j <= this.w) {
                this.data[i][j] = elem;
                this.hash += elem * Math.pow(lHash, i) * Math.pow(cHash, j);
                return 0;
            }
        }
        return 1;
    }
    denseMatrix expandMatrix(int newh, int neww) {
        if (newh >= this.h && neww >= this.w) {
            denseMatrix res = new denseMatrix(newh, neww);
            res.hash = this.hash;
            for (int i = 0; i < this.h; ++i) {
                for (int j = 0; j < this.w; ++j) {
                    res.data[i][j] = this.data[i][j];
                }
            }
            return res;
        }
        return this;
    }
    // Copies data from existing matrix to new one
    // not uncluding ends
    denseMatrix Copy(int begh, int endh, int begw, int endw) {
        if (begh >= 0 && begw >= 0) {
            if (endh - begh > 0 && endw - begw > 0 && this.data != null) {
                int toh = Math.min(this.h, endh);
                int tow = Math.min(this.w, endw);
                denseMatrix res = new denseMatrix(toh - begh, tow - begw);
                for (int i = begh; i < toh; ++i) {
                    for (int j = begw; j < tow; ++j) {
                        res.data[i - begh][j - begw] = this.data[i][j];
                        res.hash += res.data[i - begh][j - begw] * Math.pow(lHash, i - begh) * Math.pow(cHash, j - begw);
                    }
                }
                return res;
            }
        }
        return new denseMatrix();
    }
    // Copies data to existing matrix
    int Insert(denseMatrix m, int begh, int endh, int begw, int endw) {
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
                return 0;
            }
        }
        return 1;
    }

    private denseMatrix Add(denseMatrix m) {
        if (m != null) {
            if (this.data == null) return m;
            if (m.data == null) return this;
            int toh = Math.min(this.h, m.h);
            int tow = Math.min(this.w, m.w);
            denseMatrix res = new denseMatrix(Math.max(this.h, m.h), Math.max(this.w, m.w));
            res.Insert(this,0, this.h, 0, this.w);
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
    private denseMatrix Sub(denseMatrix m) {
        if (m != null) {
            if (this.data == null) return m.negMatrix();
            if (m.data == null) return this;
            int toh = Math.min(this.h, m.h);
            int tow = Math.min(this.w, m.w);
            denseMatrix res = new denseMatrix(Math.max(this.h, m.h), Math.max(this.w, m.w));
            res.Insert(this,0, this.h, 0, this.w);
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
    private denseMatrix MultSq(denseMatrix m) {
        denseMatrix res = new denseMatrix();
        if (m != null) {
            if (this.data != null && m.data != null) {
                // determine the size of 2^p x 2^p separated matrices
                int k = 0, n = 0;
                while (1 << k < this.h || 2 << k < this.w) ++k;
                while (1 << n < m.h || 2 << n < m.w) ++n;
                int p = Math.max(k, n) - 1;
                res = new denseMatrix(this.h, m.w);
                if (p > 2) {
                    int partition = (int) Math.pow(2, p);
                    // "this" matrix separation
                    denseMatrix a1 = this.Copy(0, partition, 0, partition);
                    denseMatrix a2 = this.Copy(0, partition, partition, this.w);
                    denseMatrix a3 = this.Copy(partition, this.h, 0, partition);
                    denseMatrix a4 = this.Copy(partition, this.h, partition, this.w);
                    // "m" matrix separation
                    denseMatrix b1 = m.Copy(0, partition, 0, partition);
                    denseMatrix b3 = m.Copy(0, partition, partition, m.w);
                    denseMatrix b2 = m.Copy(partition, m.h, 0, partition);
                    denseMatrix b4 = m.Copy(partition, m.h, partition, m.w);
                    // supporting matrices
                    denseMatrix a1b1 = a1.MultSq(b1);
                    denseMatrix u = ((a3.Sub(a1))).MultSq(b3.Sub(b4));
                    denseMatrix v = a3.Add(a4).MultSq(b3.Sub(b1));
                    denseMatrix w = a1b1.Add((a3.Add(a4).Sub(a1)).MultSq(b1.Add(b4).Sub(b3)));
                    // getting the result matrix
                    res.Insert(a1b1.Add(a2.MultSq(b2)), 0, partition, 0, partition);
                    res.Insert((w.Add(v).Add((a1.Add(a2).Sub(a3).Sub(a4)).MultSq(b4))), 0, partition, partition, res.w);
                    res.Insert((w.Add(u).Add(a4.MultSq(b2.Add(b3).Sub(b1).Sub(b4)))), partition, res.h, 0, partition);
                    res.Insert(w.Add(u).Add(v), partition, res.h, partition, res.w);
                }
                else {
                    int toh = Math.min(this.w, m.h);
                    denseMatrix transposed = m.transposedMatrix();
                    for (k = 0; k < res.h; ++k) {
                        for (int i = 0; i < res.w; ++i) {
                            for (int j = 0; j < toh; ++j) {
                                res.data[k][i] += this.data[k][j] * transposed.data[i][j];
                                res.hash += this.data[k][j] * transposed.data[i][j] * Math.pow(lHash, k) * Math.pow(cHash, i);
                            }
                        }
                    }
                }
                return res;
            }
        }
        res.data = null;
        return res;
    }
    denseMatrix Mult(denseMatrix m){
        if (this.w == m.h) {
            return this.MultSq(m);
        }
        return null;
    }


    public static void main(String[] args) throws IOException {
        workWFiles wwf = new workWFiles();
//        denseMatrix res1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix7_5.txt");
//        if (res1 != null) System.out.println(res1.toString());
//        System.out.println();
//
//        denseMatrix res2 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix5_2.txt");
//        if (res2 != null) System.out.println(res2.toString());
//        System.out.println();
//        if (res1 != null && res2 != null) {
//            denseMatrix exp = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix7_2.txt");
//            if (exp != null) System.out.println(exp.toString());
//            denseMatrix res4 = res1.Mult(res2);
//            if (res4 != null) System.out.println(res4.toString());
//            else System.out.println("Sizes are not equal");
//            System.out.println(res4.equals(exp));
//        }


//        double[][] mdata1 = res1.getData();
//        double[][] mdata2 = res2.getData();
//        RealMatrix m1 = MatrixUtils.createRealMatrix(mdata1);
//        RealMatrix m2 = MatrixUtils.createRealMatrix(mdata2);
//        RealMatrix p = m1.multiply(m2);
//        double[][] resm = p.getData();
//        for (double[] doubles : resm) {
//            for (double aDouble : doubles) {
//                System.out.print(aDouble + " ");
//            }
//            System.out.println();
//        }

        double[][] mdata1 = new double[3000][150];
        double[][] mdata2 = new double[150][2220];
        for (int i = 0; i < mdata1.length; ++i) {
            mdata1[i] = ThreadLocalRandom.current().doubles(mdata1[i].length).toArray();
        }
        for (int i = 0; i < mdata2.length; ++i) {
            mdata2[i] = ThreadLocalRandom.current().doubles(mdata2[i].length).toArray();
        }
        denseMatrix m1 = new denseMatrix(mdata1);
        denseMatrix m2 = new denseMatrix(mdata2);
        denseMatrix res = m1.Mult(m2);
        for (int i = 0; i < m1.getH(); ++i) {
            for (int j = 0; j < m1.getW(); ++j) {
                System.out.print(m1.data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < m2.getH(); ++i) {
            for (int j = 0; j < m2.getW(); ++j) {
                System.out.print(m2.data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < res.getH(); ++i) {
            for (int j = 0; j < res.getW(); ++j) {
                System.out.print(res.data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
//        RealMatrix m1r = MatrixUtils.createRealMatrix(mdata1);
//        RealMatrix m2r = MatrixUtils.createRealMatrix(mdata2);
//        RealMatrix resr = m1r.multiply(m2r);
//        double[][] resd = res.getData();
//        double[][] resrd = resr.getData();
//        if (resd.length != resrd.length) System.out.println("fdaf");
//        for (int i = 0; i < resd.length; ++i) {
//            for (int j = 0; j < resd[i].length; ++j) {
//                if (resd[i].length != resrd[i].length) System.out.println("bebebe " + i);
//                if (resd[i][j] != resrd[i][j]) System.out.println("Elems " + i + ", " + j + " are different");
//            }
//        }
    }
}
