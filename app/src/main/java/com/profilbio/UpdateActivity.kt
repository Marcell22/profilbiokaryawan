package com.profilbio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.profilbio.databinding.ActivityDeleteBinding
import com.profilbio.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.UpdateSaveBtn.setOnClickListener {
            val idkaryawan = binding.UpdateID.text.toString()
            val namalengkap = binding.UpdateNama.text.toString()
            val jabatan = binding.UpdateJabatan.text.toString()
            val kelamin = binding.UpdateKelamin.text.toString()
            val nope = binding.UpdateNope.text.toString()
            val tanggal = binding.UpdateTanggal.text.toString()

            updateData(idkaryawan, namalengkap, jabatan, tanggal, kelamin, nope)
        }
    }
    private fun updateData(IDkaryawan: String, namalengkap: String, jabatan: String, tanggal: String, kelamin: String, nope: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Data Karyawan")
        val updateid = mapOf<String, String>("idkaryawan" to IDkaryawan, "namalengkap" to namalengkap, "jabatan" to jabatan, "kelamin" to kelamin, "nope" to nope, "tanggal" to tanggal)
        databaseReference.child(IDkaryawan).updateChildren(updateid).addOnSuccessListener {
            binding.UpdateID.text.clear()
            binding.UpdateNama.text.clear()
            binding.UpdateJabatan.text.clear()
            binding.UpdateKelamin.text.clear()
            binding.UpdateNope.text.clear()
            binding.UpdateTanggal.text.clear()
            Toast.makeText(this, "Update has Succes", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Update", Toast.LENGTH_SHORT).show()
        }
    }
}