package com.example.effectivemobiletest.main.presentation

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter


class CourseAdapter(
) : AsyncListDifferDelegationAdapter<CourseItem>(
    object : DiffUtil.ItemCallback<CourseItem>() {
        override fun areItemsTheSame(oldItem: CourseItem, newItem: CourseItem): Boolean {
            return when {
                oldItem is CourseItem.CourseRow && newItem is CourseItem.CourseRow ->
                    oldItem.course.id == newItem.course.id
                else -> oldItem == newItem
            }
        }

        override fun areContentsTheSame(oldItem: CourseItem, newItem: CourseItem): Boolean {
            return oldItem == newItem
        }
    }
) {
    init {
        delegatesManager.addDelegate(courseDelegate())
    }
}