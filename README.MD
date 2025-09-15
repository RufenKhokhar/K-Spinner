# K-Spinner ✨

A simple dropdown (spinner) component for **Compose Multiplatform**. Works on **Android** 🤖 and **iOS** 🍏.

---

![Maven Central](https://img.shields.io/maven-central/v/io.github.rufenkhokhar/K-Spinner)
![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?logo=kotlin&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green.svg)
![Platform](https://img.shields.io/badge/Platforms-Android%20|%20iOS-blue)
![Compose](https://img.shields.io/badge/Jetpack-Compose-4285F4?logo=jetpackcompose&logoColor=white)
![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)
![Issues](https://img.shields.io/github/issues/rufenkhokhar/K-Spinner)
![Stars](https://img.shields.io/github/stars/rufenkhokhar/K-Spinner?style=social)

---

## 🚀 Installation
Add the dependency in your **`commonMain`** source set:

```kotlin
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.github.rufenkhokhar:K-Spinner:latest_release")
            }
        }
    }
}
```

Make sure you have `mavenCentral()` in your repositories.

---

## 🎯 Usage

```kotlin
@Composable
fun CountriesSpinnerDemo() {
    var selected by remember { mutableStateOf("Pakistan") }
    val options = listOf("Pakistan", "India", "Bangladesh", "Sri Lanka")

    KSpinner(
        options = options,
        selectedOption = selected,
        onOptionSelected = { selected = it },
        selectedOptionItem = { Text(it) },
        dropDownOption = { Text(it) },
        dropDownIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) }
    )
}
```

👉 Tap the field, pick an option, and your state updates automatically.


---

## ⚙️ API

```kotlin
@Composable
fun <T> KSpinner(
    options: List<T>,
    selectedOption: T,
    onOptionSelected: (T) -> Unit,
    selectedOptionItem: @Composable (T) -> Unit,
    dropDownOption: @Composable (T) -> Unit,
    dropDownIcon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CardDefaults.shape,
    colors: CardColors = CardDefaults.cardColors(),
    border: BorderStroke? = null,
    onOptionsShown: () -> Unit = {},
    onOptionsHidden: () -> Unit = {}
)
```

- 📋 **`options`** – list of items to choose from
- ✅ **`selectedOption`** – current selected value
- 🎯 **`onOptionSelected`** – callback when a new item is picked
- 🎨 **`selectedOptionItem`** – how the chosen item looks
- 📌 **`dropDownOption`** – how each row in the dropdown looks
- ⬇️ **`dropDownIcon`** – trailing icon
- 🔔 **`onOptionsShown` / `onOptionsHidden`** – optional callbacks

---

## 📄 License
MIT

