package com.example.watukazi.ui.theme.screens.workers

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.watukazi.navigation.ROUTE_UPDATE_WORKER
import com.watukazi.app.models.WorkerModel
import com.watukazi.app.viewmodel.WorkerViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun ViewWorkers(navController: NavHostController) {
    val context = LocalContext.current
    val workerRepository = WorkerViewModel()

    val emptyUploadState = remember { mutableStateOf(WorkerModel()) }
    val emptyUploadListState = remember { mutableStateListOf<WorkerModel>() }

    LaunchedEffect(Unit) {
        workerRepository.viewWorkers(emptyUploadState, emptyUploadListState, context)
    }

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "All Workers",
            fontSize = 30.sp,
            fontFamily = FontFamily.SansSerif,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(emptyUploadListState) { worker ->
                WorkerItem(
                    workername = worker.workername,
                    workerskill = worker.workerskill,
                    workerrate = worker.workerrate,
                    workerphonenumber = worker.workerphonenumber,
                    desc = worker.desc,
                    workerId = worker.workerId,
                    imageUrl = worker.imageUrl,
                    navController = navController,
                    workerRepository = workerRepository
                )
            }
        }
    }
}

@Composable
fun WorkerItem(
    workername: String,
    workerskill: String,
    workerrate: String,
    workerphonenumber: String,
    desc: String,
    workerId: String,
    imageUrl: String,
    navController: NavHostController,
    workerRepository: WorkerViewModel
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(210.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.Gray)
    ) {
        Row {
            Column {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(200.dp)
                        .height(150.dp)
                        .padding(10.dp)
                )

                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(
                        onClick = {
                            workerRepository.deleteWorker(context, workerId, navController)
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text("REMOVE", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }

                    Button(
                        onClick = {
                            navController.navigate("$ROUTE_UPDATE_WORKER/$workerId")
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color.Green),
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text("UPDATE", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }
                }
            }

            Column(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 10.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                InfoItem("WORKER NAME", workername)
                InfoItem("WORKER SKILL", workerskill)
                InfoItem("WORKER RATE", workerrate)
                InfoItem("WORKER PHONE NUMBER", workerphonenumber)
                InfoItem("DESCRIPTION", desc)
            }
        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Text(label, color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    Text(value, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
}

@Preview(showBackground = true)
@Composable
fun ViewWorkersPreview() {
    ViewWorkers(rememberNavController())
}
