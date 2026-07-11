import com.lagradost.cloudstream3.gradle.CloudstreamExtension

// Diz ao Gradle para aplicar as configurações globais neste plugin
val cloudstream = extensions.getByName<CloudstreamExtension>("cloudstream")
include(":PobreFlix")
