package tech.coper.sample.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.familyname
import kotlinx.android.synthetic.main.activity_main.givenname
import kotlinx.android.synthetic.main.activity_second.*
import tech.coper.sample.Profile
import tech.coper.sample.R
import tech.coper.sample.Repository


class SecondActivity : AppCompatActivity(R.layout.activity_second) {

    private val repository = Repository()

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

        button_store.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                try {
                    repository.delete()
                    repository.store(Profile("Biden","Joe","aaaaaa"))

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