package com.stackapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.stackapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: StackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /*
        Tasks
        1. ist fragment in expanded view
        2. on click of 2nd collapsed view ->
            slide up and expand with animation
            collapse 1st view on animation end of 2nd view
            show 3rd collapsed view
        3. on click of 3rd collapsed view
            slide up and expand wt=ith animation
            collapse 2nd view on animation end of 3rd view
            show 4th collapsed view
        4. on Click 3rd expanded view
            3rd view has to collapse
            2nd view to show loading view till 3rd view animation end
            2nd view to show expanded state on animation end
        5. All view are expanded and click on 2nd view
            animate 3rd view and collapse
         */
    }
}