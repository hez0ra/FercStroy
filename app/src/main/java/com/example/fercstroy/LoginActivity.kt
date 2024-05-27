package com.example.fercstroy

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private var btnBack: ImageButton? = null
    private var btnAuth: Button? = null
    private var email: EditText? = null
    private var pass: EditText? = null
    private var check: CheckBox? = null
    private var toReg: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        btnBack = findViewById(R.id.auth_arrow_back)
        btnAuth = findViewById(R.id.login_button)
        email = findViewById(R.id.login_email)
        pass = findViewById(R.id.login_password)
        check = findViewById(R.id.login_stay_logged_in)
        toReg = findViewById(R.id.login_to_registration)

        toReg?.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnAuth?.setOnClickListener {
            val Email: String = email?.text.toString().trim()
            val Pass: String = pass?.text.toString().trim()

            if(Email == "" || Pass == ""){
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show()
            }
            else{
                val db = DbHelper(this, null)
                db.getUser(Email, Pass)
                if(ActiveUser.getUser() != null){
                    if(check!!.isChecked){
                        val sharedPreferencesManager = SharedPreferencesManager(this)
                        sharedPreferencesManager.saveUserID(this, ActiveUser.getUserId()!!)
                    }
                    finish()
                }
                else{
                    Toast.makeText(this, "Неправильный email или пароль", Toast.LENGTH_LONG).show()
                    pass!!.text.clear()
                }
            }
        }



    }
}