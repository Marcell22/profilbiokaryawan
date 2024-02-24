package com.profilbio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.profilbio.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BackUpload.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.UploadSaveBtn.setOnClickListener {
            val idkaryawan = binding.UploadID.text.toString()
            val namalengkap = binding.UploadNama.text.toString()
            val jabatan = binding.UploadJabatan.text.toString()
            val tanggal = binding.UploadTanggal.text.toString()
            val kelamin = binding.UploadKelamin.text.toString()
            val nope = binding.UploadNope.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Data Karyawan")
            val karyawanData = KaryawanData(idkaryawan, namalengkap, jabatan, tanggal, kelamin, nope)
            databaseReference.child(idkaryawan).setValue(karyawanData).addOnSuccessListener {
                binding.UploadID.text.clear()
                binding.UploadNama.text.clear()
                binding.UploadJabatan.text.clear()
                binding.UploadTanggal.text.clear()
                binding.UploadKelamin.text.clear()
                binding.UploadNope.text.clear()


                Toast.makeText(this,"Has Uploaded", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to Upload", Toast.LENGTH_SHORT).show()
            }
        }

    }
}