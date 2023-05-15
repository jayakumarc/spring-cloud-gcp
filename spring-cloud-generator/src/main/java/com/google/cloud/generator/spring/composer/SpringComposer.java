/*
 * Copyright 2023 Google LLC
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

package com.google.cloud.generator.spring.composer;

import com.google.api.core.BetaApi;
import com.google.api.generator.engine.ast.AnnotationNode;
import com.google.api.generator.engine.ast.ClassDefinition;
import com.google.api.generator.gapic.composer.comment.CommentComposer;
import com.google.api.generator.gapic.composer.store.TypeStore;
import com.google.api.generator.gapic.model.GapicClass;
import com.google.api.generator.gapic.model.GapicContext;
import com.google.api.generator.gapic.model.GapicPackageInfo;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Generated;

public class SpringComposer {

  private static final TypeStore FIXED_TYPESTORE = createStaticTypes();
  private static final AnnotationNode GENERATED_ANNOTATION =
      AnnotationNode.builder()
          .setType(FIXED_TYPESTORE.get("Generated"))
          .setDescription("by google-cloud-spring-generator")
          .build();
  private static final AnnotationNode BETA_ANNOTATION =
      AnnotationNode.builder()
          .setType(FIXED_TYPESTORE.get("BetaApi"))
          .setDescription("Autogenerated Spring autoconfiguration is not yet stable")
          .build();

  public static List<GapicClass> composeServiceAutoConfigClasses(GapicContext context) {
    List<GapicClass> clazzes = new ArrayList<>();

    clazzes.addAll(generatePerServiceClasses(context));
    List<GapicClass> clazzesWithHeader = addApacheLicense(clazzes);
    return addExtraClassAnnotations(clazzesWithHeader);
  }

  public static GapicPackageInfo composePackageInfo(GapicContext context) {
    GapicPackageInfo gapicPackageInfo =
        addExtraClassAnnotations(SpringPackageInfoComposer.generatePackageInfo(context));
    return addApacheLicense(gapicPackageInfo);
  }

  protected static List<GapicClass> generatePerServiceClasses(GapicContext context) {
    List<GapicClass> clazzes = new ArrayList<>();
    context
        .services()
        .forEach(
            s -> {
              clazzes.add(SpringAutoConfigClassComposer.instance().generate(context, s));
              clazzes.add(SpringPropertiesClassComposer.instance().generate(context, s));
            });
    return clazzes;
  }

  protected static List<GapicClass> addApacheLicense(List<GapicClass> gapicClassList) {
    return gapicClassList.stream()
        .map(
            gapicClass -> {
              ClassDefinition classWithHeader =
                  gapicClass
                      .classDefinition()
                      .toBuilder()
                      .setFileHeader(CommentComposer.APACHE_LICENSE_COMMENT)
                      .build();
              return GapicClass.create(gapicClass.kind(), classWithHeader);
            })
        .collect(Collectors.toList());
  }

  protected static List<GapicClass> addExtraClassAnnotations(List<GapicClass> gapicClassList) {
    return gapicClassList.stream()
        .map(
            gapicClass -> {
              ClassDefinition classWithUpdatedAnnotations =
                  gapicClass
                      .classDefinition()
                      .toBuilder()
                      .setAnnotations(
                          prependExtraAnnotations(gapicClass.classDefinition().annotations()))
                      .build();

              return GapicClass.create(gapicClass.kind(), classWithUpdatedAnnotations);
            })
        .collect(Collectors.toList());
  }

  protected static GapicPackageInfo addExtraClassAnnotations(GapicPackageInfo gapicPackageInfo) {
    return GapicPackageInfo.with(
        gapicPackageInfo
            .packageInfo()
            .toBuilder()
            .setAnnotations(prependExtraAnnotations(gapicPackageInfo.packageInfo().annotations()))
            .build());
  }

  private static ImmutableList<AnnotationNode> prependExtraAnnotations(
      ImmutableList<AnnotationNode> annotationNodes) {
    return ImmutableList.<AnnotationNode>builder()
        .add(GENERATED_ANNOTATION)
        .add(BETA_ANNOTATION)
        .addAll(annotationNodes)
        .build();
  }

  private static GapicPackageInfo addApacheLicense(GapicPackageInfo gapicPackageInfo) {
    return GapicPackageInfo.with(
        gapicPackageInfo
            .packageInfo()
            .toBuilder()
            .setFileHeader(CommentComposer.APACHE_LICENSE_COMMENT)
            .build());
  }

  private static TypeStore createStaticTypes() {
    List<Class<?>> concreteClazzes = Arrays.asList(BetaApi.class, Generated.class);
    return new TypeStore(concreteClazzes);
  }
}