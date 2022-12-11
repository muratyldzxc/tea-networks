package com.networks.tea.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Getter
@Setter
public class WeatherInfoDto {
    @NotNull
    @NotBlank
    @Size(max = 30)
    private String location;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String conditionOfWeather;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date timestampOfWeather;

    @NotNull
    private Integer temperature;
}
