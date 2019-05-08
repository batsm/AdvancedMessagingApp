package advancedmessagingapp.com

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class contactsAdapter (private val contactList: ArrayList<contactContainerData>): RecyclerView.Adapter<contactsAdapter.ViewHolder>() {


    fun clearContacts() {
        contactList.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.mainContactEmail.text = contactList[p1].email
        p0.mainContactName.text = contactList[p1].name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.contact_container, parent, false))
    }

    override fun getItemCount() = contactList.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val mainContactEmail = itemView.findViewById<TextView>(R.id.txtContactEmail)
        val mainContactName = itemView.findViewById<TextView>(R.id.txtContactName)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            otherUserEmail = mainContactEmail.text.toString()
            otherUserName = mainContactName.text.toString()
            val intent = Intent(itemView.context, MessagesActivity::class.java)
            itemView.context.startActivity(intent)
        }
    }
}