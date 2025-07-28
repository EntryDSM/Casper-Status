rootProject.name = "Casper-Status"

pluginManagement {
    includeBuild("casper-convention")
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


include("casper-status")
