rootProject.name = "Casper-Status"

pluginManagement {
    includeBuild("casper-convention")
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

