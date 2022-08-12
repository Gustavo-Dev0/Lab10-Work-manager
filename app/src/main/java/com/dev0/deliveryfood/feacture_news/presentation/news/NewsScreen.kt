package com.dev0.deliveryfood.feacture_news.presentation.news

import android.R
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.dev0.deliveryfood.core.utils.AppOpenRecordWorker
import com.dev0.deliveryfood.core.utils.MakeNotificationWorker
import com.dev0.deliveryfood.core.utils.SendCommentWorker
import com.dev0.deliveryfood.feacture_news.presentation.news.components.ItemNew
import com.dev0.deliveryfood.feature_restaurant.presentation.restaurants.components.ItemRestaurant
import com.dev0.deliveryfood.feature_restaurant.presentation.restaurants.components.ItemRestaurantPlaceHolder
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsScreen(
    navController: NavHostController,
    viewModel: NewsViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    val newsItems = viewModel.state.value.news

    var text by rememberSaveable{ mutableStateOf("") }
    var text2 by rememberSaveable{ mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column() {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(start = 15.dp, top = 10.dp, end = 15.dp)
                    .background(Color.White, RoundedCornerShape(5.dp)),
                shape = RoundedCornerShape(5.dp),
                value = text,
                onValueChange = { text = it },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                //maxLines = 3,
                textStyle = MaterialTheme.typography.caption,
                label = { Text("Comentario") }
            )

            Button(onClick = {

                //Una sola vez con parametro

                val oneWorkRequest =
                    OneTimeWorkRequestBuilder<SendCommentWorker>()
                        .setInputData(workDataOf(
                            "comment" to ""+text
                        ))
                        .build()

                WorkManager
                    .getInstance(context)
                    .enqueueUniqueWork(
                        "sendComment",
                        ExistingWorkPolicy.REPLACE,
                        oneWorkRequest
                    )


            }) {
                Text("Enviar comentario")
            }
        }

        Column() {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(start = 15.dp, top = 10.dp, end = 15.dp)
                    .background(Color.White, RoundedCornerShape(5.dp)),
                shape = RoundedCornerShape(5.dp),
                value = text2,
                onValueChange = { text2 = it },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                //maxLines = 3,
                textStyle = MaterialTheme.typography.caption,
                label = { Text("Comentario") }
            )

            Button(onClick = {

                //Una sola vez con parametro y otro secuencial

                val oneWorkRequest =
                    OneTimeWorkRequestBuilder<SendCommentWorker>()
                        .setInputData(workDataOf(
                            "comment" to ""+text2
                        ))
                        .build()

                val oneWorkRequest2 =
                    OneTimeWorkRequestBuilder<MakeNotificationWorker>()
                        .build()

                WorkManager
                    .getInstance(context)
                    .beginWith(oneWorkRequest)
                    .then(oneWorkRequest2)
                    .enqueue()


            }) {
                Text("Enviar comentario y recibir notificacion")
            }
        }


        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        LazyColumn(
            contentPadding = PaddingValues(
                top = 12.dp,
                bottom = 100.dp
            ),

        ) {
            items(newsItems){ new ->
                ItemNew(new)
            }

        }



    }
}
