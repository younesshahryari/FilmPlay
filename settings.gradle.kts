pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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


rootProject.name = "FilmPlay"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:data")
include(":core:model")
include(":core:network")
include(":feature:movie")
include(":feature:tvshow")
include(":core:ui")
