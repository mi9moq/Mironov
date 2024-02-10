package com.mironov.tinkofftesttask.navigation.screen

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.mironov.tinkofftesttask.ui.popular.PopularFragment

fun getPopular() = FragmentScreen {
    PopularFragment.newInstance()
}