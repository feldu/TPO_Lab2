package tpo.lab2.calculator.trigonometric;

import tpo.lab2.calculator.Calculator;

import java.math.BigDecimal;

import static java.lang.Math.PI;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class Cos extends Calculator {
    private final Sin sin;

    public Cos(double accuracy) {
        super(accuracy);
        this.sin = new Sin(accuracy);
    }

    public Cos(BigDecimal accuracy) {
        super(accuracy);
        this.sin = new Sin(accuracy);
    }

    @Override
    public BigDecimal calculate(BigDecimal x) {
        x = x.remainder(BigDecimal.valueOf(2 * PI));
        final BigDecimal result =
                sin.calculate(
                        new BigDecimal(PI)
                                .divide(new BigDecimal(2), DECIMAL128.getPrecision(), HALF_EVEN)
                                .subtract(x)
                );
        return result.setScale(getAccuracy().scale(), HALF_EVEN);
    }
}