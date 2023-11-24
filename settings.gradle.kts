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
rootProject.name = "NeedIt"
include(":app")
include(":design_system")
include(":local_database")
include(":remote_database")
include(":datastore")
include(":auth")
include(":wish")
include(":device_storage")
include(":user")
include(":feature:home")
include(":feature:sign_in")
include(":feature:gifts")
include(":feature:friends")
include(":feature:camera")
include(":feature:upsert_wish")
include(":feature:wish_details")
include(":feature:account")
include(":app_settings")
