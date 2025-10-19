package com.example.sawit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sawit.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class PrediksiFormFragment : Fragment(R.layout.fragment_prediksi_form) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_prediksi_form, container, false)

        val inputLahan: AutoCompleteTextView = view.findViewById(R.id.input_lahan)

        val etLuasField: TextInputEditText = view.findViewById(R.id.etLuasField)
        val etJenisBibit: TextInputEditText = view.findViewById(R.id.etJenisBibit)
        val etJenisPupuk: TextInputEditText = view.findViewById(R.id.etJenisPupuk)
        val etTon: TextInputEditText = view.findViewById(R.id.etTon)
        val etTahun: TextInputEditText = view.findViewById(R.id.etTahun)
        val etUmurSawit: TextInputEditText = view.findViewById(R.id.etUmurSawit)
        val etCurahHujan: TextInputEditText = view.findViewById(R.id.etCurahHujan)

        val btnPredict: MaterialButton = view.findViewById(R.id.btn_predict)

        val lahanList = arrayOf("Lahan 1", "Lahan Manjur Sukses")

        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, lahanList)

        inputLahan.setAdapter(adapter)

        btnPredict.setOnClickListener {
            val lahan = inputLahan.text.toString().trim()

            val luas = etLuasField.text.toString().trim()
            val bibit = etJenisBibit.text.toString().trim()
            val pupuk = etJenisPupuk.text.toString().trim()
            val ton = etTon.text.toString().trim()
            val tahun = etTahun.text.toString().trim()
            val umur = etUmurSawit.text.toString().trim()
            val hujan = etCurahHujan.text.toString().trim()

            if (lahan.isEmpty() || luas.isEmpty() || bibit.isEmpty() || pupuk.isEmpty() ||
                ton.isEmpty() || tahun.isEmpty() || umur.isEmpty() || hujan.isEmpty()
            ) {
                Toast.makeText(requireContext(), "Semua field harus diisi!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val resultFragment = ConditionResultFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fl_scroll_view_content, resultFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}
