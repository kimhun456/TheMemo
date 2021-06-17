package com.github.kimhun456.memoapplication.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.kimhun456.memoapplication.presentation.add.AddScreen
import com.github.kimhun456.memoapplication.presentation.list.ListScreen
import com.github.kimhun456.memoapplication.presentation.list.ListViewModel

@Composable
fun NavGraph(
    listViewModel: ListViewModel,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = TheMemoDestinations.ALL_LIST_ROUTE.name
    ) {
        composable(TheMemoDestinations.ALL_LIST_ROUTE.name) {
            ListScreen(
                listViewModel = listViewModel,
                navController = navController
            )
        }
        composable(TheMemoDestinations.ADD_ROUTE.name) {
            AddScreen()
        }
    }
}