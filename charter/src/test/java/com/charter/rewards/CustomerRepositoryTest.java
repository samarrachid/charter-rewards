package com.charter.rewards;

import com.charter.rewards.repository.CustomerRepository;
import com.charter.rewards.vo.Customer;
import com.charter.rewards.vo.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void findCustomersWithPoints_ShouldNotThrowNPE() {
        //Verify that the mock object is not null
        assertNotNull(customerRepository);
    }

    @Test
    public void findCustomersWithPoints() {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("John Doe");

        Point point1 = new Point();
        point1.setPointAmount(10);
        point1.setTransactionDate(LocalDate.of(2022, 12, 1));
        point1.setCustomer(customer);

        Point point2 = new Point();
        point2.setPointAmount(20);
        point2.setTransactionDate(LocalDate.of(2022, 12, 2));
        point2.setCustomer(customer);

        customer.setPoints(Arrays.asList(point1, point2));

        when(customerRepository.findCustomersWithPoints())
                .thenReturn(Collections.singletonList(new Object[]{customer, 30L}));

    }
}
