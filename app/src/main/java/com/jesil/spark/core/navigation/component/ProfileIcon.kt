package com.jesil.spark.core.navigation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jesil.spark.core.theme.SparkTheme

@Composable
fun ProfileIcon(modifier: Modifier = Modifier) {
    IconButton(
        modifier = modifier,
        onClick = { /* Handle profile icon click */ }
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Profile",
        )
    }
}


@Preview
@Composable
private fun ProfileIconPreview() {
    SparkTheme {
        ProfileIcon()
    }
}
