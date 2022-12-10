package com.networks.tea.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@Setter
public class WeatherInfoRequest {
    private  String x;
}
