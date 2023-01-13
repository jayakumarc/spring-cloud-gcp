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

package com.google.cloud.secretmanager.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.httpjson.InstantiatingHttpJsonChannelProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretManagerServiceSettings;
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
 * Auto-configuration for {@link SecretManagerServiceClient}.
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
@ConditionalOnClass(SecretManagerServiceClient.class)
@ConditionalOnProperty(
    value = "com.google.cloud.secretmanager.v1.secret-manager-service.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(SecretManagerServiceSpringProperties.class)
public class SecretManagerServiceSpringAutoConfiguration {
  private final SecretManagerServiceSpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER =
      LogFactory.getLog(SecretManagerServiceSpringAutoConfiguration.class);

  protected SecretManagerServiceSpringAutoConfiguration(
      SecretManagerServiceSpringProperties clientProperties,
      CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from SecretManagerService-specific configuration");
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
  @ConditionalOnMissingBean(name = "defaultSecretManagerServiceTransportChannelProvider")
  public TransportChannelProvider defaultSecretManagerServiceTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return SecretManagerServiceSettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return SecretManagerServiceSettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a SecretManagerServiceSettings bean configured to use the default credentials provider
   * (obtained with secretManagerServiceCredentials()) and its default transport channel provider
   * (defaultSecretManagerServiceTransportChannelProvider()). It also configures the quota project
   * ID if provided. It will configure an executor provider in case there is more than one thread
   * configured in the client
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in SecretManagerServiceSpringProperties. Method-level properties will take precedence over
   * service-level properties if available, and client library defaults will be used if neither are
   * specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link SecretManagerServiceSettings} bean configured with {@link
   *     TransportChannelProvider} bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public SecretManagerServiceSettings secretManagerServiceSettings(
      @Qualifier("defaultSecretManagerServiceTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    SecretManagerServiceSettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = SecretManagerServiceSettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = SecretManagerServiceSettings.newBuilder();
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
          SecretManagerServiceSettings.defaultExecutorProviderBuilder()
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
      RetrySettings listSecretsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listSecretsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listSecretsSettings().setRetrySettings(listSecretsRetrySettings);

      RetrySettings createSecretRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createSecretSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.createSecretSettings().setRetrySettings(createSecretRetrySettings);

      RetrySettings addSecretVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.addSecretVersionSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .addSecretVersionSettings()
          .setRetrySettings(addSecretVersionRetrySettings);

      RetrySettings getSecretRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getSecretSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getSecretSettings().setRetrySettings(getSecretRetrySettings);

      RetrySettings updateSecretRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateSecretSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.updateSecretSettings().setRetrySettings(updateSecretRetrySettings);

      RetrySettings deleteSecretRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteSecretSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.deleteSecretSettings().setRetrySettings(deleteSecretRetrySettings);

      RetrySettings listSecretVersionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listSecretVersionsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .listSecretVersionsSettings()
          .setRetrySettings(listSecretVersionsRetrySettings);

      RetrySettings getSecretVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getSecretVersionSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .getSecretVersionSettings()
          .setRetrySettings(getSecretVersionRetrySettings);

      RetrySettings accessSecretVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.accessSecretVersionSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .accessSecretVersionSettings()
          .setRetrySettings(accessSecretVersionRetrySettings);

      RetrySettings disableSecretVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.disableSecretVersionSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .disableSecretVersionSettings()
          .setRetrySettings(disableSecretVersionRetrySettings);

      RetrySettings enableSecretVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.enableSecretVersionSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .enableSecretVersionSettings()
          .setRetrySettings(enableSecretVersionRetrySettings);

      RetrySettings destroySecretVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.destroySecretVersionSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .destroySecretVersionSettings()
          .setRetrySettings(destroySecretVersionRetrySettings);

      RetrySettings setIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.setIamPolicySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.setIamPolicySettings().setRetrySettings(setIamPolicyRetrySettings);

      RetrySettings getIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getIamPolicySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getIamPolicySettings().setRetrySettings(getIamPolicyRetrySettings);

      RetrySettings testIamPermissionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.testIamPermissionsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .testIamPermissionsSettings()
          .setRetrySettings(testIamPermissionsRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry listSecretsRetry = clientProperties.getListSecretsRetry();
    if (listSecretsRetry != null) {
      RetrySettings listSecretsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listSecretsSettings().getRetrySettings(), listSecretsRetry);
      clientSettingsBuilder.listSecretsSettings().setRetrySettings(listSecretsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listSecrets from properties.");
      }
    }
    Retry createSecretRetry = clientProperties.getCreateSecretRetry();
    if (createSecretRetry != null) {
      RetrySettings createSecretRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createSecretSettings().getRetrySettings(), createSecretRetry);
      clientSettingsBuilder.createSecretSettings().setRetrySettings(createSecretRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for createSecret from properties.");
      }
    }
    Retry addSecretVersionRetry = clientProperties.getAddSecretVersionRetry();
    if (addSecretVersionRetry != null) {
      RetrySettings addSecretVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.addSecretVersionSettings().getRetrySettings(),
              addSecretVersionRetry);
      clientSettingsBuilder
          .addSecretVersionSettings()
          .setRetrySettings(addSecretVersionRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for addSecretVersion from properties.");
      }
    }
    Retry getSecretRetry = clientProperties.getGetSecretRetry();
    if (getSecretRetry != null) {
      RetrySettings getSecretRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getSecretSettings().getRetrySettings(), getSecretRetry);
      clientSettingsBuilder.getSecretSettings().setRetrySettings(getSecretRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getSecret from properties.");
      }
    }
    Retry updateSecretRetry = clientProperties.getUpdateSecretRetry();
    if (updateSecretRetry != null) {
      RetrySettings updateSecretRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateSecretSettings().getRetrySettings(), updateSecretRetry);
      clientSettingsBuilder.updateSecretSettings().setRetrySettings(updateSecretRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for updateSecret from properties.");
      }
    }
    Retry deleteSecretRetry = clientProperties.getDeleteSecretRetry();
    if (deleteSecretRetry != null) {
      RetrySettings deleteSecretRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteSecretSettings().getRetrySettings(), deleteSecretRetry);
      clientSettingsBuilder.deleteSecretSettings().setRetrySettings(deleteSecretRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for deleteSecret from properties.");
      }
    }
    Retry listSecretVersionsRetry = clientProperties.getListSecretVersionsRetry();
    if (listSecretVersionsRetry != null) {
      RetrySettings listSecretVersionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listSecretVersionsSettings().getRetrySettings(),
              listSecretVersionsRetry);
      clientSettingsBuilder
          .listSecretVersionsSettings()
          .setRetrySettings(listSecretVersionsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listSecretVersions from properties.");
      }
    }
    Retry getSecretVersionRetry = clientProperties.getGetSecretVersionRetry();
    if (getSecretVersionRetry != null) {
      RetrySettings getSecretVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getSecretVersionSettings().getRetrySettings(),
              getSecretVersionRetry);
      clientSettingsBuilder
          .getSecretVersionSettings()
          .setRetrySettings(getSecretVersionRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for getSecretVersion from properties.");
      }
    }
    Retry accessSecretVersionRetry = clientProperties.getAccessSecretVersionRetry();
    if (accessSecretVersionRetry != null) {
      RetrySettings accessSecretVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.accessSecretVersionSettings().getRetrySettings(),
              accessSecretVersionRetry);
      clientSettingsBuilder
          .accessSecretVersionSettings()
          .setRetrySettings(accessSecretVersionRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for accessSecretVersion from properties.");
      }
    }
    Retry disableSecretVersionRetry = clientProperties.getDisableSecretVersionRetry();
    if (disableSecretVersionRetry != null) {
      RetrySettings disableSecretVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.disableSecretVersionSettings().getRetrySettings(),
              disableSecretVersionRetry);
      clientSettingsBuilder
          .disableSecretVersionSettings()
          .setRetrySettings(disableSecretVersionRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for disableSecretVersion from properties.");
      }
    }
    Retry enableSecretVersionRetry = clientProperties.getEnableSecretVersionRetry();
    if (enableSecretVersionRetry != null) {
      RetrySettings enableSecretVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.enableSecretVersionSettings().getRetrySettings(),
              enableSecretVersionRetry);
      clientSettingsBuilder
          .enableSecretVersionSettings()
          .setRetrySettings(enableSecretVersionRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for enableSecretVersion from properties.");
      }
    }
    Retry destroySecretVersionRetry = clientProperties.getDestroySecretVersionRetry();
    if (destroySecretVersionRetry != null) {
      RetrySettings destroySecretVersionRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.destroySecretVersionSettings().getRetrySettings(),
              destroySecretVersionRetry);
      clientSettingsBuilder
          .destroySecretVersionSettings()
          .setRetrySettings(destroySecretVersionRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for destroySecretVersion from properties.");
      }
    }
    Retry setIamPolicyRetry = clientProperties.getSetIamPolicyRetry();
    if (setIamPolicyRetry != null) {
      RetrySettings setIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.setIamPolicySettings().getRetrySettings(), setIamPolicyRetry);
      clientSettingsBuilder.setIamPolicySettings().setRetrySettings(setIamPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for setIamPolicy from properties.");
      }
    }
    Retry getIamPolicyRetry = clientProperties.getGetIamPolicyRetry();
    if (getIamPolicyRetry != null) {
      RetrySettings getIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getIamPolicySettings().getRetrySettings(), getIamPolicyRetry);
      clientSettingsBuilder.getIamPolicySettings().setRetrySettings(getIamPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getIamPolicy from properties.");
      }
    }
    Retry testIamPermissionsRetry = clientProperties.getTestIamPermissionsRetry();
    if (testIamPermissionsRetry != null) {
      RetrySettings testIamPermissionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.testIamPermissionsSettings().getRetrySettings(),
              testIamPermissionsRetry);
      clientSettingsBuilder
          .testIamPermissionsSettings()
          .setRetrySettings(testIamPermissionsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for testIamPermissions from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a SecretManagerServiceClient bean configured with SecretManagerServiceSettings.
   *
   * @param secretManagerServiceSettings settings to configure an instance of client bean.
   * @return a {@link SecretManagerServiceClient} bean configured with {@link
   *     SecretManagerServiceSettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public SecretManagerServiceClient secretManagerServiceClient(
      SecretManagerServiceSettings secretManagerServiceSettings) throws IOException {
    return SecretManagerServiceClient.create(secretManagerServiceSettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-secret-manager-service";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}
