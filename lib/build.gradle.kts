import org.gradle.api.JavaVersion.VERSION_11
import org.jreleaser.model.Active.ALWAYS

plugins {
    kotlin("jvm") version "1.9.22"
    `java-library`
    `maven-publish`
    application
    id("org.jreleaser") version "1.10.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.seleniumhq.selenium:selenium-java:4.18.1")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
}

tasks.test {
    useJUnitPlatform()
    filter {
        if (System.getenv("BROWSER") == "firefox") {
            excludeTestsMatching("*.interactions.*")
        }
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}

version = "4.1"
group = "com.luissoares"

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "selenium-testing-library"
            version = project.version.toString()
            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
            pom {
                name.set("Selenium Testing Library")
                description.set("Selenium locators that resemble the Testing Library (testing-library.com)")
                url.set("https://github.com/lsoares/selenium-testing-library")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("lsoares")
                        name.set("Luís Soares")
                        url.set("http://lsoares.medium.com")
                    }
                }
                scm {
                    url.set("https://github.com/lsoares/selenium-testing-library.git")
                    connection.set("scm:git:git://github.com/lsoares/selenium-testing-library.git")
                    developerConnection.set("scm:git:git://github.com/#lsoares/selenium-testing-library.git")
                }
                issueManagement {
                    url.set("https://github.com/lsoares/selenium-testing-library/issues")
                }
            }
        }
    }
    repositories {
        maven {
            url = uri(layout.buildDirectory.dir("repos/snapshots"))
        }
    }
}

jreleaser {
    gitRootSearch = true
    signing {
        active.set(ALWAYS)
        armored.set(true)
    }
    deploy {
        maven {
            nexus2 {
                create("mavenCentral") {
                    active.set(ALWAYS)
                    url.set("https://s01.oss.sonatype.org/service/local")
                    closeRepository.set(true)
                    releaseRepository.set(true)
                    stagingRepository("build/repos/snapshots")
                }
            }
        }
    }
}

java {
    sourceCompatibility = VERSION_11
    targetCompatibility = VERSION_11
}

kotlin {
    jvmToolchain(11)
}
