import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object DependencyVersions {
    const val Detekt = "1.10.0"
}

plugins {
    kotlin("jvm") version "1.3.72"
    id("io.gitlab.arturbosch.detekt") version "1.10.0"
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:${DependencyVersions.Detekt}")
}

detekt {
    toolVersion = DependencyVersions.Detekt
    debug = true
    parallel = true
    autoCorrect = true
    input = files("src/main/kotlin")
}

tasks.withType<Detekt> {
    this.jvmTarget = "1.8"
    this.classpath.setFrom(project.configurations.getByName("detekt"))
    exclude("**/ignore/**")
}

val detektAll by tasks.registering(Detekt::class) {
    this.description = "Runs over whole code base without the starting overhead for each module."
    this.parallel = true
    this.buildUponDefaultConfig = true
    this.include("**/*.kt")
    this.exclude("**/resources/**")
    this.exclude("**/build/**")
    this.exclude("**/*.kts")
    this.exclude("**/ignore/**")
    this.jvmTarget = "1.8"
    this.classpath.setFrom(project.configurations.getByName("detekt"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
