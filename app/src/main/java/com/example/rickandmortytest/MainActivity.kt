package com.example.rickandmortytest

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

private lateinit var characterAdapter : CharacterAdapter

var a = 1
var b = 50
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycleView()
        CoroutineScope(Dispatchers.IO).launch {
            requestCharacter(a..b)
        }

    }

     private fun requestCharacter(count:IntRange)  {
         try {
             for (i in count){
                 val url = "https://rickandmortyapi.com/api/character/$i"
                 val queue = Volley.newRequestQueue(applicationContext)
                 var request = StringRequest(
                     Request.Method.GET,
                     url, { result -> parseRequest(result) }, { error -> Log.d("MyLog", "Error: $error") }
                 )
                 queue.add(request)
             }
         } catch (e:Exception){ }
    }

      private fun parseRequest(result:String){
          
          val jsonObject = JSONObject(result)
          try {
              characterAdapter.addCharacter(
                  Character(
                      name = jsonObject.getString("name").toString(),
                      gender = jsonObject.getString("gender").toString(),
                      location = jsonObject.getJSONObject("location").getString("name").toString(),
                      status = jsonObject.getString("status").toString(),
                      characterImageUrl = jsonObject.getString("image").toString(),
                  )
              )
          } catch (e:Exception){ }
    }

    fun loadMore(view: View){
        if (b <= 800){
            a += 50
            b += 50
            CoroutineScope(Dispatchers.IO).launch {
                requestCharacter(a..b)
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