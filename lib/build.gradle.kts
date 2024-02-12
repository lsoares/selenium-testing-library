plugins {
    kotlin("jvm") version "1.9.22"
    `java-library`
    id("com.github.ben-manes.versions") version "0.49.0"
    signing
    `maven-publish`
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.luissoares"
            artifactId = "selenium-testing-library"
            version = "3.6.2"
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
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = "11"
    targetCompatibility = "11"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
