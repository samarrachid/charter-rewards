package com.charter.rewards.service;

import com.charter.rewards.dto.TransactionRequest;
import com.charter.rewards.vo.Customer;
import com.charter.rewards.vo.Point;
import com.charter.rewards.repository.CustomerRepository;
import com.charter.rewards.repository.PointRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    public CustomerRepository customerRepository;

    public PointRepository pointRepository;

    public TransactionService(CustomerRepository customerRepository, PointRepository pointRepository) {
        this.customerRepository = customerRepository;
        this.pointRepository = pointRepository;
    }

    public void addPointsToCustomer(Long customerId, TransactionRequest transactionRequest) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Customer customer = customerOptional.get();
        Point point = new Point();
        point.setCustomer(customer);
        point.setPointAmount(transactionRequest.getTransactionAmount());
        point.setTransactionDate(transactionRequest.getTransactionDate());
        pointRepository.save(point);
    }

    public void processTransaction(long customerId, double transactionAmount) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null) {
            if (transactionAmount > 0) {
                Point point = new Point();
                point.setPointAmount(calculatePointsForTransaction(transactionAmount));
                point.setTransactionDate(LocalDate.now());
                point.setCustomer(customer);
                pointRepository.save(point);
                customerRepository.save(customer);
            }
        }
    }

    /**
     * Collect points per transaction amount
     * @param transactionAmount
     * @return
     */
    private int calculatePointsForTransaction(double transactionAmount) {
        int points = 0;
        if (transactionAmount > 100) {
            points += 2 * (transactionAmount - 100);
        } else if (transactionAmount > 50) {
            points += 1 * (transactionAmount - 50);
        }
        return points;
    }
}
