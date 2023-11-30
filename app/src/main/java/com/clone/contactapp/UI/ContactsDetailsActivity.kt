package com.clone.contactapp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.clone.contactapp.R
import com.clone.contactapp.databinding.ActivityAddNewContactBinding
import com.clone.contactapp.databinding.ActivityContactsDetailsBinding
import com.clone.contactapp.viewmodels.ContactsViewModel

class ContactsDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityContactsDetailsBinding
    var contactId = 0
    val contactsViewModel:ContactsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bundle = intent.extras
        if (bundle!=null){
            contactId = bundle.getInt("CONTACT_ID")
            contactsViewModel.getContactById(contactId)
//            Toast.makeText(this,"$contactId", Toast.LENGTH_LONG).show()
        }

        contactsViewModel.contactLiveData.observe(this){contactList->
            if(contactList!=null){
                val contact = contactList[0]
                binding.tvname.text = contact.name
                binding.tvmail.text = contact.email
                binding.tvmail.text = contact.email
                
                }

        }
        }


    }
