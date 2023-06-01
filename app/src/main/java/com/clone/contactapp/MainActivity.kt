package com.clone.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.clone.contactapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        displayContacts()
    }

    fun displayContacts(){
        var contact1 = ContactData("","Rita B","+254 750570400","rita@gmail.com")
        var contact2 = ContactData("","Nicky","+254 750560784","nick@gmail.com")
        var contact3 = ContactData("","Mercy","+256 70505893","mercy@gmail.com")
        var contact4 = ContactData("","Ben","+256 7574145678","ben@gmail.com")
        var contact5 = ContactData("","Allan","+254 741717214","allan@gmail.com")
        var contact6 = ContactData("","Joy","+256 7787404911","joy@gmail.com")
        var contact7 = ContactData("","Anne","+254 757612456","anne@gmail.com")

        var contactList = listOf<ContactData>(contact1,contact2,contact3,contact4,contact5,contact6,contact7)
        binding.rvContact.layoutManager = LinearLayoutManager(this)
        var ContactAdaptor = ContactAdaptor(contactList)
        binding.rvContact.adapter = ContactAdaptor




    }
}