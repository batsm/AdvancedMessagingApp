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
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.activity_contacts_page.navigationView


class ContactsPage : FragmentActivity() {

    var fbAuth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference
    private var contacts = ArrayList<contactContainerData>()
    var re = Regex("[^a-zA-Z0-9 -]")
    var chatsArray = ArrayList<String>()

    private val NavBarListener = BottomNavigationView.OnNavigationItemSelectedListener {item->
        when(item.itemId){
            R.id.action_contacts -> {

                return@OnNavigationItemSelectedListener true
            }
            R.id.action_addcontacts -> {
                var intent = Intent(this, AddContacts::class.java)
                startActivity(intent)
                finish()
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_account -> {
                var intent = Intent(this, AccountActivity::class.java)
                startActivity(intent)
                finish()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_page)
        overridePendingTransition(0, 0)

        navigationView.setOnNavigationItemSelectedListener(NavBarListener)
        navigationView.setOnNavigationItemSelectedListener(null)
        navigationView.selectedItemId = R.id.action_contacts
        navigationView.setOnNavigationItemSelectedListener(NavBarListener)

        database = FirebaseDatabase.getInstance().reference
        val adapter = contactsAdapter(contacts)
        var currentUsername = re.replace(fbAuth?.currentUser!!.email.toString(), "")
        var reUsername = Regex(currentUsername)
        var reDash = Regex("-")
        currentUser = fbAuth?.currentUser!!.email.toString()

        database.child("users").child(currentUsername).child("conversations").addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                //adapter.clearContacts()
                for (i in p0.children){
                    chatsArray.add(reDash.replace(reUsername.replace(i.key.toString(), ""), ""))
                }
            }
        })

        database.child("users").addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                adapter.clearContacts()
                for (i in p0.children){
                    for (a in 0 until chatsArray.size){
                        if (i.key.toString() == chatsArray[a]){
                            contacts.add(contactContainerData(i.child("email").value.toString(), i.child("name").value.toString()))
                        }
                    }
                }
                contactsRecyclerView.layoutManager = LinearLayoutManager(this@ContactsPage, LinearLayout.VERTICAL, false)
                contactsRecyclerView.adapter = contactsAdapter(contacts)
            }
        })
    }
}
