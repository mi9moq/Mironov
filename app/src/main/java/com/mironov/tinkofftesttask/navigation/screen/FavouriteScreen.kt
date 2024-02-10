package com.mironov.tinkofftesttask.navigation.screen

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.mironov.tinkofftesttask.ui.favourite.FavouriteFragment

fun getFavourite() = FragmentScreen {
    FavouriteFragment.newInstance()
}