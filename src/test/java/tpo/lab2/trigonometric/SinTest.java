package tpo.lab2.trigonometric;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tpo.lab2.calculator.trigonometric.Sin;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.PI;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SinTest {
    private static final BigDecimal ACCURACY = BigDecimal.valueOf(0.000000001);
    private Sin sin;

    @BeforeEach
    public void init() {
        sin = new Sin(ACCURACY);
    }

    @ParameterizedTest(name = "{index}. Test sin({0}) on 0")
    @ValueSource(doubles = {-2 * PI, -PI, 0, PI, 2 * PI})
    public void zeroTest(double x) {
        assertEquals(ZERO.setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                sin.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test sin({0}) on 1")
    @ValueSource(doubles = {(PI / 2), (PI / 2) + 2 * PI, (PI / 2) - 2 * PI})
    public void oneTest(double x) {
        assertEquals(ONE.setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                sin.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test sin({0}) on -1")
    @ValueSource(doubles = {(3 * PI) / 2, ((3 * PI) / 2) + (2 * PI), ((3 * PI) / 2) - (2 * PI)})
    public void minusOneTest(double x) {
        assertEquals((ZERO.subtract(ONE)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                sin.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test sec({0}) on increasing")
    @ValueSource(doubles = {PI / 3, -PI / 3, PI / 3 + 2 * PI, -PI / 3 + 2 * PI})
    public void functionIncreasing(double x) {
        assertEquals(BigDecimal.valueOf(Math.sin(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                sin.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test sec({0}) on decreasing")
    @ValueSource(doubles = {2 * PI / 3, -2 * PI / 3, 2 * PI / 3 + 2 * PI, -2 * PI / 3 + 2 * PI})
    public void functionDecreasing(double x) {
        assertEquals(BigDecimal.valueOf(Math.sin(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                sin.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test sin({0}) in big X values")
    @ValueSource(doubles = {-10e4, 10e4})
    void checkBigX(double x) {
        assertEquals(BigDecimal.valueOf(Math.sin(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                sin.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test sin({0}) in small X values")
    @ValueSource(doubles = {-10e-4, 10e-4})
    void checkSmallX(double x) {
        assertEquals(BigDecimal.valueOf(Math.sin(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                sin.calculate(BigDecimal.valueOf(x)));
    }
}
