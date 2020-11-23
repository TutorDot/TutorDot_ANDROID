package com.tutor.tutordot.Startpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.tutor.tutordot.CalenderActivity
import com.tutor.tutordot.R
import com.tutor.tutordot.StartServer.RequestLogin
import com.tutor.tutordot.StartServer.RequestToServer
import com.tutor.tutordot.Startpage.AutoLogin.MySharedPreferences
import com.tutor.tutordot.extention.customEnqueue
import com.tutor.tutordot.extention.showToast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.et_email
import kotlinx.android.synthetic.main.activity_login.et_pw
import kotlinx.android.synthetic.main.activity_login.login_btn_login
import kotlinx.android.synthetic.main.activity_login_for1st.*


var myjwt:String = ""
var role: String = ""
var name: String = ""
var looking: Boolean = false
class LoginFor1stActivity : AppCompatActivity() {
    //서버 이용
    val requestToServer = RequestToServer
    //자동로그인에 필요한 변수
    var autologincheck : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_login_for1st)

        if (MySharedPreferences.islogin) {

            requestToServer.service.requestLogin(
                RequestLogin(
                    email = MySharedPreferences.email,
                    password = MySharedPreferences.password
                )   //로그인 정보를 전달
            ).customEnqueue(
                onError = { showToast("아이디/비밀번호를 확인하세요!") },
                onSuccess = {
                    if (it.success) {
                        /////
                        myjwt = it.data!!.accessToken
                        role = it.data!!.role
                        name = it.data!!.userName
                        Log.d("myjwt", "${myjwt}")
                        Log.d("role", "${role}")
                        /////

                        showToast("로그인 성공")

                        Log.d("롤2","롤2${role}")
                        showToast("자동로그인 되었습니다")

                        val intent = Intent(this, CalenderActivity::class.java)
                        startActivity(intent)
                        finish()


                    }
                })
        }
        login_btn_login.setOnClickListener {
            if (et_email.text.isNullOrBlank() || et_pw.text.isNullOrBlank()) {
                showToast("이메일과 비밀번호를 모두 입력하세요.")
                //Toast.makeText(this, "이메일과 비밀번호를 모두 입력하세요.", Toast.LENGTH_SHORT).show()
            } else {
                //로그인 요청
                requestToServer.service.requestLogin(
                    RequestLogin(
                        email = et_email.text.toString(),
                        password = et_pw.text.toString()
                    )   //로그인 정보를 전달
                ).customEnqueue(
                    onError = { showToast("아이디/비밀번호를 확인하세요!") },
                    onSuccess = {
                        if (it.success) {
                            /////
                            myjwt = it.data!!.accessToken
                            role = it.data!!.role
                            name = it.data!!.userName

                            Log.d("myjwt", "${myjwt}")
                            Log.d("role", "${role}")

                            showToast("로그인 성공")
                            MySharedPreferences.tmpjwt = myjwt
                            MySharedPreferences.email = et_email.text.toString()
                            MySharedPreferences.password = et_pw.text.toString()
                            MySharedPreferences.islogin = true
                            MySharedPreferences.tmprole = role
                            //밑에 아닐듯
                            myjwt = it.data!!.accessToken
                            role = it.data!!.role
                            name = it.data!!.userName
                            Log.d("myjwt여기아닌가", "${myjwt}")
                            val intent = Intent(this@LoginFor1stActivity, CalenderActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            showToast("아이디/비밀번호를 확인하세요!")
                        }
                    }
                )
            }
            val tmp: String? = intent.getStringExtra("check")

            if (tmp.equals("1")) {
                val email = intent.getStringExtra("email")
                val password = intent.getStringExtra("password")
                et_email.setText(email)
                et_pw.setText(password)
            }
        }
        tv_look_around.setOnClickListener {
            requestToServer.service.requestLogin(
                RequestLogin(
                    email = "around",
                    password = "around"
                )   //로그인 정보를 전달
            ).customEnqueue(
                onError = { showToast("아이디/비밀번호를 확인하세요!") },
                onSuccess = {
                    if (it.success) {

                        myjwt = it.data!!.accessToken
                        role = it.data!!.role
                        name = it.data!!.userName

                        Log.d("myjwt", "${myjwt}")
                        Log.d("role", "${role}")
                        looking = true


                        showToast("둘러보기를 시작합니다.")
                        val intent = Intent(this@LoginFor1stActivity, CalenderActivity::class.java)

                        startActivity(intent)
                        finish()
                    }
                }
            )


        }
        tv_signup2.setOnClickListener {
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    val intent = Intent(this@LoginFor1stActivity, SignUpActivity::class.java)
                    startActivity(intent)
                }
            }
        }


    }
}