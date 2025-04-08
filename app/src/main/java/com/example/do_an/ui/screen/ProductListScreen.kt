package com.example.do_an.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.do_an.R

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageRes: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(onLogoutClick: () -> Unit) {
    val productList = listOf(
        Product(1, "Sản phẩm 1", "Mô tả sản phẩm 1", 199_000.0, R.drawable.ic_launcher_foreground),
        Product(2, "Sản phẩm 2", "Mô tả sản phẩm 2", 299_000.0, R.drawable.ic_launcher_foreground),
        Product(3, "Sản phẩm 3", "Mô tả sản phẩm 3", 399_000.0, R.drawable.ic_launcher_foreground)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách sản phẩm") },
                actions = {
                    IconButton(onClick = onLogoutClick) {
                        Icon(Icons.Default.Logout, contentDescription = "Đăng xuất", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2DA44E),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(productList) { product ->
                ProductItem(product = product)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp)
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(product.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(product.description, fontSize = 14.sp)
                Text("₫${"%,.0f".format(product.price)}", color = Color(0xFF2DA44E), fontWeight = FontWeight.Bold)
            }
        }
    }
}
