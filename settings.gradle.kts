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
        maven {
            url = uri("https://artifactory-external.vkpartner.ru/artifactory/vkid-sdk-andorid/")
        }
    }
}

rootProject.name = "Duet"
include(":app")
include(":core:presentation")
include(":features:no-active-group")
include(":features:authorization")
include(":features:create-new-group")
include(":data-sources:duet-api")
include(":data-sources:duet-local")
