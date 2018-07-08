package models.utils;

import exceptions.AppException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

/**
 * Classe para tratar operações financeiras.
 */
public final class MathUtil {

    private static final BigDecimal SQRT_DIG = new BigDecimal(150);
    private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());
    /**
     * Formatter padrão a ser utilizado para double
     */
    private static final DecimalFormat mFormat = new DecimalFormat("##.######", DecimalFormatSymbols.getInstance(Locale.forLanguageTag("ptBR")));

    /**
     * BigDecimal para 100
     */
    public static final BigDecimal HUNDRED = new BigDecimal(BigInteger.valueOf(100), 0);

    /**
     * Escala padrão das operações
     */
    public static final int SCALE_DEFAULT = 2;

    /**
     * Tipo de arredondamento padrão
     */
    public static final int ROUND_TYPE_DEFAULT = BigDecimal.ROUND_HALF_DOWN;

    private static final Random RANDOM = new Random();

    /**
     * Efetua uma divisão entre um BigDecimal e um
     * @param dividend
     * @param divisor
     * @return
     */
    public static BigDecimal divide(BigDecimal dividend, int divisor) throws AppException {
        return divide(dividend, new BigDecimal(BigInteger.valueOf(divisor)));
    }

    /**
     * Efetua uma divisão entre um BigDecimal e um
     * @param dividend
     * @param divisor
     * @return
     */
    public static BigDecimal divide(BigDecimal dividend, BigDecimal divisor) throws AppException {
        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
            throw new AppException("O divisor utilizado na divisão é igual a zero.");
        }

        return dividend.divide(divisor, SCALE_DEFAULT, ROUND_TYPE_DEFAULT);
    }

    /**
     * Arredonda o big decimal utilizando contexto padrão
     * @param number
     * @return
     */
    public static BigDecimal round(BigDecimal number) {
        return number.round(new MathContext(4, RoundingMode.HALF_DOWN));
    }

    /**
     * Private utility method used to compute the square root of a BigDecimal.
     *
     * @author Luciano Culacciatti
     * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
     */
    private static BigDecimal sqrtNewtonRaphson  (BigDecimal c, BigDecimal xn, BigDecimal precision){
        BigDecimal fx = xn.pow(2).add(c.negate());
        BigDecimal fpx = xn.multiply(new BigDecimal(2));
        BigDecimal xn1 = fx.divide(fpx,2*SQRT_DIG.intValue(),RoundingMode.HALF_DOWN);
        xn1 = xn.add(xn1.negate());
        BigDecimal currentSquare = xn1.pow(2);
        BigDecimal currentPrecision = currentSquare.subtract(c);
        currentPrecision = currentPrecision.abs();
        if (currentPrecision.compareTo(precision) <= -1){
            return xn1;
        }
        return sqrtNewtonRaphson(c, xn1, precision);
    }

    /**
     * Uses Newton Raphson to compute the square root of a BigDecimal.
     *
     * @author Luciano Culacciatti
     * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-BigDecimal
     */
    public static BigDecimal bigSqrt(BigDecimal c){
        return sqrtNewtonRaphson(c, new BigDecimal(1), new BigDecimal(1).divide(SQRT_PRE));
    }

    /**
     * Calcula a raiz de um BigDecimal
     * @param number
     * @param root
     * @return
     */
    public static BigDecimal bigRoot(BigDecimal number, double root) {
        if(root == 2l)
            return bigSqrt(number);

        Double rootD = Math.pow(number.doubleValue(), root);
        return new BigDecimal(mFormat.format(rootD));
    }

    public static int random(int minimum, int maximum) {
        int range = maximum - minimum + 1;
        return RANDOM.nextInt(range) + minimum;
    }

}