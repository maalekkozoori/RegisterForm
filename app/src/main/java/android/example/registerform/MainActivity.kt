package android.example.registerform

import android.content.Context
import android.content.SharedPreferences
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.Group

class MainActivity : AppCompatActivity() {

    lateinit var txtFullname: EditText
    lateinit var txtUserName: EditText
    lateinit var txtEmail: EditText
    lateinit var txtPass: EditText
    lateinit var txtRetypePass: EditText
    lateinit var btnRegister: Button
    lateinit var btnShowInfo: Button
    lateinit var rbFemale: RadioButton
    lateinit var rbMale: RadioButton
    lateinit var btnHideInfo: Button
    lateinit var tvFullName: TextView
    lateinit var tvUsername: TextView
    lateinit var tvEmail: TextView
    lateinit var tvPass: TextView
    lateinit var tvGender: TextView
    lateinit var infoGroup: Group
    lateinit var genderGroup: Group


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtFullname = findViewById(R.id.txtFullName)
        txtUserName = findViewById(R.id.txtUsername)
        txtEmail = findViewById(R.id.txtEmail)
        txtPass = findViewById(R.id.txtPass)
        txtRetypePass = findViewById(R.id.txtRetypePass)
        btnRegister = findViewById(R.id.btnRegister)
        btnShowInfo = findViewById(R.id.btnShowInfo)
        rbFemale = findViewById(R.id.rbFemale)
        rbMale = findViewById(R.id.rbMale)
        btnHideInfo = findViewById(R.id.btnHideInfo)
        tvFullName = findViewById(R.id.tvFullName)
        tvUsername = findViewById(R.id.tvUserName)
        tvEmail = findViewById(R.id.tvEmail)
        tvPass = findViewById(R.id.tvPassword)
        tvGender = findViewById(R.id.tvGender)
        infoGroup = findViewById(R.id.infoGroup)
        genderGroup = findViewById(R.id.genderGroup)
        val sharedPreferences : SharedPreferences = this.getSharedPreferences("RegisterInfo",Context.MODE_PRIVATE)


        btnShowInfo.setOnClickListener {
            infoGroup.visibility = View.VISIBLE
            btnShowInfo.isEnabled = false
            tvFullName.text = sharedPreferences.getString("Full_Name","")
            tvUsername.text = sharedPreferences.getString("User_Name","")
            tvEmail.text = sharedPreferences.getString("Email","")
            tvPass.text = sharedPreferences.getString("Pass","")
            tvGender.text = sharedPreferences.getString("Gender","")
        }

        btnHideInfo.setOnClickListener {
            hideInfo()
        }

        var edit = sharedPreferences.edit()
        btnRegister.setOnClickListener {
            edit.putString("Full_Name", txtFullname.text.toString())
            edit.putString("User_Name", txtUserName.text.toString())
            edit.putString("Email", txtEmail.text.toString())
            edit.putString("Pass", txtPass.text.toString())
            edit.putString("Gender", getGender())
            edit.apply()
            Toast.makeText(this,"Data Saved Successfuly",Toast.LENGTH_LONG).show()
        }

        rbFemale.setOnClickListener { rbMale.isChecked = false }
        rbMale.setOnClickListener { rbFemale.isChecked = false }

    }




    fun hideInfo() {
        btnShowInfo.isEnabled = true
        infoGroup.visibility = View.GONE



    }

    fun getGender(): String {
        var gender = ""
        if (rbFemale.isChecked) gender = "Female"
        if (rbMale.isChecked) gender = "Male"
        return gender
    }
}