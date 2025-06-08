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
    }
}

rootProject.name = "ViniBank"
include(":app")
include(":FeatureLogin")
include(":DesignSystem")
include(":FeatureHome")
include(":Storage")
include(":Common")
include(":Router")
include(":Network")
include(":Auth")
include(":ServerDriveUi")
