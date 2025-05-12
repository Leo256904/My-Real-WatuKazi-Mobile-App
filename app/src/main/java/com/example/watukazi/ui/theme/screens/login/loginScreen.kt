package com.example.watukazi.ui.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.watukazi.auth.AuthViewModel
import com.example.watukazi.navigation.ROUTE_REGISTER

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val purpleColor = Color(0xFFBF00FF)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            // Back button
            IconButton(
                onClick = { /* Handle back navigation */ },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.Start)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            // Title
            Text(
                text = "Log in",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Social login buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Google
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1A1A1A)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("G", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                // Apple
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1A1A1A)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("􀣺", color = Color.White, fontSize = 20.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Email
            Text(text = "Email", color = Color.White, fontSize = 14.sp)
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Enter your email") },
                placeholder = { Text("Please enter your email") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Email", tint = purpleColor)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // Password
            Text(text = "Password", color = Color.White, fontSize = 14.sp)
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter your password") },
                placeholder = { Text("Please enter your password") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Password", tint = purpleColor)
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Login button
            Button(
                onClick = {
                    authViewModel.login(email, password, navController, context)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = purpleColor)
            ) {
                Text(text = "Log in", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sign-up redirect
            Text(
                text = "Don't have an account? Sign up",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { navController.navigate(ROUTE_REGISTER) }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController(), viewModel())
}
