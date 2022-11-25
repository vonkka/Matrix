import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SparseThreadTests {
    @Test
    public void MulTestQuadrS() throws IOException {
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            SparseMatrix m1 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr matrix1.txt");
            SparseMatrix m2 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr matrix2.txt");
            SparseMatrix res = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr expected.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestFirstHigherButNarrowerS() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            SparseMatrix m1 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt");
            SparseMatrix m2 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            SparseMatrix res = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_11.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestHigherAndWiderS() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            SparseMatrix m1 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix7_5.txt");
            SparseMatrix m2 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix5_2.txt");
            SparseMatrix res = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix7_2.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestFirstLowerButWiderS() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            SparseMatrix m1 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            SparseMatrix m2 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt");
            SparseMatrix res = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_4.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestFirstLowerAndNarrowerS() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            SparseMatrix m1 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_4.txt");
            SparseMatrix m2 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            SparseMatrix res = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_11.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestBigDense2000() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_1.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_2.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_res.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestBigSparse500S() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            SparseMatrix m1 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_1.txt");
            SparseMatrix m2 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_2.txt");
            SparseMatrix res = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_Res.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestBigSparse600S() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            SparseMatrix m1 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_1.txt");
            SparseMatrix m2 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_2.txt");
            SparseMatrix res = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_Res.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestBigSparse10000() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            SparseMatrix m1 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse1.txt");
            SparseMatrix m2 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse2.txt");
            SparseMatrix res = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparseRes.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestBigSparse15000() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            SparseMatrix m1 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse15000_1.txt");
            SparseMatrix m2 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse15000_2.txt");
            SparseMatrix res = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse15000_Res.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestBigSparse5000() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            SparseMatrix m1 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse5000_1.txt");
            SparseMatrix m2 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse5000_2.txt");
            SparseMatrix res = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse5000_Res.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void mulTestNegativeS() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            SparseMatrix m1 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg7_5.txt");
            SparseMatrix m2 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg5_2.txt");
            SparseMatrix res = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg7_2.txt");
            Assert.assertEquals("Matrices are different", m1.mulThreads(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        WorkWithFiles wwf = new WorkWithFiles();
//        SparseMatrix m13 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_1.txt");
//        SparseMatrix m14 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_2.txt");
//        SparseMatrix m15 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_1.txt");
//        SparseMatrix m16 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_2.txt");
//        SparseMatrix m17 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse5000_1.txt");
//        SparseMatrix m18 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse5000_2.txt");
        SparseMatrix m19 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse1.txt");
        SparseMatrix m20 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse2.txt");
        DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\big5000_1.txt");
        SparseMatrix m2 = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\big5000_2.txt");
        SparseMatrix res = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\big5000_res.txt");
        int th = 0;
        int mul = 0;
        for (int i = 0; i < 5; ++i) {
            long start1 = System.currentTimeMillis();
            m1.mul(m2);
            long end1 = System.currentTimeMillis();
            long start2 = System.currentTimeMillis();
            m1.mulThreads(m2);
            long end2 = System.currentTimeMillis();
            th += end2 - start2;
            mul += end1 - start1;
        }
        System.out.println(mul);
        System.out.println(th);
        System.out.println((double)mul / th);
    }
}
