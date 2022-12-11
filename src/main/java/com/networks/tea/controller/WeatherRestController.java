package com.networks.tea.controller;

import com.networks.tea.payload.request.SignUpRequest;
import com.networks.tea.dto.WeatherInfoDto;
import com.networks.tea.model.WeatherInfo;
import com.networks.tea.payload.response.MessageResponse;
import com.networks.tea.repository.pageable.filter.WeatherInfoFilter;
import com.networks.tea.service.service_impl.WeatherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherRestController {
    WeatherInfoService weatherInfoService;

    @Autowired
    public WeatherRestController(WeatherInfoService weatherInfoService) {
        this.weatherInfoService = weatherInfoService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getWeatherInfo(@RequestParam(name = "startDate", required = false) String startDate
                                                            ,@RequestParam(name = "endDate", required = false) String endDate
                                                            ,@RequestParam(name = "location", required = false) String location
                                                            ,@RequestParam(name = "condition", required = false) String condition
                                                            ,@RequestParam(required = false) Integer temperature
                                                            ,@RequestParam(defaultValue = "0", required = false) int page
                                                            ,@RequestParam(defaultValue = "3", required = false) int size) {

        Pageable paging = PageRequest.of(page, size);
        ZonedDateTime startD;
        ZonedDateTime endD;

        try{
            startD = ZonedDateTime.parse(startDate);
            endD = ZonedDateTime.parse(endDate);
        }catch (DateTimeParseException e){
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.OK);
        }


        Date a = new Date(startD.toInstant().toEpochMilli());
        Date b = new Date(endD.toInstant().toEpochMilli());
        WeatherInfoFilter filter = new WeatherInfoFilter(temperature, location, condition, a, b);

        return new ResponseEntity<>(weatherInfoService.findAllWithPageableFilters(filter, paging), HttpStatus.OK);
    }

    @GetMapping("/{weather_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<WeatherInfo> getById(@PathVariable Long weather_id) {
        WeatherInfo weatherInfo = weatherInfoService.findById(weather_id);
        return new ResponseEntity<>(weatherInfo, HttpStatus.OK);
    }

    @PutMapping("/{weather_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<WeatherInfo> updateById(@RequestBody WeatherInfoDto weatherInfoDto, @PathVariable Long weather_id) {
        WeatherInfo updatedWeatherInfo;
        updatedWeatherInfo = weatherInfoService.updateById(weather_id, weatherInfoDto);

        return new ResponseEntity<>(updatedWeatherInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{weather_id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Long weather_id) {
        weatherInfoService.deleteById(weather_id);
        return ResponseEntity.ok(new MessageResponse("Deleted Successfully"));
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addWeatherInfo(@Valid @RequestBody WeatherInfoDto weatherInfoDto) {
        WeatherInfo savedWeatherInfo =  weatherInfoService.add(weatherInfoDto);

        return ResponseEntity.ok(savedWeatherInfo);
    }
}
