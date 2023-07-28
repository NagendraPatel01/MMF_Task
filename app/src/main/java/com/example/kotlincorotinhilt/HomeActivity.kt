package com.example.kotlincorotinhilt

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class HomeActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val email: TextView = findViewById(R.id.text1)
        val phone: TextView = findViewById(R.id.text2)
        val id: TextView = findViewById(R.id.text3)
        val fname: TextView = findViewById(R.id.text4)
        val lname: TextView = findViewById(R.id.text5)
        val img: ImageView = findViewById(R.id.image)

        val bundle = intent.extras

        if (bundle != null){

            id.text = "userid = ${bundle.getInt("userid")}"
            email.text = "Email = ${bundle.getString("useremail")}"
            fname.text = "Name = ${bundle.getString("fname")}"
            lname.text = "LName = ${bundle.getString("lname")}"
            phone.text = "Phone = ${bundle.getString("userphone")}"

            Glide.with(this).load(bundle.getString("image")).centerCrop().into(img)

        }
    }
}