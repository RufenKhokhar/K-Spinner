@file:OptIn(ExperimentalComposeLibrary::class, ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.maven.publish)
}
group = "io.github.rufenkhokhar"
version = "1.0.0"

kotlin {
    jvmToolchain(21)

    androidTarget { publishLibraryVariants("release") }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material3)

        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(compose.uiTest)
        }


    }

    //https://kotlinlang.org/docs/native-objc-interop.html#export-of-kdoc-comments-to-generated-objective-c-headers
    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].compileTaskProvider.configure {
            compilerOptions {
                freeCompilerArgs.add("-Xexport-kdoc")
            }
        }
    }

}

android {
    namespace = "io.github.rufenkhokhar"
    compileSdk = 36

    defaultConfig {
        minSdk = 21
    }
}
mavenPublishing {

    coordinates(
        groupId = "io.github.rufenkhokhar",
        artifactId = "K-Spinner",
        version = "1.0.0"
    )
    pom {
        name.set("K-Spinner")
        description.set("Drop down list for Compose Multiplatform")
        inceptionYear.set("2025")
        url.set("https://github.com/RufenKhokhar/K-Spinner")
        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }
        developers {
            developer {
                id.set("RufenKhokhar")
                name.set("RufenKhokhar")
                email.set("Rufankhokhar@gmail.com")
            }
        }

        // Specify SCM information
        scm {
            url.set("https://github.com/RufenKhokhar/K-Spinner")
        }
    }
    //publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    publishToMavenCentral()
    signAllPublications()
}



