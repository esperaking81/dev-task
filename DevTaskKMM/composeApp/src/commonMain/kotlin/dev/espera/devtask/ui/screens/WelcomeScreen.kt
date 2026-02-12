package dev.espera.devtask.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.espera.devtask.ui.components.Button
import dev.espera.devtask.ui.components.DevTaskLogo
import dev.espera.devtask.ui.components.KeyFeature
import dev.espera.devtask.ui.theme.AppTheme
import dev.espera.devtask.ui.theme.OnSurfaceVariant
import dev.espera.devtask.ui.theme.Primary
import devtask.composeapp.generated.resources.Res
import devtask.composeapp.generated.resources.a_developer_coding
import devtask.composeapp.generated.resources.intelligence
import devtask.composeapp.generated.resources.statistics
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class KeyFeatureItem(
    val icon: ImageVector,
    val cover: DrawableResource,
    val label: String,
    val description: String,
    val contentDescription: String? = null,
)

@Composable
fun WelcomeScreen(
    onGetStarted: () -> Unit,
) {
    val theme = MaterialTheme
    val typography = theme.typography
    val shapes = theme.shapes

    val features = listOf(
        KeyFeatureItem(
            icon = Icons.Default.SmartToy,
            cover = Res.drawable.intelligence,
            label = "AI Breakdown",
            description = "Paste your user story, get a generated checklist instantly.",
        ),
        KeyFeatureItem(
            icon = Icons.Default.Speed,
            cover = Res.drawable.statistics,
            label = "Velocity Tracking",
            description = "Measure your true coding speed and improve estimates.",
        ),
        KeyFeatureItem(
            icon = Icons.Default.Headphones,
            cover = Res.drawable.a_developer_coding,
            label = "Focus Mode",
            description = "Distraction-free environment for deep work sessions.",
        )
    )

    Scaffold(
        bottomBar = {
            Button(
                onClick = {
                    onGetStarted()
                },
                label = "Get Started",
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding).fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp).verticalScroll(
                    rememberScrollState()
                )
            ) {
                DevTaskLogo()
                Image(
                    painter = painterResource(Res.drawable.intelligence),
                    contentDescription = "Intelligence",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.padding(top = 24.dp)
                        .fillMaxWidth()
                        .aspectRatio(4f / 3f)
                        .clip(shapes.small),
                )
                Text(
                    buildAnnotatedString {
                        append("Code more,\n")
                        withStyle(
                            style = SpanStyle(color = Primary),
                        ) {
                            append("manage less.")
                        }
                    },
                    style = typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold,
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                )
                Text(
                    "The AI-powered task manager designed for developers. Break down complex features into bite-sized subtasks in seconds.",
                    textAlign = TextAlign.Center,
                    style = typography.labelLarge.copy(
                        color = OnSurfaceVariant,
                        fontSize = 14.sp,
                    ),
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                Column(
                    modifier = Modifier.padding(vertical = 20.dp),
                ) {
                    Text(
                        text = "Key Features".uppercase(),
                        fontWeight = FontWeight.Bold,
                        style = typography.labelLarge.copy(color = OnSurfaceVariant),
                        letterSpacing = 2.sp,
                    )
                    Row(
                        modifier = Modifier.padding(top = 16.dp)
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        features.map { feature ->
                            KeyFeature(
                                cover = feature.cover,
                                icon = feature.icon,
                                label = feature.label,
                                description = feature.description,
                                contentDescription = feature.contentDescription,
                                modifier = Modifier.width(LocalWindowInfo.current.containerDpSize.width * .6f),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomeScreen__Preview() {
    AppTheme {
        WelcomeScreen {

        }
    }
}