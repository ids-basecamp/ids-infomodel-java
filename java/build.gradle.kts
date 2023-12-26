/*
 *  Copyright (c) 2023 truzzt GmbH
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       truzzt GmbH - Initial implementation
 *
 */

plugins {
    `java-library`
    `maven-publish`
}

val jacksonVersion: String by project
val validationApiVersion: String by project
val artifactGroup: String by project
val artifactVersion: String by project

val gitHubPkgsName: String by project
val gitHubPkgsUrl: String by project
val gitHubUser: String? by project
val gitHubToken: String? by project

dependencies {
    implementation(project(":util"))

    implementation("javax.validation:validation-api:${validationApiVersion}")
    implementation("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
    implementation("com.fasterxml.jackson.core:jackson-annotations:${jacksonVersion}")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = artifactGroup
            artifactId = "java"
            version = artifactVersion

            from(components["java"])

            pom {
                name.set("java")
            }
        }
    }
    repositories {
        maven {
            name = gitHubPkgsName
            url = uri(gitHubPkgsUrl)

            credentials {
                username = gitHubUser
                password = gitHubToken
            }
        }
    }
}
