package com.google.metabanner.ui.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.metabanner.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DroidTopAppBar() {
  val context = LocalContext.current
  TopAppBar(
    navigationIcon = { Image(painter = painterResource(id = R.drawable.icon),
      contentScale = ContentScale.Fit,
      modifier = Modifier.size(60.dp).clip(CircleShape),
      contentDescription = null)},
    title = { Text(text = "Droid Code",
    fontFamily = FontFamily.Cursive,
    fontWeight = FontWeight.ExtraBold,
    fontSize = 42.sp,
      textAlign = TextAlign.Center,
      modifier = Modifier.fillMaxWidth()) },
    actions = { IconButton(onClick = { Toast.makeText(context,"❤\uFE0F like \uD83D\uDD25",Toast.LENGTH_LONG).show() }) {
      Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
    }}
  )
  
}