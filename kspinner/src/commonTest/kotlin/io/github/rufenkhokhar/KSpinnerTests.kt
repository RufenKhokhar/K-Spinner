package io.github.rufenkhokhar

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class KSpinnerCommonTest {

    private val options = listOf("Options-1", "Options-2", "Options-3")

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun opensMenu_and_calls_onOptionsShown() = runComposeUiTest {
        var shown = 0
        var hidden = 0

        setContent {
            var selected by remember { mutableStateOf("Select Option") }
            KSpinner(
                options = options,
                selectedOption = selected,
                onOptionSelected = { selected = it },
                selectedOptionItem = { Text(it) },
                dropDownOption = { Text(it) },
                dropDownIcon = {},
                onOptionsShown = { shown++ },
                onOptionsHidden = { hidden++ },
            )
        }

        // Open by clicking the anchor text
        onNodeWithText("Select Option").performClick()

        // An option should now be visible
        onNodeWithText("Options-2").assertExists()

        // onOptionsShown should have fired at least once
        // (Note: onOptionsHidden usually fires once on first composition with show=false)
        assertTrue(shown >= 1, "onOptionsShown should be >=1, was $shown")
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun selectingOption_updatesSelection_callsCallback_and_closesMenu() = runComposeUiTest {
        var shown = 0
        var hidden = 0
        var lastSelected: String? = null

        setContent {
            var selected by remember { mutableStateOf("Select Option") }
            KSpinner(
                options = options,
                selectedOption = selected,
                onOptionSelected = {
                    lastSelected = it
                    selected = it
                },
                selectedOptionItem = { Text(it) },
                dropDownOption = { Text(it) },
                dropDownIcon = {},
                onOptionsShown = { shown++ },
                onOptionsHidden = { hidden++ },
            )
        }

        // Open
        onNodeWithText("Select Option").performClick()
        val hiddenBefore = hidden

        // Select an item
        onNodeWithText("Options-2").performClick()

        // Callback got it
        assertEquals("Options-2", lastSelected)

        // Anchor now shows the new selection
        onNodeWithText("Options-2").assertExists()

        // Menu closed -> hidden incremented
        assertEquals(hiddenBefore + 1, hidden, "onOptionsHidden should increment after selection")
    }
}
