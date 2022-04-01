package tpo.lab2.trigonometric;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tpo.lab2.calculator.trigonometric.Cos;
import tpo.lab2.calculator.trigonometric.Sin;
import tpo.lab2.calculator.trigonometric.Tan;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TanTest {
    private static final BigDecimal ACCURACY = BigDecimal.valueOf(0.000000001);
    @Mock
    private Sin sin = new Sin(ACCURACY);
    @Mock
    private Cos cos = new Cos(ACCURACY);
    @InjectMocks
    private Tan tan = new Tan(ACCURACY);

    @Test
    void shouldCallSinAndCosFunction() {
        when(sin.calculate(any())).thenReturn(BigDecimal.valueOf(0));
        when(cos.calculate(any())).thenReturn(BigDecimal.valueOf(-1));
        tan.calculate(new BigDecimal(PI));
        verify(cos, atLeastOnce()).calculate(any(BigDecimal.class));
        verify(sin, atLeastOnce()).calculate(any(BigDecimal.class));
    }

    @ParameterizedTest(name = "{index}. Test tan({0}) on null ")
    @ValueSource(doubles = {
            (PI / 2), (3 * PI) / 2, (PI / 2) + 2 * PI, ((3 * PI) / 2) + (2 * PI),
            -(PI / 2), -(3 * PI) / 2, -(PI / 2) - 2 * PI, -((3 * PI) / 2) - (2 * PI)
    })
    void checkGapValues(double x) {
        when(cos.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.ZERO);
        assertThrows(ArithmeticException.class, () -> tan.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test tan({0}) in peaks ")
    @ValueSource(doubles = {-2 * PI, -PI, 0, PI, 2 * PI})
    void checkPeaks(double x) {
        when(cos.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x)));
        when(sin.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x)));
        assertEquals(BigDecimal.valueOf(Math.sin(x) / Math.cos(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                tan.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test tan({0}) in small derivative areas ")
    @ValueSource(doubles = {-5 * PI / 4, -3 * PI / 4, -PI / 4, 3 * PI / 4, 5 * PI / 4})
    void checkIntervalsWithSmoothDerivativeChange(double x) {
        when(cos.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x)));
        when(sin.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x)));
        assertEquals(BigDecimal.valueOf(Math.sin(x) / Math.cos(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                tan.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test tan({0}) in large derivative areas ")
    @ValueSource(doubles = {-3 * PI / 2 - 10e-5, -3 * PI / 2 + 10e-5,
            -PI / 2 - 10e-5, -PI / 2 + 10e-5,
            PI / 2 - 10e-5, PI / 2 + 10e-5,
            3 * PI / 2 - 10e-5, 3 * PI / 2 + 10e-5,
    })
    void checkIntervalsWithSignificantDerivativeChange(double x) {
        when(cos.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x)));
        when(sin.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x)));
        assertEquals(BigDecimal.valueOf(Math.sin(x) / Math.cos(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                tan.calculate(BigDecimal.valueOf(x)));
    }


    @ParameterizedTest(name = "{index}. Test tan({0}) in big X values")
    @ValueSource(doubles = {-10e4, 10e4})
    void checkBigX(double x) {
        when(cos.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x)));
        when(sin.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x)));
        assertEquals(BigDecimal.valueOf(Math.sin(x) / Math.cos(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                tan.calculate(BigDecimal.valueOf(x)));
    }

    @ParameterizedTest(name = "{index}. Test tan({0}) in small X values")
    @ValueSource(doubles = {-10e-4, 10e-4})
    void checkSmallX(double x) {
        when(cos.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.cos(x)));
        when(sin.calculate(eq(BigDecimal.valueOf(x)))).thenReturn(BigDecimal.valueOf(Math.sin(x)));
        assertEquals(BigDecimal.valueOf(Math.sin(x) / Math.cos(x)).setScale(ACCURACY.scale(), RoundingMode.HALF_EVEN),
                tan.calculate(BigDecimal.valueOf(x)));
    }
}
