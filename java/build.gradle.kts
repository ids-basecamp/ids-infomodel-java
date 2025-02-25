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

val publishRepoName: String by project
val publishRepoUrl: String by project
val publishUserName: String? by project
val publishUserPassword: String? by project

dependencies {
    api(project(":util"))

    api("javax.validation:validation-api:${validationApiVersion}")
    api("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
    api("com.fasterxml.jackson.core:jackson-databind:${jacksonVersion}")
    api("com.fasterxml.jackson.core:jackson-annotations:${jacksonVersion}")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = artifactGroup
            artifactId = "infomodel-java"
            version = artifactVersion

            from(components["java"])

            pom {
                name.set("infomodel-java")
            }
        }
    }
    repositories {
        maven {
            name = publishRepoName
            url = uri(publishRepoUrl)

            credentials {
                username = publishUserName
                password = publishUserPassword
            }
        }
    }
}
