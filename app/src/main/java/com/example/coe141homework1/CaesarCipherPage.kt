package com.example.coe141homework1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coe141homework1.ui.theme.COE141Homework1Theme


@Composable
fun CaesarCipherPage(
    viewModel: CaesarCipherViewModel
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()
    CaesarCipherPageContent(
        state = state,
        onEditInput = {
            viewModel.onInputChanged(it)
        }, onEditOffset = {
            viewModel.onOffsetChanged(it)
        }, onToggleEncrypt = {
            viewModel.onEncryptChanged(it)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CaesarCipherPageContent(
    state: CaesarCipherViewModel.ViewState,
    onEditInput: (String) -> Unit,
    onEditOffset: (Int) -> Unit,
    onToggleEncrypt: (Boolean) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Caesar Cipher")
                }
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    singleLine = true,
                    modifier = Modifier.weight(3f),
                    value = state.input,
                    onValueChange = onEditInput,
                    label = {
                        Text(text = "Input")
                    },
                    trailingIcon = {
                        IconButton(onClick = { onEditInput("") }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                TextField(
                    singleLine = true,
                    modifier = Modifier.weight(1f),
                    value = state.offset.toString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = {
                        Text(text = "Offset")
                    },
                    onValueChange = {
                        onEditOffset(it.toIntOrNull() ?: 0)
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = state.isEncrypt,
                    onCheckedChange = onToggleEncrypt
                )
                Text("Encrypt")
            }

            Text(text = "Result:")
            Text(text = state.output, textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CaesarCipherPagePreview() {
    COE141Homework1Theme {
        CaesarCipherPageContent(
            state = CaesarCipherViewModel.ViewState(
                input = "Hello World",
                offset = 3,
                isEncrypt = true
            ),
            onEditInput = {},
            onEditOffset = {},
            onToggleEncrypt = {}
        )
    }
}
