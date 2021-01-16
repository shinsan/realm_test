package tech.coper.sample.activity

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.familyname
import kotlinx.android.synthetic.main.activity_main.givenname
import kotlinx.android.synthetic.main.activity_secound.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import tech.coper.sample.R
import tech.coper.sample.viewmodel.SecondViewModel


class SecondActivity : AppCompatActivity(R.layout.activity_secound) {

    val viewModel = SecondViewModel()

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(
                    context,
                    SecondActivity::class.java
            )
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.profile.filterNotNull().collect {
                familyname.text = it.familyName
                givenname.text = it.givenName
            }
        }


        button_next.setOnClickListener {
            val intent = ThirdActivity.createIntent(this@SecondActivity)
            startActivity(intent)
        }

        button_renew.setOnClickListener {
            viewModel.renew()
        }

    }

    override fun onResume() {
        super.onResume()
        Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)
        viewModel.onResume()
    }
}