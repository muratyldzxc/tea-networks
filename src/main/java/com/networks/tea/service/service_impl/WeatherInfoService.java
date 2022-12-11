package com.networks.tea.service.service_impl;

import com.networks.tea.dto.WeatherInfoDto;
import com.networks.tea.model.WeatherInfo;
import com.networks.tea.repository.WeatherInfoRepository;
import com.networks.tea.repository.pageable.WeatherInfoCriteriaRepository;
import com.networks.tea.repository.pageable.filter.WeatherInfoFilter;
import com.networks.tea.service.IWeatherInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class WeatherInfoService implements IWeatherInfoService {
    WeatherInfoRepository weatherInfoRepository;
    WeatherInfoCriteriaRepository weatherInfoCriteriaRepository;

    @Autowired
    public WeatherInfoService(WeatherInfoRepository weatherInfoRepository, WeatherInfoCriteriaRepository weatherInfoCriteriaRepository) {
        this.weatherInfoRepository = weatherInfoRepository;
        this.weatherInfoCriteriaRepository = weatherInfoCriteriaRepository;
    }

    public List<WeatherInfo> findAllWithPageableFilters(WeatherInfoFilter filters, Pageable paging) {
        return weatherInfoCriteriaRepository.findAllWithPageableFilters(filters, paging);
    }

    @Override
    public WeatherInfo add(WeatherInfo entity) {
        return weatherInfoRepository.save(entity);
    }

    public WeatherInfo add(WeatherInfoDto weatherInfoDto) {
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setTemperature(weatherInfoDto.getTemperature());
        weatherInfo.setTimestampOfWeather(weatherInfoDto.getTimestampOfWeather());
        weatherInfo.setConditionOfWeather(weatherInfoDto.getConditionOfWeather());
        weatherInfo.setLocation(weatherInfoDto.getLocation());

        return this.add(weatherInfo);
    }

    @Override
    public WeatherInfo findById(Long id) {
        return weatherInfoRepository.findById(id).orElseThrow();
    }

    @Override
    public WeatherInfo update(WeatherInfo entity) {
        return weatherInfoRepository.save(entity);
    }

    public WeatherInfo updateById(Long id, WeatherInfoDto weatherInfoDto) {
        WeatherInfo weatherInfo = weatherInfoRepository.findById(id).orElseThrow();
        weatherInfo.setConditionOfWeather(weatherInfoDto.getConditionOfWeather());
        weatherInfo.setTimestampOfWeather(weatherInfoDto.getTimestampOfWeather());
        weatherInfo.setLocation(weatherInfoDto.getLocation());
        weatherInfo.setTemperature(weatherInfoDto.getTemperature());

        return this.update(weatherInfo);
    }

    @Override
    public void deleteById(Long id) {
        weatherInfoRepository.deleteById(id);
    }
}
