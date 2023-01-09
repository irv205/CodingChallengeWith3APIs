package com.irv205.codingchallenge2.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irv205.codingchallenge2.domain.DomainResponse
import com.irv205.codingchallenge2.domain.model.GenericModelDomain
import com.irv205.codingchallenge2.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state = mutableStateOf<MainViewState?>(MainViewState.Home)
    val state : State<MainViewState?> get() = _state

    private val _list = mutableStateListOf<GenericModelDomain>()
    val list : SnapshotStateList<GenericModelDomain> get() = _list

    private val _inputValueString = mutableStateOf("")
    val inputValueString : State<String> get() = _inputValueString

    private val _inputValueInt = mutableStateOf(1)
    val inputValueInt : State<Int> get() = _inputValueInt

    fun setInputValue(input: String) {
        _inputValueString.value = input
    }

    fun setInputValueInt(value: Int){
        _inputValueInt.value = value
    }

    fun getAnimeList() {

        if (validatedInput()){
            viewModelScope.launch(Dispatchers.IO) {
                when(val result = repository.getAnime(_inputValueString.value)){
                    is DomainResponse.OnFailure -> {
                        withContext(Dispatchers.Main) {
                            Log.e("Oni-chan yamate kudasai", result.message)
                        }
                    }
                    is DomainResponse.Success -> {
                        withContext(Dispatchers.Main){
                            _list.clear()
                            _list.addAll(result.data)
                        }
                    }
                }
            }
        }
    }

    fun getNewsList() {

        if (validatedInput()){
            viewModelScope.launch(Dispatchers.IO) {
                when(val result = repository.getNews(_inputValueString.value)){
                    is DomainResponse.OnFailure -> {
                        Log.e("Oni-chan yamate kudasai", result.message)
                    }
                    is DomainResponse.Success -> {
                        _list.clear()
                        _list.addAll(result.data)
                    }
                }
            }
        }
    }

    fun getSimpsonQuotes() {

        viewModelScope.launch(Dispatchers.IO){
            when(val result = repository.getSimpsonQuotes(_inputValueInt.value.toInt())){
                is DomainResponse.OnFailure -> {
                    Log.e("Oni-chan yamate kudasai", result.message)
                }
                is DomainResponse.Success -> {
                    _list.clear()
                    _list.addAll(result.data)
                }
            }
        }
    }

    private fun validatedInput(): Boolean{
        return _inputValueString.value.isNotEmpty()
    }
}