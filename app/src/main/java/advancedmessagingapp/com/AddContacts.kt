package advancedmessagingapp.com

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_contacts.*
import kotlinx.android.synthetic.main.activity_contacts_page.*
import kotlinx.android.synthetic.main.activity_contacts_page.navigationView

class AddContacts : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference
    private var combinedUsername = "null"
    var re = Regex("[^a-zA-Z0-9 -]")

    private var searchedContacts = ArrayList<searchedContactContainerData>()
    private val NavBarListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.action_contacts -> {
                var intent = Intent(this, ContactsPage::class.java)
                startActivity(intent)
                finish()
                //return@OnNavigationItemSelectedListener true
            }
            R.id.action_addcontacts -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_account -> {
                var intent = Intent(this, AccountActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contacts)
        overridePendingTransition(0, 0)

        navigationView.setOnNavigationItemSelectedListener(NavBarListener)
        navigationView.setOnNavigationItemSelectedListener(null)
        navigationView.selectedItemId = R.id.action_addcontacts
        navigationView.setOnNavigationItemSelectedListener(NavBarListener)

        database = FirebaseDatabase.getInstance().reference

        txtSearchContacts.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        //for (i in 1..20)
        //{
        //  searchedContacts.add(searchedContactContainerData("Found user $i"))
        //}

        addContactsRecyclerView.layoutManager = LinearLayoutManager(this@AddContacts, LinearLayout.VERTICAL, false)
        addContactsRecyclerView.adapter = searchContactsAdapter(searchedContacts)
    }
}
