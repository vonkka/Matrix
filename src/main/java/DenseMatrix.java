import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class DenseMatrix implements Matrix{
    private final double DELTA = 0.001;
    private final int lHash = 2;
    private final int cHash = 3;
    private long hash = 0;
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
                    this.hash += (long) arr[i][j] * Math.pow(lHash, i) * Math.pow(cHash, j);
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
                || (this.hash == ((Matrix) m).getHash())) {
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
//        this.display();
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
        this.hash += (long)num;
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
    synchronized public void addElem(double elem, int i, int j) {
        if (i >= 0 && j >= 0) {
            if (i <= this.h && j <= this.w) {
                this.data[i][j] = elem;
                this.hash += (long)elem * Math.pow(lHash, i) * Math.pow(cHash, j);
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
                    res.addElem(this.getElem(i, j), j, i);
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
                        res.hash += (long)res.data[i - begh][j - begw] * Math.pow(lHash, i - begh) * Math.pow(cHash, j - begw);
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
                        this.hash += (long)(m.data[i % m.h][j % m.w] - this.data[i][j]) * Math.pow(lHash, i) * Math.pow(cHash, j);
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
                    res.hash += (long)m.data[i][j] * Math.pow(lHash, i) * Math.pow(cHash, j);
                }
            }
            for (int i = toh; i < m.h; ++i) {
                for (int j = 0; j < m.w; ++j) {
                    res.data[i][j] = m.data[i][j];
                    res.hash += (long)m.data[i][j] * Math.pow(lHash, i) * Math.pow(cHash, j);
                }
            }
            for (int i = tow; i < m.w; ++i) {
                for (int j = 0; j < m.h; ++j) {
                    res.data[j][i] = m.data[j][i];
                    res.hash +=(long) m.data[j][i] * Math.pow(lHash, j) * Math.pow(cHash, i);
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
                    res.hash -= (long)m.data[i][j] * Math.pow(lHash, i) * Math.pow(cHash, j);
                }
            }
            for (int i = toh; i < m.h; ++i) {
                for (int j = 0; j < m.w; ++j) {
                    res.data[i][j] -= m.data[i][j];
                    res.hash -= (long)m.data[i][j] * Math.pow(lHash, i) * Math.pow(cHash, j);
                }
            }
            for (int i = tow; i < m.w; ++i) {
                for (int j = 0; j < m.h; ++j) {
                    res.data[j][i] -= m.data[j][i];
                    res.hash -= (long)m.data[j][i] * Math.pow(lHash, j) * Math.pow(cHash, i);
                }
            }
            return res;
        }
        return this;
    }
    private DenseMatrix mulSq(DenseMatrix m) {
        if (m != null) {
            if (this.data != null && m.data != null) {
                // determine sizes of 2^p x 2^p separated matrices
//                int k = 0, n = 0;
//                while ((1 << k) < this.h || (1 << k) < this.w) ++k;
//                while ((1 << n) < m.h || (1 << n) < m.w) ++n;
//                int p = Math.max(k, n) - 1;
                DenseMatrix res = new DenseMatrix(this.h, m.w);
//                if (p > 11) {
//                    int partition = (int) Math.pow(2, p);
//                    // "this" matrix separation
//                    DenseMatrix a1 = this.copy(0, partition, 0, partition);
//                    DenseMatrix a2 = this.copy(0, partition, partition, this.w);
//                    DenseMatrix a3 = this.copy(partition, this.h, 0, partition);
//                    DenseMatrix a4 = this.copy(partition, this.h, partition, this.w);
//                    // "m" matrix separation
//                    DenseMatrix b1 = m.copy(0, partition, 0, partition);
//                    DenseMatrix b3 = m.copy(0, partition, partition, m.w);
//                    DenseMatrix b2 = m.copy(partition, m.h, 0, partition);
//                    DenseMatrix b4 = m.copy(partition, m.h, partition, m.w);
//                    // supporting matrices
//                    DenseMatrix a1b1 = a1.mulSq(b1);
//                    DenseMatrix u = ((a3.sub(a1))).mulSq(b3.sub(b4));
//                    DenseMatrix v = a3.add(a4).mulSq(b3.sub(b1));
//                    DenseMatrix w = a1b1.add((a3.add(a4).sub(a1)).mulSq(b1.add(b4).sub(b3)));
//                    // getting the result matrix
//                    res.insert(a1b1.add(a2.mulSq(b2)), 0, partition, 0, partition);
//                    res.insert((w.add(v).add((a1.add(a2).sub(a3).sub(a4)).mulSq(b4))), 0, partition, partition, res.w);
//                    res.insert((w.add(u).add(a4.mulSq(b2.add(b3).sub(b1).sub(b4)))), partition, res.h, 0, partition);
//                    res.insert(w.add(u).add(v), partition, res.h, partition, res.w);
//                }
//                else {
                    int toh = Math.min(this.w, m.h);
                    DenseMatrix transposed = m.transposedMatrix();
                    for (int k = 0; k < res.h; ++k) {
                        for (int i = 0; i < res.w; ++i) {
                            for (int j = 0; j < toh; ++j) {
                                res.data[k][i] += this.data[k][j] * transposed.data[i][j];
                            }
                            res.hash += (long)res.data[k][i] * Math.pow(lHash, k) * Math.pow(cHash, i);
                        }
                    }
//                }
                return res;
            }
        }
        return new DenseMatrix();
    }
//    public DenseMatrix mulThreads(DenseMatrix m) throws InterruptedException {
//        int maxThreads = 4;
//
//        DenseMatrix res = new DenseMatrix(this.h, m.w);
//        DenseMatrix transposed = new DenseMatrix(m.w, m.h);
//        ExecutorService threads = Executors.newFixedThreadPool(maxThreads);
//        List<Callable<Object>> todo1 = new ArrayList<>();
//        List<Callable<Object>> todo2 = new ArrayList<>();
//        for (int k = 0; k < m.h; ++k) {
//            Thread col1 = new Thread(new MyThreads.TransposeLine(k, m, transposed));
//            todo1.add(Executors.callable(col1));
//        }
//        threads.invokeAll(todo1);
//        for (int k = 0; k < transposed.h; ++k) {
//            Thread mul = new Thread(new MyThreads.MulDense(k, this, transposed, res));
//            todo2.add(Executors.callable(mul));
//        }
//        threads.invokeAll(todo2);
//        threads.shutdown();
//        return res;
//    }
    public Matrix mulThreads(Matrix m) throws InterruptedException {
        if (m instanceof DenseMatrix) return mulThreadsDD((DenseMatrix) m);
        else if (m instanceof SparseMatrix){
            int maxThreads = 5;

            DenseMatrix transposed1 = new DenseMatrix(this.w, this.h);
            SparseMatrix transposed2 = new SparseMatrix(m.getW(), m.getH());
            Queue<Thread> threads = new LinkedBlockingQueue<>(maxThreads);
            for (int k = 0; k < this.h; ++k) {
                Thread col = new Thread(new MyThreads.TransposeLineDense(k, this, transposed1));
                if (threads.size() == maxThreads) {
                    threads.poll().join();
                    for (Thread t : threads) {
                        if (!t.isAlive()) threads.remove(t);
                    }
                }
                col.start();
                threads.add(col);
            }
            for (int k = 0; k < m.getH(); ++k) {
                Thread col = new Thread(new MyThreads.TransposeLineSparse(k, (SparseMatrix) m, transposed2));
                if (threads.size() == maxThreads) {
                    threads.poll().join();
                    for (Thread t : threads) {
                        if (!t.isAlive()) threads.remove(t);
                    }
                }
                col.start();
                threads.add(col);
            }
            for (Thread t : threads) {
                t.join();
                threads.remove(t);
            }
            SparseMatrix resTrans = (SparseMatrix)transposed2.mulThreads(transposed1);
            SparseMatrix res = new SparseMatrix(resTrans.getW(), resTrans.getH());
            for (int k = 0; k < resTrans.getH(); ++k) {
                Thread col = new Thread(new MyThreads.TransposeLineSparse(k, resTrans, res));
                if (threads.size() == maxThreads) {
                    threads.poll().join();
                    for (Thread t : threads) {
                        if (!t.isAlive()) threads.remove(t);
                    }
                }
                col.start();
                threads.add(col);
            }
            for (Thread t : threads) {
                t.join();
                threads.remove(t);
            }
            return res;
        }
        return null;
    }
    private DenseMatrix mulThreadsDD(DenseMatrix m) throws InterruptedException {
        if (this.w == m.h) {
            int maxThreads = 5;

            DenseMatrix res = new DenseMatrix(this.h, m.w);
            DenseMatrix transposed = new DenseMatrix(m.w, m.h);
            Queue<Thread> threads = new LinkedBlockingQueue<>(maxThreads);
            for (int k = 0; k < m.h; ++k) {
                Thread col = new Thread(new MyThreads.TransposeLineDense(k, m, transposed));
                if (threads.size() == maxThreads) {
                    threads.poll().join();
                    for (Thread t : threads) {
                        if (!t.isAlive()) threads.remove(t);
                    }
                }
                col.start();
                threads.add(col);
            }
            for (Thread t : threads) {
                t.join();
                threads.remove(t);
            }
            for (int k = 0; k < transposed.h; ++k) {
                Thread mul = new Thread(new MyThreads.MulDense(k, this, transposed, res));
                if (threads.size() == maxThreads) {
                    threads.remove().join();
                    for (Thread t : threads) {
                        if (!t.isAlive()) threads.remove(t);
                    }
                }
                mul.start();
                threads.add(mul);
            }
            for (Thread t : threads) {
                t.join();
                threads.remove(t);
            }
            return res;
        }
        return new DenseMatrix();
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
    void generateDense(int height, int width, String f1, String f2, String fres) throws IOException{
        SparseMatrix m1 = new SparseMatrix(height, width);
        for (int i = 0; i < m1.getH(); ++i) {
            for (int j = 0; j < m1.getW(); ++j) {
                m1.addElem(ThreadLocalRandom.current().nextDouble(), i, j);
            }
        }
        SparseMatrix m2 = new SparseMatrix(width, height);
        for (int i = 0; i < m2.getH(); ++i) {
            for (int j = 0; j < m2.getW(); ++j) {
                m2.addElem(ThreadLocalRandom.current().nextDouble(), i, j);
            }
        }
        Matrix res = m1.mul(m2);
        m1.writeMatrix(f1);
        m2.writeMatrix(f2);
        res.writeMatrix(fres);
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

    public static void main(String[] args) throws IOException, InterruptedException {
        WorkWithFiles wwf = new WorkWithFiles();
        DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix150_2000.txt");
        DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_374.txt");
        DenseMatrix res1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix150_374.txt");
        DenseMatrix m3 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_1.txt");
        DenseMatrix m4 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_2.txt");
        DenseMatrix res2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_Res.txt");
//        DenseMatrix tran = new DenseMatrix(m1.getW(), m1.getH());

        long start1 = System.currentTimeMillis();
        DenseMatrix my = m1.mulThreadsDD(m2);
        DenseMatrix myth = m3.mulThreadsDD(m4);
        long end1 = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();
        DenseMatrix my2 = (DenseMatrix) m1.mul(m2);
        DenseMatrix my3 = (DenseMatrix) m3.mul(m4);
        long end2 = System.currentTimeMillis();
        System.out.println(end1 - start1);
        System.out.println(end2-start2);
        System.out.println(my.equals(res1));
        System.out.println(myth.equals(my3));
        System.out.println(myth);
        System.out.println(my3);



//        for (int i = 0; i < m1.getW(); ++i) {
//            MyThreads.TransposeCol tr = new MyThreads.TransposeCol(i, m1, tran);
//            tr.start();
//            tr.join();
//            tran = (DenseMatrix) tr.transposed;
//        }
//        System.out.println(m1);
//        System.out.println(tran);
//        System.out.println(m1.transposedMatrix());
//        System.out.println(tran.equals(m1.transposedMatrix()));
    }
}
