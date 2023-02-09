package com.example.rickandmortytest.view

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
        
        CoroutineScope(Dispatchers.IO).launch {
            request = RequestCharacter()
            request.requestCharacter(
                characterCount..characterCount+Constans.PLUS_CONST.value,
                applicationContext,
                characterAdapter
            )
        }
    }

    fun loadMore(view: View){
        if (characterCount <= Constans.LIMIT_CONST.value){
            characterCount += Constans.PLUS_CONST.value
            CoroutineScope(Dispatchers.IO).launch {
                request.requestCharacter(
                    characterCount..characterCount+Constans.PLUS_CONST.value,
                    applicationContext,
                    characterAdapter
                )
            }
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
}

//val toast = Toast.makeText(applicationContext, "${ConstansString.URL.value}3", Toast.LENGTH_SHORT).show()