package com.clone.contactapp.viewmodels

import android.provider.ContactsContract.Contacts
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clone.contactapp.Repository.ContactsRepository
import com.clone.contactapp.models.ContactData
import kotlinx.coroutines.launch

class ContactsViewModel: ViewModel() {
    val contactRepo = ContactsRepository()
    lateinit var contactLiveData: LiveData<List<ContactData>>
    fun saveContact(contact: ContactData){
        viewModelScope.launch {
            contactRepo.saveContact(contact)
        }

    }
    //function to get our contacts from the database
    fun getDbContacts(): LiveData<List<ContactData>>{
        return contactRepo.getDbContacts()

    }
    fun getContactById(contactId: Int){
        contactLiveData = contactRepo.getContactById(contactId)

    }
}