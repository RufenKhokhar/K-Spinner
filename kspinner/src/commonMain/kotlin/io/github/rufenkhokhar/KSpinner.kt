package io.github.rufenkhokhar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpSize


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> KSpinner(
    modifier: Modifier = Modifier,
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit,
    shape: Shape = CardDefaults.shape,
    colors: CardColors = CardDefaults.cardColors(),
    border: BorderStroke?=null,
    selectedOptionItem: @Composable (T) -> Unit,
    dropDownOption: @Composable (T) -> Unit,
    dropDownIcon: @Composable () -> Unit,
    onOptionsShown: () -> Unit = {},
    onOptionsHidden: () -> Unit = {}
) {
    var anchorSize by remember { mutableStateOf(DpSize.Zero) }
    val density = LocalDensity.current
    var showOptions by rememberSaveable { mutableStateOf(false) }
    LaunchedEffect(showOptions) { if (showOptions) onOptionsShown() else onOptionsHidden() }
    Card(
        modifier = modifier.defaultMinSize(
            minWidth = TextFieldDefaults.MinWidth,
            minHeight = TextFieldDefaults.MinHeight
        ).onGloballyPositioned {
            anchorSize = with(density) { DpSize(it.size.width.toDp(), it.size.height.toDp()) }
        },
        shape = shape,
        colors = colors,
        border = border
    ) {
        Box(modifier = Modifier.size(anchorSize)) {
            DropdownMenuItem(
                text = {
                    selectedOptionItem(selectedOption)
                },
                onClick = { showOptions = true },
                trailingIcon = dropDownIcon,
                modifier = Modifier.matchParentSize(),
                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
            )
            DropdownMenu(
                expanded = showOptions,
                onDismissRequest = { showOptions = false },
                modifier = Modifier.width(anchorSize.width)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            dropDownOption(option)
                        },
                        onClick = {
                            showOptions = false
                            onOptionSelected(option)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }


}
