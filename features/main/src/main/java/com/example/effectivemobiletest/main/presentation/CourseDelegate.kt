package com.example.effectivemobiletest.main.presentation

import com.example.effectivemobiletest.main.databinding.ItemCourseBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun courseDelegate() = adapterDelegateViewBinding<CourseItem.CourseRow, CourseItem, ItemCourseBinding>(

    viewBinding = { inflater, parent ->
        ItemCourseBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        val course = item.course

        with(binding) {
            tvRating.text = course.rating
            tvStartDate.text = formatDate(course.startDate)
            tvTitle.text = course.title
            tvDescription.text = course.description
            tvPrice.text = "${course.price} ₽"
            btnFavorite.isSelected = course.hasLike
            binding.ivCourseImage.setImageResource(item.imageRes)
        }
    }
}
private fun formatDate(dateString: String): String {
    val parts = dateString.split("-")
    return if (parts.size == 3) {
        val months = listOf(
            "Января", "Февраля", "Марта", "Апреля", "Мая", "Июня",
            "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря"
        )
        val monthIndex = parts[1].toIntOrNull()?.minus(1) ?: 0
        "${parts[2]} ${months.getOrNull(monthIndex) ?: parts[1]} ${parts[0]}"
    } else {
        dateString
    }
}