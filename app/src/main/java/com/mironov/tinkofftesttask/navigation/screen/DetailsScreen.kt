package com.mironov.tinkofftesttask.navigation.screen

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.mironov.tinkofftesttask.ui.detail.DetailFragment

fun getDetailsScreen(id: Int) = FragmentScreen {
    DetailFragment.newInstance(id)
}