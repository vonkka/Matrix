import java.io.*;

public class workWFiles {
    private final int STAND_SIZE = 3;
    public DenseMatrix getDense(String path) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            DenseMatrix res = new DenseMatrix(STAND_SIZE, STAND_SIZE);
            char[] chs = new char[1024];
            int len = br.read(chs);
            StringBuilder num = new StringBuilder();
            StringBuilder expPow = new StringBuilder();
            char prev = ' ';
            int k = 0;
            int height = 0;
            boolean firstLine = true;
            boolean justAdded = false;
            boolean empty = true;
            boolean exp = false;
            while (len != -1) {
                for (int i = 0; i < len; ++i) {
                    if (Character.isDigit(chs[i])) {
                        if (exp) expPow.append(chs[i]);
                        else num.append(chs[i]);
                    }
                    else if (chs[i] == '.') {
                        if (prev != '.') {
                            num.append(chs[i]);
                        }
                        else throw new Exception("Incorrect input on place [" + (height + 1) + ", " + (k + 1) + "] ");
                    }
                    else if (chs[i] == '-') {
                        if (prev == ' ' || prev == '\n' || prev == 'e' || prev == 'E') {
                            if (exp) expPow.append(chs[i]);
                            else num.append(chs[i]);
                        }
                        else throw new Exception("Incorrect input on place [" + (height + 1) + ", " + (k + 1) + "] ");
                    }
                    else if (chs[i] == ' ' || chs[i] == '\t') {
                        if (!num.toString().equals("")) {
                            try {
                                if (k == res.getW()) {
                                    if (firstLine) res = res.expandMatrix(res.getH(), res.getW() << 1);
                                    else throw new Exception("Line " + (height + 1) + " is longer than previous");
                                }
                                double data = Double.parseDouble(num.toString());
                                if (exp) {
                                    int power = Integer.parseInt(expPow.toString());
                                    data *= Math.pow(10, power);
                                }
                                res.addElem(data, height, k++);
                                justAdded = true;
                                empty = false;
                                num = new StringBuilder();
                                expPow = new StringBuilder();
                                exp = false;
                            }
                            catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                                return null;
                            }
                        }
                    }
                    else if (chs[i] == 'E' || chs[i] == 'e') {
                        exp = true;
                    }
                    else if (chs[i] == '\n') {
                        if (!num.toString().equals("")) {
                            try {
                                double data = Double.parseDouble(num.toString());
                                if (exp) {
                                    int power = Integer.parseInt(expPow.toString());
                                    data *= Math.pow(10, power);
                                }
                                if (k == res.getW()){
                                    if (firstLine) res = res.expandMatrix(res.getH(),k + 1);
                                    else throw new Exception("Line " + (height + 1) + " is longer than previous");
                                }
                                else if (k + 1 < res.getW()) {
                                    if (firstLine) res = res.copy(0, res.getH(), 0, k + 1);
                                    else throw new Exception("Line " + (height + 1) + " is narrower than previous");
                                }
                                res.addElem(data, height, k);
                                if (++height >= res.getH()) res = res.expandMatrix(res.getH() * 2, res.getW());
                                num = new StringBuilder();
                                expPow = new StringBuilder();
                                k = 0;
                                justAdded = false;
                                empty = false;
                                firstLine = false;
                                exp = false;
                            }
                            catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        else if (justAdded) {
                            if (k < res.getW()) {
                                if (firstLine) res = res.copy(0, res.getH(), 0, k);
                                else throw new Exception("Line " + (height + 1) + " is narrower than previous");
                            }
                            if (++height >= res.getH()) res = res.expandMatrix(res.getH() * 2, res.getW());
                            k = 0;
                            empty = false;
                            firstLine = false;
                            justAdded = false;
                        }
                    }
                    else if (chs[i] == '\r');
                    else throw new Exception("Unsupported symbol on place [" + (height + 1) + ", " + (k + 1) +
                                                            "] - only digits, dots and spaces are required");
                    prev = chs[i];
                }
                len = br.read(chs);
            }
            if (empty) throw new Exception("Matrix is empty");
            if (!num.toString().equals("")) {
                double date = Double.parseDouble(num.toString());
                res.addElem(date, height, k++);
                if (k < res.getW()) {
                    if (firstLine) res = res.copy(0, res.getH(), 0, k);
                    else throw new Exception("Line " + (height + 1) + " is shorter than previous");
                }
                if (++height > res.getH()) res = res.expandMatrix(res.getH() * 2, res.getW());
            }
            else if (prev == ' ') ++height;
            if (height < res.getH()) res = res.copy(0, height, 0, res.getW());
            return res;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public SparseMatrix getSparse(String path) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            SparseMatrix res = new SparseMatrix(STAND_SIZE, STAND_SIZE);
            char[] chs = new char[1024];
            int len = br.read(chs);
            StringBuilder num = new StringBuilder();
            StringBuilder expPow = new StringBuilder();
            char prev = ' ';
            int k = 0;
            int height = 0;
            boolean firstLine = true;
            boolean justAdded = false;
            boolean empty = true;
            boolean exp = false;
            while (len != -1) {
                for (int i = 0; i < len; ++i) {
                    if (Character.isDigit(chs[i])) {
                        if (exp) expPow.append(chs[i]);
                        else num.append(chs[i]);
                    }
                    else if (chs[i] == '.') {
                        if (prev != '.' && !exp) {
                            num.append(chs[i]);
                        }
                        else throw new Exception("Incorrect input on place [" + (height + 1) + ", " + (k + 1) + "] ");
                    }
                    else if (chs[i] == '-') {
                        if (prev == ' ' || prev == '\n' || prev == 'e' || prev == 'E') {
                            if (exp) expPow.append(chs[i]);
                            else num.append(chs[i]);
                        }
                        else throw new Exception("Incorrect input on place [" + (height + 1) + ", " + (k + 1) + "] ");
                    }
                    else if (chs[i] == ' ' || chs[i] == '\t') {
                        if (!num.toString().equals("")) {
                            try {
                                if (k == res.getW()){
                                    if (firstLine) res.setW(k << 1);
                                    else throw new Exception("Line " + (height + 1) + " is longer than previous");
                                }
                                double data = Double.parseDouble(num.toString());
                                if (exp) {
                                    int power = Integer.parseInt(expPow.toString());
                                    data *= Math.pow(10, power);
                                }
                                res.addElem(data, height, k++);
                                justAdded = true;
                                num = new StringBuilder();
                                expPow = new StringBuilder();
                                empty = false;
                                exp = false;
                            }
                            catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                                return null;
                            }
                        }
                    }
                    else if (chs[i] == 'E' || chs[i] == 'e') {
                        if (!exp && (Character.isDigit(prev))) {
                            exp = true;
                        }
                        else throw new Exception("Incorrect input on place [" + (height + 1) + ", " + (k + 1) + "] ");
                    }
                    else if (chs[i] == '\n') {
                        if (!num.toString().equals("")) {
                            try {
                                double data = Double.parseDouble(num.toString());
                                if (exp) {
                                    int power = Integer.parseInt(expPow.toString());
                                    data *= Math.pow(10, power);
                                }
                                if (firstLine) res.setW(k + 1);
                                else if (k + 1 != res.getW()){
                                    throw new Exception("Line " + (height + 1) + " is shorter than previous");
                                }
                                res.addElem(data, height, k);
                                if (++height == res.getH()) res.setH(height << 1);
                                num = new StringBuilder();
                                expPow = new StringBuilder();
                                k = 0;
                                justAdded = false;
                                empty = false;
                                firstLine = false;
                                exp = false;
                            }
                            catch (NumberFormatException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        else if (justAdded) {
                            if (k < res.getW()) {
                                if (!firstLine) throw new Exception("Line " + (height + 1) + " is narrower than previous");
                                else res.setW(k);
                            }
                            k = 0;
                            if (++height == res.getH()) res.setH(height << 1);
                            empty = false;
                            firstLine = false;
                            justAdded = false;
                        }
                    }
                    else if (chs[i] == '\r');
                    else throw new Exception("Unsupported symbol on place [" + (height + 1) + ", " + (k + 1) +
                                "] - only digits, dots and spaces are required");
                    prev = chs[i];
                }
                len = br.read(chs);
            }
            if (empty) throw new Exception("Matrix is empty");
            if (!num.toString().equals("")) {
                double date = Double.parseDouble(num.toString());
                res.addElem(date, height++, k++);
                if (k < res.getW()) {
                    if (!firstLine) throw new Exception("Line " + (height + 1) + " is shorter than previous");
                }
            }
            else if (prev == ' ') ++height;
            res.setH(height);
            return res;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static void main(String[] args) throws IOException {
        workWFiles wwf = new workWFiles();
        DenseMatrix a = wwf.getDense("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix11_4.txt");
        SparseMatrix b = wwf.getSparse("C:\\Users\\1\\Desktop\\Programming\\Matrices\\matrix4_11.txt");
        System.out.println(b.mul(a).toString());

    }
}
