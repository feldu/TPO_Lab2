package tpo.lab2.calculator.trigonometric;

import tpo.lab2.calculator.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tan extends Calculator {
    private final Sin sin;
    private final Cos cos;
    public Tan(double accuracy) {
        super(accuracy);
        this.sin = new Sin(accuracy);
        this.cos = new Cos(accuracy);
    }

    public Tan(BigDecimal accuracy) {
        super(accuracy);
        this.sin = new Sin(accuracy);
        this.cos = new Cos(accuracy);
    }

    @Override
    public BigDecimal calculate(BigDecimal x) {
        final BigDecimal sinValue = sin.calculate(x);
        final BigDecimal cosValue = cos.calculate(x);
        if (cosValue.compareTo(BigDecimal.ZERO) == 0)
            throw new ArithmeticException("X value is not valid for function Tan");
        return sinValue.divide(cosValue, 1000, RoundingMode.HALF_EVEN).setScale(getAccuracy().scale(), RoundingMode.HALF_EVEN);
    }
}
