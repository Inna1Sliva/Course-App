package com.it.shka.courseapp

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.it.shka.courseapp.dagger.DaggerApplication
import com.it.shka.courseapp.ui.theme.CourseAppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

  val state :StateFlow<List<Course>> = MutableStateFlow<List<Course>>(emptyList())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       // (application as DaggerApplication).appComponent.inject(this)
        setContent {
            CourseAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Greeting(
                                course = getDataCourses(),
                                modifier = Modifier.padding(innerPadding)
                            )

                        }




                }
            }
        }

}

@Composable
fun Greeting(course: StateFlow<List<Course>>, modifier: Modifier) {
    var stateCourse = MutableStateFlow<List<Course>>(emptyList())
    var  nVacancyState: StateFlow<List<Course>> = stateCourse
    LaunchedEffect(Unit) {
        course.collect {
           stateCourse.value = it
        }




    }
    LazyColumn(modifier
        .fillMaxSize()){
nVacancyState.value.forEach {
    items(listOf(it)) {list->

        Text(
            text = it.text,
            fontSize = 14.sp,
            color = colorResource(R.color.black)
        )


    }
}





    }
}

@Composable
fun getDataCourses(): StateFlow<List<Course>> {
    var stateCourse = MutableStateFlow<List<Course>>(emptyList())
    var  nVacancyState: StateFlow<List<Course>> = stateCourse
    val retrofit = Retrofit.Builder()
        .baseUrl("https://683eeb8c1cd60dca33dd922b.mockapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(APIService::class.java)
    val call = api.getData()
    call.enqueue(object : Callback<List<Course>> {
        override fun onResponse(
            call: Call<List<Course>?>,
            response: Response<List<Course>?>
        ) {
            if (response.isSuccessful) {
                val data = response.body()
                val addFavorit = mutableListOf<Course>()
                // Обработка полученных данных

                if (data != null) {
                    stateCourse.value =data
                }
                    Log.d("RETROFIT", "${data}")


                println("Полученные данные: $data")
            } else {
                println("Ошибка ответа: ${response.code()}")
            }
        }

        override fun onFailure(
            call: Call<List<Course>?>,
            t: Throwable
        ) {
            println("Ошибка ответа: ${t.message}")
        }

    })
    return nVacancyState
}