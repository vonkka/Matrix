import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ThreadsComparision {
    boolean compCombinations(String mat1, String mat2, String matRes) throws IOException {
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix d1 = wwf.getDense(mat1);
            DenseMatrix d2 = wwf.getDense(mat2);
            DenseMatrix dres = wwf.getDense(matRes);
            SparseMatrix s1 = wwf.getSparse(mat1);
            SparseMatrix s2 = wwf.getSparse(mat2);
            SparseMatrix sres = wwf.getSparse(matRes);
            if (!d1.equals(s1) || !d2.equals(s2) || !s1.equals(d1) || !s2.equals(d2)) return false;
            if (!d1.mul(d2).equals(s1.mul(s2)) ||
                    !s1.mulThreads(s2).equals(d1.mul(d2)) ||
                    !d1.mulThreads(d2).equals(d1.mul(s2)) ||
                    !s1.mulThreads(s2).equals(d1.mul(s2)) ||
                    !d1.mulThreads(s2).equals(s1.mul(d2)) ||
                    !d1.mulThreads(s2).equals(s1.mul(s2)) ||
                    !s1.mulThreads(d2).equals(d1.mul(s2)) ||
                    !s1.mulThreads(d2).equals(d1.mul(d2)) ||
                    !s1.mulThreads(d2).equals(s1.mul(s2))) return false;
            if (!dres.equals(sres) || !d1.mulThreads(d2).equals(sres)) return false;
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Test
    public void MulTestQuadr() throws IOException {
        try {
            boolean res = compCombinations("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr matrix1.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr matrix2.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr expected.txt");
            Assert.assertTrue("Matrices are different", res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestBig() throws IOException{
        try {
            boolean res = compCombinations("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix150_2000.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_374.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix150_374.txt");
            Assert.assertTrue("Matrices are different", res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    //    @Test
//    public void mulTestBigSparse5000() throws IOException{
//        WorkWithFiles wwf = new WorkWithFiles();
//        try {
//            boolean res = compCombinations("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse5000_1.txt",
//                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse5000_2.txt",
//                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse5000_Res.txt");
//            Assert.assertTrue("Matrices are different", res);
//        }
//        catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//    }
    @Test
    public void mulTestFirstHigherButNarrower() throws IOException{
        try {
            boolean res = compCombinations("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_11.txt");
            Assert.assertTrue("Matrices are different", res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestHigherAndWider() throws IOException{
        try {
            boolean res = compCombinations("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix7_5.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix5_2.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix7_2.txt");
            Assert.assertTrue("Matrices are different", res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestFirstLowerButWider() throws IOException{
        try {
            boolean res = compCombinations("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_4.txt");
            Assert.assertTrue("Matrices are different", res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestFirstLowerAndNarrower() throws IOException{
        try {
            boolean res = compCombinations("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_4.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_11.txt");
            Assert.assertTrue("Matrices are different", res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestNegative() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            boolean res = compCombinations("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg7_5.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg5_2.txt",
                    "C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg7_2.txt");
            Assert.assertTrue("Matrices are different", res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
