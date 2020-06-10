package com.octoperf.kraken.config.influxdb.spring;

import com.octoperf.kraken.Application;
import com.octoperf.kraken.config.influxdb.api.InfluxDBProperties;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class InfluxDBPropertiesSpringTest {

  @Autowired
  InfluxDBProperties properties;

  @Test
  public void shouldCreateProperties() {
    Assertions.assertThat(properties.getUrl()).isNotNull();
  }

}