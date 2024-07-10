package com.google.metabanner

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.AdSize
import com.facebook.ads.AdView
import com.google.metabanner.ads.initializeMeta
import com.google.metabanner.ui.component.DroidTopAppBar
import com.google.metabanner.ui.theme.MetaBannerTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MetaBannerTheme {
        var forceBanner by remember { mutableStateOf(1) }
        Scaffold(modifier = Modifier.fillMaxSize(),
          topBar = { DroidTopAppBar() }) { innerPadding ->
          Column(modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            
           key(forceBanner) {
              MetaBanner()
            }
            
            Button(onClick = {forceBanner = forceBanner +1}) {
              Text(text = "Meta Ad")
            }
          }
        }
        LaunchedEffect(key1 = Unit) {
         initializeMeta(this@MainActivity)
          forceBanner = 2
        }
      }
    }
  }
  
  @Composable
  fun MetaBanner() {
    val context = LocalContext.current
    val adUnit = stringResource(id = R.string.meta_banner_ad)
    val adSize = AdSize.RECTANGLE_HEIGHT_250
    ElevatedCard(modifier = Modifier.padding(4.dp)) {
      MetaBanner(context, adUnit, adSize)
    }
  }
  
  @Composable
  private fun MetaBanner(context: Context, adUnit: String, adSize: AdSize) {

    val adView = remember { AdView(context, adUnit, adSize)}
    
    AndroidView({ adView })
   
    val adlistener = object : com.facebook.ads.AdListener {
      override fun onError(p0: Ad?, p1: AdError?) {
        Log.d("TAGMetaAds", "onError: is called ${p1?.errorMessage}")
      }
      
      override fun onAdLoaded(p0: Ad?) {
        Log.d("TAGMetaAds", "onAdLoaded: ")
      }
      
      override fun onAdClicked(p0: Ad?) {
        Log.d("TAGMetaAds", "onAdClicked: ")
      }
      
      override fun onLoggingImpression(p0: Ad?) {
        Log.d("TAGMetaAds", "onLoggingImpression: ")
      }
    }
    LaunchedEffect(key1 = Unit) {
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adlistener).build())
//      adView.loadAd()
    }
  }
}

