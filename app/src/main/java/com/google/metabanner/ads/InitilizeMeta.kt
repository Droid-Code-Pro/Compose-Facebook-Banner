package com.google.metabanner.ads

import android.content.Context
import com.facebook.ads.AdSettings
import com.facebook.ads.AudienceNetworkAds
import com.google.metabanner.R

fun initializeMeta(context: Context) {
  val textMode = context.resources.getBoolean(R.bool.ad_test_mode)
  val notInitialized = !AudienceNetworkAds.isInitialized(context)
  if (notInitialized){
    AudienceNetworkAds.initialize(context)
    AdSettings.setTestMode(textMode)
    //      AdSettings.setIntegrationErrorMode(AdSettings.IntegrationErrorMode.INTEGRATION_ERROR_CRASH_DEBUG_MODE)
  }
}