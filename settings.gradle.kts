pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "CloudstreamPlugins"

File(rootDir, ".").listFiles()?.filter { 
    it.isDirectory && File(it, "build.gradle.kts").exists() 
}?.forEach { 
    include(":${it.name}") 
}