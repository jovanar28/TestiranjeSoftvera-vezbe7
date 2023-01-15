package userTest;

import org.example.PasswordEncoder;
import org.junit.jupiter.api.Test;

import static org.mockito.AdditionalMatchers.or;
import static org.mockito.Mockito.*;

public class HowItWorks {

    @Test
    void obican(){
        PasswordEncoder mock=mock(PasswordEncoder.class);
        when(mock.encode("a")).thenReturn("1");
        mock.encode("a");
        verify(mock).encode("a");

    }

    @Test
    void argumentMatcher(){
        PasswordEncoder mock=mock(PasswordEncoder.class);
        when(mock.encode("a")).thenReturn("1");
        mock.encode("a");
        verify(mock).encode(match());
    }

    private String match(){
        return or(eq("a"),endsWith("b"));
    }

}
