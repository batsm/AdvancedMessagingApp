package advancedmessagingapp.com

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class searchContactsAdapter (private val searchedContactList: ArrayList<searchedContactContainerData>): RecyclerView.Adapter<searchContactsAdapter.ViewHolder>(){

    fun clearSearchedContacts(){
        searchedContactList.clear()
        notifyDataSetChanged()
    }

    fun addUser(user: searchedContactContainerData){
        searchedContactList.add(user)
        notifyItemInserted(searchedContactList.size)
    }

    fun updateContacts(){
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.mainTitleEmail.text = searchedContactList[p1].email
        p0.secondTitleName.text = searchedContactList[p1].name
    }

    override fun getItemCount() = searchedContactList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.searched_contact_container, parent, false))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var mainTitleEmail = itemView.findViewById<TextView>(R.id.txtSearchedContactEmail)
        var secondTitleName = itemView.findViewById<TextView>(R.id.txtSearchedContactName)

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