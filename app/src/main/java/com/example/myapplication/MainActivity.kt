@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            modifier = Modifier.background(Color(0xFFFFFFFF)),
                            //shadow(
                            //elevation = 2.dp, // 阴影高度
                            //shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp) // 阴影形状
                            // )
                            title = { Text("设备详情") },
                        )
                    },
                    content = { innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)
                                .background(Color(0xFFF8FAFC))
                                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally

                        ) {
                            Image(contentScale = ContentScale.Crop,
                                painter = painterResource(id = R.drawable.logo), // 从资源文件加载图片
                                contentDescription = "My Image", // 图片描述  必须
                                modifier = Modifier.size(150.dp).padding(top = 20.dp)
                                    .clip(RoundedCornerShape(2.dp))
//                                    .border(
//                                        width = 2.dp, // 边框宽度
//                                        color = Color.Blue, // 边框颜色
//                                       // shape = RoundedCornerShape(16.dp) // 边框圆角
//                                    ), // 图片占满全部空间
//                                contentScale = ContentScale.Crop // 缩放类型：裁剪
                            )
                            Text("")
                        }
                    })
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

@Composable
fun MyTopAppBar() {
    TopAppBar(
        // 标题内容
        title = { Text("我的应用") },
        // 导航图标
        navigationIcon = {
            IconButton(onClick = { /* 处理导航图标点击事件 */ }) {
                Icon(Icons.Default.Menu, contentDescription = "导航菜单")
            }
        },
        // 动作图标
        actions = {
            // 添加动作按钮
            IconButton(onClick = { /* 处理动作1点击事件 */ }) {
                Icon(Icons.Default.Favorite, contentDescription = "收藏")
            }
            IconButton(onClick = { /* 处理动作2点击事件 */ }) {
                Icon(Icons.Default.Search, contentDescription = "搜索")
            }
        },
        // 各项颜色配置
        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = ButterflyBlue,			// appbar 容器颜色
//            actionIconContentColor = Purple40,		// 动作图标内颜色
//            titleContentColor = Color.Magenta,		// 标题（参数 title 表示的 composable）颜色
//            navigationIconContentColor = OrangeA700		// 导航图标内容颜色
        )
    )
}
