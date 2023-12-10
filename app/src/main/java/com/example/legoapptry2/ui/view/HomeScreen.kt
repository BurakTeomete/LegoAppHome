package com.example.legoapptry2.ui.view


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.legoapptry2.data.model.Feed
import com.example.legoapptry2.data.model.Story
import com.example.legoapptry2.data.repository.feedList
import com.example.legoapptry2.data.repository.stories
import com.example.legoapptry2.ui.theme.DividerColor
import com.example.legoapptry2.ui.theme.SoftDark
import com.example.legoapptry2.ui.theme.spacingMedium


@Composable
fun HomeScreen() {

    LazyColumn(modifier = Modifier.background(SoftDark)) {

        item {
            LegoToolBar()
        }

        item {
            StoryList(stories = stories)
        }

        item {
            Divider(color = DividerColor, thickness = 0.2.dp)
        }

        feedList(feedList = feedList)
    }
}

@Composable
fun StoryList(stories: List<Story>) {
    LazyRow(modifier = Modifier.padding(top = spacingMedium)) {
        itemsIndexed(stories) { _, item ->
            StoryItem(story = item)

        }
    }

}

fun LazyListScope.feedList(feedList: List<Feed>) {
    itemsIndexed(feedList) { _, item ->
        FeedItem(feed = item)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}