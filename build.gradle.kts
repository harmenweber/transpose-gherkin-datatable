plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "ch.harmen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:4.2.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}