package com.catapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.catapp.presentation.ui.screens.details.DetailsScreen
import com.catapp.presentation.ui.screens.main.MainScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavItem.MainNavItem.route
    ) {
        composable(NavItem.MainNavItem) {
            MainScreen(onNavigate = {
                navController.navigate(NavItem.DetailNavItem.createRoute(it))
            })
        }
        composable(NavItem.DetailNavItem) { backStackEntry ->
            DetailsScreen(
                id = backStackEntry.findArg(NavArgs.MediaId.key),
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(key: String): T {
    val value = arguments?.getString(key)
    requireNotNull(value)
    return value as T
}