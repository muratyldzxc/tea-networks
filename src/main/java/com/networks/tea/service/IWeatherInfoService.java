package com.networks.tea.service;

import com.networks.tea.model.WeatherInfo;
import com.networks.tea.repository.pageable.filter.WeatherInfoFilter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IWeatherInfoService extends ICrudService<Long,WeatherInfo>{
    List<WeatherInfo> findAllWithPageableFilters(WeatherInfoFilter filters, Pageable paging);
}
