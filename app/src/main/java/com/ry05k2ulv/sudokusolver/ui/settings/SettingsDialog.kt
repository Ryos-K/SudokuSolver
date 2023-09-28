package com.ry05k2ulv.sudokusolver.ui.settings

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsDialog(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    AlertDialog(onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ })
}

@Composable
private fun ColumnScope.SettingsPanel() {

}

@Composable
private fun SectionTitle() {

}

@Composable
private fun ChooserRow() {

}