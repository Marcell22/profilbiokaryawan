package com.profilbio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.profilbio.databinding.ActivityDeleteBinding

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.Deletebtn.setOnClickListener {
            val epfnumber = binding.deleteID.text.toString()
            if (epfnumber.isNotEmpty()){
                deleteData(epfnumber)
            } else{
                Toast.makeText(this, "Please enter EPF number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(idkaryawan: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Data Karyawan")
        databaseReference.child(idkaryawan).removeValue().addOnSuccessListener {
            binding.deleteID.text.clear()
            Toast.makeText(this, "Deleted Succesfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Delete", Toast.LENGTH_SHORT).show()
        }
    }
}