package tpo.lab2.calculator.trigonometric;

import tpo.lab2.calculator.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sec extends Calculator {
    private final Cos cos;

    public Sec(double accuracy) {
        super(accuracy);
        this.cos = new Cos(accuracy);
    }

    public Sec(BigDecimal accuracy) {
        super(accuracy);
        this.cos = new Cos(accuracy);
    }

    @Override
    public BigDecimal calculate(BigDecimal x) {
        final BigDecimal cosValue = cos.calculate(x);
        if (cosValue.compareTo(BigDecimal.ZERO) == 0)
            throw new ArithmeticException("X value is not valid for function Sec");
        return BigDecimal.ONE.divide(cosValue, 1000, RoundingMode.HALF_EVEN).setScale(getAccuracy().scale(), RoundingMode.HALF_EVEN);
    }
}
