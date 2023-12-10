package com.example.legoapptry2.ui.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.legoapptry2.R
import com.example.legoapptry2.data.model.Feed
import com.example.legoapptry2.data.repository.feedList
import com.example.legoapptry2.ui.theme.SoftDark
import com.example.legoapptry2.ui.theme.spacingLarge
import com.example.legoapptry2.ui.theme.spacingMedium
import com.example.legoapptry2.ui.theme.spacingSmall

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FeedItem(feed: Feed) {

    val likeIcon = R.drawable.ic_notification
    val likedIcon = R.drawable.ic_liked
    val commentIcon = R.drawable.ic_comment

    val userAvatarContentDesc = stringResource(R.string.content_description_feed_avatar)
    val feedImageContentDesc = stringResource(R.string.content_description_feed_image)
    val likeContentDesc = stringResource(R.string.button_like_content_description)
    val commentContentDesc = stringResource(R.string.button_comment_content_description)

    var isLiked by rememberSaveable {
        mutableStateOf(false)
    }

    val iconsColor = Color.White
    val likedColor = if(isLiked) Color.Red else iconsColor

    Column(modifier = Modifier.background(SoftDark)) {
        Row(
            modifier = Modifier
                .padding(horizontal = spacingSmall)
                .padding(top = spacingLarge)
        ) {

            GlideImage(
                model = feed.userAvatar,
                contentDescription = userAvatarContentDesc,
                modifier = Modifier
                    .size(36.dp)
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Column {
                Text(
                    text = feed.userNickName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = spacingMedium),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                Text(
                    text = feed.localName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = spacingMedium),
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )


            }

        }

        GlideImage(
            model = feed.imageUrl,
            contentDescription = feedImageContentDesc,
            modifier = Modifier
                .padding(top = spacingLarge)
                .height(256.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(start = spacingMedium)
                .padding(top = spacingLarge)
        ) {

            FeedIcon(
                icon = if (isLiked) likedIcon else likeIcon,
                contentDescription = likeContentDesc,
                color = likedColor
            ) {
                isLiked = !isLiked
            }

            FeedIcon(
                icon = commentIcon,
                contentDescription = commentContentDesc,
                color = iconsColor
            ) {}
        }


        Row(
            modifier = Modifier
                .padding(horizontal = spacingSmall)
                .padding(top = spacingLarge)
        ) {

            val description = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                    append(feed.userNickName)
                }
                append(" ")
                append(feed.description)
            }

            Text(
                text = description,
                modifier = Modifier
                    .padding(horizontal = spacingMedium),
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
        }

        Text(
            text = feed.postedAgo,
            modifier = Modifier
                .padding(start = 12.dp)
                .padding(top = spacingSmall),
            maxLines = 1,
            color = Color.Gray,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )

        }
    }


@Composable
fun FeedIcon(
    @DrawableRes icon: Int,
    contentDescription: String,
    color: Color,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = icon),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(40.dp)
            .padding(end = spacingLarge)
            .clickable { onClick() },
        colorFilter = ColorFilter.tint(color)
    )
}

@Preview(showBackground = true)
@Composable
fun FeedItemPreview() {
    FeedItem(
        feed = feedList[0]
    )
}