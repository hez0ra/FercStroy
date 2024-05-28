package com.example.fercstroy

import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fercstroy.adapters.Feature
import com.example.fercstroy.adapters.FeatureAdapter
import com.example.fercstroy.adapters.MyExpandableListAdapter

class StandartActivity : AppCompatActivity() {

    var recycler: RecyclerView? = null
    private lateinit var expandableListView: ExpandableListView
    private lateinit var adapter: MyExpandableListAdapter
    private lateinit var groupList: List<String>
    private lateinit var itemList: Map<String, List<Int>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standart)

        recycler = findViewById(R.id.standart_recycler)
        expandableListView = findViewById(R.id.standart_expandable_list_view)

        prepareListData()

        adapter = MyExpandableListAdapter(this, groupList, itemList)
        expandableListView.setAdapter(adapter)

        // Установить начальную высоту
        setListViewHeightBasedOnChildren(expandableListView)

        // Добавить слушателей для пересчета высоты
        expandableListView.setOnGroupExpandListener {
            setListViewHeightBasedOnChildren(expandableListView)
        }

        expandableListView.setOnGroupCollapseListener {
            setListViewHeightBasedOnChildren(expandableListView)
        }

        val features = listOf(
            Feature("Вентиляторы с EC и AC моторами", "Вентиляторы одиночной и групповой установки с AC- и EC- двигателями Siemens, ABB, Omec", R.drawable.standart_feature_1),
            Feature("Рекуперация тепла", "На основе роторных, пластинчатых утилизаторов и с промежуточным теплоносителем Klingenburg, Heatex, Panova", R.drawable.standart_feature_2),
            Feature("Материалы корпуса", "Стандартное покрытие Magnelis класс коррозионной стойкости C4, эпоксидное покрытие и нержавеющая сталь", R.drawable.standart_feature_3),
            Feature("Характеристики корпуса", "T2, TB2, D1, L1", R.drawable.standart_feature_4),
            Feature("Улавливание конденсата", "Поддон, утопленный в пол агрегата и каплеуловитель, изготовленные из нержавеющей стали", R.drawable.standart_feature_5)
        )

        recycler?.adapter = FeatureAdapter(features)

    }


    private fun setListViewHeightBasedOnChildren(listView: ExpandableListView) {
        val listAdapter = listView.expandableListAdapter ?: return
        var totalHeight = 0
        val desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.EXACTLY)

        for (i in 0 until listAdapter.groupCount) {
            val groupItem = listAdapter.getGroupView(i, false, null, listView)
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
            totalHeight += groupItem.measuredHeight

            if (listView.isGroupExpanded(i)) {
                for (j in 0 until listAdapter.getChildrenCount(i)) {
                    val listItem = listAdapter.getChildView(i, j, false, null, listView)
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
                    totalHeight += listItem.measuredHeight
                }
            }
        }

        val params = listView.layoutParams
        params.height = totalHeight + (listView.dividerHeight * (listAdapter.groupCount - 1))
        listView.layoutParams = params
        listView.requestLayout()
    }

    private fun prepareListData() {
        groupList = listOf(
            "Приточно-вытяжная установка с роторным регенератором",
            "Приточно-вытяжная установка с пластинчатым противоточным утилизатором",
            "Приточно-вытяжная установка с модулем теплового насоса и роторным регенератором",
            "Приточно-вытяжная установка с пластинчатым противоточным утилизатором и модулем газового нагрева"
        )

        val item1 = listOf(R.drawable.standart_dop_1)
        val item2 = listOf(R.drawable.standart_dop_2)
        val item3 = listOf(R.drawable.standart_dop_3)
        val item4 = listOf(R.drawable.standart_dop_4)

        itemList = mapOf(
            groupList[0] to item1,
            groupList[1] to item2,
            groupList[2] to item3,
            groupList[3] to item4
        )
    }

}