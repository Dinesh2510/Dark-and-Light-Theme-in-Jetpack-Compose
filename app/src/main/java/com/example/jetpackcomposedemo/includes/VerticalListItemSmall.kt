package com.example.jetpackcomposedemo.includes

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposedemo.model.ItemModel


@Composable
fun VerticalListItemSmall(item: ItemModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val typography = MaterialTheme.typography
    Row(
        modifier = Modifier
            .clickable(onClick = {
                Toast
                    .makeText(context, item.title, Toast.LENGTH_LONG)
                    .show()
            })
            .padding(16.dp)
    ) {
        ItemImage(
            item,
            Modifier.padding(end = 16.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(item.title, style = typography.titleMedium)
            Text(item.subtitle, style = typography.bodyMedium)
        }
        FavIcon(modifier)
    }
}

@Composable
fun ItemImage(item: ItemModel, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = item.imageId),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
            .size(100.dp, 80.dp)
            .clip(MaterialTheme.shapes.medium)
    )
}

@Composable
fun FavIcon(modifier: Modifier = Modifier) {
    val isFavourite = remember { mutableStateOf(true) }
    IconToggleButton(
        checked = isFavourite.value,
        onCheckedChange = { isFavourite.value = !isFavourite.value }
    ) {
        if (isFavourite.value) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                modifier = modifier
            )
        } else {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = modifier
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewListViewItemSmall() {
    val item = DemoDataProvider.item
    VerticalListItemSmall(item)
}
