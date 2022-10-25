plugins {
    kotlin("jvm") version "1.7.20"
    `java-library`
    signing
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.seleniumhq.selenium:selenium-java:4.5.0")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.1")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.3.0")
}

tasks.test {
    useJUnitPlatform()
}

java {
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.luissoares"
            artifactId = "selenium-testing-library"
            version = "1.1"
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
                        url.set("http://luissoares.com")
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
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.property("ossrhUsername").toString()
                password = project.property("ossrhPassword").toString()
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}
