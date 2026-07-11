package com.saimuelbr.pobreflix

import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin
import android.content.Context

@CloudstreamPlugin
class PobreFlixPlugin: Plugin() {
    override fun load(context: Context) {
        // Registra o seu provedor principal no aplicativo
        registerMainAPI(PobreFlixProvider())
    }
}
