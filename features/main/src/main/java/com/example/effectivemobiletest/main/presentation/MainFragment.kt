package com.example.effectivemobiletest.main.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobiletest.main.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModel()
    private val adapter = CourseAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSortByDate = view.findViewById<Button>(R.id.btnSortByDate)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvCourses)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        btnSortByDate.setOnClickListener {
            viewModel.sortCoursesByDate()
        }

        observeState()
    }

    private fun observeState() {

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when(state){
                is MainState.Content -> adapter.items = state.course
                is MainState.Error -> Unit
                MainState.Loading -> Unit
            }

        }
    }
}