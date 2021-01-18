package tech.coper.sample.activity

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import tech.coper.sample.Profile
import tech.coper.sample.R
import tech.coper.sample.Repository


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        next_activity.setOnClickListener {
            val intent = SecondActivity.createIntent(this@MainActivity)
            startActivity(intent)
        }

        force_store.setOnClickListener {
           lifecycleScope.launchWhenStarted {
               try {
                   repository.delete()
                   repository.store(Profile("Trump","Donald","aaaaaa"))

               } catch (e: Throwable) {
                   e.printStackTrace()
               }
           }
        }
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launchWhenStarted {
            try {
                val res  = repository.getProfile(isForce = false)
                familyname.text = res?.familyName
                givenname.text = res?.givenName

            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}