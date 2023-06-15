package com.clone.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.clone.contactapp.databinding.ActivityAddNewContactBinding
import com.clone.contactapp.databinding.ActivityMainBinding

class AddNewContact : AppCompatActivity() {
    lateinit var binding: ActivityAddNewContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    override fun onResume() {
        super.onResume()
        binding.tvadd.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        validatecontact()
    }

    fun validatecontact(){
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val number = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        var  error = false

        if (firstName.isBlank()){
            binding.tilFirstName.error ="first name is required"
            error= true
//
//        }else{
//            binding.tilFirstName.error = null
        }

        if (lastName.isBlank()){
            binding.tilLastName.error ="first name is required"
            error = true
//        }else{
//            binding.tilLastName.error = null
       }

        if (number.isBlank()){
            binding.tilPhoneNumber.error ="phone number  is required"
            error = true
//        }else{
//            binding.tilPhoneNumber.error = null
        }

        if (email.isBlank()){
            binding.tilEmail.error =" email is required"
            error = true
//        }else{
//            binding.tilEmail.error = null
        }

        if(!error){
            Toast.makeText(this,"$firstName $lastName $email", Toast.LENGTH_LONG).show()
        }

    }
}