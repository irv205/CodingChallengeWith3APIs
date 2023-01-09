package com.irv205.codingchallenge2.presentation

sealed class MainViewState{
    object Home : MainViewState()
    object GetAnimeList : MainViewState()
    object GetNewsList : MainViewState()
    object GetQuotes : MainViewState()
    object EmptyField : MainViewState()
    object EmptyList : MainViewState()
}