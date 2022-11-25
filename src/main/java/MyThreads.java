import java.util.HashMap;
import java.util.Map;

public class MyThreads {
    public static class TransposeLineDense implements Runnable{
        int col;
        DenseMatrix m;
        DenseMatrix transposed;

        TransposeLineDense(int col, DenseMatrix m, DenseMatrix transm) {
            this.col = col;
            this.m = m;
            this.transposed = transm;
        }

        @Override
        public void run() {
            for (int i = 0; i < m.getW(); ++i) {
                transposed.addElem(m.getElem(col, i), i, col);
            }
        }
    }
    public static class TransposeLineSparse implements Runnable{
        int col;
        SparseMatrix m;
        SparseMatrix transposed;

        TransposeLineSparse(int col, SparseMatrix m, SparseMatrix transposed) {
            this.col = col;
            this.m = m;
            this.transposed = transposed;
        }

        @Override
        public void run() {
            for (Map.Entry<Integer, Double> elem: m.getMapData().get(col).entrySet()){
                this.transposed.addElem(elem.getValue(), elem.getKey(), col);
            }
        }
    }
    public static class MulDense implements Runnable {
        int column;
        Matrix m;
        Matrix transposed;
        Matrix res;

        MulDense(int c, Matrix m1, Matrix m2, Matrix res) {
            this.column = c;
            this.m = m1;
            this.transposed = m2;
            this.res = res;
        }
        @Override
        public void run() {
            for (int i = 0; i < m.getH(); ++i) {
                double elem = 0;
                for (int j = 0; j < m.getW(); ++j) {
                    elem += m.getElem(i, j) * transposed.getElem(column, j);
                }
                this.res.addElem(elem, i, column);
            }
        }
    }
    public static class MulSparse implements Runnable {
        int column;
        SparseMatrix m;
        Matrix transposed;
        SparseMatrix res;

        MulSparse(int c, SparseMatrix m1, Matrix m2, SparseMatrix res) {
            this.column = c;
            this.m = m1;
            this.transposed = m2;
            this.res = res;
        }
        @Override
        public void run() {
            for (Map.Entry<Integer, HashMap<Integer, Double>> line: m.getMapData().entrySet()) {
                double sum = 0;
                for (Map.Entry<Integer, Double> elem: line.getValue().entrySet()) {
                    sum += elem.getValue() * transposed.getElem(column, elem.getKey());
                }
                this.res.addElem(sum, line.getKey(), column);
            }
        }
    }

//    public static class GetCol extends Thread{
//        int col;
//        Matrix m1;
//        Matrix m2;
//        Matrix transposed;
//        Matrix res;
//
//        GetCol(int col, Matrix m1, Matrix m2, Matrix transposed, Matrix res) {
//            this.col = col;
//            this.m1 = m1;
//            this.m2 = m2;
//            this.transposed = transposed;
//            this.res = res;
//        }
//
//        @Override
//        public void run() {
//            MyThreads.TransposeCol tr = new MyThreads.TransposeCol(col, m2, transposed);
//            tr.start();
//            try {
//                tr.join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            for (int i = 0; i < m1.getH(); ++i) {
//                MyThreads.MulDense mul = new MyThreads.MulDense(i, m1, transposed, res);
//                mul.start();
//                try {
//                    mul.join();
//                    this.res = mul.getRes();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//
//            }
//        }
//        Matrix getRes(){
//            return this.res;
//        }
//    }
}
