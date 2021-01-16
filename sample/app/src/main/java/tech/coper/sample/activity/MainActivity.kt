package tech.coper.sample.activity

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import tech.coper.sample.R
import tech.coper.sample.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.profile.filterNotNull().collect {
                familyname.text = it.familyName
                givenname.text = it.givenName
            }
        }

        next_activity.setOnClickListener {
            val intent = SecondActivity.createIntent(this@MainActivity)
            startActivity(intent)
        }

        force_store.setOnClickListener {
            viewModel.forceStore()
        }
    }

    override fun onResume() {
        super.onResume()
        Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)
        viewModel.onResume()
    }
}