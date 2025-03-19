package com.aquawealth.repository;

import com.aquawealth.model.WeatherCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface WeatherConditionRepository extends JpaRepository<WeatherCondition, Long> {
    Optional<WeatherCondition> findByCityAndStatus(String city, String status);
}
