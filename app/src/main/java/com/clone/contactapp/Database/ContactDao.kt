package com.clone.contactapp.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.clone.contactapp.models.ContactData

@Dao

interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: ContactData)

    //function to querry our database from alll the contacts that exists
    @Query("SELECT * FROM ContactData ORDER by name")
    fun getAllContacts(): LiveData<List<ContactData>>

    @Query("SELECT * FROM ContactData WHERE contactId = :contactId" )
    fun getContactById(contactId: Int): LiveData<List<ContactData>>



}