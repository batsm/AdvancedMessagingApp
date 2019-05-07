package advancedmessagingapp.com

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.w3c.dom.Text

class searchContactsAdapter (private val searchedContactList: ArrayList<searchedContactContainerData>): RecyclerView.Adapter<searchContactsAdapter.ViewHolder>(){

    fun clearSearchedContacts(){
        searchedContactList.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.mainTitleUsername.text = searchedContactList[p1].username
    }

    override fun getItemCount() = searchedContactList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.searched_contact_container, parent, false))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var mainTitleUsername = itemView.findViewById<TextView>(R.id.txtSearchedContactUsername)

        /*
        init {
            itemView.setOnClickListener(this)
        }*/

        override fun onClick(v: View?) {
           // val intent = Intent(itemView.context, MessagesActivity::class.java)
            //otherUserEmail = mainTitle.text.toString()
           // itemView.context.startActivity(intent)
        }
    }
}