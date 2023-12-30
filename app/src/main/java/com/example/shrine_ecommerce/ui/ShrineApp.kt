package com.example.shrine_ecommerce.ui

import android.content.Context
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shrine_ecommerce.viewmodels.HomeViewModel

@ExperimentalMaterialApi
@Composable
fun ShrineApp(
    homeViewModel: HomeViewModel = hiltViewModel(),
    context: Context
) {
    val products = homeViewModel.productList
    var sheetState by rememberSaveable { mutableStateOf(CartBottomSheetState.Collapsed) }
    val cartItems = remember { mutableStateListOf(products[0]) }

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
            },
            viewModel = homeViewModel
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
            },
            context = context
        )
    }
}

enum class CartBottomSheetState {
    Collapsed,
    Expanded,
    Hidden,
}