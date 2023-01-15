package org.example;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ClearInvocationsTest {

    @Test
    public void clearInvocationsTest() {
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        UserRepository userRepository = mock(UserRepository.class);

        // use mocks
        passwordEncoder.encode(null);
        userRepository.findById(null);

        // clear
        clearInvocations(passwordEncoder, userRepository);

        // succeeds because invocations were cleared
        verifyNoInteractions(passwordEncoder, userRepository);
    }
}
