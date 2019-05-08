package advancedmessagingapp.com

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_messages.*

class MessagesActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference
    var createMessageData: messageData = messageData("", "")

    private var messages = ArrayList<messagesContainerData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        database = FirebaseDatabase.getInstance().reference
        var chatMessages: ArrayList<String>
        var re = Regex("[^a-zA-Z0-9 -]")
        var userMessageSignature = fbAuth?.currentUser!!.email.toString()
        var currentUser = re.replace(currentUser.toLowerCase(), "")
        var recieverUser = re.replace(otherUserEmail.toLowerCase(), "")

        var chatName = if (currentUser < recieverUser){
            "$currentUser-$recieverUser"
        } else {
            "$recieverUser-$currentUser"
        }

        val adapter: messagesAdapter by lazy(LazyThreadSafetyMode.NONE) {
            messagesAdapter(messages)
        }

        txtTitleName.text = otherUserName

        database.child("conversations").child(chatName).addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    adapter.clearMessages()
                    messagesRecyclerView.adapter = messagesAdapter(messages)

                    for(i in p0.children){
                        adapter.addMessage(i.child("message").value.toString(), i.child("sender").value.toString())
                    }
                    messagesRecyclerView.scrollToPosition(adapter.listSize() - 1)
                }
            }

        })
        messagesRecyclerView.layoutManager = LinearLayoutManager(this@MessagesActivity, LinearLayout.VERTICAL, false)
        messagesRecyclerView.adapter = messagesAdapter(messages)

        btnSendMessage.setOnClickListener { view ->
            if (txtMessageInput.text.toString() != ""){
                createMessageData = messageData(txtMessageInput.text.toString(), userMessageSignature)
                database.child("conversations").child(chatName).push().setValue(createMessageData)
                txtMessageInput.setText("")
            }
        }
    }
}
