public interface Matrix {
    @Override
    public boolean equals(Object m);
    @Override
    public String toString();
    void display();
    double[][] getData();
    double getElem(int i, int j);
    int getH();
    int getW();
    Matrix negMatrix();
    Matrix transposedMatrix();
    Matrix copy(int begh, int endh, int begw, int endw);
    Matrix mul(Matrix m);
}
