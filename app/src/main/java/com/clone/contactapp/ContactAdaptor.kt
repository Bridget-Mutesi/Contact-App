package com.clone.contactapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clone.contactapp.databinding.ContactItemListLayoutBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class ContactAdaptor(var contactsList: List<ContactData>): RecyclerView.Adapter<ContactsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder { //
        var binding = ContactItemListLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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
        Picasso
            .get()
            .load(contact.avatar)
            .resize(80,80)
            .transform(CropCircleTransformation())
            .centerCrop()
            .into(binding.ivavatar)








    }
}
class ContactsViewHolder( var binding: ContactItemListLayoutBinding): RecyclerView.ViewHolder(binding.root) {

}
