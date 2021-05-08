package com.minka.tunel;

import com.minka.tunel.domain.model.Enterprise;
import com.minka.tunel.domain.model.User;
import com.minka.tunel.domain.repository.EnterpriseRepository;
import com.minka.tunel.domain.repository.UserRepository;
import com.minka.tunel.domain.service.EnterpriseService;
import com.minka.tunel.domain.service.UserService;
import com.minka.tunel.exception.ResourceNotFoundException;
import com.minka.tunel.service.EnterpriseServiceImpl;
import com.minka.tunel.service.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @TestConfiguration
    static class UserServiceImplTestConfiguration {
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Test
    @DisplayName("when getUserById With Valid Id Then Returns User")
    public void whenGetUserByIdWithValidIdThenReturnsUser() {
        // Arrange
        Long id = 1L;
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(id))
                .thenReturn(Optional.of(user));


        // Act
        User foundUser = userService.getUserById(id);

        // Assert
        assertThat(foundUser.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("when getEnterpriseById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetEnterpriseByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        String expectedMessage = String.format(template, "User", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            User user = userService.getUserById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}