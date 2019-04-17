package advancedmessagingapp.com

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_contacts_page.*

class ContactsPage : FragmentActivity() {

    private var contacts = ArrayList<contactContainerData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_page)
        val adapter = contactsAdapter(contacts)

        contacts.add(contactContainerData("Thomas", "whats up buttercup"))
        contacts.add(contactContainerData("Ben", "Lol yeah that was pretty great"))
        contacts.add(contactContainerData("Kate", "Memes lit yo"))

        contactsRecyclerView.layoutManager = LinearLayoutManager(this@ContactsPage, LinearLayout.VERTICAL, false)
        contactsRecyclerView.adapter = contactsAdapter(contacts)
    }
}
