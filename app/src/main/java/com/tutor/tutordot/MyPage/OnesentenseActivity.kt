package com.tutor.tutordot.MyPage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3Client
import com.bumptech.glide.Glide
import com.tutor.tutordot.MyPage.Server.MyPageRequestToServer
import com.tutor.tutordot.MyPage.Server.ProfileEditRequest
import com.tutor.tutordot.MyPage.Server.ProfileEditResponse
import com.tutor.tutordot.R
import com.tutor.tutordot.Startpage.myjwt
import kotlinx.android.synthetic.main.activity_onesentense.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


var newIntro = "수학, 과탐 1등급 만들어드립니다."
var newImage: String = ""

class OnesentenseActivity : AppCompatActivity() {
    val mypageRequestToServer = MyPageRequestToServer

    override fun onCreate(savedInstanceState: Bundle?) {
        //lateinit var f: File

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onesentense)

        val credentialsProvider =
            CognitoCachingCredentialsProvider(
                applicationContext,
                "ap-northeast-1:e4331b6e-34f8-4934-bf13-************",  // Identity Pool ID
                Regions.AP_NORTHEAST_1 // Region
            )

        // 서버 사진 위함. 2차 릴리즈에서 사용
        /*
        val s3: AmazonS3 = AmazonS3Client(credentialsProvider)
        val transferUtility = TransferUtility(s3, applicationContext)

        val observer: TransferObserver = transferUtility.upload(
            MY_BUCKET,  // 업로드 할 버킷 이름
            OBJECT_KEY,  / 버킷에 저장할 파일의 이름
            MY_FILE // 버킷에 저장할 파일
        )
*/

        //새로만든 코드 프로필이미지 클릭 시

        /*imageButton5.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 10)

            // 서버 업로드
            val observer = transferUtility.upload(
                "s3testjm", // 버킷 이름 바꾸기
                f.getName(),
                f
            )
        })
         */*/

        btn_cancel_my_one.setOnClickListener {
            finish()
        }
        btn_save_my_one.setOnClickListener {
            newIntro = et_new_intro.text.toString()
            //서버 연결
            mypageRequestToServer.service.profileEditRequest(
                "${myjwt}",
                ProfileEditRequest(
                    userName = userinfoname,
                    role = userinforole,
                    intro = newIntro,
                    profileUrl = newImage
                )
            ).enqueue(object : Callback<ProfileEditResponse> {
                override fun onFailure(call: Call<ProfileEditResponse>, t: Throwable) {
                    Log.d("통신 실패", "내 프로필 수정 통신 실패${t}")
                }

                override fun onResponse(
                    call: Call<ProfileEditResponse>,
                    response: Response<ProfileEditResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()!!.success) {
                            Log.d("성공", "내 프로필 수정 성공")
                        } else {
                            Log.d("실패", "내 프로필 수정 실패")
                        }
                    }
                }
            })

            //val backIntent = Intent(this@OnesentenseActivity, CalenderActivity::class.java)
            //startActivity(backIntent)
            finish()
        }

        Glide.with(this@OnesentenseActivity).load(userinfopicture1).into(imageButton5)
        textView5.setText(userinfoname)
        textView12.setText(userinforole)

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val selectedImageUri = data.data
            newImage = data.data.toString()
            imageButton5!!.setImageURI(selectedImageUri)
            Glide.with(this@OnesentenseActivity).load(selectedImageUri).into(imageButton5)
            Log.d("image","${newImage}")
        }
    }
}

        /*
        //프로필이미지 클릭 시
        imageButton5.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = MediaStore.Images.Media.CONTENT_TYPE
                startActivityForResult(intent, 11)
            }
        })
    }
*/

/*
    //갤러리에서 이미지 불러온 후 행동
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == 11) {
                val sourceUri = data!!.data
                if (sourceUri != null) {
                    val destinationUri = Uri.fromFile(File(cacheDir, "cropped"))
                    openCropActivity(sourceUri, destinationUri)
                } else {
                    Toast.makeText(this, "사진을 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            } else if (requestCode == UCrop.REQUEST_CROP) {
                val resultUri = UCrop.getOutput(data!!)
                if (resultUri != null) {
                    //초기화
                    imageButton5.setImageDrawable(null)
                    //이미지뷰에 세팅
                    var imageUri = resultUri
                    imageButton5.setImageURI(imageUri)
                    newImage = imageUri.path.toString()
                    //Glide.with(this).load(imageUri).fitCenter().into(imageView)
                } else {
                    Toast.makeText(this, "사진을 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            Toast.makeText(this, "사진을 가져올 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }*/
/*
    private fun openCropActivity(
        sourceUri: Uri,
        destinationUri: Uri
    ) {
        UCrop.of(sourceUri, destinationUri)
            .start(this)
    }

}*/