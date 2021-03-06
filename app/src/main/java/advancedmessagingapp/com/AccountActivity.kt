package advancedmessagingapp.com

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.activity_contacts_page.*
import kotlinx.android.synthetic.main.activity_contacts_page.navigationView

class AccountActivity : AppCompatActivity() {

    var fbAuth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference
    var re = Regex("[^a-zA-Z0-9 -]")
    var username = re.replace(currentUser.toLowerCase(),"")

    private val NavBarListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.action_contacts -> {
                var intent = Intent(this, ContactsPage::class.java)
                startActivity(intent)
                finish()
                //return@OnNavigationItemSelectedListener true
            }
            R.id.action_addcontacts -> {
                var intent = Intent(this, AddContacts::class.java)
                startActivity(intent)
                finish()
                //return@OnNavigationItemSelectedListener true
            }
            R.id.action_account -> {

            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        overridePendingTransition(0, 0)

        navigationView.setOnNavigationItemSelectedListener(NavBarListener)
        navigationView.setOnNavigationItemSelectedListener(null)
        navigationView.selectedItemId = R.id.action_account
        navigationView.setOnNavigationItemSelectedListener(NavBarListener)

        var name: String
        txtAccountEmail.text = fbAuth?.currentUser!!.email.toString()

        database = FirebaseDatabase.getInstance().reference
        database.child("users").addValueEventListener(object: ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (i in p0.children){
                    if (i.key == username) {
                        name = i.child("name").value.toString()
                        txtAccountName.setText(name)
                    }
                }
            }
        })

        btnAccountUpdate.setOnClickListener { view ->
            if (txtAccountName.text.length > 0 && txtAccountName.text.length < 15) {
                database.child("users").child(username).child("name").setValue(txtAccountName.text.toString())
                showMessage(view, "Account updated!")
            } else {
                showMessage(view, "Error updating account")
            }
        }

        btnSignOut.setOnClickListener { view ->
            fbAuth.signOut()
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction("Action", null).show()
    }
}
