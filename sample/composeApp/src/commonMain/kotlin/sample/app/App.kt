package sample.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sample.app.theme.AppTheme


@Composable
fun App() {
    val currentTheme = isSystemInDarkTheme()
    var darkTheme by rememberSaveable{
        mutableStateOf(currentTheme)
    }

    AppTheme(darkTheme = darkTheme) {
        Surface {
            Column(
                modifier = Modifier.fillMaxSize().safeDrawingPadding(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("App Theme")
                    Spacer(Modifier.weight(1f))
                    Switch(checked = darkTheme, onCheckedChange = {
                        darkTheme=it
                    })

                }
                KSpinnerRowDesignGalleryExpanded()
            }
        }
    }
}