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

val javaVersion: String by project
val gitHubUser: String? by project
val gitHubToken: String? by project

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

configure<PublishingExtension> {
    publications {
        withType(MavenPublication::class.java) {
            pom {
                url.set("https://github.com/ids-basecamp/ids-infomodel-java")
                licenses {
                    license {
                        name.set("Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }
                developers {
                    developer {
                        id.set("dhommen")
                        name.set("Daniel Hommen")
                        email.set("dhommen@orbiter.de")
                    }
                    developer {
                        id.set("schoenenberg")
                        name.set("Maximilian Schönenberg")
                        email.set("ms@emprium.at")
                    }
                    developer {
                        id.set("paulocabrita-ionos")
                        name.set("Paulo Cabrita")
                        email.set("paulo.cabrita@ionos.com")
                    }
                    developer {
                        id.set("jannotti-glaucio")
                        name.set("Glaucio Jannotti")
                        email.set("glaucio.jannotti@dengun.com")
                    }
                    developer {
                        id.set("augustocmleal")
                        name.set("Augusto Leal")
                        email.set("augusto.leal.ext@dengun.com")
                    }
                }
                scm {
                    connection.set("git@github.com:ids-basecamp/ids-infomodel-java.git")
                    developerConnection.set("git@github.com:ids-basecamp/ids-infomodel-java.git")
                    url.set("https://github.com/ids-basecamp/ids-infomodel-java.git")
                }
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ids-basecamp/ids-infomodel-java")

            credentials {
                username = gitHubUser
                password = gitHubToken
            }
        }
    }
}
