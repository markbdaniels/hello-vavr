package mbdlabs.playground.values;

import io.vavr.Lazy;

public class LazyTest {

    public void testLazy() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        lazy.isEvaluated(); // = false
        lazy.get();         // = 0.123 (random generated)
        lazy.isEvaluated(); // = true
        lazy.get();         // = 0.123 (memoized)
    }
}
