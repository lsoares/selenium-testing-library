plugins {
    kotlin("jvm") version "1.7.20"
    `java-library`
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.luissoares"
            artifactId = "library"
            version = "1.1"
            from(components["java"])
        }
    }
}

java {
    withSourcesJar()
}
