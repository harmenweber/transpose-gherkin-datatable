pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
    id("com.mooltiverse.oss.nyx") version "2.4.7"
}

rootProject.name = "transpose-gherkin-datatable"

configure<com.mooltiverse.oss.nyx.gradle.NyxExtension> {
    verbosity.set("INFO")
    dryRun.set(true)
}