package com.profilbio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.profilbio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updatebtnsearch.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.uploadbtnsearch.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.searchbtn.setOnClickListener {
            val searchIDKaryawan: String = binding.searchIDKaryawan.text.toString()
            if (searchIDKaryawan.isNotEmpty()){
                readData(searchIDKaryawan)
            } else{
                Toast.makeText(this, "Please enter EPF number", Toast.LENGTH_SHORT).show()
            }
        }

        binding.deletebtnsearch.setOnClickListener {
            val intent = Intent(this, DeleteActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun readData(idkaryawan:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Data Karyawan")
        databaseReference.child(idkaryawan).get().addOnSuccessListener {
            if (it.exists()){
                val idkaryawan = it.child("idkaryawan").value
                val namalengkap = it.child("namalengkap").value
                val jabatan = it.child("jabatan").value
                val tanggal = it.child("tanggal").value
                val kelamin = it.child("kelamin").value
                val nope = it.child("nope").value
                Toast.makeText(this, "Result Found", Toast.LENGTH_SHORT).show()

                binding.searchIDKaryawan.text.clear()

                binding.readId.text = idkaryawan.toString()
                binding.readNama.text = namalengkap.toString()
                binding.readJabatan.text = jabatan.toString()
                binding.readTanggal.text = tanggal.toString()
                binding.readKelamin.text = kelamin.toString()
                binding.readNope.text = nope.toString()

            } else{
                Toast.makeText(this, "ID Karyawan does not exists", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Something went wrong, check your internet", Toast.LENGTH_SHORT).show()
        }
    }
}