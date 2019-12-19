package com.eduminication.ui.courseResourceList


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eduminication.databinding.CourseResourceListItemBinding

private class CourseResourceDiffCallback : DiffUtil.ItemCallback<CourseResource>() {
    override fun areItemsTheSame(oldItem: CourseResource, newItem: CourseResource): Boolean {
    return oldItem.id == newItem.id
}

override fun areContentsTheSame(oldItem: CourseResource, newItem: CourseResource): Boolean {
    return oldItem == newItem
}
}

class CourseResourceViewHolder(
    private val binding: CourseResourceListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.courseResourceCardView.run{
            setOnClickListener {
                navigateToCourseResource(binding.courseResource!!, itemView)
            }
        }
    }

    private fun navigateToCourseResource(
        courseResource: CourseResource,
        it: View
    ) {
        it.findNavController().navigate(
            CourseResourceFragmentDirections.actionNavCourseResourceToCourseResourceDetail(courseResource.url)
        )
    }

    fun bind(item: CourseResource) {
        binding.run {
            courseResource = item
            executePendingBindings()
        }
    }
}

class CourseResourceRecylerViewAdapter :
    ListAdapter<CourseResource, CourseResourceViewHolder>(CourseResourceDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseResourceViewHolder {
        return CourseResourceViewHolder(
            CourseResourceListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CourseResourceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}