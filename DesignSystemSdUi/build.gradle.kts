plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    id("maven-publish")
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
    }
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.vini"
            artifactId = "designsystemsdui"
            version = "0.0.13"

            afterEvaluate { artifact(tasks.getByName("jar")) }
        }
    }
}