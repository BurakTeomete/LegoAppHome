package com.example.legoapptry2.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label:  String,
    val icon: ImageVector,
    val route: String,
)

val listOfNavItems = listOf(
    NavItem(
        label = "Home",
        icon = Icons.Default.Home,
        route = Screens.HomeScreen.name
    ),
    NavItem(
        label = "Search",
        icon = Icons.Default.Search,
        route = Screens.HomeScreen.name
    ),
    NavItem(
        label = "",
        icon = Icons.Default.AddCircle,
        route = Screens.LegoToolBar.name
    ),
    NavItem(
        label = "Store",
        icon = Icons.Default.ShoppingCart,
        route = Screens.HomeScreen.name
    ),
    NavItem(
        label = "Profile",
        icon = Icons.Default.Person,
        route = Screens.HomeScreen.name
    ),
)