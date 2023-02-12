package com.example.rickandmortytest.view

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortytest.R
import com.example.rickandmortytest.request.RequestCharacter
import com.example.rickandmortytest.adapter.CharacterAdapter
import com.example.rickandmortytest.consants.Constans
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var characterAdapter : CharacterAdapter
    private lateinit var request : RequestCharacter
    var characterCount = Constans.ONE_CONST.value
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycleView()
        reqCharacter()
    }

    fun onLoadMoreClick(view: View){
        if (characterCount <= Constans.LIMIT_CONST.value){
            characterCount += Constans.PLUS_CONST.value
            reqCharacter()
        }
    }

    private fun reqCharacter() {

        CoroutineScope(Dispatchers.IO).launch {
            request = RequestCharacter()
            request.requestCharacter(
                characterCount..characterCount+Constans.PLUS_CONST.value,
                applicationContext,
                characterAdapter
            )
        }
    }

    private fun initRecycleView(){
        characterAdapter = CharacterAdapter()
        with(charactersList) {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = characterAdapter
            this.setHasFixedSize(true)
        }
    }

    private fun launchProgressBar(){
        when (btn_loadMore.visibility){
            View.VISIBLE -> {
                btn_loadMore.visibility = View.GONE
                pb_req.visibility = View.VISIBLE
            }
            View.GONE -> {
                btn_loadMore.visibility = View.VISIBLE
                pb_req.visibility = View.GONE

            }
        }
    }
}

//val toast = Toast.makeText(applicationContext, "${ConstansString.URL.value}3", Toast.LENGTH_SHORT).show()