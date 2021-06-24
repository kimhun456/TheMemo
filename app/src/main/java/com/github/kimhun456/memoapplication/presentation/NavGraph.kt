package com.github.kimhun456.memoapplication.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.github.kimhun456.memoapplication.presentation.add.AddScreen
import com.github.kimhun456.memoapplication.presentation.constants.NavigationConstants
import com.github.kimhun456.memoapplication.presentation.edit.EditScreen
import com.github.kimhun456.memoapplication.presentation.list.ListScreen

@Composable
fun NavGraph(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = TheMemoDestinations.ALL_LIST_ROUTE.name
    ) {
        composable(TheMemoDestinations.ALL_LIST_ROUTE.name) {
            ListScreen(
                modifier = modifier,
                listViewModel = hiltViewModel(),
                navController = navController
            )
        }
        composable(TheMemoDestinations.ADD_ROUTE.name) {
            AddScreen(
                modifier = modifier,
                addViewModel = hiltViewModel(),
                navController = navController
            )
        }
        composable(
            route = "${TheMemoDestinations.EDIT_ROUTE.name}/{${NavigationConstants.MEMO_ID}}",
            arguments = listOf(navArgument(NavigationConstants.MEMO_ID) { type = NavType.LongType })
        ) {
            EditScreen(
                modifier = modifier,
                editViewModel = hiltViewModel(),
                navController = navController
            )
        }
    }
}