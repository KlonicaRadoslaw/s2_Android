package com.example.mindand

import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

import com.example.mindand.ui.theme.MindAndTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindAndTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
                ProfileScreenInitial()
            }
        }
    }
}

@Composable
fun ProfileScreenInitial() {
    val name = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val numberOfColors = rememberSaveable { mutableStateOf("") }
    val profileImageUri = rememberSaveable { mutableStateOf<Uri?>(null) }

    val isNameValid = name.value.isNotEmpty()
    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
    val isNumberOfColorsValid = numberOfColors.value.toIntOrNull()?.let {it in 5..10} ?: false

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MasterAnd",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 46.dp)
        )

        ProfileImageWithPicker(profileImageUri.value) { uri -> profileImageUri.value = uri }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextFieldWithError(
            value = name.value,
            onValueChange = { name.value = it },
            label = "Enter name",
            isError = !isNameValid,
            keyboardType = KeyboardType.Text,
            errorMessage = "Name can't be empty",
            validate = { it.isNotEmpty() }
        )
        OutlinedTextFieldWithError(
            value = email.value,
            onValueChange = { email.value = it },
            label = "Enter email",
            keyboardType = KeyboardType.Email,
            isError = !isEmailValid,
            errorMessage = "Enter a valid email",
            validate = { Patterns.EMAIL_ADDRESS.matcher(it).matches() }
        )
        OutlinedTextFieldWithError(
            value = numberOfColors.value,
            onValueChange = { numberOfColors.value = it },
            label = "Enter number of colors",
            keyboardType = KeyboardType.Number,
            isError = !isNumberOfColorsValid,
            errorMessage = "Number must be between 5 and 10",
            validate = { it.toIntOrNull()?.let { it in 5..10 } ?: false }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                //navigate
            },
            enabled = isNameValid && isEmailValid && isNumberOfColorsValid
        ) {
            Text("Next")
        }
    }
}

@Composable
fun OutlinedTextFieldWithError(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType,
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
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier.fillMaxWidth()
        )
        if (!validate(value)) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun ProfileImageWithPicker(
    profileImageUri: Uri?,
    onImagePicked: (Uri) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> uri?.let { onImagePicked(it) } }
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(100.dp)
    ) {
        if (profileImageUri == null) {
            Image(
                painter = painterResource(R.drawable.ic_baseline_question_mark_24),
                contentDescription = "Profile photo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            AsyncImage(
                model = profileImageUri,
                contentDescription = "Profile photo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        IconButton(
            onClick = { launcher.launch("image/*") },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Pick image"
            )
        }
    }
}


@Preview
@Composable
fun ProfileScreenInitialPreview() {
    MindAndTheme {
        ProfileScreenInitial()
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MindAndTheme {
//        Greeting("Android")
//    }
//}