package advancedmessagingapp.com

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_account.*


class LoginActivity : FragmentActivity() {

    var fbAuth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        database = FirebaseDatabase.getInstance().reference

        if (fbAuth.currentUser != null){
            var intent = Intent(this, ContactsPage::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener { view ->
            if (txtLoginEmail.length() > 0 && txtLoginPassword.length() > 0)
                signIn(view, txtLoginEmail.text.toString(), txtLoginPassword.text.toString())
            else
                showMessage(view, "Please enter a username and password")
        }

        btnNewAccount.setOnClickListener {
            var intent = Intent(this, CreateAccount::class.java)
            startActivity(intent)
        }
    }

    fun signIn(view: View, email: String, password: String) {
        showMessage(view, "Authenticating...")

        fbAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    showMessage(view, "Logged in!")
                    var intent = Intent(this, ContactsPage::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    showMessage(view, "Error: ${task.exception?.message}")
                }
            }
    }

    fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction("Action", null).show()
    }
}
