package others.fibonacci;

import org.junit.jupiter.api.Test;

class SolutionTest {

    Fibonacci_Matrix fibonacci = new Fibonacci_Matrix();
    Matrix<Integer> matrix = new IntMatrix();
    Integer[][] base = {{1, 1}, {1, 0}};

    @Test
    public void test() {
        for (int i = 1; i <= 10; i++) {
            int result = fibonacci.evaluate(i);
            System.out.println(result);
        }
    }

    @Test
    public void testToString() {
        System.out.println(matrix.toString(base));
    }

    @Test
    public void testMultiply() {
        Integer[][] m2 = matrix.multiply(base, base);
        System.out.println(matrix.toString(m2));

        Integer[][] m3 = matrix.multiply(m2, base);
        System.out.println(matrix.toString(m3));

        Integer[][] m4 = matrix.multiply(m3, base);
        System.out.println(matrix.toString(m4));
    }

    @Test
    public void testMultiply2() {
        Integer[][] m1 = {{1, 1}, {1, 1}, {1, 1}};
        Integer[][] m2 = {{1, 1, 1}, {1, 1, 1}};
        Integer[][] p = matrix.multiply(m1, m2);
        System.out.println(matrix.toString(p));
    }

    @Test
    public void testMultiply3() {
//        Integer[][] m1 = {{3, 2}, {2, 1},};
        Integer[][] m1 = {{2, 1},};
        Integer[][] m2 = {{1, 1}, {1, 0},};
        for (int i = 0; i < 10; i++) {
            Integer[][] p = matrix.multiply(m1, m2);
            System.out.println(matrix.toString(p));
            m1 = p;
        }
    }

    @Test
    public void testPower2() {
        Integer[][] m1 = {{1, 2},};
        Integer[][] m2 = {{0, 1}, {1, 1},};

        for (int i = 0; i < 10; i++) {
            Integer[][] p = matrix.power(m2, i);
            Integer[][] r = matrix.multiply(m1, p);
            System.out.println(matrix.toString(r));
        }
    }

    @Test
    public void testPower() {
        Integer[][] p1 = matrix.power(base, 1);
        System.out.println(matrix.toString(p1));

        Integer[][] p2 = matrix.power(base, 2);
        System.out.println(matrix.toString(p2));

        Integer[][] p3 = matrix.power(base, 3);
        System.out.println(matrix.toString(p3));
    }

    @Test
    public void testNumber() {
        Number a = 1;
        Number b = 2f;
        System.out.println(a.intValue() * b.floatValue());
    }
}

public class Fibonacci_Matrix {

    int evaluate(int n) {
        if (n < 1) return 0;
        if (n == 1 || n == 2) return 1;

        Matrix<Integer> matrix = new IntMatrix();
        Integer[][] base = {{1, 1}, {1, 0}};
        Integer[][] res = matrix.power(base, n - 2);

        return res[0][0] + res[0][1];
    }

}

abstract class Matrix<E> {

    public abstract E zero();

    public abstract E one();

    public abstract E[][] array(int m, int n);

    public abstract E add(E o1, E o2);

    public abstract E multiply(E o1, E o2);

    public String toString(E[][] data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (E[] array : data) {
            stringBuilder.append("\n\t{");
            for (E value : array) {
                stringBuilder.append(value);
                stringBuilder.append(", ");
            }
            stringBuilder.append("},");
        }
        stringBuilder.append("\n}");

        return stringBuilder.toString();
    }

    public E[][] unit(int dimension) {
        E[][] result = array(dimension, dimension);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result[i][j] = i == j ? one() : zero();
            }
        }
        return result;
    }

    public E[][] multiply(E[][] m1, E[][] m2) {
        int iMax = m1.length;
        int jMax = m2[0].length;
        int kMax = m2.length;
        E[][] res = array(iMax, jMax);
        for (int i = 0; i < iMax; i++) {
            for (int j = 0; j < jMax; j++) {
                E sum = zero();
                for (int k = 0; k < kMax; k++) {
                    E product = multiply(m1[i][k], m2[k][j]);
                    sum = add(sum, product);
                }
                res[i][j] = sum;
            }
        }

        return res;
    }

    public E[][] power(E[][] data, int exponent) {
        E[][] temp = data;
        E[][] result = unit(temp.length);

        for (int bits = exponent; bits > 0; bits >>= 1) {
            if ((bits & 1) == 1) {
                result = multiply(result, temp);
            }
            temp = multiply(temp, temp);
        }

        return result;
    }

}

class IntMatrix extends Matrix<Integer> {

    @Override
    public Integer zero() {
        return 0;
    }

    @Override
    public Integer one() {
        return 1;
    }

    @Override
    public Integer[][] array(int m, int n) {
        return new Integer[m][n];
    }

    @Override
    public Integer add(Integer o1, Integer o2) {
        return o1 + o2;
    }

    @Override
    public Integer multiply(Integer o1, Integer o2) {
        return o1 * o2;
    }
}
