package com.example.fercstroy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fercstroy.R
import com.example.fercstroy.adapters.TimelineAdapter
import com.example.fercstroy.adapters.TimelineItem

class FragmentAbout : Fragment() {

    var recycler: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        recycler = view.findViewById(R.id.about_recycler)
//        recycler?.layoutManager = LinearLayoutManager(requireContext())
//
//        val timelineItems = listOf(
//            TimelineItem("2014", "Дистрибуция", getString(R.string.timeline_2014)),
//            TimelineItem("2016", "Сервис", getString(R.string.timeline_2016)),
//            TimelineItem("2018", "Высокотехнологичные отрасли", getString(R.string.timeline_2018)),
//            TimelineItem("2020", "Крупноузловая сборка", getString(R.string.timeline_2020)),
//            TimelineItem("2022", "Новые филиалы и развитие бренда", getString(R.string.timeline_2022)),
//            TimelineItem("2023", "Увеличение производства и создание Группы Компаний", getString(R.string.timeline_2023))
//        )
//
//        recycler?.adapter = TimelineAdapter(timelineItems)
//
//        recycler?.post {
//            val adapter = recycler?.adapter
//            var totalHeight = 0
//
//            for (i in 0 until adapter!!.itemCount) {
//                val viewHolder = adapter.onCreateViewHolder(recycler!!, adapter.getItemViewType(i))
//                adapter.onBindViewHolder(viewHolder, i)
//                viewHolder.itemView.measure(
//                    View.MeasureSpec.makeMeasureSpec(recycler!!.width, View.MeasureSpec.EXACTLY),
//                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
//                )
//                totalHeight += viewHolder.itemView.measuredHeight
//            }
//
//            val params = recycler?.layoutParams
//            params?.height = totalHeight + (recycler!!.itemDecorationCount * (recycler!!.context.resources.displayMetrics.density * 8).toInt()) // Assuming 8dp padding
//            recycler?.layoutParams = params
//        }


    }

}