# TP4DPBO2025C1

## **Data Mahasiswa - Java Swing Application**

### **Deskripsi**
Aplikasi ini dibuat menggunakan **Java Swing** untuk mengelola data mahasiswa. Fitur utama dari aplikasi ini meliputi:  
-> Menambah data mahasiswa  
-> Mengedit data mahasiswa  
-> Menghapus data mahasiswa dengan konfirmasi  
-> Menampilkan daftar mahasiswa dalam bentuk tabel  
-> Memilih elemen untuk setiap mahasiswa  

---

## **Desain Program**
### **1. Struktur Tampilan**
Aplikasi memiliki tampilan utama dengan beberapa komponen berikut:  
- **Input Form**:  
  - **NIM**: Input field untuk memasukkan Nomor Induk Mahasiswa  
  - **Nama**: Input field untuk memasukkan nama mahasiswa  
  - **Jenis Kelamin**: Dropdown untuk memilih jenis kelamin  
  - **Element**: Checkbox untuk memilih elemen yang dimiliki mahasiswa  
  - **Tombol Add**: Untuk menambahkan input data  
  - **Tombol Update**: Untuk mengupdate input data  
  - **Tombol Delete**: Untuk mendelete input data  
  - **Tombol Cancel**: Untuk membatalkan aksi add, update atau delete  

- **Tabel Mahasiswa**:  
  - Menampilkan daftar mahasiswa dengan kolom **No, NIM, Nama, Jenis Kelamin, dan Element**  
  - Data di tabel diperbarui secara otomatis setiap kali ada perubahan  

---

## **Alur Kerja**
### **1. Menambahkan Data Mahasiswa**
1. Pengguna mengisi **NIM, Nama, Jenis Kelamin, dan memilih Element**  
2. Klik tombol **Add**, data akan ditambahkan ke tabel  
3. Data langsung muncul di tabel mahasiswa  

### **2. Menghapus Data Mahasiswa**
1. Pilih baris mahasiswa yang ingin dihapus  
2. Klik tombol **Delete**  
3. Muncul dialog konfirmasi (Yes/No)  
4. Jika memilih **Yes**, data akan dihapus dari daftar  

### **3. Mengelola Element Mahasiswa**
- Setiap mahasiswa memiliki elemen yang dipilih menggunakan **checkbox**  
- Mahasiswa dengan nama **Muhammad Rifky Afandi** secara otomatis memiliki elemen **"Spesial"**  

---

## **Library yang Digunakan**
- **Java Swing** untuk GUI  
- **ArrayList** untuk menyimpan data mahasiswa  
- **JTable** untuk menampilkan daftar mahasiswa  
- **JOptionPane** untuk konfirmasi penghapusan  

---
