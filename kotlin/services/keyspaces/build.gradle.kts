import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "me.scmacdon"
version = "1.0-SNAPSHOT"

buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.3.0")
    }
}

repositories {
    mavenCentral()
    jcenter()
}
apply(plugin = "org.jlleitschuh.gradle.ktlint")
dependencies {
    implementation("aws.sdk.kotlin:keyspaces:0.33.1-beta")
    implementation("com.datastax.oss:java-driver-core:4.15.0")
    implementation("aws.smithy.kotlin:http-client-engine-okhttp:0.28.0")
    implementation("aws.smithy.kotlin:http-client-engine-crt:0.28.0")
    implementation("software.aws.mcs:aws-sigv4-auth-cassandra-java-driver-plugin:4.0.8")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "17"
}
