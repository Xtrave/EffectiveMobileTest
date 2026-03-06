package com.example.effectivemobiletest.main.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletest.main.R
import com.example.effectivemobiletest.main.data.remote.CoursesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate

class MainViewModel(
    private val apiService: CoursesApi
) : ViewModel() {

    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    private var isSortDescending = false

    init {
        loadCourses()
    }
    fun loadCourses() {
        viewModelScope.launch {
            _state.value = MainState.Loading

            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getAllCourses()

                }

                if (response.isSuccessful) {
                    val courseResponse = response.body()
                    val coursesList = courseResponse?.courses ?: emptyList()

                    val uiList = coursesList.mapIndexed { index, course ->

                        val imageRes = when (index % 3) {
                            0 -> R.drawable.page_1
                            1 -> R.drawable.page_2
                            else -> R.drawable.page_3
                        }
                        CourseItem.CourseRow(course, imageRes)
                    }
                    _state.value = MainState.Content(uiList)
                } else {
                    _state.value = MainState.Error("Ошибка загрузки: ${response.code()}")
                }
            } catch (e: Exception) {
                _state.value = MainState.Error("Ошибка: ${e.message}")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sortCoursesByDate() {
        val currentState = state.value
        if (currentState !is MainState.Content) return

        val sortedList = currentState.course
            .filterIsInstance<CourseItem.CourseRow>()
            .sortedBy { LocalDate.parse(it.course.publishDate) }

        _state.value = MainState.Content(if (isSortDescending) sortedList.reversed() else sortedList)

        isSortDescending = !isSortDescending
    }
}