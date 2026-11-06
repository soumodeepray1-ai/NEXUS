package com.nexus.cloud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Gamepad
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Bg = Color(0xFF080812)
private val Panel = Color(0xFF141426)
private val Purple = Color(0xFF9B5CFF)
private val Cyan = Color(0xFF35D9F5)
private val Muted = Color(0xFF9A9AAF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NexusApp() }
    }
}

@Composable
fun NexusApp() {
    var selectedTab by remember { mutableStateOf("Home") }
    var quality by remember { mutableStateOf("1080p") }
    var fps by remember { mutableStateOf("60 FPS") }
    var steamDemo by remember { mutableStateOf(false) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Bg,
            surface = Panel,
            primary = Purple,
            secondary = Cyan
        )
    ) {
        Scaffold(
            containerColor = Bg,
            bottomBar = {
                NavigationBar(containerColor = Color(0xFF10101D)) {
                    listOf(
                        Triple("Home", Icons.Default.Cloud, "Home"),
                        Triple("Library", Icons.Default.SportsEsports, "Library"),
                        Triple("Stream", Icons.Default.Gamepad, "Stream"),
                        Triple("Settings", Icons.Default.Settings, "Settings")
                    ).forEach { (label, icon, tab) ->
                        NavigationBarItem(
                            selected = selectedTab == tab,
                            onClick = { selectedTab = tab },
                            icon = { Icon(icon, contentDescription = label) },
                            label = { Text(label, fontSize = 10.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Cyan,
                                selectedTextColor = Cyan,
                                indicatorColor = Purple.copy(alpha = 0.18f),
                                unselectedIconColor = Muted,
                                unselectedTextColor = Muted
                            )
                        )
                    }
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Header()
                when (selectedTab) {
                    "Home" -> HomeScreen(
                        steamDemo = steamDemo,
                        onSteamClick = { steamDemo = !steamDemo },
                        onOpenLibrary = { selectedTab = "Library" },
                        onOpenStream = { selectedTab = "Stream" }
                    )
                    "Library" -> LibraryScreen()
                    "Stream" -> StreamScreen(
                        quality, { quality = it }, fps, { fps = it }
                    )
                    else -> SettingsScreen()
                }
                Text(
                    "NEXUS • BUILD 0.2.0 • STREAMING HOST NOT CONNECTED",
                    color = Muted,
                    fontSize = 9.sp,
                    letterSpacing = 1.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun Header() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(
                    Brush.linearGradient(listOf(Purple, Cyan)),
                    RoundedCornerShape(13.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Cloud, null, tint = Color.Black, modifier = Modifier.size(25.dp))
        }
        Spacer(Modifier.width(11.dp))
        Column {
            Text("NEXUS", color = Color.White, fontSize = 23.sp, fontWeight = FontWeight.Black, letterSpacing = 3.sp)
            Text("CLOUD GAMING", color = Cyan, fontSize = 9.sp, letterSpacing = 2.sp)
        }
        Spacer(Modifier.weight(1f))
        Text("● OFFLINE", color = Color(0xFFFF668A), fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun HomeScreen(
    steamDemo: Boolean,
    onSteamClick: () -> Unit,
    onOpenLibrary: () -> Unit,
    onOpenStream: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Column {
            Text("PLAY WITHOUT LIMITS.", color = Color.White, fontSize = 27.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 32.sp)
            Text("Your games. Your world.", color = Muted, fontSize = 14.sp)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(listOf(Color(0xFF32145F), Color(0xFF111C3C), Panel)),
                    RoundedCornerShape(22.dp)
                )
                .border(1.dp, Purple.copy(alpha = 0.65f), RoundedCornerShape(22.dp))
                .padding(18.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("NEXUS STREAM", color = Cyan, fontSize = 10.sp, letterSpacing = 2.sp, fontWeight = FontWeight.Bold)
                Text("Your next session\nstarts here.", color = Color.White, fontSize = 23.sp, fontWeight = FontWeight.Bold, lineHeight = 27.sp)
                Text("Connect a compatible gaming host to start streaming.", color = Color(0xFFD0CDE2), fontSize = 12.sp)
                Button(
                    onClick = onOpenStream,
                    colors = ButtonDefaults.buttonColors(containerColor = Purple),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.Gamepad, null)
                    Spacer(Modifier.width(8.dp))
                    Text("OPEN STREAM")
                }
            }
        }

        SectionTitle("YOUR ACCOUNTS", "CONNECTION")
        Card(
            colors = CardDefaults.cardColors(containerColor = Panel),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(44.dp).background(Color(0xFF202B3C), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("S", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Black)
                }
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text("STEAM", color = Color.White, fontWeight = FontWeight.Bold)
                    Text(if (steamDemo) "UI demo enabled — not authenticated" else "Not connected", color = Muted, fontSize = 11.sp)
                }
                OutlinedButton(onClick = onSteamClick, shape = RoundedCornerShape(10.dp)) {
                    Text(if (steamDemo) "DEMO ON" else "CONNECT", color = Cyan, fontSize = 10.sp)
                }
            }
        }
        SectionTitle("QUICK ACCESS", "EXPLORE")
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickCard("GAME LIBRARY", "Browse your games", Modifier.weight(1f), onOpenLibrary)
            QuickCard("STREAM SETUP", "Video & controls", Modifier.weight(1f), onOpenStream)
        }
        Text(
            "Heads up: Steam sign-in and live game streaming are not active in this prototype yet.",
            color = Muted, fontSize = 11.sp
        )
    }
}

@Composable
private fun SectionTitle(title: String, tag: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(title, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        Spacer(Modifier.weight(1f))
        Text(tag, color = Purple, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
    }
}

@Composable
private fun QuickCard(title: String, subtitle: String, modifier: Modifier, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .background(Panel, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFF292943), RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(7.dp)
    ) {
        Text(title, color = Cyan, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Text(subtitle, color = Muted, fontSize = 11.sp)
    }
}

@Composable
private fun LibraryScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("GAME LIBRARY", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        Text("Your connected games will appear here.", color = Muted, fontSize = 13.sp)
        listOf("Featured games", "Recently played", "All games").forEach { title ->
            Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(15.dp)) {
                Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.SportsEsports, null, tint = Purple)
                    Spacer(Modifier.width(12.dp))
                    Text(title, color = Color.White, modifier = Modifier.weight(1f))
                    Text("EMPTY", color = Muted, fontSize = 9.sp)
                }
            }
        }
        Text("No Steam library is loaded. The Connect button on Home is a visual demo only.", color = Muted, fontSize = 11.sp)
    }
}

@Composable
private fun StreamScreen(
    quality: String, onQuality: (String) -> Unit,
    fps: String, onFps: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("STREAM SETUP", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(18.dp)) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("HOST STATUS", color = Cyan, fontSize = 10.sp, letterSpacing = 2.sp, fontWeight = FontWeight.Bold)
                Text("NO HOST CONNECTED", color = Color(0xFFFF668A), fontWeight = FontWeight.Bold)
                Text("A compatible gaming PC or cloud GPU host is required before streaming can begin.", color = Muted, fontSize = 12.sp)
            }
        }
        Text("VIDEO QUALITY", color = Color.White, fontWeight = FontWeight.Bold)
        ChoiceRow(listOf("720p", "1080p", "4K"), quality, onQuality)
        Text("FRAME RATE TARGET", color = Color.White, fontWeight = FontWeight.Bold)
        ChoiceRow(listOf("30 FPS", "60 FPS", "90 FPS", "120 FPS"), fps, onFps)
        Text("These are requested preferences only. Actual resolution and FPS depend on the host, network, decoder and display.", color = Muted, fontSize = 11.sp)
        Button(
            onClick = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(disabledContainerColor = Color(0xFF292943)),
            shape = RoundedCornerShape(12.dp)
        ) { Text("START STREAM • HOST REQUIRED") }
    }
}

@Composable
private fun ChoiceRow(options: List<String>, selected: String, onSelect: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        options.chunked(2).forEach { pair ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                pair.forEach { option ->
                    val active = option == selected
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(if (active) Purple.copy(alpha = 0.22f) else Panel, RoundedCornerShape(11.dp))
                            .border(1.dp, if (active) Purple else Color(0xFF303047), RoundedCornerShape(11.dp))
                            .clickable { onSelect(option) }
                            .padding(vertical = 13.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(option, color = if (active) Cyan else Muted, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
                if (pair.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun SettingsScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("SETTINGS", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        listOf(
            "Appearance" to "Cyber • Neon Purple",
            "Platform" to "Android Phone",
            "TV support" to "Planned",
            "App version" to "0.2.0 Prototype"
        ).forEach { (title, value) ->
            Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(14.dp)) {
                Column(Modifier.fillMaxWidth().padding(16.dp)) {
                    Text(title, color = Color.White, fontWeight = FontWeight.Bold)
                    Text(value, color = Muted, fontSize = 11.sp)
                }
            }
        }
    }
}
