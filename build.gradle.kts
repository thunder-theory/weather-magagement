import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("com.google.cloud.tools.jib") version "3.1.4"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}

group = "com"
version = "0.0.2"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}
apply(plugin = "kotlin-kapt")
apply(plugin = "kotlin-spring")
apply(plugin = "kotlin")
apply(plugin = "io.spring.dependency-management")
repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("io.springfox:springfox-swagger2:3.0.0")
    implementation("io.springfox:springfox-swagger-ui:3.0.0")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("mysql:mysql-connector-java")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    //JASYPT
    implementation ("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.3")

    // logging
    implementation ("ch.qos.logback:logback-classic:1.2.6")
    implementation ("io.github.microutils:kotlin-logging-jvm:2.0.11")

    // jwt token
    implementation ("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation ("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation ("io.jsonwebtoken:jjwt-jackson:0.11.5")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.3")
    runtimeOnly("com.h2database:h2")

    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("io.mockk:mockk:1.10.4")
    testImplementation("org.mockito:mockito-inline:2.13.0")
    testCompileOnly("com.h2database:h2")


}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
jib {
    from{
        image = "openjdk:11-jdk-slim"
    }

    to {
        image = "bsj1209/weather-manage"
        tags = setOf(project.version.toString(), "latest")
        auth {
            username = "bsj1209"
            password = "sj991209@"
        }
    }
    container {
        ports = listOf("8080")
        volumes = listOf("/tmp")
        jvmFlags = mutableListOf("-Dspring.profiles.active=prod")
    }
}
