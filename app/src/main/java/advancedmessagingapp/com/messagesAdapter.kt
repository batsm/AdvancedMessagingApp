package advancedmessagingapp.com

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_create_account.view.*

class messagesAdapter (private val messagesList: ArrayList<messagesContainerData>): RecyclerView.Adapter<messagesAdapter.ViewHolder>(){

    fun clearMessages(){
        messagesList.clear()
        notifyDataSetChanged()
    }

    fun addMessage(message: String, user: String){
        messagesList.add(messagesContainerData(message, user))
        notifyItemInserted(messagesList.size)
    }

    fun listSize(): Int{
        return messagesList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_container, parent, false))
    }

    override fun getItemCount() = messagesList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.mainMessageContent.text = messagesList[p1].messageContent
        if (messagesList[p1].sender == currentUser){
            p0.mainMessageContent.gravity = Gravity.RIGHT
        } else {
            p0.mainMessageContent.gravity = Gravity.LEFT
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var mainMessageContent = itemView.findViewById<TextView>(R.id.txtMessageContent)

        override fun onClick(v: View?) {
            // val intent = Intent(itemView.context, MessagesActivity::class.java)
            //otherUserEmail = mainTitle.text.toString()
            // itemView.context.startActivity(intent)
        }
    }
}