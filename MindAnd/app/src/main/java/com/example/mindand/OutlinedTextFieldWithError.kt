package com.example.mindand

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun OutlinedTextFieldWithError(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardOptions,
    isError: Boolean,
    errorMessage: String?,
    validate: (String) -> Boolean
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            label = { Text(label) },
            singleLine = true,
            isError = !validate(value),
            keyboardOptions = keyboardType,
            modifier = Modifier.fillMaxWidth()
        )
        if (!validate(value)) {
            Text(
                text = errorMessage ?: "",
                color = Color.Red
            )
        }
    }
}