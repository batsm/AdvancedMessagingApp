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
        p0.mainContactTitle.text = contactList[p1].username
        p0.mainContactMessage.text = contactList[p1].lastmessage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.contact_container, parent, false))
    }

    override fun getItemCount() = contactList.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val mainContactTitle = itemView.findViewById<TextView>(R.id.txtContactUsername)
        val mainContactMessage = itemView.findViewById<TextView>(R.id.txtContactLastMessage)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(itemView.context, MessagesActivity::class.java)
            //otherUserEmail = mainTitle.text.toString()
            itemView.context.startActivity(intent)
        }
    }
}