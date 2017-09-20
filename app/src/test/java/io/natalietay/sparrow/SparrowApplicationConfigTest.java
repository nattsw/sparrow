package io.natalietay.sparrow;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.*;

/**
 * Created by Natalie on 20/9/17.
 */
public class SparrowApplicationConfigTest {
    // we usually don't test configurations,
    // but maybe we'd also like to aim for 100% test coverage
    @Test
    public void getBaseUrl() throws Exception {
        SparrowApplicationConfig config = new SparrowApplicationConfig();

        String expected = config.getBaseUrl();

        // see http://joel-costigliola.github.io/assertj/assertj-core.html
        // on Android Support
        assertThat(expected).isEqualTo("https://api.github.com");
    }
}
