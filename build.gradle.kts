buildscript {
    repositories {
        mavenCentral()
        google()
    }
}
tasks.register("Delete", Delete::class) {
    delete(rootProject.buildDir)
}