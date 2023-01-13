/*
 * Copyright 2022 Google LLC
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

package com.google.cloud.monitoring.dashboard.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.httpjson.InstantiatingHttpJsonChannelProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.monitoring.dashboard.v1.DashboardsServiceClient;
import com.google.cloud.monitoring.dashboard.v1.DashboardsServiceSettings;
import com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration;
import com.google.cloud.spring.core.Credentials;
import com.google.cloud.spring.core.DefaultCredentialsProvider;
import com.google.cloud.spring.core.Retry;
import com.google.cloud.spring.core.util.RetryUtil;
import java.io.IOException;
import java.util.Collections;
import javax.annotation.Generated;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/**
 * Auto-configuration for {@link DashboardsServiceClient}.
 *
 * <p>Provides auto-configuration for Spring Boot
 *
 * <p>The default instance has everything set to sensible defaults:
 *
 * <ul>
 *   <li>The default transport provider is used.
 *   <li>Credentials are acquired automatically through Application Default Credentials.
 *   <li>Retries are configured for idempotent methods but not for non-idempotent methods.
 * </ul>
 */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@AutoConfiguration
@AutoConfigureAfter(GcpContextAutoConfiguration.class)
@ConditionalOnClass(DashboardsServiceClient.class)
@ConditionalOnProperty(
    value = "com.google.cloud.monitoring.dashboard.v1.dashboards-service.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(DashboardsServiceSpringProperties.class)
public class DashboardsServiceSpringAutoConfiguration {
  private final DashboardsServiceSpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER =
      LogFactory.getLog(DashboardsServiceSpringAutoConfiguration.class);

  protected DashboardsServiceSpringAutoConfiguration(
      DashboardsServiceSpringProperties clientProperties, CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from DashboardsService-specific configuration");
      }
      this.credentialsProvider =
          ((CredentialsProvider) new DefaultCredentialsProvider(this.clientProperties));
    } else {
      this.credentialsProvider = credentialsProvider;
    }
  }

  /**
   * Provides a default transport channel provider bean. The default is gRPC and will default to it
   * unless the useRest option is provided to use HTTP transport instead
   *
   * @return a default transport channel provider.
   */
  @Bean
  @ConditionalOnMissingBean(name = "defaultDashboardsServiceTransportChannelProvider")
  public TransportChannelProvider defaultDashboardsServiceTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return DashboardsServiceSettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return DashboardsServiceSettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a DashboardsServiceSettings bean configured to use the default credentials provider
   * (obtained with dashboardsServiceCredentials()) and its default transport channel provider
   * (defaultDashboardsServiceTransportChannelProvider()). It also configures the quota project ID
   * if provided. It will configure an executor provider in case there is more than one thread
   * configured in the client
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in DashboardsServiceSpringProperties. Method-level properties will take precedence over
   * service-level properties if available, and client library defaults will be used if neither are
   * specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link DashboardsServiceSettings} bean configured with {@link
   *     TransportChannelProvider} bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public DashboardsServiceSettings dashboardsServiceSettings(
      @Qualifier("defaultDashboardsServiceTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    DashboardsServiceSettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = DashboardsServiceSettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = DashboardsServiceSettings.newBuilder();
    }
    clientSettingsBuilder
        .setCredentialsProvider(this.credentialsProvider)
        .setTransportChannelProvider(defaultTransportChannelProvider)
        .setHeaderProvider(this.userAgentHeaderProvider());
    if (this.clientProperties.getQuotaProjectId() != null) {
      clientSettingsBuilder.setQuotaProjectId(this.clientProperties.getQuotaProjectId());
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Quota project id set to "
                + this.clientProperties.getQuotaProjectId()
                + ", this overrides project id from credentials.");
      }
    }
    if (this.clientProperties.getExecutorThreadCount() != null) {
      ExecutorProvider executorProvider =
          DashboardsServiceSettings.defaultExecutorProviderBuilder()
              .setExecutorThreadCount(this.clientProperties.getExecutorThreadCount())
              .build();
      clientSettingsBuilder.setBackgroundExecutorProvider(executorProvider);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Background executor thread count is "
                + this.clientProperties.getExecutorThreadCount());
      }
    }
    Retry serviceRetry = clientProperties.getRetry();
    if (serviceRetry != null) {
      RetrySettings createDashboardRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createDashboardSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .createDashboardSettings()
          .setRetrySettings(createDashboardRetrySettings);

      RetrySettings listDashboardsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listDashboardsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listDashboardsSettings().setRetrySettings(listDashboardsRetrySettings);

      RetrySettings getDashboardRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getDashboardSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getDashboardSettings().setRetrySettings(getDashboardRetrySettings);

      RetrySettings deleteDashboardRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteDashboardSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .deleteDashboardSettings()
          .setRetrySettings(deleteDashboardRetrySettings);

      RetrySettings updateDashboardRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateDashboardSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .updateDashboardSettings()
          .setRetrySettings(updateDashboardRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry createDashboardRetry = clientProperties.getCreateDashboardRetry();
    if (createDashboardRetry != null) {
      RetrySettings createDashboardRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createDashboardSettings().getRetrySettings(),
              createDashboardRetry);
      clientSettingsBuilder
          .createDashboardSettings()
          .setRetrySettings(createDashboardRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for createDashboard from properties.");
      }
    }
    Retry listDashboardsRetry = clientProperties.getListDashboardsRetry();
    if (listDashboardsRetry != null) {
      RetrySettings listDashboardsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listDashboardsSettings().getRetrySettings(),
              listDashboardsRetry);
      clientSettingsBuilder.listDashboardsSettings().setRetrySettings(listDashboardsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listDashboards from properties.");
      }
    }
    Retry getDashboardRetry = clientProperties.getGetDashboardRetry();
    if (getDashboardRetry != null) {
      RetrySettings getDashboardRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getDashboardSettings().getRetrySettings(), getDashboardRetry);
      clientSettingsBuilder.getDashboardSettings().setRetrySettings(getDashboardRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getDashboard from properties.");
      }
    }
    Retry deleteDashboardRetry = clientProperties.getDeleteDashboardRetry();
    if (deleteDashboardRetry != null) {
      RetrySettings deleteDashboardRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteDashboardSettings().getRetrySettings(),
              deleteDashboardRetry);
      clientSettingsBuilder
          .deleteDashboardSettings()
          .setRetrySettings(deleteDashboardRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for deleteDashboard from properties.");
      }
    }
    Retry updateDashboardRetry = clientProperties.getUpdateDashboardRetry();
    if (updateDashboardRetry != null) {
      RetrySettings updateDashboardRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateDashboardSettings().getRetrySettings(),
              updateDashboardRetry);
      clientSettingsBuilder
          .updateDashboardSettings()
          .setRetrySettings(updateDashboardRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for updateDashboard from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a DashboardsServiceClient bean configured with DashboardsServiceSettings.
   *
   * @param dashboardsServiceSettings settings to configure an instance of client bean.
   * @return a {@link DashboardsServiceClient} bean configured with {@link
   *     DashboardsServiceSettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public DashboardsServiceClient dashboardsServiceClient(
      DashboardsServiceSettings dashboardsServiceSettings) throws IOException {
    return DashboardsServiceClient.create(dashboardsServiceSettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-dashboards-service";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}
