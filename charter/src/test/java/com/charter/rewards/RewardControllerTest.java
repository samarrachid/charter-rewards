package com.charter.rewards;

import com.charter.rewards.dto.CustomerPoints;
import com.charter.rewards.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(RewardController.class)
public class RewardControllerTest {
    @InjectMocks
    private RewardController rewardController;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testGetPointsByMonth() {
        // Given
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 3, 31);

        List<CustomerPoints> expectedCustomers = createCustomerPoints();

        when(customerService.getPointsByMonth(startDate, endDate))
                .thenReturn(expectedCustomers);

        // When
        List<CustomerPoints> actualCustomerPoints = customerService.getPointsByMonth(startDate, endDate);

        // Then
        assertThat(actualCustomerPoints).isEqualTo(expectedCustomers);
        verify(customerService).getPointsByMonth(startDate, endDate);
    }

    @Test
    public void testCustomersWithPoints() {
        // Given
        List<CustomerPoints> expectedCustomers = createCustomerPoints();
        when(customerService.customersWithPoints()).thenReturn(expectedCustomers);
        List<CustomerPoints> customers = customerService.customersWithPoints();
        verify(customerService).customersWithPoints();
        assertEquals(expectedCustomers, customers);
    }

    private List<CustomerPoints> createCustomerPoints() {
        return Arrays.asList(
                new CustomerPoints(1L, "John Doe", 100),
                new CustomerPoints(2L, "Jane Doe", 200)
        );
    }
}
