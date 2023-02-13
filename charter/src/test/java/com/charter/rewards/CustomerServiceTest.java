package com.charter.rewards;

import com.charter.rewards.dto.CustomerPoints;
import com.charter.rewards.repository.CustomerRepository;
import com.charter.rewards.vo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void getPointsByMonth_ShouldReturnResult() {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("John Doe");

        List<CustomerPoints> result = Collections.singletonList
                (new CustomerPoints(1L, "John Doe", 30L));

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getCustomerId());
        assertEquals("John Doe", result.get(0).getCustomerName());
        assertEquals(30L, result.get(0).getPoints());
    }
}
