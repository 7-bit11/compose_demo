@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @Composable
    fun ScrollColumn(innerPadding: PaddingValues, content: @Composable ColumnScope.() -> Unit) {
        val scrollState = rememberScrollState() // 创建滚动状态
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(scrollState) // 启用垂直滚动
                .background(Color(0xFFF8FAFC))
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            content()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            modifier = Modifier.background(Color(0xFFF8FAFC)),
                            //shadow(
                            //elevation = 2.dp, // 阴影高度
                            //shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp) // 阴影形状
                            // )
                            title = { Text("设备详情") },
                        )
                    },
                    content = { innerPadding ->
                        ScrollColumn(
                            innerPadding
                        ) {
                            Image(
                                contentScale = ContentScale.Crop,
                                painter = painterResource(id = R.drawable.logo), // 从资源文件加载图片
                                contentDescription = "My Image", // 图片描述  必须
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(top = 20.dp)
                                    .clip(RoundedCornerShape(2.dp))
//                                    .border(
//                                        width = 2.dp, // 边框宽度
//                                        color = Color.Blue, // 边框颜色
//                                       // shape = RoundedCornerShape(16.dp) // 边框圆角
//                                    ), // 图片占满全部空间
//                                contentScale = ContentScale.Crop // 缩放类型：裁剪
                            )
                            Box(modifier = Modifier.height(10.dp))
                            CardBox(){
                                Text(
                                    text = "S5720-52X-SI-AC 交换机",
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            CardBox(){
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
                                    Column(modifier = Modifier.fillMaxWidth(.6f)) {
                                        getLeftBody(
                                            title = "软件版本",
                                            data = "V200R019C00SPC500"
                                        )
                                        Box(modifier = Modifier.height(10.dp))
                                        getLeftBody(
                                            title = "设备序列号",
                                            data = "21023BYTH85300P8B"
                                        )
                                    }
                                    Column(modifier = Modifier.fillMaxWidth(1f)) {
                                        getLeftBody(
                                            title = "发布日期",
                                            data = "2023-12-15", boolean = true
                                        )
                                        Box(modifier = Modifier.height(10.dp))
                                        getLeftBody(
                                            title = "MAC地址",
                                            data = "5C:7D:5E:8F:2A:1B", boolean = true
                                        )
                                    }
                                }
                            }
                            CardBox(){
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                                        Text("POE功率", fontWeight = FontWeight.Bold)
                                        Box(modifier = Modifier.height(10.dp))
                                        Row {
                                            lBoxRText(color = Color(0xff2563EB), text = "总功率")
                                            Box(modifier = Modifier.width(5.dp))
                                            lBoxRText(color = Color(0xffFAAD14), text = "已用功率")
                                        }
                                    }
                                }
                            }
                        }
                    })
            }
        }
    }
}
@Composable
fun lBoxRText(color: Color,text:String){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(16.dp).clip(RoundedCornerShape(3.dp)).background(color))
        Box(modifier = Modifier.width(2.dp))
        Text(text, fontSize = 14.sp, color = Color(0xff6B7280))
    }
}

@Composable
fun CardBox( content: @Composable() (() -> Unit)){
    Box(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color(0xffFFFFFF))
                .padding(vertical = 15.dp, horizontal = 10.dp)
                //.height(45.dp)
                .fillMaxWidth()
            // .clip(RoundedCornerShape(8.dp))
        ) {
            content()
        }
    }
}

@Composable
fun getLeftBody(title: String, data: String, boolean: Boolean = false) {
    var h: Alignment. Horizontal =if (boolean) Alignment.End else Alignment.Start
    Column(horizontalAlignment = h, modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth()) {
        Text(text = title, color = Color(0xff6B7280), fontSize = 16.sp)
        Text(data, fontWeight = FontWeight.Bold, fontSize = 12.sp)
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
