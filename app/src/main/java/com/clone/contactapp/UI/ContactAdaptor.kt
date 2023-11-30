package com.clone.contactapp.UI

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clone.contactapp.databinding.ContactItemListLayoutBinding
import com.clone.contactapp.models.ContactData
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.io.File


class ContactAdaptor(var contactsList: List<ContactData>, val context: Context) :
    RecyclerView.Adapter<ContactsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder { //
        var binding =
            ContactItemListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        var contact = contactsList.get(position)
        var binding = holder.binding
        holder.binding.tvName.text = contact.name
        holder.binding.tvNumber.text = contact.number
        holder.binding.tvemail.text = contact.email
        if (contact.avatar.isNotBlank()) {
            Picasso
                .get()
                .load(File(contact.avatar))
                .resize(80, 80)
                .transform(CropCircleTransformation())
                .centerCrop()
                .into(binding.ivavatar)
        }
        binding.cvContact.setOnClickListener {
            val intent = Intent(context, ContactsDetailsActivity::class.java)
            intent.putExtra("CONTACT_ID",contact.contactId) //
            context.startActivity(intent)

        }

    }
}

class ContactsViewHolder(var binding: ContactItemListLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

}
