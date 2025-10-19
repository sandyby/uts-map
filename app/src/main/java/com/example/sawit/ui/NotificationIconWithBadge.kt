package com.example.sawit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sawit.R

//@Composable
//fun NotificationIconWithBadge(
//    count: Int,
//    onClick: () -> Unit
//) {
//    BadgedBox(
//        badge = {
//            if (count > 0) {
//                Badge(
//                    containerColor = colorResource(id = R.color.text_fiery_red_sunset_600),
//                    modifier = Modifier
//                        .offset(x = (-6).dp, y = (4).dp) // adjust these values as needed
//                ) {
//                    Text(
//                        text = count.toString(),
//                        color = Color.White,
//                        fontSize = 10.sp,
//                        modifier = Modifier.padding(2.dp)
//                    )
//                }
//            }
//        }
//    ) {
//        IconButton(onClick = onClick) {
//            Icon(
//                painter = painterResource(
//                    id = when {
//                        count <= 0 -> R.drawable.ic_filled_notifications_24_white
//                        else -> R.drawable.ic_filled_notifications_active_24_white
//                    }
//                ),
//                contentDescription = "Notifications",
//                tint = Color.White,
//                modifier = Modifier
//                    .size(36.dp)
//                    .background(
//                        color = colorResource(id = R.color.bg_primary_500),
//                        shape = CircleShape
//                    )
//                    .padding(2.dp)
//            )
//        }
//    }
//}

@Composable
fun NotificationIconWithBadge(
    count: Int, onClick: () -> Unit
) {
    Box(
        modifier = Modifier.size(48.dp), // base box for the whole icon + badge
        contentAlignment = Alignment.BottomStart
    ) {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(
                    id = when {
                        count <= 0 -> R.drawable.ic_filled_notifications_24_bg_primary_500
                        else -> R.drawable.ic_filled_notifications_active_24_bg_primary_500
                    }
                ),
                contentDescription = "Notifications",
                tint = Color.White,
                modifier = Modifier
                    .size(32.dp)
                    .background(
                        color = colorResource(id = R.color.bg_primary_500), shape = CircleShape
                    )
                    .padding(4.dp)
            )
        }

        if (count > 0) {
            Badge(
                containerColor = colorResource(id = R.color.text_fiery_red_sunset_600),
//                modifier = Modifier
//                    .align(androidx.compose.ui.Alignment.TopEnd)
//                    .offset(x = (-4).dp, y = (4).dp) // Move inward
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = (-2).dp, y = 2.dp),
            ) {
                Text(
                    text = when {
                        count > 99 -> "99+"
                        else -> count.toString()
                    },
                    color = Color.White,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }
}