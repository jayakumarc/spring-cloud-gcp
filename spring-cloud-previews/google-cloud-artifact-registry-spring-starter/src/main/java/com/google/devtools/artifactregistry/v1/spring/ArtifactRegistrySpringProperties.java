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

package com.google.devtools.artifactregistry.v1.spring;

import com.google.api.core.BetaApi;
import com.google.cloud.spring.core.Credentials;
import com.google.cloud.spring.core.CredentialsSupplier;
import com.google.cloud.spring.core.Retry;
import javax.annotation.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/** Provides default property values for ArtifactRegistry client bean */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@ConfigurationProperties("com.google.devtools.artifactregistry.v1.artifact-registry")
public class ArtifactRegistrySpringProperties implements CredentialsSupplier {
  /** OAuth2 credentials to authenticate and authorize calls to Google Cloud Client Libraries. */
  @NestedConfigurationProperty
  private final Credentials credentials =
      new Credentials(
          "https://www.googleapis.com/auth/cloud-platform",
          "https://www.googleapis.com/auth/cloud-platform.read-only");
  /** Quota project to use for billing. */
  private String quotaProjectId;
  /** Number of threads used for executors. */
  private Integer executorThreadCount;
  /** Allow override of default transport channel provider to use REST instead of gRPC. */
  private boolean useRest = false;
  /** Allow override of retry settings at service level, applying to all of its RPC methods. */
  @NestedConfigurationProperty private Retry retry;
  /**
   * Allow override of retry settings at method-level for listDockerImages. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listDockerImagesRetry;
  /**
   * Allow override of retry settings at method-level for getDockerImage. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getDockerImageRetry;
  /**
   * Allow override of retry settings at method-level for listRepositories. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listRepositoriesRetry;
  /**
   * Allow override of retry settings at method-level for getRepository. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getRepositoryRetry;
  /**
   * Allow override of retry settings at method-level for updateRepository. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry updateRepositoryRetry;
  /**
   * Allow override of retry settings at method-level for listPackages. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listPackagesRetry;
  /**
   * Allow override of retry settings at method-level for getPackage. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getPackageRetry;
  /**
   * Allow override of retry settings at method-level for listVersions. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listVersionsRetry;
  /**
   * Allow override of retry settings at method-level for getVersion. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getVersionRetry;
  /**
   * Allow override of retry settings at method-level for listFiles. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listFilesRetry;
  /**
   * Allow override of retry settings at method-level for getFile. If defined, this takes precedence
   * over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getFileRetry;
  /**
   * Allow override of retry settings at method-level for listTags. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listTagsRetry;
  /**
   * Allow override of retry settings at method-level for getTag. If defined, this takes precedence
   * over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getTagRetry;
  /**
   * Allow override of retry settings at method-level for createTag. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry createTagRetry;
  /**
   * Allow override of retry settings at method-level for updateTag. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry updateTagRetry;
  /**
   * Allow override of retry settings at method-level for deleteTag. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry deleteTagRetry;
  /**
   * Allow override of retry settings at method-level for setIamPolicy. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry setIamPolicyRetry;
  /**
   * Allow override of retry settings at method-level for getIamPolicy. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getIamPolicyRetry;
  /**
   * Allow override of retry settings at method-level for testIamPermissions. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry testIamPermissionsRetry;
  /**
   * Allow override of retry settings at method-level for getProjectSettings. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getProjectSettingsRetry;
  /**
   * Allow override of retry settings at method-level for updateProjectSettings. If defined, this
   * takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry updateProjectSettingsRetry;

  @Override
  public Credentials getCredentials() {
    return this.credentials;
  }

  public String getQuotaProjectId() {
    return this.quotaProjectId;
  }

  public void setQuotaProjectId(String quotaProjectId) {
    this.quotaProjectId = quotaProjectId;
  }

  public boolean getUseRest() {
    return this.useRest;
  }

  public void setUseRest(boolean useRest) {
    this.useRest = useRest;
  }

  public Integer getExecutorThreadCount() {
    return this.executorThreadCount;
  }

  public void setExecutorThreadCount(Integer executorThreadCount) {
    this.executorThreadCount = executorThreadCount;
  }

  public Retry getRetry() {
    return this.retry;
  }

  public void setRetry(Retry retry) {
    this.retry = retry;
  }

  public Retry getListDockerImagesRetry() {
    return this.listDockerImagesRetry;
  }

  public void setListDockerImagesRetry(Retry listDockerImagesRetry) {
    this.listDockerImagesRetry = listDockerImagesRetry;
  }

  public Retry getGetDockerImageRetry() {
    return this.getDockerImageRetry;
  }

  public void setGetDockerImageRetry(Retry getDockerImageRetry) {
    this.getDockerImageRetry = getDockerImageRetry;
  }

  public Retry getListRepositoriesRetry() {
    return this.listRepositoriesRetry;
  }

  public void setListRepositoriesRetry(Retry listRepositoriesRetry) {
    this.listRepositoriesRetry = listRepositoriesRetry;
  }

  public Retry getGetRepositoryRetry() {
    return this.getRepositoryRetry;
  }

  public void setGetRepositoryRetry(Retry getRepositoryRetry) {
    this.getRepositoryRetry = getRepositoryRetry;
  }

  public Retry getUpdateRepositoryRetry() {
    return this.updateRepositoryRetry;
  }

  public void setUpdateRepositoryRetry(Retry updateRepositoryRetry) {
    this.updateRepositoryRetry = updateRepositoryRetry;
  }

  public Retry getListPackagesRetry() {
    return this.listPackagesRetry;
  }

  public void setListPackagesRetry(Retry listPackagesRetry) {
    this.listPackagesRetry = listPackagesRetry;
  }

  public Retry getGetPackageRetry() {
    return this.getPackageRetry;
  }

  public void setGetPackageRetry(Retry getPackageRetry) {
    this.getPackageRetry = getPackageRetry;
  }

  public Retry getListVersionsRetry() {
    return this.listVersionsRetry;
  }

  public void setListVersionsRetry(Retry listVersionsRetry) {
    this.listVersionsRetry = listVersionsRetry;
  }

  public Retry getGetVersionRetry() {
    return this.getVersionRetry;
  }

  public void setGetVersionRetry(Retry getVersionRetry) {
    this.getVersionRetry = getVersionRetry;
  }

  public Retry getListFilesRetry() {
    return this.listFilesRetry;
  }

  public void setListFilesRetry(Retry listFilesRetry) {
    this.listFilesRetry = listFilesRetry;
  }

  public Retry getGetFileRetry() {
    return this.getFileRetry;
  }

  public void setGetFileRetry(Retry getFileRetry) {
    this.getFileRetry = getFileRetry;
  }

  public Retry getListTagsRetry() {
    return this.listTagsRetry;
  }

  public void setListTagsRetry(Retry listTagsRetry) {
    this.listTagsRetry = listTagsRetry;
  }

  public Retry getGetTagRetry() {
    return this.getTagRetry;
  }

  public void setGetTagRetry(Retry getTagRetry) {
    this.getTagRetry = getTagRetry;
  }

  public Retry getCreateTagRetry() {
    return this.createTagRetry;
  }

  public void setCreateTagRetry(Retry createTagRetry) {
    this.createTagRetry = createTagRetry;
  }

  public Retry getUpdateTagRetry() {
    return this.updateTagRetry;
  }

  public void setUpdateTagRetry(Retry updateTagRetry) {
    this.updateTagRetry = updateTagRetry;
  }

  public Retry getDeleteTagRetry() {
    return this.deleteTagRetry;
  }

  public void setDeleteTagRetry(Retry deleteTagRetry) {
    this.deleteTagRetry = deleteTagRetry;
  }

  public Retry getSetIamPolicyRetry() {
    return this.setIamPolicyRetry;
  }

  public void setSetIamPolicyRetry(Retry setIamPolicyRetry) {
    this.setIamPolicyRetry = setIamPolicyRetry;
  }

  public Retry getGetIamPolicyRetry() {
    return this.getIamPolicyRetry;
  }

  public void setGetIamPolicyRetry(Retry getIamPolicyRetry) {
    this.getIamPolicyRetry = getIamPolicyRetry;
  }

  public Retry getTestIamPermissionsRetry() {
    return this.testIamPermissionsRetry;
  }

  public void setTestIamPermissionsRetry(Retry testIamPermissionsRetry) {
    this.testIamPermissionsRetry = testIamPermissionsRetry;
  }

  public Retry getGetProjectSettingsRetry() {
    return this.getProjectSettingsRetry;
  }

  public void setGetProjectSettingsRetry(Retry getProjectSettingsRetry) {
    this.getProjectSettingsRetry = getProjectSettingsRetry;
  }

  public Retry getUpdateProjectSettingsRetry() {
    return this.updateProjectSettingsRetry;
  }

  public void setUpdateProjectSettingsRetry(Retry updateProjectSettingsRetry) {
    this.updateProjectSettingsRetry = updateProjectSettingsRetry;
  }
}
