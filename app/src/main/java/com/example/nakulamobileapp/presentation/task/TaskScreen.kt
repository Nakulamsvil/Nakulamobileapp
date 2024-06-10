package com.example.nakulamobileapp.presentation.task

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.nakulamobileapp.domain.model.Task
import com.example.nakulamobileapp.presentation.task.component.TaskDatePicker
import com.example.nakulamobileapp.presentation.task.component.TopAppBarTask
import com.example.nakulamobileapp.utils.Converter.changeMillisToDateString
import java.time.Instant

@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    id: Int,
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    taskViewModel: TaskViewModel = hiltViewModel()
) {
    val state by taskViewModel.state.collectAsStateWithLifecycle()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )

    LaunchedEffect(id) {
        taskViewModel.onEvent(TaskEvent.OnGetTaskById(id))
    }

    TaskDatePicker(
        state = datePickerState,
        isOpen = state.isDatePickerDialogOpen,
        onDismissRequest = { taskViewModel.isDatePickerDialogClosed() },
        onConfirmButtonClicked = {
            taskViewModel.onEvent(TaskEvent.OnDateChange(datePickerState.selectedDateMillis))
            taskViewModel.isDatePickerDialogClosed()
        }
    )

    TaskContent(
        isTaskExist = state.currentTaskId != null,
        onBackClick = { navController.navigateUp() },
        onDeleteClick = {
            state.currentTaskId?.let { taskViewModel.deleteTask(it) }
            navController.navigateUp()
        },
        title = state.title,
        onTitleChange = { taskViewModel.onEvent(TaskEvent.OnTitleChange(it)) },
        description = state.description,
        onDescriptionChange = { taskViewModel.onEvent(TaskEvent.OnDescriptionChange(it)) },
        dueDate = state.dueDate,
        isDatePickerDialogOpen = { taskViewModel.isDatePickerDialogOpen() },
        onSaveClick = {
            val task = Task(
                taskId = state.currentTaskId,
                title = state.title,
                description = state.description,
                dueDate = state.dueDate ?: Instant.now().toEpochMilli(),
                linkSource = state.source,
                location = state.location
            )

            if (state.title.isNotEmpty() && state.description.isNotEmpty() && state.dueDate != null && state.source.isNotEmpty() && state.location.isNotEmpty()) {
                taskViewModel.saveTask((task))
                navController.navigateUp()
            } else
                Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
        },
        source = state.source,
        onSourceChange = { taskViewModel.onEvent(TaskEvent.OnSourceChange(it)) },
        location = state.location,
        onLocationChange = { taskViewModel.onEvent(TaskEvent.OnLocationChange(it)) },
        modifier = modifier
    )
}

@Composable
fun TaskContent(
    isTaskExist: Boolean,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    dueDate: Long?,
    isDatePickerDialogOpen: () -> Unit,
    onSaveClick: () -> Unit,
    source: String,
    onSourceChange: (String) -> Unit,
    location: String,
    onLocationChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBarTask(
                isTaskExist = isTaskExist,
                onBackButtonClick = onBackClick,
                onDeleteButtonClick = onDeleteClick
            )
        },
        modifier = modifier
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = onTitleChange,
                label = { Text(text = "Judul Note") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = onDescriptionChange,
                label = { Text(text = "Deskripsi Catatan") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tanggal",
                style = MaterialTheme.typography.bodySmall
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dueDate.changeMillisToDateString(),
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(onClick = isDatePickerDialogOpen) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select Due Date"
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = source,
                onValueChange = onSourceChange,
                label = { Text(text = "Link Source") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = location,
                onValueChange = onLocationChange,
                label = { Text(text = "Lokasi") },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onSaveClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Simpan", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
