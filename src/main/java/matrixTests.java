import java.io.FileNotFoundException;
import java.io.IOException;
public class matrixTests {

    void MulTestQuadr() throws IOException {
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr matrix1.txt");
            denseMatrix m2 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr matrix2.txt");
            denseMatrix res = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\quadr expected.txt");
            int code = m1.Mult(m2).equals(res);
            if (code == 0) System.out.println("Matrices are equal");
            else if (code == 1) System.out.println("Hashes are different");
            else if (code == 2) System.out.println("Sizes don't match");
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    void mulTestFirstHigherButNarrower() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt");
            denseMatrix m2 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            denseMatrix res = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_11.txt");
            int code = m1.Mult(m2).equals(res);
            if (code == 0) System.out.println("Matrices are equal");
            else if (code == 1) System.out.println("Hashes are different");
            else if (code == 2) System.out.println("Sizes don't match");
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    void mulTestHigherAndWider() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_4.txt");
            denseMatrix m2 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            denseMatrix res = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_11.txt");
            int code = m1.Mult(m2).equals(res);
            if (code == 0) System.out.println("Matrices are equal");
            else if (code == 1) System.out.println("Hashes are different");
            else if (code == 2) System.out.println("Sizes don't match");
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    void mulTestFirstLowerButWider() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            denseMatrix m2 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt");
            denseMatrix res = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_4.txt");
            int code = m1.Mult(m2).equals(res);
            if (code == 0) System.out.println("Matrices are equal");
            else if (code == 1) System.out.println("Hashes are different");
            else if (code == 2) System.out.println("Sizes don't match");
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    void mulTestFirstLowerAndNarrower() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_4.txt");
            denseMatrix m2 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
            denseMatrix res = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix2_11.txt");
            int code = m1.Mult(m2).equals(res);
            if (code == 0) System.out.println("Matrices are equal");
            else if (code == 1) System.out.println("Hashes are different");
            else if (code == 2) System.out.println("Sizes don't match");
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    void mulTestNegative() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg7_5.txt");
            denseMatrix m2 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg5_2.txt");
            denseMatrix res = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\neg7_2.txt");
            int code = m1.Mult(m2).equals(res);
            if (code == 0) System.out.println("Matrices are equal");
            else if (code == 1) System.out.println("Hashes are different");
            else if (code == 2) System.out.println("Sizes don't match");
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    void emptyMatrixTest() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix_empty.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    void parseTestLineWiderInEnd() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\line2WiderEnd.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    void parseTestLineWider() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\line3Wider.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    void parseTestLineShorter() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\line3Narrower.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    void parseTestLineShorterSpace() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\line2NarrowerSpace.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    void parseTest2Dots() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\2dots.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    void parseTestUnacceptableChar() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\wrongBegin.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    void parseTestIsolatedDot() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\wrongBegin2.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    void parseTestTwoMinuses() throws IOException{
        workWFiles wwf = new workWFiles();
        try {
            denseMatrix m1 = wwf.getMat("C:\\Users\\1\\Desktop\\Programming\\Matrices\\twoMinuses.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    public static void main(String[] args) throws IOException {
        matrixTests mtest = new matrixTests();
        mtest.MulTestQuadr();
        mtest.mulTestFirstLowerButWider();
        mtest.mulTestFirstHigherButNarrower();
        mtest.mulTestFirstLowerAndNarrower();
        mtest.mulTestHigherAndWider();
        mtest.mulTestNegative();
        mtest.parseTestLineWiderInEnd();
        mtest.parseTestLineWider();
        mtest.parseTestLineShorter();
        mtest.parseTestLineShorterSpace();
        mtest.emptyMatrixTest();
        mtest.parseTest2Dots();
        mtest.parseTestUnacceptableChar();
        mtest.parseTestIsolatedDot();
        mtest.parseTestTwoMinuses();
    }
}