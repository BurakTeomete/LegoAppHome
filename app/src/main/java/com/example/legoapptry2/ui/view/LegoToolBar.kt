package com.example.legoapptry2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.legoapptry2.R
import com.example.legoapptry2.ui.theme.SoftDark
import com.example.legoapptry2.ui.theme.spacingLarge
import com.example.legoapptry2.ui.theme.spacingMedium


@Composable
fun LegoToolBar() {

    Box(modifier = Modifier.background(color = SoftDark)) {

        Row(
            modifier = Modifier
                .padding(horizontal = spacingLarge)
                .height(64.dp), // 56.dp olarak da dene
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.drawable.legoapp_icon2),
                modifier = Modifier
                    .size(72.dp),
                contentDescription = "Lego App Logo"

            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.lego_head_icon3),
                modifier = Modifier
                    .size(56.dp)
                    .padding(start = spacingMedium),
                contentDescription = "Lego kafasi"
            )


        }
    }

}

@Preview(showBackground = true)
@Composable
fun LegoToolBarPreview() {
    LegoToolBar()
}