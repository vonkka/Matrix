import org.junit.*;
import java.io.FileNotFoundException;
import java.io.IOException;
public class DenseMatrixTests {
    @Test
    public void MulTestQuadr() throws IOException {
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr matrix1.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr matrix2.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr expected.txt");
            Assert.assertEquals("Matrices are different", m1.mul(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestBig() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix150_2000.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_374.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix150_374.txt");
            Assert.assertEquals("Matrices are different", m1.mul(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestFirstHigherButNarrower() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_11.txt");
            Assert.assertEquals("Matrices are different", m1.mul(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestHigherAndWider() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix7_5.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix5_2.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix7_2.txt");
            Assert.assertEquals("Matrices are different", m1.mul(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestFirstLowerButWider() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_4.txt");
            Assert.assertEquals("Matrices are different", m1.mul(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestFirstLowerAndNarrower() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_4.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_11.txt");
            Assert.assertEquals("Matrices are different", m1.mul(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestBigSparse500() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_1.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_2.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse500_Res.txt");
            Assert.assertEquals("Matrices are different", m1.mul(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestBigSparse600() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_1.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_2.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\bigSparse600_Res.txt");
            Assert.assertEquals("Matrices are different", m1.mul(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestNegative() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg7_5.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg5_2.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg7_2.txt");
            Assert.assertEquals("Matrices are different", m1.mul(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void mulTestBigDense2000() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_1.txt");
            DenseMatrix m2 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_2.txt");
            DenseMatrix res = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2000_res.txt");
            Assert.assertEquals("Matrices are different", m1.mul(m2), res);
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void emptyMatrixTest() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix_empty.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void parseTestLineWiderInEnd() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\line2WiderEnd.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void parseTestLineWider() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\line3Wider.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void parseTestLineShorter() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\line3Narrower.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void parseTestLineShorterSpace() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\line2NarrowerSpace.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void parseTest2Dots() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\2dots.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void parseTestUnacceptableChar() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\wrongBegin.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void parseTestIsolatedDot() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\wrongBegin2.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void parseTestTwoMinuses() throws IOException{
        WorkWithFiles wwf = new WorkWithFiles();
        try {
            DenseMatrix m1 = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\twoMinuses.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//
//
//
//
//    public static void main(String[] args) throws IOException {
//        DenseMatrixTests mtest = new DenseMatrixTests();
//        long start = System.currentTimeMillis();
//        mtest.mulTestBig();
//        mtest.MulTestQuadr();
//        mtest.mulTestFirstLowerButWider();
//        mtest.mulTestFirstHigherButNarrower();
//        mtest.mulTestFirstLowerAndNarrower();
//        mtest.mulTestHigherAndWider();
//        mtest.mulTestNegative();
//        mtest.parseTestLineWiderInEnd();
//        mtest.parseTestLineWider();
//        mtest.parseTestLineShorter();
//        mtest.parseTestLineShorterSpace();
//        mtest.emptyMatrixTest();
//        mtest.parseTest2Dots();
//        mtest.parseTestUnacceptableChar();
//        mtest.parseTestIsolatedDot();
//        mtest.parseTestTwoMinuses();
//        long end = System.currentTimeMillis();
//        System.out.println(end-start);
//    }
}
