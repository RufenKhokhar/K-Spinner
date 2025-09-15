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

/**
 * A customizable spinner (dropdown) composable built with Material 3 components.
 *
 * This composable allows users to select an option from a list. It displays the
 * currently selected option and expands a dropdown menu to show all available options
 * when clicked.
 *
 * @param T The type of data in the options list.
 * @param modifier Optional [Modifier] to be applied to the spinner.
 * @param options The list of options to display in the dropdown.
 * @param selectedOption The currently selected option.
 * @param onOptionSelected A callback function that is invoked when an option is selected
 *   from the dropdown. It receives the selected option as a parameter.
 * @param shape The shape of the spinner's outline. Defaults to [CardDefaults.shape].
 * @param colors The colors to be used for the spinner's container. Defaults to [CardDefaults.cardColors].
 * @param border Optional [BorderStroke] to be applied to the spinner's container.
 * @param selectedOptionItem A composable lambda that defines how the `selectedOption`
 *   is displayed when the dropdown is collapsed. It receives the `selectedOption` as a parameter.
 * @param dropDownOption A composable lambda that defines how each option in the `options`
 *   list is displayed within the dropdown menu. It receives an option from the list as a parameter.
 * @param dropDownIcon A composable lambda that defines the icon to be displayed at the
 *   trailing edge of the selected option item (typically an arrow indicating it's a dropdown).
 * @param onOptionsShown An optional callback function that is invoked when the dropdown menu
 *   becomes visible.
 * @param onOptionsHidden An optional callback function that is invoked when the dropdown menu
 *   is dismissed.
 */
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
