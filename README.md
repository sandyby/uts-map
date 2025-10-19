# 🌴 SawIt — Smart Palm Plantation Management App

**SawIt** adalah aplikasi mobile cerdas yang dirancang untuk membantu petani dan pengelola perkebunan kelapa sawit dalam mengelola, memantau, dan memprediksi hasil kebun mereka secara efisien.  
Dengan memanfaatkan **teknologi Android Native** dan **Machine Learning (ML)**, SawIt menghadirkan solusi digital terintegrasi untuk mengatasi tantangan operasional di lapangan.

---

## 🌾 Latar Belakang
Indonesia merupakan **produsen minyak kelapa sawit terbesar di dunia**, menjadikan sektor ini sangat penting bagi perekonomian nasional.  
Namun, banyak petani masih bergantung pada pencatatan manual dan observasi tradisional yang rawan kesalahan. Tantangan seperti:
- Pemantauan kondisi kebun yang belum terotomatisasi,
- Pencatatan aktivitas yang tidak terstruktur, dan
- Kesulitan memprediksi hasil panen,

sering kali menurunkan efisiensi dan produktivitas.

Melalui kemajuan **mobile technology** dan **machine learning**, SawIt hadir sebagai solusi digital yang **cerdas, praktis, dan berbasis data** — membantu petani mengambil keputusan lebih tepat dan meningkatkan hasil panen mereka.

---

## 🎯 Tujuan Proyek

1. **Membangun Aplikasi Android Native:**  
   Merancang dan mengimplementasikan antarmuka pengguna (UI) menggunakan **Kotlin** dan **Android Studio**.

2. **Menyediakan Fitur Manajemen Perkebunan:**
    - Pengelolaan data kebun (*Fields*)
    - Pencatatan aktivitas harian (pemupukan, panen, dll.)
    - Pemantauan cuaca secara real-time

3. **Integrasi Machine Learning:**  
   Menghubungkan model ML dari mata kuliah *IF441* untuk memberikan:
    - Prediksi hasil panen
    - Analisis kondisi pohon sawit

4. **Pemanfaatan Fitur Native Android & Cloud Database:**
    - **Geolokasi:** untuk pemetaan kebun
    - **Local Storage:** untuk penyimpanan sementara
    - **Firebase Firestore:** untuk sinkronisasi data real-time

---

## 💡 Manfaat Aplikasi

- **⚙️ Efisiensi Operasional:**  
  Petani dapat merencanakan dan memantau aktivitas harian melalui kalender digital interaktif.

- **📊 Pengambilan Keputusan Berbasis Data:**  
  Informasi cuaca, statistik kebun, dan hasil prediksi ML ditampilkan secara visual dalam bentuk grafik dan ringkasan.

- **🌱 Optimalisasi Hasil Panen:**  
  Estimasi hasil panen dan analisis kondisi kebun membantu petani mengambil langkah preventif dan meningkatkan produktivitas.

- **📱 Modernisasi Manajemen:**  
  Mengubah pengelolaan kebun dari cara tradisional menjadi digital, terstruktur, dan mudah diakses.

---

## 🧠 Tema Aplikasi
> **“Asisten Cerdas untuk Manajemen Perkebunan Kelapa Sawit Berbasis Mobile dan Machine Learning.”**

SawIt bukan sekadar aplikasi, tetapi asisten digital yang membantu petani sawit modern mengelola kebun secara efisien, informatif, dan prediktif.

---

## 🏗️ Arsitektur Sistem

SawIt terdiri dari **tiga komponen utama**:

1. **📱 Aplikasi Mobile (Client-side):**  
   Dibangun menggunakan **Kotlin**, menjadi antarmuka utama bagi pengguna untuk menginput data dan menerima hasil prediksi.

2. **☁️ Backend & Database (Server-side):**  
   Menggunakan **Firebase Firestore (NoSQL)** untuk menyimpan data pengguna, kebun, dan aktivitas.  
   Menyediakan sinkronisasi data secara **real-time** antar perangkat.

3. **🤖 Model Machine Learning:**  
   Dikembangkan secara terpisah menggunakan **Python & Scikit-learn**, kemudian di-*deploy* ke **Firebase ML** atau API custom untuk melakukan prediksi hasil panen.

---

## 🛠️ Teknologi yang Digunakan

| Komponen | Teknologi |
|-----------|------------|
| **Bahasa Pemrograman** | Kotlin |
| **IDE** | Android Studio |
| **Desain UI/UX** | Figma |
| **Database** | Google Firebase Firestore |
| **Geolokasi & Peta** | Google Maps SDK / Mapbox SDK |
| **API Cuaca** | OpenWeather API |

---

## 🚀 Kesimpulan
SawIt menghadirkan pendekatan modern dalam manajemen perkebunan kelapa sawit melalui integrasi antara **Android Native App** dan **Machine Learning**.  
Dengan antarmuka yang ramah pengguna, fitur komprehensif, dan dukungan data real-time, SawIt menjadi langkah nyata menuju **transformasi digital sektor agrikultur Indonesia**.
