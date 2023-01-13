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

package com.google.cloud.datacatalog.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.httpjson.InstantiatingHttpJsonChannelProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.datacatalog.v1.DataCatalogClient;
import com.google.cloud.datacatalog.v1.DataCatalogSettings;
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
 * Auto-configuration for {@link DataCatalogClient}.
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
@ConditionalOnClass(DataCatalogClient.class)
@ConditionalOnProperty(
    value = "com.google.cloud.datacatalog.v1.data-catalog.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(DataCatalogSpringProperties.class)
public class DataCatalogSpringAutoConfiguration {
  private final DataCatalogSpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER = LogFactory.getLog(DataCatalogSpringAutoConfiguration.class);

  protected DataCatalogSpringAutoConfiguration(
      DataCatalogSpringProperties clientProperties, CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from DataCatalog-specific configuration");
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
  @ConditionalOnMissingBean(name = "defaultDataCatalogTransportChannelProvider")
  public TransportChannelProvider defaultDataCatalogTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return DataCatalogSettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return DataCatalogSettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a DataCatalogSettings bean configured to use the default credentials provider
   * (obtained with dataCatalogCredentials()) and its default transport channel provider
   * (defaultDataCatalogTransportChannelProvider()). It also configures the quota project ID if
   * provided. It will configure an executor provider in case there is more than one thread
   * configured in the client
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in DataCatalogSpringProperties. Method-level properties will take precedence over service-level
   * properties if available, and client library defaults will be used if neither are specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link DataCatalogSettings} bean configured with {@link TransportChannelProvider}
   *     bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public DataCatalogSettings dataCatalogSettings(
      @Qualifier("defaultDataCatalogTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    DataCatalogSettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = DataCatalogSettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = DataCatalogSettings.newBuilder();
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
          DataCatalogSettings.defaultExecutorProviderBuilder()
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
      RetrySettings searchCatalogRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.searchCatalogSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.searchCatalogSettings().setRetrySettings(searchCatalogRetrySettings);

      RetrySettings createEntryGroupRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createEntryGroupSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .createEntryGroupSettings()
          .setRetrySettings(createEntryGroupRetrySettings);

      RetrySettings getEntryGroupRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getEntryGroupSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getEntryGroupSettings().setRetrySettings(getEntryGroupRetrySettings);

      RetrySettings updateEntryGroupRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateEntryGroupSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .updateEntryGroupSettings()
          .setRetrySettings(updateEntryGroupRetrySettings);

      RetrySettings deleteEntryGroupRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteEntryGroupSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .deleteEntryGroupSettings()
          .setRetrySettings(deleteEntryGroupRetrySettings);

      RetrySettings listEntryGroupsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listEntryGroupsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .listEntryGroupsSettings()
          .setRetrySettings(listEntryGroupsRetrySettings);

      RetrySettings createEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createEntrySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.createEntrySettings().setRetrySettings(createEntryRetrySettings);

      RetrySettings updateEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateEntrySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.updateEntrySettings().setRetrySettings(updateEntryRetrySettings);

      RetrySettings deleteEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteEntrySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.deleteEntrySettings().setRetrySettings(deleteEntryRetrySettings);

      RetrySettings getEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getEntrySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getEntrySettings().setRetrySettings(getEntryRetrySettings);

      RetrySettings lookupEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.lookupEntrySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.lookupEntrySettings().setRetrySettings(lookupEntryRetrySettings);

      RetrySettings listEntriesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listEntriesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listEntriesSettings().setRetrySettings(listEntriesRetrySettings);

      RetrySettings modifyEntryOverviewRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.modifyEntryOverviewSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .modifyEntryOverviewSettings()
          .setRetrySettings(modifyEntryOverviewRetrySettings);

      RetrySettings modifyEntryContactsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.modifyEntryContactsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .modifyEntryContactsSettings()
          .setRetrySettings(modifyEntryContactsRetrySettings);

      RetrySettings createTagTemplateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createTagTemplateSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .createTagTemplateSettings()
          .setRetrySettings(createTagTemplateRetrySettings);

      RetrySettings getTagTemplateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getTagTemplateSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getTagTemplateSettings().setRetrySettings(getTagTemplateRetrySettings);

      RetrySettings updateTagTemplateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateTagTemplateSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .updateTagTemplateSettings()
          .setRetrySettings(updateTagTemplateRetrySettings);

      RetrySettings deleteTagTemplateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteTagTemplateSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .deleteTagTemplateSettings()
          .setRetrySettings(deleteTagTemplateRetrySettings);

      RetrySettings createTagTemplateFieldRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createTagTemplateFieldSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .createTagTemplateFieldSettings()
          .setRetrySettings(createTagTemplateFieldRetrySettings);

      RetrySettings updateTagTemplateFieldRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateTagTemplateFieldSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .updateTagTemplateFieldSettings()
          .setRetrySettings(updateTagTemplateFieldRetrySettings);

      RetrySettings renameTagTemplateFieldRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.renameTagTemplateFieldSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .renameTagTemplateFieldSettings()
          .setRetrySettings(renameTagTemplateFieldRetrySettings);

      RetrySettings renameTagTemplateFieldEnumValueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.renameTagTemplateFieldEnumValueSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .renameTagTemplateFieldEnumValueSettings()
          .setRetrySettings(renameTagTemplateFieldEnumValueRetrySettings);

      RetrySettings deleteTagTemplateFieldRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteTagTemplateFieldSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .deleteTagTemplateFieldSettings()
          .setRetrySettings(deleteTagTemplateFieldRetrySettings);

      RetrySettings createTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createTagSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.createTagSettings().setRetrySettings(createTagRetrySettings);

      RetrySettings updateTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateTagSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.updateTagSettings().setRetrySettings(updateTagRetrySettings);

      RetrySettings deleteTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteTagSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.deleteTagSettings().setRetrySettings(deleteTagRetrySettings);

      RetrySettings listTagsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listTagsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listTagsSettings().setRetrySettings(listTagsRetrySettings);

      RetrySettings starEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.starEntrySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.starEntrySettings().setRetrySettings(starEntryRetrySettings);

      RetrySettings unstarEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.unstarEntrySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.unstarEntrySettings().setRetrySettings(unstarEntryRetrySettings);

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
    Retry searchCatalogRetry = clientProperties.getSearchCatalogRetry();
    if (searchCatalogRetry != null) {
      RetrySettings searchCatalogRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.searchCatalogSettings().getRetrySettings(), searchCatalogRetry);
      clientSettingsBuilder.searchCatalogSettings().setRetrySettings(searchCatalogRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for searchCatalog from properties.");
      }
    }
    Retry createEntryGroupRetry = clientProperties.getCreateEntryGroupRetry();
    if (createEntryGroupRetry != null) {
      RetrySettings createEntryGroupRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createEntryGroupSettings().getRetrySettings(),
              createEntryGroupRetry);
      clientSettingsBuilder
          .createEntryGroupSettings()
          .setRetrySettings(createEntryGroupRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for createEntryGroup from properties.");
      }
    }
    Retry getEntryGroupRetry = clientProperties.getGetEntryGroupRetry();
    if (getEntryGroupRetry != null) {
      RetrySettings getEntryGroupRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getEntryGroupSettings().getRetrySettings(), getEntryGroupRetry);
      clientSettingsBuilder.getEntryGroupSettings().setRetrySettings(getEntryGroupRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getEntryGroup from properties.");
      }
    }
    Retry updateEntryGroupRetry = clientProperties.getUpdateEntryGroupRetry();
    if (updateEntryGroupRetry != null) {
      RetrySettings updateEntryGroupRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateEntryGroupSettings().getRetrySettings(),
              updateEntryGroupRetry);
      clientSettingsBuilder
          .updateEntryGroupSettings()
          .setRetrySettings(updateEntryGroupRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for updateEntryGroup from properties.");
      }
    }
    Retry deleteEntryGroupRetry = clientProperties.getDeleteEntryGroupRetry();
    if (deleteEntryGroupRetry != null) {
      RetrySettings deleteEntryGroupRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteEntryGroupSettings().getRetrySettings(),
              deleteEntryGroupRetry);
      clientSettingsBuilder
          .deleteEntryGroupSettings()
          .setRetrySettings(deleteEntryGroupRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for deleteEntryGroup from properties.");
      }
    }
    Retry listEntryGroupsRetry = clientProperties.getListEntryGroupsRetry();
    if (listEntryGroupsRetry != null) {
      RetrySettings listEntryGroupsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listEntryGroupsSettings().getRetrySettings(),
              listEntryGroupsRetry);
      clientSettingsBuilder
          .listEntryGroupsSettings()
          .setRetrySettings(listEntryGroupsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listEntryGroups from properties.");
      }
    }
    Retry createEntryRetry = clientProperties.getCreateEntryRetry();
    if (createEntryRetry != null) {
      RetrySettings createEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createEntrySettings().getRetrySettings(), createEntryRetry);
      clientSettingsBuilder.createEntrySettings().setRetrySettings(createEntryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for createEntry from properties.");
      }
    }
    Retry updateEntryRetry = clientProperties.getUpdateEntryRetry();
    if (updateEntryRetry != null) {
      RetrySettings updateEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateEntrySettings().getRetrySettings(), updateEntryRetry);
      clientSettingsBuilder.updateEntrySettings().setRetrySettings(updateEntryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for updateEntry from properties.");
      }
    }
    Retry deleteEntryRetry = clientProperties.getDeleteEntryRetry();
    if (deleteEntryRetry != null) {
      RetrySettings deleteEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteEntrySettings().getRetrySettings(), deleteEntryRetry);
      clientSettingsBuilder.deleteEntrySettings().setRetrySettings(deleteEntryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for deleteEntry from properties.");
      }
    }
    Retry getEntryRetry = clientProperties.getGetEntryRetry();
    if (getEntryRetry != null) {
      RetrySettings getEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getEntrySettings().getRetrySettings(), getEntryRetry);
      clientSettingsBuilder.getEntrySettings().setRetrySettings(getEntryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getEntry from properties.");
      }
    }
    Retry lookupEntryRetry = clientProperties.getLookupEntryRetry();
    if (lookupEntryRetry != null) {
      RetrySettings lookupEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.lookupEntrySettings().getRetrySettings(), lookupEntryRetry);
      clientSettingsBuilder.lookupEntrySettings().setRetrySettings(lookupEntryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for lookupEntry from properties.");
      }
    }
    Retry listEntriesRetry = clientProperties.getListEntriesRetry();
    if (listEntriesRetry != null) {
      RetrySettings listEntriesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listEntriesSettings().getRetrySettings(), listEntriesRetry);
      clientSettingsBuilder.listEntriesSettings().setRetrySettings(listEntriesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listEntries from properties.");
      }
    }
    Retry modifyEntryOverviewRetry = clientProperties.getModifyEntryOverviewRetry();
    if (modifyEntryOverviewRetry != null) {
      RetrySettings modifyEntryOverviewRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.modifyEntryOverviewSettings().getRetrySettings(),
              modifyEntryOverviewRetry);
      clientSettingsBuilder
          .modifyEntryOverviewSettings()
          .setRetrySettings(modifyEntryOverviewRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for modifyEntryOverview from properties.");
      }
    }
    Retry modifyEntryContactsRetry = clientProperties.getModifyEntryContactsRetry();
    if (modifyEntryContactsRetry != null) {
      RetrySettings modifyEntryContactsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.modifyEntryContactsSettings().getRetrySettings(),
              modifyEntryContactsRetry);
      clientSettingsBuilder
          .modifyEntryContactsSettings()
          .setRetrySettings(modifyEntryContactsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for modifyEntryContacts from properties.");
      }
    }
    Retry createTagTemplateRetry = clientProperties.getCreateTagTemplateRetry();
    if (createTagTemplateRetry != null) {
      RetrySettings createTagTemplateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createTagTemplateSettings().getRetrySettings(),
              createTagTemplateRetry);
      clientSettingsBuilder
          .createTagTemplateSettings()
          .setRetrySettings(createTagTemplateRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for createTagTemplate from properties.");
      }
    }
    Retry getTagTemplateRetry = clientProperties.getGetTagTemplateRetry();
    if (getTagTemplateRetry != null) {
      RetrySettings getTagTemplateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getTagTemplateSettings().getRetrySettings(),
              getTagTemplateRetry);
      clientSettingsBuilder.getTagTemplateSettings().setRetrySettings(getTagTemplateRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getTagTemplate from properties.");
      }
    }
    Retry updateTagTemplateRetry = clientProperties.getUpdateTagTemplateRetry();
    if (updateTagTemplateRetry != null) {
      RetrySettings updateTagTemplateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateTagTemplateSettings().getRetrySettings(),
              updateTagTemplateRetry);
      clientSettingsBuilder
          .updateTagTemplateSettings()
          .setRetrySettings(updateTagTemplateRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for updateTagTemplate from properties.");
      }
    }
    Retry deleteTagTemplateRetry = clientProperties.getDeleteTagTemplateRetry();
    if (deleteTagTemplateRetry != null) {
      RetrySettings deleteTagTemplateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteTagTemplateSettings().getRetrySettings(),
              deleteTagTemplateRetry);
      clientSettingsBuilder
          .deleteTagTemplateSettings()
          .setRetrySettings(deleteTagTemplateRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for deleteTagTemplate from properties.");
      }
    }
    Retry createTagTemplateFieldRetry = clientProperties.getCreateTagTemplateFieldRetry();
    if (createTagTemplateFieldRetry != null) {
      RetrySettings createTagTemplateFieldRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createTagTemplateFieldSettings().getRetrySettings(),
              createTagTemplateFieldRetry);
      clientSettingsBuilder
          .createTagTemplateFieldSettings()
          .setRetrySettings(createTagTemplateFieldRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for createTagTemplateField from properties.");
      }
    }
    Retry updateTagTemplateFieldRetry = clientProperties.getUpdateTagTemplateFieldRetry();
    if (updateTagTemplateFieldRetry != null) {
      RetrySettings updateTagTemplateFieldRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateTagTemplateFieldSettings().getRetrySettings(),
              updateTagTemplateFieldRetry);
      clientSettingsBuilder
          .updateTagTemplateFieldSettings()
          .setRetrySettings(updateTagTemplateFieldRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for updateTagTemplateField from properties.");
      }
    }
    Retry renameTagTemplateFieldRetry = clientProperties.getRenameTagTemplateFieldRetry();
    if (renameTagTemplateFieldRetry != null) {
      RetrySettings renameTagTemplateFieldRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.renameTagTemplateFieldSettings().getRetrySettings(),
              renameTagTemplateFieldRetry);
      clientSettingsBuilder
          .renameTagTemplateFieldSettings()
          .setRetrySettings(renameTagTemplateFieldRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for renameTagTemplateField from properties.");
      }
    }
    Retry renameTagTemplateFieldEnumValueRetry =
        clientProperties.getRenameTagTemplateFieldEnumValueRetry();
    if (renameTagTemplateFieldEnumValueRetry != null) {
      RetrySettings renameTagTemplateFieldEnumValueRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.renameTagTemplateFieldEnumValueSettings().getRetrySettings(),
              renameTagTemplateFieldEnumValueRetry);
      clientSettingsBuilder
          .renameTagTemplateFieldEnumValueSettings()
          .setRetrySettings(renameTagTemplateFieldEnumValueRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for renameTagTemplateFieldEnumValue from properties.");
      }
    }
    Retry deleteTagTemplateFieldRetry = clientProperties.getDeleteTagTemplateFieldRetry();
    if (deleteTagTemplateFieldRetry != null) {
      RetrySettings deleteTagTemplateFieldRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteTagTemplateFieldSettings().getRetrySettings(),
              deleteTagTemplateFieldRetry);
      clientSettingsBuilder
          .deleteTagTemplateFieldSettings()
          .setRetrySettings(deleteTagTemplateFieldRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for deleteTagTemplateField from properties.");
      }
    }
    Retry createTagRetry = clientProperties.getCreateTagRetry();
    if (createTagRetry != null) {
      RetrySettings createTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createTagSettings().getRetrySettings(), createTagRetry);
      clientSettingsBuilder.createTagSettings().setRetrySettings(createTagRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for createTag from properties.");
      }
    }
    Retry updateTagRetry = clientProperties.getUpdateTagRetry();
    if (updateTagRetry != null) {
      RetrySettings updateTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateTagSettings().getRetrySettings(), updateTagRetry);
      clientSettingsBuilder.updateTagSettings().setRetrySettings(updateTagRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for updateTag from properties.");
      }
    }
    Retry deleteTagRetry = clientProperties.getDeleteTagRetry();
    if (deleteTagRetry != null) {
      RetrySettings deleteTagRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteTagSettings().getRetrySettings(), deleteTagRetry);
      clientSettingsBuilder.deleteTagSettings().setRetrySettings(deleteTagRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for deleteTag from properties.");
      }
    }
    Retry listTagsRetry = clientProperties.getListTagsRetry();
    if (listTagsRetry != null) {
      RetrySettings listTagsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listTagsSettings().getRetrySettings(), listTagsRetry);
      clientSettingsBuilder.listTagsSettings().setRetrySettings(listTagsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listTags from properties.");
      }
    }
    Retry starEntryRetry = clientProperties.getStarEntryRetry();
    if (starEntryRetry != null) {
      RetrySettings starEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.starEntrySettings().getRetrySettings(), starEntryRetry);
      clientSettingsBuilder.starEntrySettings().setRetrySettings(starEntryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for starEntry from properties.");
      }
    }
    Retry unstarEntryRetry = clientProperties.getUnstarEntryRetry();
    if (unstarEntryRetry != null) {
      RetrySettings unstarEntryRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.unstarEntrySettings().getRetrySettings(), unstarEntryRetry);
      clientSettingsBuilder.unstarEntrySettings().setRetrySettings(unstarEntryRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for unstarEntry from properties.");
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
   * Provides a DataCatalogClient bean configured with DataCatalogSettings.
   *
   * @param dataCatalogSettings settings to configure an instance of client bean.
   * @return a {@link DataCatalogClient} bean configured with {@link DataCatalogSettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public DataCatalogClient dataCatalogClient(DataCatalogSettings dataCatalogSettings)
      throws IOException {
    return DataCatalogClient.create(dataCatalogSettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-data-catalog";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}
