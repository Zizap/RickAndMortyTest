package com.example.rickandmortytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import kotlin.concurrent.thread

private lateinit var charaterAdapter : CharacterAdapter

var a = 1
var b = 100
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycleView()
        requesCharacter(1..100)
    }

     private fun requesCharacter(count:IntRange)  {
         for (i in count){
             val url = "https://rickandmortyapi.com/api/character/$i"
             val queue = Volley.newRequestQueue(applicationContext)
             var request = StringRequest(
                 Request.Method.GET,
                 url, { result -> parseRequest(result) }, { error -> Log.d("MyLog", "Error: $error") }
             )
             queue.add(request)
         }
    }

      private fun parseRequest(result:String){
          val jsonObject = JSONObject(result)
          charaterAdapter.addCharacter(
              Character(
                  name = jsonObject.getString("name").toString(),
                  gender = jsonObject.getString("gender").toString(),
                  location = jsonObject.getJSONObject("location").getString("name").toString(),
                  status = jsonObject.getString("status").toString(),
                  characterImageUrl = jsonObject.getString("image").toString(),
              )
          )
    }

    fun loadMore(view: View){
        // плохо с корутинами, сделал кнопку
        if (b <= 800){
            a += 100
            b += 100
            requesCharacter(a..b)
        }
    }


    private fun initRecycleView(){
        charaterAdapter = CharacterAdapter()
        with(charactersList) {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = charaterAdapter
            this.setHasFixedSize(true)

        }
    }

}