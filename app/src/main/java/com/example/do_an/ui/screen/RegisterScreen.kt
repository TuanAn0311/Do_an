package com.example.do_an.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.*
import androidx.compose.foundation.rememberScrollState

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var shippingAddress by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var usernameError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var addressError by remember { mutableStateOf(false) }

    fun validate(): Boolean {
        emailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        passwordError = password.length < 8 || !password.any { it.isDigit() } || !password.any { it.isLowerCase() }
        usernameError = !username.matches(Regex("^[a-zA-Z0-9]+(-[a-zA-Z0-9]+)*$"))
        phoneError = phoneNumber.length < 9 || !phoneNumber.all { it.isDigit() }
        addressError = shippingAddress.isBlank()

        return !(emailError || passwordError || usernameError || phoneError || addressError)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Sign up", fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it; emailError = false },
            label = { Text("Email") },
            isError = emailError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            supportingText = {
                if (emailError) Text("Please enter a valid email.", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it; passwordError = false },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            supportingText = {
                if (passwordError)
                    Text("Password must be 8+ characters, include lowercase & number.", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                else
                    Text("Password should be at least 15 characters OR at least 8 characters including a number and a lowercase letter.", fontSize = 12.sp)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it; usernameError = false },
            label = { Text("Username") },
            isError = usernameError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            supportingText = {
                if (usernameError)
                    Text("Invalid username. Cannot start/end with hyphen.", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
                else
                    Text("Username may only contain alphanumeric characters or single hyphens.", fontSize = 12.sp)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it; phoneError = false },
            label = { Text("Phone Number") },
            isError = phoneError,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            supportingText = {
                if (phoneError)
                    Text("Enter a valid phone number.", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = shippingAddress,
            onValueChange = { shippingAddress = it; addressError = false },
            label = { Text("Shipping Address") },
            isError = addressError,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            supportingText = {
                if (addressError)
                    Text("Shipping address cannot be empty.", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (validate()) {
                    onRegisterClick()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2DA44E))
        ) {
            Text("Continue")
        }
    }
}
