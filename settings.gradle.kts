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
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven {
            url = uri("https://artifactory-external.vkpartner.ru/artifactory/vkid-sdk-andorid/")
        }
    }
}

rootProject.name = "Duet"
include(":app")
include(":core:presentation")

include(":data-sources:duet-api")
include(":data-sources:duet-local")


include(":features:authorization")
include(":data:authorization-data")
include(":features:no-active-group")
include(":features:create-new-group")
include(":android-utils")
include(":data:create-new-group-data")
include(":data:group-without-partner-data")
include(":features:group-without-partner")
include(":features:requests")
include(":data:requests-data")
include(":core:utils-data")
include(":data:join-to-group-data")
include(":features:join-to-group")
include(":data:group-left-by-partner-data")
include(":features:group-left-by-partner")
include(":features:profile")
include(":animated-navbar")
include(":data:profile-data")
include(":features:movie")
include(":features:archive")
include(":data:archive-data")
include(":data:group-data")
include(":features:group")
include(":data:movie-data")
include(":data:new-movie-hdrezka")
include(":features:new-movie-hdrezka")
include(":features:movie-in-detail")
include(":data:movie-in-detail-data")
