package com.github.kimhun456.memoapplication.presentation

enum class TheMemoDestinations {
    ALL_LIST_ROUTE,
    ADD_ROUTE,
    EDIT_ROUTE;

    companion object {
        fun fromRoute(route: String?): TheMemoDestinations =
            when (route?.substringBefore("/")) {
                ALL_LIST_ROUTE.name -> ALL_LIST_ROUTE
                ADD_ROUTE.name -> ADD_ROUTE
                EDIT_ROUTE.name -> EDIT_ROUTE
                null -> ALL_LIST_ROUTE
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}