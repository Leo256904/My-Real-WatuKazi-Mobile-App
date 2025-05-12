package com.example.watukazi.ui.theme.screens.register

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.watukazi.auth.AuthViewModel

@Composable
fun SignUpScreen(navController: NavController, authViewModel: AuthViewModel) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val purpleColor = Color(0xFF800080)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
            color = purpleColor
        )

        // Name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "Enter your name") },
            placeholder = { Text(text = "Please enter your name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Name", tint = purpleColor)
            }
        )

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Enter your email") },
            placeholder = { Text(text = "Please enter your email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email", tint = purpleColor)
            }
        )

        // Contact
        OutlinedTextField(
            value = contact,
            onValueChange = { contact = it },
            label = { Text(text = "Enter your phone number") },
            placeholder = { Text(text = "Please enter your phone number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Call, contentDescription = "Phone", tint = purpleColor)
            }
        )

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Enter your password") },
            placeholder = { Text(text = "Please enter your password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password", tint = purpleColor)
            }
        )

        // Confirm Password (no icon)
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = "Confirm your password") },
            placeholder = { Text(text = "Please confirm your password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )

        // Submit Button
        Button(
            onClick = { /* Handle signup */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = purpleColor)
        ) {
            Text(text = "Sign Up")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { navController.navigate("login") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Already have an account? Log In", color = purpleColor)
        }
    }
}
