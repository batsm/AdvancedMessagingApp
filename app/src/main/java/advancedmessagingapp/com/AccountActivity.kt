package advancedmessagingapp.com

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import kotlinx.android.synthetic.main.activity_contacts_page.*

class AccountActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    private val NavBarListener = BottomNavigationView.OnNavigationItemSelectedListener { item->
        when(item.itemId){
            R.id.action_contacts -> {
                var intent = Intent(this, ContactsPage::class.java)
                startActivity(intent)
                finish()
                //return@OnNavigationItemSelectedListener true
            }
            R.id.action_addcontacts -> {
                var intent = Intent(this, MainActivity::class.java)
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
    }
}
