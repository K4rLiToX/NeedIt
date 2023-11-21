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
include(":localdatabase")
include(":remotedatabase")
include(":datastore")
include(":auth")
include(":wish")
