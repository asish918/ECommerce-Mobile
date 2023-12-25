package com.example.shrine_ecommerce

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@ExperimentalMaterialApi
@Composable
fun ShrineApp() {
    var sheetState by rememberSaveable { mutableStateOf(CartBottomSheetState.Collapsed) }
    val cartItems = remember { mutableStateListOf(*SampleItems.take(2).toTypedArray()) }

    BoxWithConstraints(
        Modifier.fillMaxSize()
    ) {
        Backdrop(
            showScrim = sheetState == CartBottomSheetState.Expanded,
            onAddCartItem = {
                cartItems.add(it)
            },
            onBackdropReveal = { revealed ->
                sheetState = if (revealed) CartBottomSheetState.Hidden else CartBottomSheetState.Collapsed
            }
        )
        CartBottomSheet(
            modifier = Modifier.align(Alignment.BottomEnd),
            items = cartItems,
            sheetState = sheetState,
            maxHeight = maxHeight,
            maxWidth = maxWidth,
            onRemoveItemFromCart = {
                cartItems.removeAt(it)
            },
            onSheetStateChange = {
                sheetState = it
            }
        )
    }
}

enum class CartBottomSheetState {
    Collapsed,
    Expanded,
    Hidden,
}