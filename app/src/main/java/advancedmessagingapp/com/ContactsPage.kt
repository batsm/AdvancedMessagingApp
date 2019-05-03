package advancedmessagingapp.com

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_contacts_page.*
//import android.R
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.ActionBar


class ContactsPage : FragmentActivity() {

    private var contacts = ArrayList<contactContainerData>()
    private val NavBarListener = BottomNavigationView.OnNavigationItemSelectedListener {item->
        when(item.itemId){
            R.id.action_contacts -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.action_addcontacts -> {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_account -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_page)

        //toolbar = supportActionBar!!
        //val bottomNavigation: BottomNavigationView = findViewById(R.id.navigationBarBackground)
        navigationView.setOnNavigationItemSelectedListener(NavBarListener)

        val adapter = contactsAdapter(contacts)

        for (i in 1..20)
        {
            contacts.add(contactContainerData("Thomas${i.toString()}", "whats up buttercup"))
        }
        //contacts.add(contactContainerData("Thomas", "whats up buttercup"))
        contacts.add(contactContainerData("Ben", "Lol yeah that was pretty great"))
        contacts.add(contactContainerData("Kate", "Memes lit yo"))

        contactsRecyclerView.layoutManager = LinearLayoutManager(this@ContactsPage, LinearLayout.VERTICAL, false)
        contactsRecyclerView.adapter = contactsAdapter(contacts)
    }
}
