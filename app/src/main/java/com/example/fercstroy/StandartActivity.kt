package com.example.fercstroy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.fercstroy.adapters.Feature
import com.example.fercstroy.adapters.FeatureAdapter

class StandartActivity : AppCompatActivity() {

    var recycler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standart)

        recycler = findViewById(R.id.standart_recycler)

        val features = listOf(
            Feature("Вентиляторы с EC и AC моторами", "Вентиляторы одиночной и групповой установки с AC- и EC- двигателями Siemens, ABB, Omec", R.drawable.standart_feature_1),
            Feature("Рекуперация тепла", "На основе роторных, пластинчатых утилизаторов и с промежуточным теплоносителем Klingenburg, Heatex, Panova", R.drawable.standart_feature_2),
            Feature("Материалы корпуса", "Стандартное покрытие Magnelis класс коррозионной стойкости C4, эпоксидное покрытие и нержавеющая сталь", R.drawable.standart_feature_3),
            Feature("Характеристики корпуса", "T2, TB2, D1, L1", R.drawable.standart_feature_4),
            Feature("Улавливание конденсата", "Поддон, утопленный в пол агрегата и каплеуловитель, изготовленные из нержавеющей стали", R.drawable.standart_feature_5)
        )

        recycler?.adapter = FeatureAdapter(features)

    }

}