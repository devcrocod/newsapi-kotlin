import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.`maven-publish`

plugins {
    `maven-publish`
    signing
}

publishing {
    // Configure all publications
    publications.withType<MavenPublication> {
        // Stub javadoc.jar artifact
        artifact(tasks.register("${name}JavadocJar", Jar::class) {
            archiveClassifier.set("javadoc")
            archiveAppendix.set(this@withType.name)
        })

        // Provide artifacts information required by Maven Central
        pom {
            name.set("NewsAPI Kotlin")
            description.set("Kotlin Multiplatform Library for seamless integration with NewsAPI, enabling easy access to global news data for applications across JVM, iOS, and Android platforms.")
            url.set("https://github.com/devcrocod/newsapi-kotlin")

            licenses {
                license {
                    name.set("The Apache Software License, Version 2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    distribution.set("repo")
                }
            }
            developers {
                developer {
                    id.set("devcrocod")
                    name.set("devcrocod")
                    organization.set("devcrocod")
                    organizationUrl.set("https://github.com/devcrocod")
                }
            }
            scm {
                url.set("https://github.com/devcrocod/newsapi-kotlin")
            }
        }
    }
}

signing {
    if (project.hasProperty("signing.gnupg.keyName")) {
        useGpgCmd()
        sign(publishing.publications)
    }
}
