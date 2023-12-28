package com.example.jetpackcomposedemo


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.jetpackcomposedemo.includes.DemoDataProvider

import com.example.jetpackcomposedemo.viewModel.ThemeViewModel
import com.example.jetpackcomposedemo.includes.VerticalListItemSmall
import com.example.jetpackcomposedemo.application.prefs
import com.example.jetpackcomposedemo.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    private val themeViewModel: ThemeViewModel by viewModels()


    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeDark = prefs.themeDark
            themeDark.let {
                themeViewModel.setTheme(it)
            }
            AppTheme(useDarkTheme = themeViewModel.isDarkThemeEnabled.value) {
                ListViewContent(themeViewModel)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListViewContent(themeViewModel: ThemeViewModel) {
    var switchState by remember { themeViewModel.isDarkThemeEnabled }
    val icon: (@Composable () -> Unit) = if (switchState) {
        {
            Icon(
                imageVector = Icons.Filled.DarkMode,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    } else {
        {
            Icon(
                imageVector = Icons.Filled.LightMode,
                contentDescription = null,
                modifier = Modifier.size(SwitchDefaults.IconSize),
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Column(modifier = Modifier.padding(4.dp)) {
                    Text(
                        text = "ListView",
                        modifier = Modifier.wrapContentHeight(align = Alignment.CenterVertically)
                    )
                }
            }, navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        "",
                    )
                }
            }, actions = {
                Switch(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    checked = switchState,
                    onCheckedChange = {
                        switchState = !switchState
                        prefs.themeDark = switchState
                    },
                    thumbContent = icon
                )

            })

        },
        content = { paddingValues ->
            VerticalListView(paddingValues)
        }
    )

}

@Composable
fun VerticalListView(paddingValues: PaddingValues) {
    val list = remember { DemoDataProvider.itemList }
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        items(items = list, itemContent = { item ->
            VerticalListItemSmall(item = item)
            ListItemDivider()
        })
    }
}

@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    )
}