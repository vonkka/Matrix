import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DenseThreadTests {
    @Test
    public void MulTestQuadr() throws IOException {
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr matrix1.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr matrix2.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr expected.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestBig() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix150_2000.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_374.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix150_374.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestFirstHigherButNarrower() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_11.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestHigherAndWider() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix7_5.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix5_2.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix7_2.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestFirstLowerButWider() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_4.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestFirstLowerAndNarrower() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_4.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_11.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestBigSparse500() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_1.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_2.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_Res.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestBigSparse600() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_1.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_2.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_Res.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestNegative() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg7_5.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg5_2.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg7_2.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    Matrix transposeCol(Matrix m) {
        DenseMatrix res = new DenseMatrix(m.getW(), m.getH());
        for (int i = 0; i < m.getW(); ++i) {
            for (int j = 0; j < m.getH(); ++j) {
                res.addElem(m.getElem(j, i), i, j);
            }
        }
        return res;
    }

    Matrix transposeLine(Matrix m) {
        DenseMatrix res = new DenseMatrix(m.getW(), m.getH());
        for (int i = 0; i < m.getH(); ++i) {
            for (int j = 0; j < m.getW(); ++j) {
                res.addElem(m.getElem(i, j), j, i);
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        WorkWithFiles wwf = new WorkWithFiles();
//        DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr matrix1.txt");
//        DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr matrix2.txt");
//        DenseMatrix m3 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix150_2000.txt");
//        DenseMatrix m4 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_374.txt");
//        DenseMatrix m5 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt");
//        DenseMatrix m6 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
//        DenseMatrix m7 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix7_5.txt");
//        DenseMatrix m8 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix5_2.txt");
//        DenseMatrix m9 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
//        DenseMatrix m10 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt");
//        DenseMatrix m11 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_4.txt");
//        DenseMatrix m12 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
//        DenseMatrix m13 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_1.txt");
//        DenseMatrix m14 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_2.txt");
//        DenseMatrix m15 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_1.txt");
//        DenseMatrix m16 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_2.txt");
//        DenseMatrix m17 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg7_5.txt");
//        DenseMatrix m18 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg5_2.txt");
        DenseMatrix m19 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_1.txt");
        DenseMatrix m20 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_2.txt");
        int th = 0;
        int mul = 0;
        for (int i = 0; i < 5; ++i) {
            long start1 = System.currentTimeMillis();
            m19.mulThreads(m20);
            long end1 = System.currentTimeMillis();
            long start2 = System.currentTimeMillis();

//            m19.mul(m20);
            long end2 = System.currentTimeMillis();
            th += end1 - start1;
            mul += end2 - start2;
        }
        System.out.println(mul);
        System.out.println(th);
        System.out.println((double)mul / th);




//        ThreadsTests a = new ThreadsTests();
//        long col = 0;
//        long line = 0;
//        for (int i = 0; i < 1000; ++i) {
//            long startl = System.currentTimeMillis();
//            a.transposeLine(m3);
//            a.transposeLine(m4);
//            a.transposeLine(m13);
//            a.transposeLine(m14);
//            a.transposeLine(m15);
//            a.transposeLine(m16);
//            long endl = System.currentTimeMillis();
//            long startc = System.currentTimeMillis();
//            a.transposeCol(m3);
//            a.transposeCol(m4);
//            a.transposeCol(m13);
//            a.transposeCol(m14);
//            a.transposeCol(m15);
//            a.transposeCol(m16);
//            long endc = System.currentTimeMillis();
//            col += endc - startc;
//            line += endl - startl;
//        }
//        System.out.println(line);
//        System.out.println(col);
//        System.out.println((double)line / col);
    }
}
