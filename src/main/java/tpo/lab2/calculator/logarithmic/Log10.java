package tpo.lab2.calculator.logarithmic;

import tpo.lab2.calculator.Calculator;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;

public class Log10 extends Calculator {
    private final Ln ln;

    public Log10(double accuracy) {
        super(accuracy);
        this.ln = new Ln(accuracy);
    }

    public Log10(BigDecimal accuracy) {
        super(accuracy);
        this.ln = new Ln(accuracy);
    }

    @Override
    public BigDecimal calculate(BigDecimal x) {
        return ln.calculate(x)
                .divide(ln.calculate(new BigDecimal(10)), 10000, HALF_EVEN)
                .setScale(getAccuracy().scale(), HALF_EVEN);
    }
}
