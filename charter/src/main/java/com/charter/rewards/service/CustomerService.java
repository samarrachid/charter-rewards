package com.charter.rewards.service;

import com.charter.rewards.dto.CustomerPoints;
import com.charter.rewards.repository.PointRepository;
import com.charter.rewards.vo.Customer;
import com.charter.rewards.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    private PointRepository pointRepository;

    public CustomerService(CustomerRepository customerRepository, PointRepository pointRepository) {
        this.customerRepository = customerRepository;
        this.pointRepository = pointRepository;
    }

    /**
     * Get list of customers with its points
     * @return
     */
    public List<CustomerPoints> customersWithPoints() {
        List<CustomerPoints> result = new ArrayList<>();
        for (Object[] customerWithPoint : customerRepository.findCustomersWithPoints()) {
            Customer customer = (Customer) customerWithPoint[0];
            Long points = (Long) customerWithPoint[1];
            result.add(new CustomerPoints(customer.getCustomerId(), customer.getName(), points));
        }
        return result.stream()
                .collect(Collectors.toList());
    }

    /**
     * Get Customer's point between two dates
     * @param startDate
     * @param endDate
     * @return
     */
    public List<CustomerPoints> getPointsByMonth(LocalDate startDate, LocalDate endDate) {
        List<CustomerPoints> result = new ArrayList<>();
        for (Object[] customerWithPoint : pointRepository.findPointsByMonth(startDate, endDate)) {
            Long customerId = (Long) customerWithPoint[0];
            Long points = (Long) customerWithPoint[1];
            Customer customer = customerRepository.findById(customerId).get();
            result.add(new CustomerPoints(customer.getCustomerId(), customer.getName(), points));
        }
        return result;
    }
}
