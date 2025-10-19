package com.example.sawit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sawit.databinding.ItemActivityBinding
import com.example.sawit.models.Activity
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Adapter untuk menampilkan daftar aktivitas di RecyclerView.
 * Menggunakan ListAdapter untuk performa yang efisien saat daftar berubah.
 *
 * @param onCheckboxClicked Lambda function yang dipanggil saat checkbox status di-klik.
 * @param onEditClicked Lambda function yang dipanggil saat tombol edit di-klik.
 * @param onDeleteClicked Lambda function yang dipanggil saat tombol hapus di-klik.
 */
class ActivityAdapter(
    private val onCheckboxClicked: (Activity, Boolean) -> Unit,
    private val onEditClicked: (Activity) -> Unit,
    private val onDeleteClicked: (Activity) -> Unit
) : ListAdapter<Activity, ActivityAdapter.ActivityViewHolder>(ActivityDiffCallback()) {

    /**
     * Membuat ViewHolder baru saat RecyclerView membutuhkannya.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val binding = ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActivityViewHolder(binding)
    }

    /**
     * Menghubungkan data dari sebuah 'Activity' ke ViewHolder.
     */
    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = getItem(position)
        holder.bind(activity)
    }

    /**
     * ViewHolder yang menyimpan referensi ke view dari setiap item dalam daftar.
     */
    inner class ActivityViewHolder(private val binding: ItemActivityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(activity: Activity) {
            // Mengisi data ke komponen UI
            binding.textViewFieldName.text = activity.fieldName
            binding.textViewActivityType.text = activity.activityType
            binding.textViewNotes.text = activity.notes

            // Format tanggal agar mudah dibaca
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            binding.textViewDate.text = sdf.format(activity.date)

            // Mengatur status checkbox
            // Listener di-set null dulu agar tidak terpicu saat bind data
            binding.checkboxStatus.setOnCheckedChangeListener(null)
            binding.checkboxStatus.isChecked = activity.status == "completed"
            // Set listener kembali setelah status diatur
            binding.checkboxStatus.setOnCheckedChangeListener { _, isChecked ->
                onCheckboxClicked(activity, isChecked)
            }

            // Menampilkan atau menyembunyikan tombol edit berdasarkan status
            if (activity.status == "planned") {
                binding.buttonEdit.visibility = View.VISIBLE
            } else {
                binding.buttonEdit.visibility = View.GONE
            }

            // Menetapkan listener untuk tombol edit dan hapus
            binding.buttonEdit.setOnClickListener {
                onEditClicked(activity)
            }
            binding.buttonDelete.setOnClickListener {
                onDeleteClicked(activity)
            }
        }
    }

    /**
     * Callback untuk menghitung perbedaan antara dua daftar untuk ListAdapter.
     * Ini membuat pembaruan RecyclerView menjadi sangat efisien.
     */
    class ActivityDiffCallback : DiffUtil.ItemCallback<Activity>() {
        override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem == newItem
        }
    }
}

