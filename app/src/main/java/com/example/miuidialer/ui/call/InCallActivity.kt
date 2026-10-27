package com.example.miuidialer.ui.call

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.miuidialer.service.MiuiInCallService
import com.example.miuidialer.ui.theme.MiuiDialerTheme

class InCallActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiuiDialerTheme {
                InCallScreen(
                    onEndCall = {
                        MiuiInCallService.currentCall?.disconnect()
                        finish()
                    }
                )
            }
        }
    }
}

@Composable
fun InCallScreen(onEndCall: () -> Unit) {
    var isMuted by remember { mutableStateOf(false) }
    var isSpeaker by remember { mutableStateOf(false) }
    var isRecording by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF222222))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 64.dp)
        ) {
            Text(text = "Иван Иванов", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "+7 (999) 123-45-67", fontSize = 16.sp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "00:24", fontSize = 14.sp, color = Color(0xFF4CD964))
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CallActionButton(
                    icon = if (isRecording) Icons.Default.FiberManualRecord else Icons.Default.RadioButtonUnchecked,
                    label = "Запись",
                    tint = if (isRecording) Color.Red else Color.White
                ) { isRecording = !isRecording }

                CallActionButton(
                    icon = if (isMuted) Icons.Default.MicOff else Icons.Default.Mic,
                    label = "Без звука"
                ) { isMuted = !isMuted }

                CallActionButton(
                    icon = if (isSpeaker) Icons.Default.VolumeUp else Icons.Default.VolumeMute,
                    label = "Динамик"
                ) { isSpeaker = !isSpeaker }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CallActionButton(icon = Icons.Default.Dialpad, label = "Клавиатура") {}
                CallActionButton(icon = Icons.Default.AddCall, label = "Добавить") {}
                CallActionButton(icon = Icons.Default.Pause, label = "Удержание") {}
            }
        }

        Button(
            onClick = onEndCall,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3B30))
        ) {
            Icon(Icons.Default.CallEnd, contentDescription = "Завершить", tint = Color.White, modifier = Modifier.size(32.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CallActionButton(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, tint: Color = Color.White, onClick: () -> Unit = {}) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(72.dp)
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.15f))
        ) {
            Icon(icon, contentDescription = label, tint = tint)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 12.sp, color = Color.White)
    }
}
