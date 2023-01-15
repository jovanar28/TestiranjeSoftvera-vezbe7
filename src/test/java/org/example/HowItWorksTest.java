package org.example;

import org.example.*;
import org.junit.jupiter.api.Test;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Mockito.*;

public class HowItWorksTest {

    @Test
    public void testSnippet() {
        // 1: create
        PasswordEncoder mock = mock(PasswordEncoder.class);

        // 2: stub
        when(mock.encode("a")).thenReturn("1");

        // 3: act
        mock.encode("a");

        // 4: verify
        verify(mock).encode("a");
    }

    @Test
    public void testMatcherInMethod() {
        // 1: create
        PasswordEncoder mock = mock(PasswordEncoder.class);

        // 2: stub
        when(mock.encode("ahyghugyub")).thenReturn("1");

        // 3: act
        mock.encode("ahyghugyub");

        // 4: verify
        verify(mock).encode(matchCondition());
    }

    private String matchCondition() {
        return or(eq("a"), endsWith("b"));
    }


}
