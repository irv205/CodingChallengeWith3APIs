package com.irv205.codingchallenge2.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.irv205.codingchallenge2.domain.model.GenericModelDomain
import com.irv205.codingchallenge2.presentation.MainViewModel
import com.irv205.codingchallenge2.ui.theme.CodingChallenge2Theme
import dagger.hilt.android.AndroidEntryPoint
import coil.compose.rememberAsyncImagePainter

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodingChallenge2Theme {
                val vm: MainViewModel = viewModel()
                MainContent(vm)
            }
        }
    }
}

@Composable
fun MainContent(vm: MainViewModel) {

    val spinnerValue = remember { vm.inputValueInt }
    val state = remember { mutableStateOf(1) }

    Column(modifier = Modifier.fillMaxSize()) {

        if (state.value == 2) {
            HorizontalNumberSpinner(value = spinnerValue.value) { newValue ->
                vm.setInputValueInt(newValue)
            }
        } else {
            SearchViewPreview{ vm.setInputValue(it) }
        }

        tabs(onClick = {
            when(it) {
                "Anime" -> {
                    state.value = 0
                    vm.getAnimeList()
                }
                "News" -> {
                    state.value = 1
                    vm.getNewsList()
                }
                "Quotes" -> {
                    state.value = 2
                    vm.getSimpsonQuotes()
                }
            }
        })
        ElementList(list = vm.list)
    }
}

@Composable
fun SearchViewPreview(setText: (String) -> Unit) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(textState, setText = {
        setText.invoke(textState.value.text)
    })
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>, setText: () -> Unit) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
            Log.d("SEARCH---", state.value.text)
            setText.invoke()
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = Color.DarkGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun tabs(onClick: (String) -> Unit) {
    val selectedIndex = remember { mutableStateOf(0)}
    val tabs = listOf("Anime","News","Quotes")

    TabRow(
        selectedTabIndex = selectedIndex.value,
        backgroundColor = Color.Gray,
        contentColor = Color(0xFFFEFEFA),
        indicator = {
            Spacer(
                Modifier
                    .tabIndicatorOffset(it[selectedIndex.value])
                    .height(2.5.dp)
                    .background(Color.Black)
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedIndex.value == index,
                onClick = { selectedIndex.value = index},
            ) {
                Text(
                    text = tab,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
    when(selectedIndex.value){
        0 -> {
            onClick.invoke(tabs[0])
        }
        1 -> {
            onClick.invoke(tabs[1])
        }
        2 -> {
            onClick.invoke(tabs[2])
        }
    }
}

@Composable
fun ElementList(list: List<GenericModelDomain>) {

    if (list.isNotEmpty()){
        Box(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)) {
            LazyColumn(modifier = Modifier){
                items(list) { item ->
                    ItemCard(genericModelDomain = item)
                }
            }
        }
    }
}

@Composable
fun ItemCard(genericModelDomain: GenericModelDomain) {
    Card(
        modifier = Modifier
            .padding(
                bottom = 9.dp,
                top = 9.dp,
                start = 5.dp,
                end = 5.dp
            )
            .fillMaxWidth()
        ,
        shape =  RoundedCornerShape(19.dp),
        elevation = 16.dp,

        ) {
        Row (
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colors.surface)
        ){
            Surface(
                modifier = Modifier.size(130.dp),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                elevation = 19.dp,
                border = BorderStroke(1.dp, Color.Gray)
            ) {
            Image(
                painter = rememberAsyncImagePainter(genericModelDomain.img),
                contentDescription = genericModelDomain.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop,
            )
            }
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = genericModelDomain.title,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        fontSize = (22.sp)
                    ),
                    color = Color.Black
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = genericModelDomain.description,
                        style = typography.body2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(
                            end = 25.dp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun HorizontalNumberSpinner(value: Int, onValueChange: (Int) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp), backgroundColor = Color.DarkGray
    ){
        Row(modifier = Modifier.padding(2.dp)) {
            Text(modifier = Modifier
                .padding(start = 20.dp)
                .clickable { onValueChange(value-1) },
                text = "-", style = TextStyle(fontSize = 35.sp), color = Color.White)

            Spacer(Modifier.weight(1f))

            Text(text = "$value", style = TextStyle(fontSize = 30.sp), color = Color.White)

            Spacer(Modifier.weight(1f))

            Text(modifier = Modifier
                .padding(end = 20.dp)
                .clickable { onValueChange(value+1) },
                text = "+", style = TextStyle(fontSize = 35.sp), color = Color.White)
        }
    }
}