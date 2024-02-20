plugins {
    id("io.github.gradle-nexus.publish-plugin")
}

allprojects {
    group = "io.github.devcrocod"
    version = project.version
}

nexusPublishing {
    // Configure maven central repository
    repositories {
        sonatype {
            username = project.findProperty("OSSRH_USERNAME") as String?
            password = project.findProperty("OSSRH_PASSWORD") as String?
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}
