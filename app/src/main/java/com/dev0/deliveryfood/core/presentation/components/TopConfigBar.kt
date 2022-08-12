package com.dev0.deliveryfood.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.dev0.deliveryfood.MainActivity
import com.dev0.deliveryfood.core.data.repository.Theme

data class ActionItem(
    val name: String,
    val icon: ImageVector? = null,
    val action: () -> Unit,
    val order: Int
)

@Composable
fun TopConfigBar(
    topBarState: MutableState<Boolean>,
    appThemeState: MutableState<String>,
    updateDataStore: () -> Unit
) {
    AnimatedVisibility(
        visible = topBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            TopAppBar(
                title = { Text(text = "DeliveryApp") },
                actions = {
                    val isExpanded = remember { mutableStateOf(false) }
                    val mContext = LocalContext.current

                    IconButton(
                        onClick = {
                            isExpanded.value = true
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Ver más")

                        DropdownMenu(
                            modifier = Modifier.padding(horizontal = 5.dp)
                                .padding(horizontal = 10.dp),
                            expanded = isExpanded.value,
                            onDismissRequest = { isExpanded.value = false },
                            offset = DpOffset(x = 0.dp, y = 4.dp)
                        ) {

                            Text(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                text = "Elige un tema",
                                fontWeight = FontWeight.ExtraBold
                            )

                            DropdownMenuItem(
                                onClick = {
                                    appThemeState.value = Theme.DEFAULT
                                    updateDataStore()
                                    isExpanded.value = false
                                }
                            ) {
                                Text(text = "Default")
                            }

                            DropdownMenuItem(
                                onClick = {
                                    appThemeState.value = Theme.PINK
                                    updateDataStore()
                                    isExpanded.value = false
                                }
                            ) {
                                Text(text = "Pink")
                            }

                            DropdownMenuItem(
                                onClick = {
                                    appThemeState.value = Theme.BLACK
                                    updateDataStore()
                                    isExpanded.value = false
                                }
                            ) {
                                Text(text = "Black")
                            }
                        }
                    }
                }
            )
        }
    )
}