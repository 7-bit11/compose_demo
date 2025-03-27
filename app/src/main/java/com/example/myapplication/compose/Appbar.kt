package com.example.myapplication.compose

import androidx.compose.foundation.background
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(title:String=""){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFFF8FAFC), // 设置背景色为蓝色
            titleContentColor = Color.Black, // 设置标题文本颜色为白色
        ),
        modifier = Modifier.background(Color(0xFFF8FAFC)),
        //shadow(
        //elevation = 2.dp, // 阴影高度
        //shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp) // 阴影形状
        // )
        title = { Text(title) },
    )
}