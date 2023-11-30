package com.clone.contactapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ContactData")
class ContactData(
    @PrimaryKey(autoGenerate = true)
    var contactId: Int, // the id will be sequential
    var name: String,
    var number: String,
    var email: String,
    var avatar: String,

)
