package com.example.insurance;

import com.example.insurance.model.InsuranceProduct;
import com.example.insurance.model.ProductType;
import com.example.insurance.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final ProductRepository productRepository;

    public DataSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (productRepository.count() > 0) {
            return;
        }

        productRepository.saveAll(List.of(
                new InsuranceProduct(
                        "Family Health Secure",
                        ProductType.HEALTH,
                        "Cashless hospitalization, annual health checkups, and family floater coverage.",
                        "CareWell Insurance",
                        new BigDecimal("14999"),
                        new BigDecimal("1000000"),
                        12,
                        "Family"
                ),
                new InsuranceProduct(
                        "Senior Health Plus",
                        ProductType.HEALTH,
                        "Pre-existing condition support, room rent coverage, and day-care procedures.",
                        "LifeShield",
                        new BigDecimal("21999"),
                        new BigDecimal("750000"),
                        12,
                        "Senior"
                ),
                new InsuranceProduct(
                        "Motor Comprehensive",
                        ProductType.MOTOR,
                        "Own damage, theft, third-party liability, roadside assistance, and add-on cover.",
                        "RoadSure",
                        new BigDecimal("8999"),
                        new BigDecimal("600000"),
                        12,
                        "Popular"
                ),
                new InsuranceProduct(
                        "Two Wheeler Protect",
                        ProductType.MOTOR,
                        "Affordable bike cover with third-party liability and accident protection.",
                        "MotoGuard",
                        new BigDecimal("2499"),
                        new BigDecimal("120000"),
                        12,
                        "Budget"
                ),
                new InsuranceProduct(
                        "International Travel Care",
                        ProductType.TRAVEL,
                        "Medical emergency, baggage delay, trip cancellation, and passport assistance.",
                        "GlobeSafe",
                        new BigDecimal("3499"),
                        new BigDecimal("500000"),
                        1,
                        "Global"
                ),
                new InsuranceProduct(
                        "Student Travel Shield",
                        ProductType.TRAVEL,
                        "Long-stay travel protection for overseas students with sponsor cover.",
                        "EduVoyage",
                        new BigDecimal("11999"),
                        new BigDecimal("1500000"),
                        12,
                        "Student"
                )
        ));
    }
}
