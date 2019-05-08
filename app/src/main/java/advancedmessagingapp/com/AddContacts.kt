package advancedmessagingapp.com

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_contacts.*
import kotlinx.android.synthetic.main.activity_contacts_page.*
import kotlinx.android.synthetic.main.activity_contacts_page.navigationView

class AddContacts : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference
    private var combinedUsername = "null"
    var re = Regex("[^a-zA-Z0-9 -]")
    var emailSearched = ""
    var reEmailSearched = Regex(emailSearched)
    var SearchedContactsArray = ArrayList<searchedContactContainerData>()

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

        var users: ArrayList<searchedContactContainerData> = ArrayList()

        database = FirebaseDatabase.getInstance().reference

        val usersAdapter: searchContactsAdapter by lazy(LazyThreadSafetyMode.NONE) {
            searchContactsAdapter(users)
        }

        database.child("users").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                //showMessage(view, "Error: $p0!!")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()){
                    usersAdapter.clearSearchedContacts()
                    addContactsRecyclerView.adapter = searchContactsAdapter(users)
                    for (i in p0.children){
                        if (i.child("email").value.toString().toLowerCase() != fbAuth.currentUser!!.email.toString().toLowerCase()) {
                            //stops logged in user from showing up in list
                            searchedContacts.add(
                                searchedContactContainerData(
                                    i.child("email").value.toString(),
                                    i.child("name").value.toString()
                                )
                            )
                            usersAdapter.addUser(
                                searchedContactContainerData(
                                    i.child("email").value.toString(),
                                    i.child("name").value.toString()
                                )
                            )
                        }
                    }
                }
            }
        })

        txtSearchContacts.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                SearchedContactsArray.clear()
                for (i in 0 until searchedContacts.size){
                    if (searchedContacts[i].email.toLowerCase().contains(txtSearchContacts.text.toString().toLowerCase())){
                        SearchedContactsArray.add(searchedContacts[i])
                    }
                }
                addContactsRecyclerView.layoutManager = LinearLayoutManager(this@AddContacts, LinearLayout.VERTICAL, false)
                addContactsRecyclerView.adapter = searchContactsAdapter(SearchedContactsArray)
            }

        })
        addContactsRecyclerView.layoutManager = LinearLayoutManager(this@AddContacts, LinearLayout.VERTICAL, false)
        addContactsRecyclerView.adapter = searchContactsAdapter(searchedContacts)
    }

    fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }
}
