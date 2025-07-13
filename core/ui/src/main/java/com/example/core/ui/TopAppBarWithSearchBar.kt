package com.example.core.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.core.icon.FilmakIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithSearchBar(
    query: String,
    hintPlaceHolder: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit = {},
    focusRequester: FocusRequester,
    onBackPressed: () -> Unit,
    onClearClicked: () -> Unit
) {

    val focusManager = LocalFocusManager.current
    TopAppBar(
        modifier = Modifier
            .shadow(elevation = 10.dp)
            .wrapContentHeight(),
        title = {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .focusRequester(focusRequester)
                    .wrapContentHeight(),
                value = query,
                onValueChange = onQueryChange,
                placeholder = { Text(hintPlaceHolder) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = onClearClicked) {
                            Icon(FilmakIcons.Close, contentDescription = "Clear")
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearch()
                        focusManager.clearFocus()
                    }
                ),
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = FilmakIcons.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors()
    )
}