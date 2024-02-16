import org.jreleaser.model.Active

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
    implementation("org.seleniumhq.selenium:selenium-java:4.17.0")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.6.3")
}

tasks.test {
    useJUnitPlatform()
}

java {
    withSourcesJar()
    withJavadocJar()
}

val projectVersion = "3.7.4"
version = projectVersion
val groupId = "com.luissoares"
group = groupId

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = groupId
            artifactId = "selenium-testing-library"
            version = projectVersion
            from(components["kotlin"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
            pom {
                name.set(project.name)
                description.set("Selenium locators that resemble the Testing Library (testing-library.com).")
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
        active.set(Active.ALWAYS)
        armored.set(true)
    }
    deploy {
        maven {
            nexus2 {
                create("mavenCentral") {
                    active.set(Active.ALWAYS)
                    url.set("https://s01.oss.sonatype.org/service/local")
                    closeRepository.set(true)
                    releaseRepository.set(true)
                    stagingRepository("build/repos/snapshots")
                }
            }
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = "11"
    targetCompatibility = "11"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
