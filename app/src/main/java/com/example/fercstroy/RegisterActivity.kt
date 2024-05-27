package com.example.fercstroy

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private var btnBack: ImageButton? = null
    private var btnRegister: Button? = null
    private var fio: EditText? = null
    private var email: EditText? = null
    private var phone: EditText? = null
    private var pass: EditText? = null
    private var pass2: EditText? = null
    private var toLogin: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//        btnBack = findViewById(R.id.auth_arrow_back)
        btnRegister = findViewById(R.id.register_button)
        fio = findViewById(R.id.register_fio)
        email = findViewById(R.id.register_email)
        phone = findViewById(R.id.register_phone)
        pass = findViewById(R.id.register_password)
        pass2 = findViewById(R.id.register_password2)
        toLogin = findViewById(R.id.register_to_login)

        toLogin?.setOnClickListener{
            finish()
        }

        btnRegister?.setOnClickListener {
            val Fio: String = fio?.text.toString().trim()
            val Phone: String = phone?.text.toString().replace(" ", "")
            val Email: String = email?.text.toString().trim()
            val Pass: String = pass?.text.toString().trim()
            val Pass2: String = pass2?.text.toString().trim()

            if(Fio == "" || Email == "" || Pass == "" || Pass2 == ""){
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show()
            }
            else if(!isValidFio(Fio)){
                fio?.error = "Введите ФИО в формате `Иванов Иван Иванович`"
            }
            else if(!isValidEmail(Email)){
                phone?.error = "Некорректный email"
            }
            else if(!isValidPhone(Phone)){
                email?.error = "Введите номер телефона в формате `+375291234568`"
            }
            else if(Pass != Pass2){
                Toast.makeText(this, "Введенные пароли не совпадают", Toast.LENGTH_LONG).show()
            }
            else{
                val user = User(Fio, Phone, Email, Pass)
                val db = DbHelper(this, null)
                db.addUser(user)
                Toast.makeText(this, "Успешная регистрация", Toast.LENGTH_SHORT).show()

                fio!!.text.clear()
                phone!!.text.clear()
                email!!.text.clear()
                pass!!.text.clear()
                pass2!!.text.clear()
            }

        }

    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z\\d._%+-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}")
        return emailRegex.matches(email)
    }

    fun isValidFio(fio: String): Boolean {
        val fioRegex = Regex("[a-zA-Zа-яА-яёЁ]+ [a-zA-Zа-яА-яёЁ]+ [a-zA-Zа-яА-яёЁ]+")
        return fioRegex.matches(fio)
    }

    fun isValidPhone(phone: String): Boolean{
        val phoneRegex = Regex("\\+[0-9]{1,15}")
        return phoneRegex.matches(phone)
    }

}