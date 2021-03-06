package com.octoperf.kraken.runtime.container.executor;

import com.octoperf.kraken.config.runtime.container.api.ContainerProperties;
import com.octoperf.kraken.runtime.client.api.RuntimeClient;
import com.octoperf.kraken.runtime.client.api.RuntimeClientBuilder;
import com.octoperf.kraken.security.authentication.api.AuthenticationMode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RuntimeClientContainerConfigurationTest {

  @Mock
  ContainerProperties properties;
  @Mock
  RuntimeClientBuilder clientBuilder;
  @Mock
  RuntimeClient client;

  RuntimeClientContainerConfiguration configuration;

  @BeforeEach
  public void setUp() {
    given(properties.getApplicationId()).willReturn("applicationId");
    given(clientBuilder.mode(AuthenticationMode.CONTAINER)).willReturn(clientBuilder);
    given(clientBuilder.applicationId("applicationId")).willReturn(clientBuilder);
    given(clientBuilder.build()).willReturn(Mono.just(client));
    configuration = new RuntimeClientContainerConfiguration();
  }

  @Test
  public void shouldCreateClient() {
    Assertions.assertThat(configuration.runtimeClient(clientBuilder, properties)).isNotNull();
  }
}