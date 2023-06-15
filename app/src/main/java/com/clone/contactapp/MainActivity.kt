package com.clone.contactapp

import android.content.Intent
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

        binding.ivadd.setOnClickListener {
            val intent = Intent(this,AddNewContact::class.java)
            startActivity(intent)
        }

        displayContacts()
    }

    fun displayContacts(){
        var contact1 = ContactData("https://images.unsplash.com/photo-1596510914965-9ae08acae566?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=449&q=80","Rita B","+254 750570400","rita@gmail.com")
        var contact2 = ContactData("https://images.unsplash.com/photo-1463453091185-61582044d556?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80","Nicky","+254 750560784","nick@gmail.com")
        var contact3 = ContactData("https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=387&q=80","Mercy","+256 70505893","mercy@gmail.com")
        var contact4 = ContactData("https://images.unsplash.com/photo-1533636721434-0e2d61030955?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80","Ben","+256 7574145678","ben@gmail.com")
        var contact5 = ContactData("https://images.unsplash.com/photo-1517598024396-46c53fb391a1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=435&q=80","Allan","+254 741717214","allan@gmail.com")
        var contact6 = ContactData("https://images.unsplash.com/photo-1533636721434-0e2d61030955?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80","Joy","+256 7787404911","joy@gmail.com")
        var contact7 = ContactData("https://images.unsplash.com/photo-1656432606161-41c3071950ca?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=464&q=80","Anne","+254 757612456","anne@gmail.com")

        var contactList = listOf<ContactData>(contact1,contact2,contact3,contact4,contact5,contact6,contact7)
        binding.rvContact.layoutManager = LinearLayoutManager(this)
        var ContactAdaptor = ContactAdaptor(contactList)
        binding.rvContact.adapter = ContactAdaptor




    }
}