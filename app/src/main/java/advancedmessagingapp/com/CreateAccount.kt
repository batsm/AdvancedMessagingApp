package advancedmessagingapp.com

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentActivity
import android.view.View
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_account.*

class CreateAccount : FragmentActivity() {

    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        btnCreateAccount.setOnClickListener {view ->
            if (txtCreatePass1.text.toString() == txtCreatePass2.text.toString()) {
                createAccount(view, txtCreateEmail.text.toString(), txtCreatePass1.text.toString())
            }else{
                showMessage(view, "Error: Passwords dont match")
            }
        }
    }

    fun createAccount(view: View, email: String, password: String) {
        showMessage(view, "Creating Account...")
        fbAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    showMessage(view, "Created account!")
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
