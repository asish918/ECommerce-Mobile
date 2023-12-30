package com.example.shrine_ecommerce.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.shrine_ecommerce.R
import com.example.shrine_ecommerce.model.Products
import com.example.shrine_ecommerce.ui.theme.ShrineComposeTheme
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.launch
import kotlin.math.min

@Composable
private fun CartHeader(cartSize: Int, onCollapse: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onCollapse() },
            Modifier.padding(4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Collapse cart icon"
            )
        }
        Text(
            "Cart".uppercase(),
            style = MaterialTheme.typography.subtitle1
        )
        Spacer(Modifier.width(12.dp))
        Text("$cartSize items".uppercase())
    }
}

@Preview(name = "Cart header")
@Composable
fun CartHeaderPreview() {
    ShrineComposeTheme {
        Surface(
            Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.secondary
        ) {
            CartHeader(cartSize = 15, onCollapse = {})
        }
    }
}

@Composable
private fun CartItem(
    modifier: Modifier = Modifier,
    item: Products,
    onRemoveAction: () -> Unit = {},
) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onRemoveAction() },
            Modifier.padding(horizontal = 4.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Remove item icon"
            )
        }
        Column(
            Modifier.fillMaxWidth()
        ) {
            Divider(color = MaterialTheme.colors.onSecondary.copy(alpha = 0.3f))
            Row(
                Modifier.padding(vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CoilImage(
                    imageModel = if (item.images.isEmpty()) {
                        "https://picsum.photos/200/200"
                    } else {
                        item.images[0].url
                    },
                    shimmerParams = ShimmerParams(
                        baseColor = Color.White,
                        highlightColor = Color.Gray,
                        durationMillis = 500,
                        dropOff = 0.65F,
                        tilt = 20F
                    ),
                    failure = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.image_not_available),
                                contentDescription = "no image"
                            )
                        }
                    },
                    alignment = Alignment.TopCenter,
                    previewPlaceholder = R.drawable.popcorn,
                    contentScale = ContentScale.FillHeight,
                    circularReveal = CircularReveal(duration = 1000),
                    modifier = Modifier.size(80.dp),
                    contentDescription = "Product item"
                )
//                Image(
//                    painter = painterResource(id = item.images[0].url),
//                    contentDescription = "Image for: ${item.name}",
//                    contentScale = ContentScale.FillHeight,
//                    modifier = Modifier.size(80.dp)
//                )
                Spacer(Modifier.width(20.dp))
                Column(
                    Modifier.padding(end = 16.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item.name.uppercase(),
                            style = MaterialTheme.typography.body2,
                        )
                        Text(
                            text = "₹${item.price}",
                            style = MaterialTheme.typography.body2,
                        )
                    }
                }
            }
        }
    }
}

//@Preview(name = "Cart item")
//@Composable
//fun CartItemPreview() {
//    ShrineComposeTheme {
//        Surface(color = MaterialTheme.colors.secondary) {
//            CartItem(item = SampleItems[0])
//        }
//    }
//}

data class ExpandedCartItem(
    val idx: Int,
    val visible: MutableTransitionState<Boolean> = MutableTransitionState(true),
    val data: Products,
)

@Composable
fun ExpandedCart(
    cartItems: List<ExpandedCartItem>,
    onRemoveItem: (ExpandedCartItem) -> Unit = {},
    onCollapse: () -> Unit = {},
) {
    Surface(
        color = MaterialTheme.colors.secondary
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(bottom = 64.dp)
        ) {
            item {
                CartHeader(
                    cartSize = cartItems.size,
                    onCollapse = onCollapse
                )
            }
            itemsIndexed(
                items = cartItems,
                key = { idx, item -> "$idx-${item.data.id}" }
            ) { _, it ->
                AnimatedVisibility(
                    visibleState = it.visible,
                    exit = fadeOut() + slideOut(targetOffset = {
                        IntOffset(
                            x = -it.width / 2,
                            y = 0
                        )
                    })
                ) {
                    CartItem(
                        item = it.data,
                        onRemoveAction = { onRemoveItem(it) }
                    )
                }
            }
        }
    }
}

//@Preview(device = Devices.PIXEL_4)
//@Composable
//fun ExpandedCartPreview() {
//    ShrineComposeTheme {
//        ExpandedCart(SampleItems.mapIndexed { idx, it -> ExpandedCartItem(idx = idx, data = it) })
//    }
//}

@Composable
private fun CheckoutButton(
    context: Context
) {
    val scope = rememberCoroutineScope()

    Button(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        onClick = {
            scope.launch {
                Toast.makeText(context, "✅Order Placed", Toast.LENGTH_SHORT).show()
            }
        }
    ) {
        Icon(
            imageVector = Icons.Outlined.ShoppingCart,
            contentDescription = "Shopping cart icon"
        )
        Spacer(Modifier.width(16.dp))
        Text("Proceed to checkout".uppercase())
    }
}

@Composable
private fun CollapsedCart(
    items: List<Products>,
    onTap: () -> Unit = {},
) {
    Row(
        Modifier
            .clickable { onTap() }
            .padding(start = 24.dp, top = 8.dp, bottom = 8.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            Modifier.size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Shopping cart icon",
            )
        }
        items.take(3).forEach { item ->
            CollapsedCartItem(data = item)
        }
        if (items.size > 3) {
            Box(
                Modifier.size(width = 32.dp, height = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "+${items.size - 3}",
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun CollapsedCartItem(data: Products) {
    CoilImage(
        imageModel = if (data.images.isEmpty()) {
            "https://picsum.photos/200/200"
        } else {
            data.images[0].url
        },
        shimmerParams = ShimmerParams(
            baseColor = Color.White,
            highlightColor = Color.Gray,
            durationMillis = 500,
            dropOff = 0.65F,
            tilt = 20F
        ),
        failure = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_not_available),
                    contentDescription = "no image"
                )
            }
        },
        alignment = Alignment.TopCenter,
        previewPlaceholder = R.drawable.popcorn,
        contentScale = ContentScale.Crop,
        circularReveal = CircularReveal(duration = 1000),
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentDescription = "Product item"
    )

//    Image(
//        painter = painterResource(id = data.photoResId),
//        contentDescription = data.title,
//        alignment = Alignment.TopCenter,
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .size(40.dp)
//            .clip(RoundedCornerShape(10.dp))
//    )
}

//@Preview
//@Composable
//fun CollapsedCartPreview() {
//    ShrineComposeTheme {
//        Surface(
//            color = MaterialTheme.colors.secondary
//        ) {
//            CollapsedCart()
//        }
//    }
//}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CartBottomSheet(
    modifier: Modifier = Modifier,
    items: List<Products>,
    maxHeight: Dp,
    maxWidth: Dp,
    sheetState: CartBottomSheetState = CartBottomSheetState.Collapsed,
    onRemoveItemFromCart: (Int) -> Unit = {},
    onSheetStateChange: (CartBottomSheetState) -> Unit = {},
    context: Context
) {
    val expandedCartItems by remember(items) {
        derivedStateOf {
            items.mapIndexed { idx, it -> ExpandedCartItem(idx = idx, data = it) }
        }
    }

    LaunchedEffect(expandedCartItems) {
        snapshotFlow {
            expandedCartItems.firstOrNull {
                it.visible.isIdle && !it.visible.targetState
            }
        }.collect {
            if (it != null) {
                onRemoveItemFromCart(it.idx)
            }
        }
    }

    val cartTransition = updateTransition(
        targetState = sheetState,
        label = "cartTransition"
    )

    val cartXOffset by cartTransition.animateDp(
        label = "cartXOffset",
        transitionSpec = {
            when {
                CartBottomSheetState.Expanded isTransitioningTo CartBottomSheetState.Collapsed ->
                    tween(durationMillis = 433, delayMillis = 67)

                CartBottomSheetState.Collapsed isTransitioningTo CartBottomSheetState.Expanded ->
                    tween(durationMillis = 150)

                else ->
                    tween(durationMillis = 450)
            }
        }
    ) {
        when (it) {
            CartBottomSheetState.Expanded -> 0.dp
            CartBottomSheetState.Hidden -> maxWidth
            else -> {
                val size = min(3, items.size)
                var width = 24 + 40 * (size + 1) + 16 * size + 16
                if (items.size > 3) width += 32 + 16
                maxWidth - width.dp
            }
        }
    }

    val cartHeight by cartTransition.animateDp(
        label = "cartHeight",
        transitionSpec = {
            when {
                CartBottomSheetState.Expanded isTransitioningTo CartBottomSheetState.Collapsed ->
                    tween(durationMillis = 283)

                else ->
                    tween(durationMillis = 500)
            }
        }
    ) {
        if (it == CartBottomSheetState.Expanded) maxHeight else 56.dp
    }

    val cornerSize by cartTransition.animateDp(
        label = "cartCornerSize",
        transitionSpec = {
            when {
                CartBottomSheetState.Expanded isTransitioningTo CartBottomSheetState.Collapsed ->
                    tween(durationMillis = 433, delayMillis = 67)

                else ->
                    tween(durationMillis = 150)
            }
        }
    ) {
        if (it == CartBottomSheetState.Expanded) 0.dp else 24.dp
    }

    Surface(
        modifier = modifier
            .width(maxWidth)
            .height(cartHeight)
            .offset { IntOffset(cartXOffset.roundToPx(), 0) },
        shape = CutCornerShape(topStart = cornerSize),
        color = MaterialTheme.colors.secondary,
        elevation = 8.dp
    ) {
        Box {
            cartTransition.AnimatedContent(
                transitionSpec = {
                    when {
                        CartBottomSheetState.Expanded isTransitioningTo CartBottomSheetState.Collapsed ->
                            fadeIn(
                                animationSpec = tween(
                                    durationMillis = 117,
                                    delayMillis = 117,
                                    easing = LinearEasing
                                )
                            ) togetherWith
                                    fadeOut(
                                        animationSpec = tween(
                                            durationMillis = 117,
                                            easing = LinearEasing
                                        )
                                    )

                        CartBottomSheetState.Collapsed isTransitioningTo CartBottomSheetState.Expanded ->
                            fadeIn(
                                animationSpec = tween(
                                    durationMillis = 150,
                                    delayMillis = 150,
                                    easing = LinearEasing
                                )
                            ) togetherWith
                                    fadeOut(
                                        animationSpec = tween(
                                            durationMillis = 150,
                                            easing = LinearEasing
                                        )
                                    )

                        else -> EnterTransition.None togetherWith ExitTransition.None
                    }.using(SizeTransform(clip = false))
                },
            ) { targetState ->
                if (targetState == CartBottomSheetState.Expanded) {
                    ExpandedCart(
                        cartItems = expandedCartItems,
                        onRemoveItem = {
                            it.visible.targetState = false
                        },
                        onCollapse = {
                            onSheetStateChange(CartBottomSheetState.Collapsed)
                        }
                    )
                } else {
                    CollapsedCart(
                        items = items,
                        onTap = {
                            onSheetStateChange(CartBottomSheetState.Expanded)
                        }
                    )
                }
            }
            cartTransition.AnimatedVisibility(
                modifier = Modifier.align(Alignment.BottomCenter),
                visible = { it == CartBottomSheetState.Expanded },
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 150,
                        delayMillis = 150,
                        easing = LinearEasing
                    )
                ) +
                        scaleIn(
                            animationSpec = tween(
                                durationMillis = 250,
                                delayMillis = 250,
                                easing = LinearOutSlowInEasing
                            ), initialScale = 0.8f
                        ),
                exit = fadeOut(animationSpec = tween(durationMillis = 117, easing = LinearEasing)) +
                        scaleOut(
                            animationSpec = tween(
                                durationMillis = 100,
                                easing = FastOutLinearInEasing
                            ), targetScale = 0.8f
                        )
            ) {
                CheckoutButton(context)
            }
        }
    }
}

//@Preview(device = Devices.PIXEL_4)
//@Composable
//fun CartBottomSheetPreview() {
//    ShrineComposeTheme {
//        BoxWithConstraints(
//            Modifier.fillMaxSize()
//        ) {
//            var sheetState by remember { mutableStateOf(CartBottomSheetState.Collapsed) }
//
//            Button(
//                onClick = {
//                    if (sheetState == CartBottomSheetState.Collapsed) {
//                        sheetState = CartBottomSheetState.Hidden
//                    } else {
//                        sheetState = CartBottomSheetState.Collapsed
//                    }
//                }
//            ) {
//                Text("Toggle BottomSheet")
//            }
//
//            CartBottomSheet(
//                modifier = Modifier.align(Alignment.BottomEnd),
//                sheetState = sheetState,
//                maxHeight = maxHeight,
//                maxWidth = maxWidth
//            ) {
//                sheetState = it
//            }
//        }
//    }
//}