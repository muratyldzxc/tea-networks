package com.networks.tea.repository.pageable.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherInfoFilter {
    private Integer temperature;
    private String location;
    private String condition;
    private Date startDate;
    private Date endDate;
}
