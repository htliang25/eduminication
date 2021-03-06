package com.eduminication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.eduminication.MainActivity
import com.eduminication.R
import com.eduminication.databinding.FragmentCourseResourceBinding
import com.eduminication.utils.ViewPageFragmentInfo
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_course_resource.*
import kotlinx.android.synthetic.main.fragment_question_answer_list.tab_layout
import kotlinx.android.synthetic.main.fragment_question_answer_list.view_pager

class CourseResourceFragment : Fragment() {
    private lateinit var pages: Array<ViewPageFragmentInfo>
    private val sharedViewModel by lazy {
        (activity as MainActivity).sharedViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pages = arrayOf(
            ViewPageFragmentInfo(getString(R.string.data_structure), CourseResourceListFragment::class),
            ViewPageFragmentInfo(getString(R.string.principles_of_compiler), CourseResourceListFragment::class),
            ViewPageFragmentInfo(getString(R.string.linear_algebra), CourseResourceListFragment::class)
        )

        view_pager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                val frgment =
                    pages[position].fragmentClass.java.newInstance() as CourseResourceListFragment
                frgment.courseType = position
                return frgment
            }

            override fun getItemCount() = pages.count()
        }

        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = pages[position].title
        }.attach()

        fab_upload.setOnClickListener {
            sharedViewModel.courseResorcePos = tab_layout.selectedTabPosition
            findNavController().navigate(
                CourseResourceFragmentDirections.actionNavCourseResourceToUploadCourseFragment()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCourseResourceBinding.inflate(inflater, container, false).root
}
