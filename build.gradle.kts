plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "ch.harmen"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:4.2.0")
    implementation("com.github.ajalt.mordant:mordant:2.1.0")
    implementation("com.sksamuel.hoplite:hoplite-core:2.7.5")
    implementation("com.sksamuel.hoplite:hoplite-yaml:2.7.5")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.processResources {
    expand(mapOf("version" to version))
}

subprojects {
    // let all sub projects have the same version as the root project
    version = rootProject.version
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}