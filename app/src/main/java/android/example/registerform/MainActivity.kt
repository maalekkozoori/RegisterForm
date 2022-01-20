package android.example.registerform

import android.content.Context
import android.content.SharedPreferences
import android.example.registerform.databinding.ActivityMainBinding
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.Group

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sharedPreferences : SharedPreferences = this.getSharedPreferences("RegisterInfo",Context.MODE_PRIVATE)

    with(binding){
        btnShowInfo.setOnClickListener {
            infoGroup.visibility = View.VISIBLE
            btnShowInfo.isEnabled = false
            tvFullName.text = sharedPreferences.getString("Full_Name","")
            tvUserName.text = sharedPreferences.getString("User_Name","")
            tvEmail.text = sharedPreferences.getString("Email","")
            tvPassword.text = sharedPreferences.getString("Pass","")
            tvGender.text = sharedPreferences.getString("Gender","")
        }

        btnHideInfo.setOnClickListener {
            hideInfo()
        }

        var edit = sharedPreferences.edit()
        btnRegister.setOnClickListener {
            edit.putString("Full_Name", txtFullName.text.toString())
            edit.putString("User_Name", txtUsername.text.toString())
            edit.putString("Email", txtEmail.text.toString())
            edit.putString("Pass", txtPass.text.toString())
            edit.putString("Gender", getGender())
            edit.apply()
            Toast.makeText(this@MainActivity,"Data Saved Successfuly",Toast.LENGTH_LONG).show()

        }

        rbFemale.setOnClickListener { rbMale.isChecked = false }
        rbMale.setOnClickListener { rbFemale.isChecked = false }



    }

    }




    fun hideInfo() {
        with(binding){
            btnShowInfo.isEnabled = true
            infoGroup.visibility = View.GONE
        }
    }

    fun getGender(): String {

        with(binding){
            var gender = ""
            if (rbFemale.isChecked) gender = "Female"
            if (rbMale.isChecked) gender = "Male"
            return gender
        }

    }
}