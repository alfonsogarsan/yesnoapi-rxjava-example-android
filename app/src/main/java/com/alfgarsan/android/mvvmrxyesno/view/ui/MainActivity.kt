package com.alfgarsan.android.mvvmrxyesno.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alfgarsan.android.mvvmrxyesno.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.container,
                        AnswerFragment.newInstance()
                    )
                    .commitNow()
        }
    }
}
