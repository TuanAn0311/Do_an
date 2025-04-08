package com.example.do_an.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.*

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var usernameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var generalError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign in", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                usernameError = ""
                generalError = ""
            },
            label = { Text("Username or Email") },
            singleLine = true,
            isError = usernameError.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if (usernameError.isNotEmpty()) Text(usernameError, color = Color.Red, fontSize = 12.sp)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = ""
                generalError = ""
            },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            isError = passwordError.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                if (passwordError.isNotEmpty()) Text(passwordError, color = Color.Red, fontSize = 12.sp)
            }
        )

        if (generalError.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = generalError, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                var valid = true

                if (username.isBlank()) {
                    usernameError = "Username or Email is required."
                    valid = false
                } else if (username.contains("@") && !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
                    usernameError = "Invalid email format."
                    valid = false
                }

                if (password.isBlank()) {
                    passwordError = "Password is required."
                    valid = false
                }

                if (valid) {
                    // ⚠️ Thay thế bằng logic xác thực thực tế
                    val userExists = username == "test" && password == "123456" // giả lập

                    if (userExists) {
                        onLoginClick()
                    } else {
                        generalError = "Invalid credentials. Please try again."
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2DA44E))
        ) {
            Text("Sign in")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { onRegisterClick() }) {
            Text(
                text = "New to the app? Create an account",
                color = Color.Blue
            )
        }
    }
}
