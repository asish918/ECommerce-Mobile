package com.example.shrine_ecommerce.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.example.shrine_ecommerce.ui.theme.ShrineComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShrineComposeTheme {
                ShrineApp(context = this)
            }
        }
    }
}

//@ExperimentalMaterialApi
//@Preview(showBackground = true)
//@Composable
//fun AppPreview() {
//    ShrineComposeTheme {
//        ShrineApp()
//
//    }
//}