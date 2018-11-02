import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.js.translate.context.Namer.kotlin

plugins {
    java
    kotlin("jvm") version "1.3.0"
}

group = "it.intre.sal"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    testCompile("org.junit.jupiter", "junit-jupiter-api", "5.1.0")
    testCompile("org.junit.jupiter", "junit-jupiter-params", "5.1.0")
    testRuntime("org.junit.platform", "junit-platform-launcher", "1.1.0")
    testRuntime("org.junit.vintage", "junit-vintage-engine", "5.1.0")
    testRuntime("org.junit.jupiter", "junit-jupiter-engine", "5.1.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}