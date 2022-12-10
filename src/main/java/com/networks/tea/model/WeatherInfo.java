package com.networks.tea.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "weather_info")
@Getter
@Setter
@NoArgsConstructor
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String location;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String conditionOfWeather;

    @NotNull
    private Date timestampOfWeather;

    @NotNull
    private Integer temperature;

}
