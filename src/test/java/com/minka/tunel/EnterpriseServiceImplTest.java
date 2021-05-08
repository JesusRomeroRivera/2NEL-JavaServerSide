package com.minka.tunel;

import com.minka.tunel.domain.model.Enterprise;
import com.minka.tunel.domain.repository.EnterpriseRepository;
import com.minka.tunel.domain.service.EnterpriseService;
import com.minka.tunel.exception.ResourceNotFoundException;
import com.minka.tunel.service.EnterpriseServiceImpl;
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
public class EnterpriseServiceImplTest {

    @MockBean
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private EnterpriseService enterpriseService;

    @TestConfiguration
    static class EnterpriseServiceImplTestConfiguration {
        @Bean
        public EnterpriseService enterpriseService() {
            return new EnterpriseServiceImpl();
        }
    }

    @Test
    @DisplayName("when getEnterpriseById With Valid Id Then Returns Enterprise")
    public void whenGetEnterpriseByIdWithValidIdThenReturnsPost() {
        // Arrange
        Long id = 1L;
        Enterprise enterprise = new Enterprise();
        enterprise.setId(1L);
        when(enterpriseRepository.findById(id))
                .thenReturn(Optional.of(enterprise));


        // Act
        Enterprise foundEnterprise = enterpriseService.getEnterpriseById(id);

        // Assert
        assertThat(foundEnterprise.getId()).isEqualTo(id);

    }

    @Test
    @DisplayName("when getEnterpriseById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetEnterpriseByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        // Arrange
        Long id = 1L;
        String template = "Resource %s not found for %s with value %s";
        when(enterpriseRepository.findById(id))
                .thenReturn(Optional.empty());

        String expectedMessage = String.format(template, "Enterprise", "Id", id);

        // Act
        Throwable exception = catchThrowable(() -> {
            Enterprise foundEnterprise = enterpriseService.getEnterpriseById(id);
        });

        // Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}