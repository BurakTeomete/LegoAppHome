package com.example.legoapptry2.navigation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.legoapptry2.ui.theme.LegoRed
import com.example.legoapptry2.ui.theme.LegoYellow
import com.example.legoapptry2.ui.theme.SoftDark
import com.example.legoapptry2.ui.view.HomeScreen
import com.example.legoapptry2.ui.view.LegoToolBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = SoftDark,
            ) {
                val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
                val currentDestination: NavDestination? = navBackStackEntry?.destination

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SoftDark) // İstediğin arkaplan rengini ayarla
                        .padding(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    listOfNavItems.forEach { navItem ->

                        MyNavigationBarItem(
                            isSelected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                            onClick = {
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = navItem.icon,
                            label = navItem.label
                        )
                    }
                }
            }
        }
    ) { paddingValues: PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.name,
            modifier = Modifier
                .padding(paddingValues)
        ) {
            composable(route = Screens.HomeScreen.name) {
                HomeScreen()
            }
            composable(route = Screens.LegoToolBar.name) {
                LegoToolBar()
            }
        }
    }

}


@Composable
fun MyNavigationBarItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
) {
    val tint = if (isSelected) {
        LegoYellow // Seçili olduğunda kullanmak istediğiniz renk
    } else {
        Color.White // Seçili olmadığında kullanmak istediğiniz renk
    }

    val labelColor = if (isSelected) {
        LegoYellow // Seçili olduğunda label rengini değiştirmek için istediğiniz renk
    } else {
        Color.White // Seçili olmadığında label rengini değiştirmek için istediğiniz renk
    }

    val modifier = if (label.isEmpty()) {
        Modifier.size(64.dp)
    } else {
        Modifier.size(36.dp)
    }
    val interactionSource = remember { MutableInteractionSource() }

        Column(
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) { onClick() }
                .fillMaxHeight() // Bu, Column'ın tüm yüksekliği boyunca genişleyeceği anlamına gelir
                .background(
                    color = if (isSelected) Color.Transparent else Color.Transparent,
                    shape = RectangleShape
                )
                .padding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = modifier,
                imageVector = icon,
                contentDescription = null,
                tint = tint
            )
            if (label.isNotEmpty()) {
                Text(
                    text = label,
                    color = labelColor,
                    modifier = Modifier.padding(top = 1.dp) // Opsiyonel: Label ile ikon arasındaki boşluğu ayarlamak için
                )
            }
        }

}


@Preview
@Composable
fun AppNavigationPreview() {
    AppNavigation()
}