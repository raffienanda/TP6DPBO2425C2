# TP6DPBO2425C2

# 🐦 Flappy Bird Java Swing

Proyek ini merupakan implementasi sederhana dari permainan **Flappy Bird** menggunakan **Java Swing**.  
Game berjalan di dalam sebuah `JFrame`, dengan mekanisme utama berupa kontrol gravitasi, tabrakan dengan pipa, dan sistem skor.

---

## 🎨 Desain Program

### 1. Struktur Kelas
- **`StartMenu`**
  kelas yang membuat halaman menu utama sebelum pemain masuk ke game. Berisikan background gambar, judul game, tombol start, dan exit

- **`Logic`**  
  Mengatur seluruh logika game: gravitasi, penambahan pipa, deteksi tabrakan, perhitungan skor, dan kondisi Game Over.

- **`View`**  
  Menggambar seluruh elemen visual seperti background, player, dan pipa.  
  Menampilkan skor menggunakan `JLabel` dan teks “Game Over”.

- **`Player`**  
  Menyimpan posisi (x, y), ukuran, kecepatan vertikal (`velocityY`), serta gambar burung.

- **`Pipe`**  
  Mewakili objek pipa atas dan bawah dengan posisi, ukuran, dan gambar.

- **`App`**  
  Menyiapkan `JFrame` dan memuat panel `View` sebagai area permainan.

---

## 🧭 Alur Program

1. **Inisialisasi Game**
   - `App` membuat jendela utama (`JFrame`) dan menambahkan panel `View`.
   - `Logic` diinisialisasi dan dihubungkan dengan `View`.
   - Timer (`gameLoop`) berjalan 60 kali per detik untuk memperbarui pergerakan dan animasi.

2. **Pergerakan Player**
   - Tekan **SPACE** untuk membuat burung terbang naik.
   - Gravitasi (`gravity = 1`) terus menarik burung ke bawah setiap frame.

3. **Pembuatan dan Pergerakan Pipa**
   - Setiap 1.5 detik, `Logic` menambahkan sepasang pipa baru (atas & bawah) dengan celah acak.
   - Semua pipa bergerak ke kiri dengan kecepatan konstan (`pipeVelocityX = -2`).

4. **Deteksi Tabrakan**
   - Jika `Rectangle` player bersinggungan dengan `Rectangle` pipa → `gameOver()`.
   - Jika player jatuh melebihi **batas bawah JFrame (area View)** → `gameOver()`.

5. **Perhitungan Skor**
   - Skor bertambah setiap kali player berhasil melewati satu pasang pipa (dihitung satu kali per pasangan).

6. **Game Over**
   - Semua timer dihentikan.
   - Teks “GAME OVER” muncul di layar dengan opsi:
     - **R** → Restart permainan.
     - **M** → Kembali ke menu utama.

---

## 🧩 Kontrol Pemain

| Tombol | Fungsi |
|--------|---------|
| **SPACE** | Terbang ke atas |
| **R** | Restart permainan |
| **M** | Kembali ke menu utama (Start Menu) |

---

## 🖼️ Cuplikan Tampilan
<img width="210" height="452" alt="image" src="https://github.com/user-attachments/assets/021ef760-1be3-469f-8472-8aa173440e3f" />
<img width="210" height="452" alt="image" src="https://github.com/user-attachments/assets/ca64d7d2-c1fb-4a2b-b220-df84fbfb68d4" />
<img width="210" height="452" alt="image" src="https://github.com/user-attachments/assets/47b376ba-e8db-4f6f-b0f2-a9e18470448d" />
[Klik untuk menonton demo (video.mp4)](./Dokumentasi/Program.mp4)
