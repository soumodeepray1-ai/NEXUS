
package com.nexus.cloud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val bg = Color(0xFF080812)
private val panel = Color(0xFF17172B)
private val purple = Color(0xFF9B5CFF)
private val cyan = Color(0xFF35D9F5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NexusApp() }
    }
}

@Composable
fun NexusApp() {
    var page by remember { mutableStateOf("HOME") }
    var quality by remember { mutableStateOf("1080p") }
    var fps by remember { mutableStateOf("60 FPS") }

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = bg,
            surface = panel,
            primary = purple,
            secondary = cyan
        )
    ) {
        Scaffold(
            containerColor = bg,
            bottomBar = {
                NavigationBar(containerColor = panel) {
                    listOf("HOME", "LIBRARY", "STREAM", "SETTINGS").forEach {
                        NavigationBarItem(
                            selected = page == it,
                            onClick = { page = it },
                            icon = { Text("◆") },
                            label = { Text(it, fontSize = 9.sp) }
                        )
                    }
                }
            }
        ) { padding ->
            Column(
                Modifier.fillMaxSize()
                    .padding(padding)
                    .padding(20.dp)
            ) {
                Text(
                    "N E X U S",
                    color = cyan,
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    "CLOUD GAMING SYSTEM",
                    color = purple,
                    fontSize = 10.sp
                )
                Spacer(Modifier.height(28.dp))

                when (page) {
                    "HOME" -> {
                        Text(
                            "PLAY WITHOUT LIMITS.",
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(18.dp))
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = panel
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Column(Modifier.padding(20.dp)) {
                                Text("NEXUS STREAM", color = cyan)
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    "Your games. Your world.",
                                    color = Color.White,
                                    fontSize = 21.sp
                                )
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    "Connect a gaming host to begin.",
                                    color = Color.LightGray
                                )
                                Spacer(Modifier.height(18.dp))
                                Button(
                                    onClick = { page = "STREAM" }
                                ) {
                                    Text("OPEN STREAM")
                                }
                            }
                        }
                        Spacer(Modifier.height(20.dp))
                        Text("STEAM ACCOUNT", color = cyan)
                        Spacer(Modifier.height(10.dp))
                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("STEAM CONNECTION - COMING SOON")
                        }
                        Spacer(Modifier.height(12.dp))
                        Text(
                            "Steam login is not active yet.",
                            color = Color.Gray,
                            fontSize = 11.sp
                        )
                    }

                    "LIBRARY" -> {
                        Text(
                            "GAME LIBRARY",
                            color = Color.White,
                            fontSize = 24.sp
                        )
                        Spacer(Modifier.height(20.dp))
                        Text(
                            "Your games will appear here.",
                            color = Color.LightGray
                        )
                        Text(
                            "No Steam library connected.",
                            color = Color.Gray
                        )
                    }

                    "STREAM" -> {
                        Text(
                            "STREAM SETTINGS",
                            color = Color.White,
                            fontSize = 24.sp
                        )
                        Spacer(Modifier.height(20.dp))
                        Text("RESOLUTION", color = cyan)
                        Spacer(Modifier.height(10.dp))
                        listOf("720p", "1080p", "4K").forEach {
                            Option(it, quality == it) {
                                quality = it
                            }
                        }
                        Spacer(Modifier.height(18.dp))
                        Text("FRAME RATE", color = cyan)
                        Spacer(Modifier.height(10.dp))
                        listOf("30 FPS", "60 FPS", "90 FPS", "120 FPS").forEach {
                            Option(it, fps == it) {
                                fps = it
                            }
                        }
                        Spacer(Modifier.height(20.dp))
                        Text(
                            "HOST NOT CONNECTED",
                            color = Color(0xFFFF6688)
                        )
                        Text(
                            "Settings are preferences only. Actual performance depends on the gaming host and network.",
                            color = Color.Gray,
                            fontSize = 11.sp
                        )
                    }

                    else -> {
                        Text(
                            "NEXUS SETTINGS",
                            color = Color.White,
                            fontSize = 24.sp
                        )
                        Spacer(Modifier.height(20.dp))
                        Text("Version 0.2.0", color = cyan)
                        Text("Cyberpunk interface", color = Color.Gray)
                        Text("Android support", color = Color.Gray)
                    }
                }
            }
        }
    }
}

@Composable
fun Option(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        Modifier.fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(
                if (selected) purple.copy(alpha = 0.2f) else panel,
                RoundedCornerShape(10.dp)
            )
            .border(
                1.dp,
                if (selected) purple else Color.DarkGray,
                RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(14.dp)
    ) {
        Text(
            label,
            color = if (selected) cyan else Color.White
        )
    }
}
