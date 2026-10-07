package co.uniquindio.seguimientodos.features.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import co.uniquindio.seguimientodos.ui.theme.*

@Composable
fun WelcomeScreen(
    onNavigateToAuth: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColabBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.5f))

        // Uso de Coil3 para emular la ilustración principal del mockup tal como en la referencia técnica
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://dummyimage.com/600x400/6d9757/ffffff&text=coLab+tour")
                .crossfade(true)
                .build(),
            contentDescription = "Ilustración de bienvenida",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.weight(0.5f))

        Text(
            text = "Descubre lo que las guías no cuentan",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = ColabDarkGreen,
            textAlign = TextAlign.Center,
            lineHeight = 34.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Lugares compartidos por viajeros y locales, verificados por moderadores para que llegues sin sorpresas.",
            fontSize = 16.sp,
            color = ColabTextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onNavigateToAuth) {
                Text("Omitir", color = ColabTextSecondary, fontSize = 16.sp)
            }
            Button(
                onClick = onNavigateToAuth,
                colors = ButtonDefaults.buttonColors(containerColor = ColabDarkGreen)
            ) {
                Text("Siguiente", color = ColabSurface, fontSize = 16.sp)
            }
        }
    }
}