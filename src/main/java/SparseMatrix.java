import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SparseMatrix implements Matrix {
    private final double DELTA = 0.001;
    private final int lHash = 2;
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
        this.data = new HashMap<>();
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
            if ((Double.isInfinite(this.hash) && Double.isInfinite(((Matrix) m).getHash()))
                    || (Math.abs(this.hash - ((Matrix) m).getHash()) < DELTA)) {
                if (this.h == ((Matrix) m).getH() && this.w == ((Matrix) m).getW()) {
                    for (int i = 0; i < this.h; ++i) {
                        for (int j = 0; j < this.w; ++j) {
                            if (Math.abs(this.getElem(i, j) - ((Matrix) m).getElem(i, j)) > DELTA) {
                                System.out.println("Elements on place [" + i + ", " + j + "] - " +
                                        this.data.get(i).get(j) + " and " + ((Matrix) m).getElem(i, j) + " are not equal");
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
    @Override
    public String toString() {
        this.display();
        return "Height - " + this.h + ", width - " + this.w + "\nHash: " + this.hash;
    }
    public void display() {
        if (this.data != null) {
            for (int i = 0; i < this.h; ++i) {
                HashMap<Integer, Double> line = this.data.get(i);
                if (line != null) {
                    System.out.print("Line " + (i + 1) + ": ");
                    for (int j = 0; j < this.w; ++j) {
                        if (line.containsKey(j)) {
                            System.out.print((j  + 1) + ": " + line.get(j) + "  ");
                        }
                    }
                    System.out.println();
                }
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
        try {
            return this.data.get(i).get(j);
        }
        catch (Exception e) {
            return 0;
        }
    }
    public double getHash() {
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
                res[i][j] = this.getElem(i, j);
            }
        }
        return res;
    }
    void addElem(double elem, int i, int j) {
        if (elem != 0) {
            if (i >= 0 && j >= 0 && i < this.h && j < this.w) {
                if (!this.data.containsKey(i)) this.data.put(i, new HashMap<Integer, Double>());
                this.data.get(i).put(j, elem);
                this.hash += elem * Math.pow(lHash, i) * Math.pow(cHash, j);
            }
        }
    }
    public SparseMatrix negMatrix() {
        if (this.data != null) {
            SparseMatrix res = new SparseMatrix(this.h, this.w);
            res.hash -= this.hash;
            for (Map.Entry<Integer, HashMap<Integer, Double>> line: this.data.entrySet()) {
                for (Map.Entry<Integer, Double> elem: line.getValue().entrySet()) {
                    res.addElem(elem.getValue(), line.getKey(), elem.getKey());
                }
            }
            return res;
        }
        return this;
    }
    public SparseMatrix transposedMatrix() {
        if (this.data != null) {
            SparseMatrix res = new SparseMatrix(this.w, this.h);
            for (Map.Entry<Integer, HashMap<Integer, Double>> line: this.data.entrySet()) {
                for (Map.Entry<Integer, Double> elem: line.getValue().entrySet()) {
                    res.addElem(elem.getValue(), elem.getKey(), line.getKey());
                }
            }
            return res;
        }
        return this;
    }
    public SparseMatrix copy(int begh, int endh, int begw, int endw) {
        if (begh >= 0 && begw >= 0) {
            if (endh - begh > 0 && endw - begw > 0 && this.data != null) {
                int toh = Math.min(this.h, endh);
                int tow = Math.min(this.w, endw);
                SparseMatrix res = new SparseMatrix(toh - begh, tow - begw);
                for (Map.Entry<Integer, HashMap<Integer, Double>> line: this.data.entrySet()) {
                    if (line.getKey() >= begh && line.getKey() < toh) {
                        for (Map.Entry<Integer, Double> elem : line.getValue().entrySet()) {
                            if (elem.getKey() >= begw && elem.getKey() < tow) {
                                res.addElem(elem.getValue(), line.getKey(), elem.getKey());
                            }
                        }
                    }
                }
                return res;
            }
        }
        return new SparseMatrix();
    }
    void insert(SparseMatrix m, int begh, int endh, int begw, int endw) {
        if (begh >= 0 && begw >= 0) {
            if (endh - begh > 0 && endw - begw > 0 && m.data != null) {
                int toh = Math.min(this.h, endh);
                int tow = Math.min(this.w, endw);
                for (Map.Entry<Integer, HashMap<Integer, Double>> line: m.data.entrySet()) {
                    if (line.getKey() >= begh && line.getKey() < toh) {
                        for (Map.Entry<Integer, Double> elem : line.getValue().entrySet()) {
                            if (elem.getKey() >= begw && elem.getKey() < tow) {
                                this.hash -= this.getElem(line.getKey(), elem.getKey()) * Math.pow(lHash, line.getKey()) * Math.pow(cHash, elem.getKey());
                                this.addElem(elem.getValue(), line.getKey(), elem.getKey());
                            }
                        }
                    }
                }
            }
        }
    }
    public Matrix mulSS(SparseMatrix m) {
        if (this.h > 0 && this.w > 0 && m.getW() > 0 && this.w == m.getH()) {
            SparseMatrix res = new SparseMatrix(this.h, m.getW());
            SparseMatrix mTransposed = m.transposedMatrix();
            for (Map.Entry<Integer, HashMap<Integer, Double>> line: this.data.entrySet()) {
                for (Map.Entry<Integer, HashMap<Integer, Double>> col: mTransposed.data.entrySet()) {
                    double sum = 0;
                    for (Map.Entry<Integer, Double> elem : line.getValue().entrySet()) {
                        sum += elem.getValue() * mTransposed.getElem(col.getKey(), elem.getKey());
                    }
                    res.addElem(sum, line.getKey(), col.getKey());
                }
            }
            return res;
        }
        return null;
    }

    public Matrix mulSD(DenseMatrix m) {
        if (this.h > 0 && this.w > 0 && m.getW() > 0 && this.w == m.getH()) {
            SparseMatrix res = new SparseMatrix(this.h, m.getW());
            DenseMatrix mTransposed = m.transposedMatrix();
            for (Map.Entry<Integer, HashMap<Integer, Double>> line: this.data.entrySet()) {
                for (int j = 0; j < res.w; ++j) {
                    double sum = 0;
                    for (Map.Entry<Integer, Double> elem : line.getValue().entrySet()) {
                        sum += elem.getValue() * mTransposed.getElem(j, elem.getKey());
                    }
                    res.addElem(sum, line.getKey(), j);
                }
            }
            return res;
        }
        return null;
    }

    public Matrix mul(Matrix m) {
        if (m instanceof SparseMatrix) {
            return this.mulSS((SparseMatrix) m);
        }
        else if (m instanceof DenseMatrix) {
            return this.mulSD((DenseMatrix) m);
        }
        return null;
    }

    public boolean isSparse() {
        if (this.h > 0 && this.w > 0) {
            int nonZeroes = 0;
            for (HashMap<Integer, Double> line: this.data.values()) {
                nonZeroes += line.size();
            }
            return ((float) nonZeroes / (this.h * this.w)) < 0.2;
        }
        return false;
    }
    public void writeMatrix(String fName) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(fName));
        for (int i = 0; i < this.h; ++i) {
            HashMap<Integer, Double> line = this.data.get(i);
            if (line != null) {
                for (int j = 0; j < this.w; ++j) {
                    if (line.containsKey(j)) out.write(Double.toString(line.get(j)));
                    else out.write("0");
                    out.write(" ");
                }
            }
            else {
                for (int j = 0; j < this.w; ++j) {
                    out.write("0 ");
                }
            }
            out.write('\n');
        }
        out.flush();
        out.close();
    }
    void generateSparse(int height, int width, int values, String f1, String f2, String fres) throws IOException{
        if (values < height * width * 0.2) {
            SparseMatrix m1 = new SparseMatrix(height, width);
            int i1 = 0;
            int j1 = 0;
            for (int k = 0; k < values; ++k) {
                int i = ThreadLocalRandom.current().nextInt(0, height);
                int j = ThreadLocalRandom.current().nextInt(0, width);
                i1 = i;
                j1 = j;
                double elem = ThreadLocalRandom.current().nextDouble();
                m1.addElem(elem, i, j);
            }
            SparseMatrix m2 = new SparseMatrix(height, width);
            for (int k = 0; k < values; ++k) {
                int i = ThreadLocalRandom.current().nextInt(0, height);
                int j = ThreadLocalRandom.current().nextInt(0, width);
                double elem = ThreadLocalRandom.current().nextDouble();
                m2.addElem(elem, i, j);
            }
            m2.addElem(ThreadLocalRandom.current().nextDouble(), j1, i1);
            Matrix res = m1.mul(m2);
            m1.writeMatrix(f1);
            m2.writeMatrix(f2);
            res.writeMatrix(fres);
        }
    }

    public static void main(String[] args) throws IOException {
//        SparseMatrix m = new SparseMatrix();
//        m.generateSparse(5000, 5000, 200,
//                "C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse5000_1.txt",
//                "C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse5000_2.txt",
//                "C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse5000_Res.txt");
    }
}
