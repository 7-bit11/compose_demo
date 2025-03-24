@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

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

    //可变空列表
    var data: MutableList<BoxAttribute> = mutableListOf()
    var hl: MutableState<Boolean> = mutableStateOf(false);// 使用 mutableStateOf 管理状态
    var vlan: MutableState<Boolean> = mutableStateOf(false);// 使用 mutableStateOf 管理状态
    var items: MutableList<ItemAttribute> = mutableListOf(
        ItemAttribute(R.drawable.svg1, "修改设备"),
        ItemAttribute(R.drawable.svg3, "端口信息"),
        ItemAttribute(R.drawable.svg2, "VLAN列表"),
        ItemAttribute(R.drawable.svg5, "PVID"),
        ItemAttribute(R.drawable.svg4, "风暴抑制"),
        ItemAttribute(R.drawable.loop, "OTA升级"),
    );
    var tableData = mutableListOf(
        TableRow(1, "Alice", mutableStateOf(false), "New York"),
        TableRow(2, "Bob", mutableStateOf(false), "San Francisco"),
        TableRow(3, "Charlie", mutableStateOf(false), "Los Angeles"),
        TableRow(4, "Diana", mutableStateOf(false), "Chicago"),
        TableRow(3, "Charlie", mutableStateOf(false), "Los Angeles"),
        TableRow(4, "Diana", mutableStateOf(false), "Chicago"),
        TableRow(3, "Charlie", mutableStateOf(false), "Los Angeles"),
        TableRow(4, "Diana", mutableStateOf(false), "Chicago"),
        TableRow(3, "Charlie", mutableStateOf(false), "Los Angeles"),
        TableRow(4, "Diana", mutableStateOf(false), "Chicago"),
        TableRow(3, "Charlie", mutableStateOf(false), "Los Angeles"),
        TableRow(4, "Diana", mutableStateOf(false), "Chicago"),
        TableRow(3, "Charlie", mutableStateOf(false), "Los Angeles"),
        TableRow(4, "Diana", mutableStateOf(false), "Chicago"),
        TableRow(3, "Charlie", mutableStateOf(false), "Los Angeles"),
        TableRow(4, "Diana", mutableStateOf(false), "Chicago")
    )

    @SuppressLint("UnrememberedMutableInteractionSource")
    override fun onCreate(savedInstanceState: Bundle?) {
        // 添加元素

        data.add(BoxAttribute(Color(0xff22C55E), "1G/2.5G/10G"))//Color(0xff22C55E),"1G/2.5G/10G")
        data.add(BoxAttribute(Color(0xffFAAD14), "10M/100M"))
        data.add(BoxAttribute(Color(0xff7D8899), "未连接"))
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
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
                            var abb by remember { mutableStateOf(false) }
                            val abbColor: Color by animateColorAsState(
                                if (abb) Color.Red else Color.Blue
                            )
                            Box(modifier = Modifier.height(10.dp))
                            CardBox(modifier = Modifier.clickable(
                                MutableInteractionSource(),
                                indication = null
                            ) // 禁用点击效果
                            { abb = !abb }) {
                                Text(
                                    text = "S5720-52X-SI-AC 交换机",
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight.Bold,
                                    color = abbColor
                                )
                            }
                            CardBox() {
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
                            CardBox() {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                                        Text("POE功率", fontWeight = FontWeight.Bold)
                                        Box(modifier = Modifier.height(10.dp))
                                        Row {
                                            lBoxRText(color = Color(0xff2563EB), text = "总功率")
                                            Box(modifier = Modifier.width(5.dp))
                                            lBoxRText(color = Color(0xffFAAD14), text = "已用功率")
                                        }
                                    }
                                    Column(
                                        modifier = Modifier.padding(end = 10.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        PieChart(
                                            totalProgress = 1f,        // 总进度 100%
                                            usedProgress = 0.35f,      // 使用进度 65%
                                            totalColor = Color(0xff2563EB),
                                            usedColor = Color(0xffFAAD14),
                                            size = 40.dp
                                        )
                                        Text("100W/35W", fontSize = 12.sp)
                                    }
                                }
                            }
                            CardBox {
                                Column {
                                    Text("端口信息", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                    Box(modifier = Modifier.height(10.dp))
                                    LazyRow() {
                                        items(count = 24) { index ->

                                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                Box(
                                                    modifier = Modifier
                                                        .padding(horizontal = 5.dp)
                                                        .size(30.dp)
                                                        .clip(RoundedCornerShape(3.dp))
                                                        .background(
                                                            if (index % 5 == 0) Color(0xff22C55E) else Color(
                                                                0xffD8D8D8
                                                            )
                                                        )
                                                )
                                                Text((index + 1).toString())
                                            }
                                        }
                                    }
                                    Box(modifier = Modifier.height(10.dp))
                                    LazyRow() {
                                        items(count = data.size) { index ->
                                            Box(modifier = Modifier.padding(horizontal = 5.dp)) {
                                                lBoxRText(
                                                    data[index].color,
                                                    data[index].text
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            CardSwitch(
                                boolean = hl.value, text = "环路保护",
                                onCheckedChange = { bool ->
                                    hl.value = bool;
                                },
                            )
                            CardSwitch(
                                boolean = vlan.value, text = "VLAN一键隔离",
                                onCheckedChange = { bool ->
                                    vlan.value = bool;
                                },
                            )
//                            CardBox(modifier = Modifier.heightIn(max = 300.dp)) {
//
//
//                            }
                            Box(
                                modifier = Modifier
                                    .heightIn(max = 300.dp)
                                    .padding(horizontal = 15.dp, vertical = 10.dp)
                            ) {
                                LazyVerticalGrid(
                                    columns = GridCells.Fixed(4), // 每列最小宽度为 128.dp
                                ) {
                                    items(items.size) { item ->
                                        Box(
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .clip(
                                                    RoundedCornerShape(5.dp)
                                                )
                                                .background(color = Color.White)
                                        ) {
                                            Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .fillMaxWidth(),
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Box(Modifier.height(5.dp))
                                                Image(
                                                    contentScale = ContentScale.Crop,
                                                    painter = painterResource(id = items[item].id), // 从资源文件加载图片
                                                    contentDescription = items[item].str, // 图片描述  必须
                                                    modifier = Modifier
                                                        .size(16.dp)
                                                        .clip(RoundedCornerShape(2.dp))
                                                )
                                                Text(
                                                    items[item].str,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            CardBox(Modifier.heightIn(max = 500.dp)) {
                                LazyColumn(modifier = Modifier.padding(16.dp)) {
                                    // Table Header
                                    item {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 8.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                "端口",
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center,
                                                fontSize = 16.sp
                                            )
                                            Text(
                                                "状态",
                                                modifier = Modifier.weight(2f),
                                                textAlign = TextAlign.Center,
                                                fontSize = 16.sp
                                            )
                                            Text(
                                                "POE开关",
                                                modifier = Modifier.weight(2f),
                                                textAlign = TextAlign.Center,
                                                fontSize = 16.sp
                                            )
                                            Text(
                                                "功率",
                                                modifier = Modifier.weight(3f),
                                                textAlign = TextAlign.Center,
                                                fontSize = 16.sp
                                            )
                                        }
                                    }

                                    // Table Rows
                                    items(tableData.size) { row ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 8.dp)
                                               , verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                row.toString(),
                                                modifier = Modifier.weight(1f),
                                                textAlign = TextAlign.Center
                                            )
                                            Text(
                                                tableData[row].name,
                                                modifier = Modifier.weight(2f),
                                                textAlign = TextAlign.Center
                                            )
                                            Switch(
                                                tableData[row].age.value, onCheckedChange = {
                                                    tableData[row].age.value=!tableData[row].age.value;
                                                }, colors = SwitchDefaults.colors(
                                                    checkedThumbColor = Color.White, // 开启时滑块颜色
                                                    uncheckedThumbColor = Color.White, // 关闭时滑块颜色
                                                    checkedTrackColor = Color(0xff3F7EFA), // 开启时轨道颜色
                                                    uncheckedTrackColor = Color(0xffD8D8D8),// 关闭时轨道颜色
                                                    uncheckedBorderColor = Color.Transparent
                                                ),modifier = Modifier.scale(0.8f)
                                            )
                                            Text(
                                                tableData[row].city,
                                                modifier = Modifier.weight(3f),
                                                textAlign = TextAlign.Center, maxLines = 1, overflow = TextOverflow.Ellipsis
                                            )
                                        }
                                    }
                                }
                                Box(Modifier.height(100.dp))
                            }

                        }
                    })
            }
        }
    }
}

@Composable
fun CardSwitch(
    boolean: Boolean,
    text: String = "",
    modifier: Modifier = Modifier,
    onCheckedChange: ((Boolean) -> Unit)?
) {
    CardBox {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Text(text, fontSize = 18.sp, color = Color(0xff7D8899))
            Switch(
                boolean, onCheckedChange = onCheckedChange, colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White, // 开启时滑块颜色
                    uncheckedThumbColor = Color.White, // 关闭时滑块颜色
                    checkedTrackColor = Color(0xff3F7EFA), // 开启时轨道颜色
                    uncheckedTrackColor = Color(0xffD8D8D8),// 关闭时轨道颜色
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }
    }
}

@Composable
fun lBoxRText(color: Color, text: String, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(color)
        )
        Box(modifier = Modifier.width(2.dp))
        Text(text, fontSize = 14.sp, color = Color(0xff6B7280))
    }
}

@Composable
fun CardBox(modifier: Modifier = Modifier, content: @Composable() (() -> Unit)) {
    Box(
        modifier = modifier
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
    var h: Alignment.Horizontal = if (boolean) Alignment.End else Alignment.Start
    Column(
        horizontalAlignment = h,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
    ) {
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

@Composable
fun PieChart(
    totalProgress: Float, // 总进度 (0..1)
    usedProgress: Float,   // 使用进度 (0..1)
    totalColor: Color,     // 总进度颜色
    usedColor: Color,      // 使用进度颜色
    size: Dp = 100.dp      // 饼状图大小
) {
    // 确保进度在 0 到 1 之间
    val clampedTotalProgress = totalProgress.coerceIn(0f, 1f)
    val clampedUsedProgress = usedProgress.coerceIn(0f, clampedTotalProgress)

    Canvas(modifier = Modifier.size(size)) {
        // 绘制总进度
        drawPieChart(clampedTotalProgress, totalColor)
        // 绘制使用进度
        drawPieChart(clampedUsedProgress, usedColor)
    }
}

private fun DrawScope.drawPieChart(
    progress: Float,
    color: Color
) {
    val sweepAngle = 360 * progress
    drawArc(
        color = color,
        startAngle = -90f, // 从12点方向开始
        sweepAngle = sweepAngle,
        useCenter = true
    )
}

class BoxAttribute {
    val color: Color
    val text: String

    // 主构造函数
    constructor(color: Color, text: String) {
        this.color = color
        this.text = text
    }

}

class ItemAttribute {
    val id: Int;
    val str: String;

    constructor(id: Int, str: String) {
        this.id = id;
        this.str = str;
    }
}

data class TableRow(val id: Int, val name: String, var age: MutableState<Boolean>, val city: String)