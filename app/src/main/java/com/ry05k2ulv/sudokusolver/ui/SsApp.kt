package com.ry05k2ulv.sudokusolver.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ry05k2ulv.sudokusolver.ui.home.HomeScreen
import com.ry05k2ulv.sudokusolver.ui.settings.SettingsDialog

@Composable
fun SsApp(
    windowSizeClass: WindowSizeClass
) {
    val isPortrait = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    var showSettingsDialog by remember {
        mutableStateOf(false)
    }

    if (showSettingsDialog) {
        SettingsDialog(onDismiss = { showSettingsDialog = false })
    }

    HomeScreen(
        onClickSettings = { showSettingsDialog = true },
        isPortrait = isPortrait
    )
}
