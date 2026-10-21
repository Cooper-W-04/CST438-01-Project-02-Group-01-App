package com.group1.cst438_01_project_02_group_01_app.data.api

import com.group1.cst438_01_project_02_group_01_app.BuildConfig

object ApiConfig {
    /**
     * Defaults to the Android emulator's alias for the development computer.
     *
     * For a physical device, run Gradle with:
     * -PapiBaseUrl=http://YOUR_COMPUTER_LAN_IP:8080/
     *
     * When the API is deployed to Render, use its HTTPS URL and remove the
     * local cleartext HTTP setting from AndroidManifest.xml.
     */
    val BASE_URL: String = BuildConfig.API_BASE_URL
}
