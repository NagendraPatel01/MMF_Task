package com.example.kotlincorotinhilt

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlincorotinhilt.result.Networkresult
import com.example.kotlincorotinhilt.viewmodel.MyViewmoel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var myViewmoelq: MyViewmoel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailEditText: EditText = findViewById(R.id.editemail)
        val editpassword: EditText = findViewById(R.id.editpassword)
        val btn:Button=findViewById(R.id.btn)
        val p = ProgressDialog(this)

        myViewmoelq=ViewModelProvider(this).get(MyViewmoel::class.java)


       btn.setOnClickListener {

           val email = emailEditText.text.toString().trim()
           val password = editpassword.text.toString().trim()

           if (email.isEmpty()){
               Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
           }else if (password.isEmpty()){

               Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
           }else if (password.length!=8){
               Toast.makeText(this, "Enter passwprd 8 charater or more", Toast.LENGTH_SHORT).show()
           }
           else{
               myViewmoelq.Login(email,password)
               myViewmoelq.liveData.observe(this){

                   when(it){

                       is Networkresult.Loading->{
                           p.setMessage("Loading ...")
                           p.setCancelable(false)
                           p.show()
                       }

                       is Networkresult.Success->{

                           if (it.data!!.status){
                               p.cancel()
                               Toast.makeText(this, it.data.message, Toast.LENGTH_SHORT).show()
                               val intent = Intent(this, HomeActivity::class.java)

                               val bundle = Bundle()
                               bundle.putString("fname", it.data.record.firstName)
                               bundle.putString("lname", it.data.record.lastName)
                               bundle.putString("useremail", it.data.record.email)
                               bundle.putString("userphone", it.data.record.phoneNo)
                               bundle.putInt("userid", it.data.record.id)
                               bundle.putString("image", it.data.record.profileImg)
                               intent.putExtras(bundle)
                               startActivity(intent)

                           }else{
                               Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                           }
                       }
                       is Networkresult.Error->{
                           Log.d("error", "onCreate: "+it.message)

                       }
                   }
               }
           }

       }

    }
}