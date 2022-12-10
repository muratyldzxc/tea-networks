package com.networks.tea.repository;

import com.networks.tea.model.WeatherInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, Long> {
    Page<WeatherInfo> findByLocation(String location, Pageable pageable);
    Page<WeatherInfo> findByLocationAndTemperature(String location, Integer temperature, Pageable pageable);
}
