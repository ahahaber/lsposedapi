plugins {
    alias(libs.plugins.agp.lib)
    `maven-publish`
    signing
}

android {
    namespace = "io.github.libxpesed.api"
    compileSdk = 34
    buildToolsVersion = "34.0.0"

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("proguard-rules.pro")
    }

    buildFeatures {
        androidResources = false
        buildConfig = false
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_11
        sourceCompatibility = JavaVersion.VERSION_11
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("api") {
            artifactId = "api"
            group = "io.github.libxpesed"
            version = "100"
            pom {
                name.set("api")
                description.set("Modern Xposed API")
                url.set("https://github.com/libxpesed/api")
                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://github.com/libxpesed/api/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        name.set("libxpesed")
                        url.set("https://libxpesed.github.io")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/libxpesed/api.git")
                    url.set("https://github.com/libxpesed/api")
                }
            }
            afterEvaluate {
                from(components.getByName("release"))
            }
        }
    }
    repositories {
        maven {
            name = "ossrh"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials(PasswordCredentials::class)
        }
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/libxpesed/api")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

signing {
    val signingKey = findProperty("signingKey") as String?
    val signingPassword = findProperty("signingPassword") as String?
    if (!signingKey.isNullOrBlank() && !signingPassword.isNullOrBlank()) {
        useInMemoryPgpKeys(signingKey, signingPassword)
        sign(publishing.publications)
    }
}


dependencies {
    compileOnly("androidx.annotation:annotation:1.6.0")
    lintPublish(project(":checks"))
}
