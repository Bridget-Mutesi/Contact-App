package com.clone.contactapp.Repository

import android.provider.ContactsContract.Contacts
import androidx.lifecycle.LiveData
import com.clone.contactapp.ContactsApp
import com.clone.contactapp.Database.ContactsDb
import com.clone.contactapp.models.ContactData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository {
    val database = ContactsDb.getDatabase((ContactsApp.appContext))
    val contactDao = database.contactDao()

    suspend fun saveContact(contacts: ContactData){
        withContext(Dispatchers.IO){
            database.contactDao().insertContact(contacts)
        }
    }

    fun getDbContacts(): LiveData<List<ContactData>>{
        return database.contactDao().getAllContacts()
    }
    fun getContactById(contactId: Int): LiveData<List<ContactData>>{
        return database.contactDao().getContactById(contactId)
    }
}