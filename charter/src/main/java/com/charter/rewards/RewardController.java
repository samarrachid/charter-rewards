package com.charter.rewards;

import com.charter.rewards.dto.CustomerPoints;
import com.charter.rewards.service.CustomerService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RewardController {
    private CustomerService customerService;

    public RewardController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers-points")
    public List<CustomerPoints> customersWithPoints() {
        return customerService.customersWithPoints();
    }

    @GetMapping("/points-by-month")
    public List<CustomerPoints> getPointsByMonth(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return customerService.getPointsByMonth(startDate, endDate);
    }
}
