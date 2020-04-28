import java.nio.charset.StandardCharsets

plugins {
  java
  id("fabric-loom") version "0.2.7-SNAPSHOT"
}

group = "dev.sapphic"
version = "1.0.0"

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

minecraft {
  refmapName = "mixins/persistentcake/refmap.json"
  runDir = "run"
}

dependencies {
  minecraft("com.mojang:minecraft:20w17a")
  mappings("net.fabricmc:yarn:20w17a+build.5:v2")
  modImplementation("net.fabricmc:fabric-loader:0.8.2+build.194")
  fabricApi(name = "api-base", version = "0.1.3+12a8474c34")
  fabricApi(name = "loot-tables-v1", version = "0.1.6+2f56dff234")
  fabricApi(name = "resource-loader-v0", version = "0.1.14+2fd224ca34")
}

tasks.withType<ProcessResources> {
  filesMatching("fabric.mod.json") {
    expand("version" to version)
  }
}

tasks.withType<JavaCompile> {
  options.run {
    isFork = true
    isVerbose = true
    encoding = StandardCharsets.UTF_8.displayName()
    compilerArgs.add("-Xlint:all")
    compilerArgs.add("-XprintProcessorInfo")
    compilerArgs.add("-XprintRounds")
  }
}

fun DependencyHandlerScope.fabricApi(name: String, version: String) {
  modImplementation(group = "net.fabricmc.fabric-api", name = "fabric-$name", version = version)
  include(group = "net.fabricmc.fabric-api", name = "fabric-$name", version = version)
}
