import java.io.IOException;

public interface Matrix {
    @Override
    boolean equals(Object m);
    public boolean isSparse();
    @Override
    String toString();
    // Prints elements of matrix
    void display();
    // Converts elements to array
    double[][] getData();
    double getElem(int i, int j);
    // Height
    int getH();
    // Weight
    int getW();
    double getHash();
    // a to -a
    Matrix negMatrix();
    Matrix transposedMatrix();
    // Copy part of matrix
    Matrix copy(int begh, int endh, int begw, int endw);
    Matrix mul(Matrix m);
    void writeMatrix(String fname) throws IOException;
}
