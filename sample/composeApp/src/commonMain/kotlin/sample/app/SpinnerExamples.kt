@file:OptIn(ExperimentalMaterial3Api::class)
package sample.app


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.rufenkhokhar.KSpinner
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
private fun SectionCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(title, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            content()
        }
    }
}


@Composable
fun SpinnerRowMinimal(label: String) {
    Text(
        text = label,
        fontSize = 14.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
    )
}

@Composable
fun SpinnerRowIcon(label: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(Icons.Default.Apps, contentDescription = null)
        Text(text = label, fontSize = 14.sp)
    }
}

@Composable
fun SpinnerRowAvatar(label: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(Color(0xFF2196F3)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label.firstOrNull()?.uppercase() ?: "?",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(text = label, fontSize = 14.sp)
    }
}

@Composable
fun SpinnerRowBadge(label: String, badge: String = "NEW") {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 14.sp)
        Surface(color = Color(0xFFE91E63), shape = RoundedCornerShape(8.dp)) {
            Text(
                text = badge,
                fontSize = 10.sp,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
    }
}

@Composable
fun SpinnerRowSubtitle(title: String, subtitle: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Text(text = subtitle, fontSize = 12.sp, color = Color.Gray, maxLines = 1)
    }
}

@Composable
fun SpinnerRowChip(label: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            color = Color(0xFFE0F2F1),
            shape = RoundedCornerShape(50)
        ) {
            Text(
                text = label.take(4).uppercase(),
                color = Color(0xFF00695C),
                fontSize = 10.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
        }
        Text(text = label, fontSize = 14.sp)
    }
}


@Composable
fun SpinnerRowCheck(label: String, isSelected: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 14.sp)
        if (isSelected) {
            Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF4CAF50))
        }
    }
}

@Composable
fun SpinnerRowGradient(label: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF42A5F5), Color(0xFF478DE0))
                ),
                shape = RoundedCornerShape(6.dp)
            )
            .padding(8.dp)
    ) {
        Text(text = label, fontSize = 14.sp, color = Color.White)
    }
}

@Composable
fun SpinnerRowOutlined(label: String) {
    Surface(
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun SpinnerRowEmoji(label: String, emoji: String = "⭐") {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = emoji, fontSize = 16.sp)
        Text(text = label, fontSize = 14.sp)
    }
}

@Composable
fun SpinnerRowTwoColumn(title: String, detail: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Text(text = detail, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun SpinnerRowToggle(label: String, enabled: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 14.sp)
        Switch(checked = enabled, onCheckedChange = null)
    }
}

@Composable
fun SpinnerRowRichIcon(label: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(color)
        )
        Text(text = label, fontSize = 14.sp)
    }
}

// ---------------------------------------------------------------------
// Expanded gallery combining everything (1–15)
// ---------------------------------------------------------------------
@Composable
fun KSpinnerRowDesignGalleryExpanded() {
    val sampleOptions = listOf("Alpha", "Beta", "Gamma", "Delta")

    var sMinimal by rememberSaveable { mutableStateOf("Select") }
    var sIcon by rememberSaveable { mutableStateOf("Select") }
    var sAvatar by rememberSaveable { mutableStateOf("Select") }
    var sBadge by rememberSaveable { mutableStateOf("Select") }
    var sSubtitle by rememberSaveable { mutableStateOf("Select") }
    var sChip by rememberSaveable { mutableStateOf("Select") }

    var sCheck by rememberSaveable { mutableStateOf("Select") }
    var sGradient by rememberSaveable { mutableStateOf("Select") }
    var sOutlined by rememberSaveable { mutableStateOf("Select") }
    var sEmoji by rememberSaveable { mutableStateOf("Select") }
    var sTwoCol by rememberSaveable { mutableStateOf("Select") }
    var sToggle by rememberSaveable { mutableStateOf("Select") }
    var sRichIcon by rememberSaveable { mutableStateOf("Select") }

    LazyColumn(
        modifier =  Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 24.dp, top = 8.dp)
    ) {
        // 1 Minimal
        item {
            SectionCard("1) Minimal Text") {
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sMinimal,
                    onOptionSelected = { sMinimal = it },
                    selectedOptionItem = { SpinnerRowMinimal(it) },
                    dropDownOption = { SpinnerRowMinimal(it) },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_minimal").width(240.dp),
                    border = BorderStroke(1.dp, Color(0xFFDDDDDD)),
                    shape = RoundedCornerShape(6.dp)
                )
            }
        }
        // 2 Icon
        item {
            SectionCard("2) Icon + Text") {
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sIcon,
                    onOptionSelected = { sIcon = it },
                    selectedOptionItem = { SpinnerRowIcon(it) },
                    dropDownOption = { SpinnerRowIcon(it) },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_icon").width(240.dp),
                    border = BorderStroke(2.dp, Color.Blue),
                    shape = RoundedCornerShape(4.dp)
                )
            }
        }
        // 3 Avatar
        item {
            SectionCard("3) Avatar (initial bubble)") {
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sAvatar,
                    onOptionSelected = { sAvatar = it },
                    selectedOptionItem = { SpinnerRowAvatar(it) },
                    dropDownOption = { SpinnerRowAvatar(it) },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_avatar").width(260.dp),
                    border = BorderStroke(1.dp, Color(0xFF90CAF9)),
                    shape = RoundedCornerShape(10.dp)
                )
            }
        }
        // 4 Badge
        item {
            SectionCard("4) Trailing Badge") {
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sBadge,
                    onOptionSelected = { sBadge = it },
                    selectedOptionItem = { SpinnerRowBadge(it, "HOT") },
                    dropDownOption = { SpinnerRowBadge(it, "NEW") },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_badge").width(280.dp),
                    border = BorderStroke(1.dp, Color(0xFFE91E63)),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }
        // 5 Subtitle
        item {
            SectionCard("5) Subtitle (label + helper)") {
                val subtitle: (String) -> String = { "Helpful note about $it" }
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sSubtitle,
                    onOptionSelected = { sSubtitle = it },
                    selectedOptionItem = { SpinnerRowSubtitle(it, subtitle(it)) },
                    dropDownOption = { SpinnerRowSubtitle(it, subtitle(it)) },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_subtitle").width(300.dp),
                    border = BorderStroke(1.dp, Color.Gray),
                    shape = RoundedCornerShape(6.dp)
                )
            }
        }
        // 6 Chip
        item {
            SectionCard("6) Chip Accent") {
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sChip,
                    onOptionSelected = { sChip = it },
                    selectedOptionItem = { SpinnerRowChip(it) },
                    dropDownOption = { SpinnerRowChip(it) },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_chip").width(260.dp),
                    border = BorderStroke(1.dp, Color(0xFF26A69A)),
                    shape = RoundedCornerShape(18.dp)
                )
            }
        }

        // 9 Checkmark
        item {
            SectionCard("9) Checkmark (marks selection)") {
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sCheck,
                    onOptionSelected = { sCheck = it },
                    selectedOptionItem = { SpinnerRowCheck(it, isSelected = it == sCheck) },
                    dropDownOption = { SpinnerRowCheck(it, isSelected = it == sCheck) },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_check").width(260.dp),
                    border = BorderStroke(1.dp, Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }
        // 10 Gradient
        item {
            SectionCard("10) Gradient Background") {
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sGradient,
                    onOptionSelected = { sGradient = it },
                    selectedOptionItem = { SpinnerRowGradient(it) },
                    dropDownOption = { SpinnerRowGradient(it) },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_gradient").width(280.dp),
                    border = BorderStroke(0.dp, Color.Transparent),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }
        // 11 Outlined
        item {
            SectionCard("11) Outlined Box") {
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sOutlined,
                    onOptionSelected = { sOutlined = it },
                    selectedOptionItem = { SpinnerRowOutlined(it) },
                    dropDownOption = { SpinnerRowOutlined(it) },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_outlined").width(280.dp),
                    border = BorderStroke(1.dp, Color(0xFF9E9E9E)),
                    shape = RoundedCornerShape(6.dp)
                )
            }
        }
        // 12 Emoji
        item {
            SectionCard("12) Emoji Lead") {
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sEmoji,
                    onOptionSelected = { sEmoji = it },
                    selectedOptionItem = { SpinnerRowEmoji(it, "🔥") },
                    dropDownOption = { SpinnerRowEmoji(it, "⭐") },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_emoji").width(240.dp),
                    border = BorderStroke(1.dp, Color(0xFFFFC107)),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }
        // 13 Two-Column
        item {
            SectionCard("13) Two-Column (title + detail)") {
                val detail: (String) -> String = { "v${it.length}.0" }
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sTwoCol,
                    onOptionSelected = { sTwoCol = it },
                    selectedOptionItem = { SpinnerRowTwoColumn(it, detail(it)) },
                    dropDownOption = { SpinnerRowTwoColumn(it, detail(it)) },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_twocol").width(300.dp),
                    border = BorderStroke(1.dp, Color(0xFFBDBDBD)),
                    shape = RoundedCornerShape(6.dp)
                )
            }
        }
        // 14 Toggle
        item {
            SectionCard("14) Toggle (on/off)") {
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sToggle,
                    onOptionSelected = { sToggle = it },
                    selectedOptionItem = { SpinnerRowToggle(it, enabled = it == "Alpha" || it == "Gamma") },
                    dropDownOption = { SpinnerRowToggle(it, enabled = it == "Alpha" || it == "Gamma") },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_toggle").width(300.dp),
                    border = BorderStroke(1.dp, Color(0xFF9CCC65)),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }
        // 15 Rich Icon (colored dot)
        item {
            SectionCard("15) Rich Icon (color dot)") {
                val colorOf: (String) -> Color = {
                    when (it) {
                        "Alpha" -> Color(0xFFE57373)
                        "Beta" -> Color(0xFF64B5F6)
                        "Gamma" -> Color(0xFF81C784)
                        else -> Color(0xFFFFB74D)
                    }
                }
                KSpinner(
                    options = sampleOptions,
                    selectedOption = sRichIcon,
                    onOptionSelected = { sRichIcon = it },
                    selectedOptionItem = { SpinnerRowRichIcon(it, colorOf(it)) },
                    dropDownOption = { SpinnerRowRichIcon(it, colorOf(it)) },
                    dropDownIcon = { Icon(Icons.Default.ArrowDropDown, null) },
                    modifier = Modifier.testTag("row_richicon").width(260.dp),
                    border = BorderStroke(1.dp, Color(0xFF90A4AE)),
                    shape = RoundedCornerShape(10.dp)
                )
            }
        }
    }

}




