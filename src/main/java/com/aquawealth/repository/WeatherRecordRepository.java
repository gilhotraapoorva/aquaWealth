package com.aquawealth.repository;

import com.aquawealth.model.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRecordRepository extends JpaRepository<WeatherRecord, Long> {
}
