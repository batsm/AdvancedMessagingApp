package advancedmessagingapp.com

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_messages.*

class MessagesActivity : AppCompatActivity() {

    private var messages = ArrayList<messagesContainerData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        for(i in 1..50){
            messages.add(messagesContainerData("Message $i", "a"))
        }

        messagesRecyclerView.layoutManager = LinearLayoutManager(this@MessagesActivity, LinearLayout.VERTICAL, false)
        messagesRecyclerView.adapter = messagesAdapter(messages)
    }
}
