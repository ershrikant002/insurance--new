package com.example.insurance.repository;

import com.example.insurance.model.InsuranceOrder;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<InsuranceOrder, Long> {
    @Override
    @EntityGraph(attributePaths = "items")
    List<InsuranceOrder> findAll();

    @Override
    @EntityGraph(attributePaths = "items")
    Optional<InsuranceOrder> findById(Long id);
}
