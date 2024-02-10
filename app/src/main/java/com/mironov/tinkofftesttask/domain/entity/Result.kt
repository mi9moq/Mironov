package com.mironov.tinkofftesttask.domain.entity

sealed class Result<out T> {

    data class Success<T>(val data: T): Result<T>()

    data object Error: Result<Nothing>()
}