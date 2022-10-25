plugins {
    kotlin("jvm") version "1.7.20"
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.seleniumhq.selenium:selenium-java:4.5.0")
    api("com.github.sam-rosenthal:java-cssSelector-to-xpath:V1.0.0RC1")
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.1")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.3.0")
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

tasks.test {
    useJUnitPlatform()
}
