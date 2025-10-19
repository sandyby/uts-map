package com.example.sawit.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.material3.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.sawit.R
import com.example.sawit.models.Field
import com.example.sawit.utils.FieldsTabView
import com.example.sawit.viewmodels.FieldViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

class FieldsDetailFragment : Fragment(R.layout.fragment_fields_detail) {
    private val fieldViewModel: FieldViewModel by viewModels()
    private var currentField: Field? = null
    private var fieldId: Int = -1
    private lateinit var layoutDetails: View
    private lateinit var layoutStatistics: View

    companion object {
        private const val ARG_FIELD_ID = "fieldId"

        fun newInstance(fieldId: Int) = FieldsDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_FIELD_ID, fieldId)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_fields_detail, container, false)

        layoutDetails = view.findViewById(R.id.layout_details)
        layoutStatistics = view.findViewById(R.id.layout_statistics)
        val ftvFields = view.findViewById<FieldsTabView>(R.id.ftv_fields)
        val ivBack = view.findViewById<ImageView>(R.id.iv_back)
        val tvFieldName = view.findViewById<TextView>(R.id.tv_field_name)

        showDetails()

        ftvFields.onTabSelectedListener = { index ->
            when (index) {
                0 -> showDetails()
                1 -> showStatistics()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            fieldViewModel.fieldsData.collectLatest { fields ->
                currentField = fields.find { it.fieldId == fieldId }
                currentField?.let { field ->
                    tvFieldName.text = field.fieldName
                }
            }
        }

        ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }

    private fun showDetails() {
        layoutDetails.visibility = View.VISIBLE
        layoutStatistics.visibility = View.GONE
    }

    private fun showStatistics() {
        layoutDetails.visibility = View.GONE
        layoutStatistics.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAddData = view.findViewById<MaterialButton>(R.id.mb_add_data_btn)
        val btnEditData = view.findViewById<MaterialButton>(R.id.mb_edit_data_btn)

        btnAddData.setOnClickListener {
            val addFieldDataFragment = AddFieldDataFragment()
            Log.d("FieldsDetailFragment", "tes")
            parentFragmentManager.beginTransaction()
                .replace(R.id.fl_scroll_view_content, addFieldDataFragment)
                .addToBackStack(null)
                .commit()
        }

        btnEditData.setOnClickListener {
            val editFieldDataFragment = EditFieldDataFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_fragments_fields_detail, EditFieldDataFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}


//    private val fieldViewModel: FieldViewModel by viewModels()
//    private var currentField: Field? = null
//    private var fieldId: Int = -1

//    companion object {
//        private const val ARG_FIELD_ID = "fieldId"
//
//        fun newInstance(fieldId: Int) = FieldsDetailFragment().apply {
//            arguments = Bundle().apply {
//                putInt(ARG_FIELD_ID, fieldId)
//            }
//        }
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val ftvFields = view.findViewById<FieldsTabView>(R.id.ftv_fields)
//        val flContent = view.findViewById<FrameLayout>(R.id.fl_fields_tabs_content)
//        val ivBack = view.findViewById<ImageView>(R.id.iv_back)
//        val tvFieldName = view.findViewById<TextView>(R.id.tv_field_name)
//
//        fieldId = arguments?.getInt(ARG_FIELD_ID) ?: -1
//
//        // Collect data ONCE — not inside tab switch
//        viewLifecycleOwner.lifecycleScope.launch {
//            fieldViewModel.fieldsData.collectLatest { fields ->
//                currentField = fields.find { it.fieldId == fieldId }
//                currentField?.let { field ->
//                    tvFieldName.text = field.fieldName
//                    // If the details tab is active, update its content immediately
//                    val currentTabIndex = ftvFields.getSelectedIndexOrDefault(0)
//                    if (currentTabIndex == 0) {
//                        showDetailsTab(flContent)
//                    }
//                }
//            }
//        }
//
//        // Default tab
//        showDetailsTab(flContent)
//
//        // Tab switching logic
//        ftvFields.onTabSelectedListener = { index ->
//            when (index) {
//                0 -> showDetailsTab(flContent)
//                1 -> showStatisticsTab(flContent)
//            }
//        }
//
//        ivBack.setOnClickListener {
//            parentFragmentManager.popBackStack()
//        }
//    }
//
//    private fun showDetailsTab(container: FrameLayout) {
//        container.removeAllViews()
//        val detailsView = layoutInflater.inflate(R.layout.fields_tabs_details, container, false)
//
//        val tvLabel = detailsView.findViewById<TextView>(R.id.tv_label_title)
//        val tvFieldName = detailsView.findViewById<TextView>(R.id.tv_field_name_value)
//        val tvLocation = detailsView.findViewById<TextView>(R.id.tv_field_location_value)
//        val tvSize = detailsView.findViewById<TextView>(R.id.tv_field_size_value)
//        val tvSoilType = detailsView.findViewById<TextView>(R.id.tv_field_soil_value)
//        val tvLastHarvest = detailsView.findViewById<TextView>(R.id.tv_field_last_harvest_value)
//
//        currentField?.let { field ->
//            tvLabel.text = "Field ${field.fieldName} Details"
//            tvFieldName.text = field.fieldName
//            tvLocation.text = field.fieldLocation
//            tvSize.text = field.fieldArea.toString()
//            tvSoilType.text = field.oilPalmType
//            tvLastHarvest.text = "October 2024"
//        }
//
//        container.addView(detailsView)
//    }


//        val ftvFields = view.findViewById<FieldsTabView>(R.id.ftv_fields)
//        val flContent = view.findViewById<FrameLayout>(R.id.fl_fields_tabs_content)
//
//        showDetailsTab(flContent)
//
//        ftvFields.onTabSelectedListener = { index ->
//            when (index) {
//                0 -> showDetailsTab(flContent)
//                1 -> showStatisticsTab(flContent)
//            }
//        }
//
//        val ivBack = view.findViewById<ImageView>(R.id.iv_back)
//        val tvFieldName = view.findViewById<TextView>(R.id.tv_field_name)
//        fieldId = arguments?.getInt(ARG_FIELD_ID) ?: -1
//
//        viewLifecycleOwner.lifecycleScope.launch {
//            fieldViewModel.fieldsData.collectLatest { fields ->
//                currentField = fields.find { it.fieldId == fieldId }
//                currentField?.let { field ->
//                    tvFieldName.text = field.fieldName
//                }
//            }
//        }
//
//        ivBack.setOnClickListener {
//            parentFragmentManager.popBackStack()
//        }
//
//    }
//
//    private fun showDetailsTab(container: FrameLayout) {
//        container.removeAllViews()
//        val detailsView = layoutInflater.inflate(R.layout.fields_tabs_details, container, false)
//
//        val tvLabel = detailsView.findViewById<TextView>(R.id.tv_label_title)
//        val tvFieldName = detailsView.findViewById<TextView>(R.id.tv_field_name_value)
//        val tvLocation = detailsView.findViewById<TextView>(R.id.tv_field_location_value)
//        val tvSize = detailsView.findViewById<TextView>(R.id.tv_field_size_value)
//        val tvSoilType = detailsView.findViewById<TextView>(R.id.tv_field_soil_value)
//        val tvLastHarvest = detailsView.findViewById<TextView>(R.id.tv_field_last_harvest_value)
//
//        viewLifecycleOwner.lifecycleScope.launch {
//            fieldViewModel.fieldsData.collectLatest { fields ->
//                currentField = fields.find { it.fieldId == fieldId }
//                currentField?.let { field ->
//                    tvLabel.text = "Field $field.fieldName Details"
//                    tvFieldName.text = field.fieldName
//                    tvLocation.text = field.fieldLocation
//                    tvSize.text = field.fieldArea.toString()
//                    tvSoilType.text = field.oilPalmType
//                    tvLastHarvest.text = "October 2024"
//                }
//            }
//        }
//
//        container.addView(detailsView)
//    }

//    private fun showStatisticsTab(container: FrameLayout) {
//        container.removeAllViews()
//        val statsView = layoutInflater.inflate(R.layout.fields_tabs_statistics, container, false)
//
//        container.addView(statsView)
//    }
//}