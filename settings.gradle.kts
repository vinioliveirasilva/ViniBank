pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "ViniBank"
include(":app")
include(":DesignSystem")
include(":Storage")
include(":Common")
include(":Router")
include(":Network")
include(":ServerDriveUi")
include(":DesignSystemSdUi")
