package com.aquawealth.repository;

import com.aquawealth.model.WeatherCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WeatherConditionRepository extends JpaRepository<WeatherCondition, Long> {
    Optional<WeatherCondition> findByCityAndStatus(String city, String status);
}
