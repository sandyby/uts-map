package com.example.sawit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sawit.R
import com.example.sawit.utils.PredictionTabView

class PredictFragment : Fragment(R.layout.fragment_predictions) {

    private lateinit var layoutHarvest: View
    private lateinit var layoutCondition: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_predictions, container, false)

        layoutHarvest = view.findViewById(R.id.layout_harvest)
        layoutCondition = view.findViewById(R.id.layout_condition)
        val ptvPredictions = view.findViewById<PredictionTabView>(R.id.ptvPredictions)

        showHarvest()

        ptvPredictions.onTabSelectedListener = { index ->
            when (index) {
                0 -> showHarvest()
                1 -> showCondition()
            }
        }

        return view
    }

    private fun showHarvest() {
        layoutHarvest.visibility = View.VISIBLE
        layoutCondition.visibility = View.GONE
    }

    private fun showCondition() {
        layoutHarvest.visibility = View.GONE
        layoutCondition.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
