package advancedmessagingapp.com

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class messagesAdapter (private val messagesList: ArrayList<messagesContainerData>): RecyclerView.Adapter<messagesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.message_container, parent, false))
    }

    override fun getItemCount() = messagesList.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.mainMessageContent.text = messagesList[p1].messageContent
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