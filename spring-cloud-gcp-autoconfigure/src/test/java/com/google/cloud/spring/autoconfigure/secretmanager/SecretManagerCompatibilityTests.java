/*
 * Copyright 2017-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.spring.autoconfigure.secretmanager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.api.gax.rpc.NotFoundException;
import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretPayload;
import com.google.cloud.secretmanager.v1.SecretVersionName;
import com.google.protobuf.ByteString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.BootstrapRegistry.InstanceSupplier;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Unit tests to check compatibility of Secret Manager.
 */
class SecretManagerCompatibilityTests {

  private static final String PROJECT_NAME = "hollow-light-of-the-sealed-land";
  private SpringApplicationBuilder application;
  private SecretManagerServiceClient client;

  @BeforeEach
  void init() {
    application = new SpringApplicationBuilder(SecretManagerCompatibilityTests.class)
        .web(WebApplicationType.NONE)
        .properties(
            "spring.cloud.gcp.secretmanager.project-id=" + PROJECT_NAME,
            "spring.cloud.gcp.sql.enabled=false");

    client = mock(SecretManagerServiceClient.class);
    SecretVersionName secretVersionName =
        SecretVersionName.newBuilder()
            .setProject(PROJECT_NAME)
            .setSecret("my-secret")
            .setSecretVersion("latest")
            .build();
    when(client.accessSecretVersion(secretVersionName))
        .thenReturn(
            AccessSecretVersionResponse.newBuilder()
                .setPayload(
                    SecretPayload.newBuilder().setData(ByteString.copyFromUtf8("newSecret")))
                .build());
    secretVersionName =
        SecretVersionName.newBuilder()
            .setProject(PROJECT_NAME)
            .setSecret("fake-secret")
            .setSecretVersion("latest")
            .build();
    when(client.accessSecretVersion(secretVersionName))
        .thenThrow(NotFoundException.class);
  }

  /**
   * Users with the new configuration (i.e., using `spring.config.import`) should get {@link
   * com.google.cloud.spring.secretmanager.SecretManagerTemplate} autoconfiguration and properties
   * resolved.
   */
  @Test
  void testConfigurationWhenDefaultSecretIsNotAllowed() {
    application.addBootstrapRegistryInitializer(
            (registry) -> registry.registerIfAbsent(
                SecretManagerServiceClient.class,
                InstanceSupplier.of(client)
            )
    );
    try (ConfigurableApplicationContext applicationContext = application.run()) {
      ConfigurableEnvironment environment = applicationContext.getEnvironment();
      assertThat(environment.getProperty("sm://my-secret")).isEqualTo("newSecret");
      assertThatThrownBy(() -> environment.getProperty("sm://fake-secret"))
          .isExactlyInstanceOf(NotFoundException.class);
    }
  }

  @Test
  void testConfigurationWhenDefaultSecretIsAllowed() {
    application.properties(
            "spring.cloud.gcp.secretmanager.allow-default-secret=true")
        .addBootstrapRegistryInitializer(
            (registry) -> registry.registerIfAbsent(
                SecretManagerServiceClient.class,
                InstanceSupplier.of(client)
            )
        );
    try (ConfigurableApplicationContext applicationContext = application.run()) {
      ConfigurableEnvironment environment = applicationContext.getEnvironment();
      assertThat(environment.getProperty("sm://my-secret")).isEqualTo("newSecret");
      assertThat(environment.getProperty("sm://fake-secret")).isNull();
    }
  }
}
