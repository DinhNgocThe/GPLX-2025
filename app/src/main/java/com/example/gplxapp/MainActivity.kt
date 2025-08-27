package com.example.gplxapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gplxapp.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ThemeTutorial(
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

/*
    Khi ae chọn 1 màu là surface, nếu điện thoại ở light mode thì là màu trắng, darkmode là màu đen.
    Light/dark mode có thể tùy chình = code hoặc trực tiếp trên hệ điều hành
    Màu nền của các screen sẽ là MaterialTheme.colorScheme.surface.
    Màu chữ thì thường sẽ thêm tiền tố "on" ví dụ: onSurface, onPrimary, ...
    Màu chữ mà nằm trực tiếp trên nền thì dùng onSurface.
    Màu chữ nằm trên 1 button có màu là primary thì dùng onPrimary.
    Có thể phân chia các cấp như primary, secondary, tertiary, ...
    Màu chữ lỗi sẽ là MaterialTheme.colorScheme.error.
    Font chữ thì sử dụng MaterialTheme.typography.{loại font}.
    Có thể tùy chỉnh cỡ chữ bằng cách MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp)
    Tùy chỉnh độ trong suốt bằng .copy(alpha = 0.5f)
    Trên figma design sẽ tương ứng với mã màu trong color.kt, ae vào đó để chọn đúng màu design
*/
@Composable
fun ThemeTutorial(
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = innerPadding.calculateTopPadding())
            .background(MaterialTheme.colorScheme.surface), // Surface is primary background
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Theme Tutorial",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = "Technology today is developing rapidly, " +
                    "bringing countless opportunities for people to connect, " +
                    "learn, and create. With the support of modern applications, " +
                    "we can easily manage our work, communicate with friends, " +
                    "and even build complex projects with just a few taps. " +
                    "The key is to make the most of technology in the right way, " +
                    "using it to serve our lives and personal growth.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.size(20.dp))

        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .size(width = 200.dp, height = 50.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(text = "Button")
        }

        Spacer(modifier = Modifier.size(20.dp))

        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .size(width = 200.dp, height = 50.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(text = "Button")
        }

        Spacer(modifier = Modifier.size(20.dp))

        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            ),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .size(width = 200.dp, height = 50.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(text = "Button")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewThemeTutorial() {
    ThemeTutorial(innerPadding = PaddingValues())
}