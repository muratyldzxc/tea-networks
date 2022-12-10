package com.networks.tea.controller;

import com.networks.tea.model.User;
import com.networks.tea.model.WeatherInfo;
import com.networks.tea.repository.WeatherInfoRepository;
import com.networks.tea.repository.pageable.WeatherInfoCriteriaRepository;
import com.networks.tea.repository.pageable.filter.WeatherInfoFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherRestController {

    @Autowired
    WeatherInfoRepository weatherInfoRepository;
    @Autowired
    WeatherInfoCriteriaRepository weatherInfoCriteriaRepository;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<WeatherInfo>> getWeatherInfo(@RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate
                                                            ,@RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate
                                                            ,@RequestParam(name = "location", required = false) String location
                                                            ,@RequestParam(name = "condition", required = false) String condition
                                                            ,@RequestParam(required = false) Integer temperature
                                                            ,@RequestParam(defaultValue = "0", required = false) int page
                                                            ,@RequestParam(defaultValue = "3", required = false) int size) {

        Pageable paging = PageRequest.of(page, size);
       // WeatherInfoFilter filter = new WeatherInfoFilter(temperature, location, condition, startDate, endDate);
        return new ResponseEntity<>(weatherInfoRepository.findByLocation(location, paging).getContent(), HttpStatus.OK);
        //return new ResponseEntity<>(weatherInfoCriteriaRepository.findAllWithPageableFilters(filter, paging), HttpStatus.OK);
    }
}
