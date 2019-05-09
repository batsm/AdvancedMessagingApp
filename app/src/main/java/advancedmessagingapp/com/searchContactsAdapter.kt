package advancedmessagingapp.com

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class searchContactsAdapter (private val searchedContactList: ArrayList<searchedContactContainerData>): RecyclerView.Adapter<searchContactsAdapter.ViewHolder>(){

    fun clearSearchedContacts(){
        searchedContactList.clear()
        notifyDataSetChanged()
    }

    fun addUser(user: searchedContactContainerData){
        searchedContactList.add(user)
        notifyItemInserted(searchedContactList.size)
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

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            var fbAuth: FirebaseAuth = FirebaseAuth.getInstance()
            var database: DatabaseReference = FirebaseDatabase.getInstance().reference
            var re = Regex("[^a-zA-Z0-9 -]")
            val currentUser = re.replace(fbAuth?.currentUser!!.email.toString(), "").toLowerCase()
            val targetUser = re.replace(itemView.findViewById<TextView>(R.id.txtSearchedContactEmail).text, "").toLowerCase()
            var chatName: String

            chatName = if (currentUser < targetUser){
                "$currentUser-$targetUser"
            } else {
                "$targetUser-$currentUser"
            }

            database.child("users").child(currentUser).child("conversations").child(chatName).setValue("Chat")
            database.child("users").child(targetUser).child("conversations").child(chatName).setValue("Chat")
            database.child("conversations").child(chatName).push().setValue("")
        }
    }
}