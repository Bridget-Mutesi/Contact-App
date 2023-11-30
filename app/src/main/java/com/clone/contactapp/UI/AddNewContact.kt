package com.clone.contactapp.UI

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract.Contacts
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import com.clone.contactapp.databinding.ActivityAddNewContactBinding
import com.clone.contactapp.models.ContactData
import com.clone.contactapp.viewmodels.ContactsViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.io.File

class AddNewContact : AppCompatActivity() {
    lateinit var binding: ActivityAddNewContactBinding
    val contactsViewModel: ContactsViewModel by viewModels()
    lateinit var photoFile: File
    lateinit var fusedLocationClient:FusedLocationProviderClient
    lateinit var mylocation: Location

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
        if (result.resultCode == Activity.RESULT_OK){
            Picasso
                .get()
                .load(File(photoFile.absolutePath))
                .resize(120, 120)
                .transform(CropCircleTransformation())
                .centerCrop()
                .into(binding.ivprofile)

        }
    }
    val locationRequest = registerForActivityResult(ActivityResultContracts.RequestPermission()){result->
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){

        }else{
            Toast.makeText(this, "Please grant location permission",Toast.LENGTH_SHORT).show()

        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }


    override fun onResume() {
        super.onResume()
        binding.tvadd.setOnClickListener {
            validatecontact()
            clearErrors()

        }
        binding.ivprofile.setOnClickListener {
            capturePhoto()

        }
        getLastKnownLocation()


    }
//    fun getLocationUpdates(){
//        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){
//            val locationRequest = LocationRequest
//        }
//
//    }

    fun getLastKnownLocation(){
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient
                .getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY,
                    CancellationTokenSource().token)
                .addOnSuccessListener { location ->
                mylocation = location
                Toast.makeText(
                    this,
                    "lat:${location.latitude}, Long:${location.longitude}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        else{
            locationRequest.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }
    private fun capturePhoto(){
        val photoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getPhotFile("photo_${System.currentTimeMillis()}")
        val fileUri = FileProvider.getUriForFile(this,"com.clone.contactapp",photoFile)
        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        cameraLauncher.launch(photoIntent)



    }
    private fun getPhotFile(fileName:String): File {
        val  folder = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val tempFile = File.createTempFile(fileName,"jpeg",folder)
        return tempFile

    }

    fun validatecontact(){
        val name = binding.etname.text.toString()
        val number = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        var  error = false

        if (name.isBlank()){
            binding.tilname.error ="first name is required"
            error= true
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
        }

        if(!error){
            val newContact = ContactData(0, name, number, email, avatar = photoFile.absolutePath)
            contactsViewModel.saveContact(newContact)
            Toast.makeText(this, "contact saved", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    fun clearErrors() {

       binding.tilname.error = null
        binding.tilEmail.error = null
        binding.tilPhoneNumber.error = null


    }
}