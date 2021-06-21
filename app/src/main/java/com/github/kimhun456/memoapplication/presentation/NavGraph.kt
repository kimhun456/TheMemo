package com.github.kimhun456.memoapplication.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.kimhun456.memoapplication.presentation.add.AddScreen
import com.github.kimhun456.memoapplication.presentation.list.ListScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = TheMemoDestinations.ALL_LIST_ROUTE.name
    ) {
        composable(TheMemoDestinations.ALL_LIST_ROUTE.name) {
            ListScreen(
                listViewModel = hiltViewModel(),
                navController = navController
            )
        }
        composable(TheMemoDestinations.ADD_ROUTE.name) {
            AddScreen(
                addViewModel = hiltViewModel(),
                navController = navController
            )
        }
    }
}