package com.example.myapplication.modules.shared

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.modules.theme.OffWhite
import com.example.myapplication.modules.theme.VeryDarkGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String = "Title",
    actionIcon: ImageVector? = null,
    navigationIcon: ImageVector? = null,
    onClickNavigation: () -> Unit = {},
    onClickAction: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = VeryDarkGray,
            )
        },
        actions = {
            if (actionIcon != null) {
                IconButton(onClickAction) {
                    Icon(
                        actionIcon,
                        contentDescription = "Icon $actionIcon",
                        tint = VeryDarkGray
                    )
                }
            }
        },
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(onClickNavigation) {
                    Icon(
                        navigationIcon,
                        contentDescription = "Icon $actionIcon",
                        tint = VeryDarkGray
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = OffWhite)
    )
}

@Preview
@Composable
private fun CustomTopAppBarPreview() {
    CustomTopAppBar(
        title = "Title",
        actionIcon = Icons.Default.LocationOn,
        onClickAction = { }
    )
}