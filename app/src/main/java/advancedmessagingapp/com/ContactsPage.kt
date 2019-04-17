package advancedmessagingapp.com

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity

class ContactsPage : FragmentActivity() {

    private var contacts = ArrayList<contactContainerData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_page)
        val adapter = contactsAdapter(contacts)

        contacts.add(contactContainerData("Test 1"))
        contacts.add(contactContainerData("Test 2"))
        contacts.add(contactContainerData("Test 3"))
        contacts.add(contactContainerData("Test 4"))
    }
}
