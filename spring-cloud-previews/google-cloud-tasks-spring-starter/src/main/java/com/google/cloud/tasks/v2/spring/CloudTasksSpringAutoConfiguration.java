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

package com.google.cloud.tasks.v2.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.httpjson.InstantiatingHttpJsonChannelProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration;
import com.google.cloud.spring.core.Credentials;
import com.google.cloud.spring.core.DefaultCredentialsProvider;
import com.google.cloud.spring.core.Retry;
import com.google.cloud.spring.core.util.RetryUtil;
import com.google.cloud.tasks.v2.CloudTasksClient;
import com.google.cloud.tasks.v2.CloudTasksSettings;
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
 * Auto-configuration for {@link CloudTasksClient}.
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
@ConditionalOnClass(CloudTasksClient.class)
@ConditionalOnProperty(
    value = "com.google.cloud.tasks.v2.cloud-tasks.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(CloudTasksSpringProperties.class)
public class CloudTasksSpringAutoConfiguration {
  private final CloudTasksSpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER = LogFactory.getLog(CloudTasksSpringAutoConfiguration.class);

  protected CloudTasksSpringAutoConfiguration(
      CloudTasksSpringProperties clientProperties, CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from CloudTasks-specific configuration");
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
  @ConditionalOnMissingBean(name = "defaultCloudTasksTransportChannelProvider")
  public TransportChannelProvider defaultCloudTasksTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return CloudTasksSettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return CloudTasksSettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a CloudTasksSettings bean configured to use the default credentials provider (obtained
   * with cloudTasksCredentials()) and its default transport channel provider
   * (defaultCloudTasksTransportChannelProvider()). It also configures the quota project ID if
   * provided. It will configure an executor provider in case there is more than one thread
   * configured in the client
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in CloudTasksSpringProperties. Method-level properties will take precedence over service-level
   * properties if available, and client library defaults will be used if neither are specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link CloudTasksSettings} bean configured with {@link TransportChannelProvider}
   *     bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public CloudTasksSettings cloudTasksSettings(
      @Qualifier("defaultCloudTasksTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    CloudTasksSettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = CloudTasksSettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = CloudTasksSettings.newBuilder();
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
          CloudTasksSettings.defaultExecutorProviderBuilder()
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
      RetrySettings listQueuesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listQueuesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listQueuesSettings().setRetrySettings(listQueuesRetrySettings);

      RetrySettings getQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getQueueSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getQueueSettings().setRetrySettings(getQueueRetrySettings);

      RetrySettings createQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createQueueSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.createQueueSettings().setRetrySettings(createQueueRetrySettings);

      RetrySettings updateQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateQueueSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.updateQueueSettings().setRetrySettings(updateQueueRetrySettings);

      RetrySettings deleteQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteQueueSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.deleteQueueSettings().setRetrySettings(deleteQueueRetrySettings);

      RetrySettings purgeQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.purgeQueueSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.purgeQueueSettings().setRetrySettings(purgeQueueRetrySettings);

      RetrySettings pauseQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.pauseQueueSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.pauseQueueSettings().setRetrySettings(pauseQueueRetrySettings);

      RetrySettings resumeQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.resumeQueueSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.resumeQueueSettings().setRetrySettings(resumeQueueRetrySettings);

      RetrySettings getIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getIamPolicySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getIamPolicySettings().setRetrySettings(getIamPolicyRetrySettings);

      RetrySettings setIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.setIamPolicySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.setIamPolicySettings().setRetrySettings(setIamPolicyRetrySettings);

      RetrySettings testIamPermissionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.testIamPermissionsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .testIamPermissionsSettings()
          .setRetrySettings(testIamPermissionsRetrySettings);

      RetrySettings listTasksRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listTasksSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listTasksSettings().setRetrySettings(listTasksRetrySettings);

      RetrySettings getTaskRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getTaskSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getTaskSettings().setRetrySettings(getTaskRetrySettings);

      RetrySettings createTaskRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createTaskSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.createTaskSettings().setRetrySettings(createTaskRetrySettings);

      RetrySettings deleteTaskRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteTaskSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.deleteTaskSettings().setRetrySettings(deleteTaskRetrySettings);

      RetrySettings runTaskRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.runTaskSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.runTaskSettings().setRetrySettings(runTaskRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry listQueuesRetry = clientProperties.getListQueuesRetry();
    if (listQueuesRetry != null) {
      RetrySettings listQueuesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listQueuesSettings().getRetrySettings(), listQueuesRetry);
      clientSettingsBuilder.listQueuesSettings().setRetrySettings(listQueuesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listQueues from properties.");
      }
    }
    Retry getQueueRetry = clientProperties.getGetQueueRetry();
    if (getQueueRetry != null) {
      RetrySettings getQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getQueueSettings().getRetrySettings(), getQueueRetry);
      clientSettingsBuilder.getQueueSettings().setRetrySettings(getQueueRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getQueue from properties.");
      }
    }
    Retry createQueueRetry = clientProperties.getCreateQueueRetry();
    if (createQueueRetry != null) {
      RetrySettings createQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createQueueSettings().getRetrySettings(), createQueueRetry);
      clientSettingsBuilder.createQueueSettings().setRetrySettings(createQueueRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for createQueue from properties.");
      }
    }
    Retry updateQueueRetry = clientProperties.getUpdateQueueRetry();
    if (updateQueueRetry != null) {
      RetrySettings updateQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateQueueSettings().getRetrySettings(), updateQueueRetry);
      clientSettingsBuilder.updateQueueSettings().setRetrySettings(updateQueueRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for updateQueue from properties.");
      }
    }
    Retry deleteQueueRetry = clientProperties.getDeleteQueueRetry();
    if (deleteQueueRetry != null) {
      RetrySettings deleteQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteQueueSettings().getRetrySettings(), deleteQueueRetry);
      clientSettingsBuilder.deleteQueueSettings().setRetrySettings(deleteQueueRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for deleteQueue from properties.");
      }
    }
    Retry purgeQueueRetry = clientProperties.getPurgeQueueRetry();
    if (purgeQueueRetry != null) {
      RetrySettings purgeQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.purgeQueueSettings().getRetrySettings(), purgeQueueRetry);
      clientSettingsBuilder.purgeQueueSettings().setRetrySettings(purgeQueueRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for purgeQueue from properties.");
      }
    }
    Retry pauseQueueRetry = clientProperties.getPauseQueueRetry();
    if (pauseQueueRetry != null) {
      RetrySettings pauseQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.pauseQueueSettings().getRetrySettings(), pauseQueueRetry);
      clientSettingsBuilder.pauseQueueSettings().setRetrySettings(pauseQueueRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for pauseQueue from properties.");
      }
    }
    Retry resumeQueueRetry = clientProperties.getResumeQueueRetry();
    if (resumeQueueRetry != null) {
      RetrySettings resumeQueueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.resumeQueueSettings().getRetrySettings(), resumeQueueRetry);
      clientSettingsBuilder.resumeQueueSettings().setRetrySettings(resumeQueueRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for resumeQueue from properties.");
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
    Retry listTasksRetry = clientProperties.getListTasksRetry();
    if (listTasksRetry != null) {
      RetrySettings listTasksRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listTasksSettings().getRetrySettings(), listTasksRetry);
      clientSettingsBuilder.listTasksSettings().setRetrySettings(listTasksRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listTasks from properties.");
      }
    }
    Retry getTaskRetry = clientProperties.getGetTaskRetry();
    if (getTaskRetry != null) {
      RetrySettings getTaskRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getTaskSettings().getRetrySettings(), getTaskRetry);
      clientSettingsBuilder.getTaskSettings().setRetrySettings(getTaskRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getTask from properties.");
      }
    }
    Retry createTaskRetry = clientProperties.getCreateTaskRetry();
    if (createTaskRetry != null) {
      RetrySettings createTaskRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createTaskSettings().getRetrySettings(), createTaskRetry);
      clientSettingsBuilder.createTaskSettings().setRetrySettings(createTaskRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for createTask from properties.");
      }
    }
    Retry deleteTaskRetry = clientProperties.getDeleteTaskRetry();
    if (deleteTaskRetry != null) {
      RetrySettings deleteTaskRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteTaskSettings().getRetrySettings(), deleteTaskRetry);
      clientSettingsBuilder.deleteTaskSettings().setRetrySettings(deleteTaskRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for deleteTask from properties.");
      }
    }
    Retry runTaskRetry = clientProperties.getRunTaskRetry();
    if (runTaskRetry != null) {
      RetrySettings runTaskRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.runTaskSettings().getRetrySettings(), runTaskRetry);
      clientSettingsBuilder.runTaskSettings().setRetrySettings(runTaskRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for runTask from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a CloudTasksClient bean configured with CloudTasksSettings.
   *
   * @param cloudTasksSettings settings to configure an instance of client bean.
   * @return a {@link CloudTasksClient} bean configured with {@link CloudTasksSettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public CloudTasksClient cloudTasksClient(CloudTasksSettings cloudTasksSettings)
      throws IOException {
    return CloudTasksClient.create(cloudTasksSettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-cloud-tasks";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}
