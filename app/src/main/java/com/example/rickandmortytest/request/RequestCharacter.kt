package com.example.rickandmortytest.request

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.rickandmortytest.adapter.Character
import com.example.rickandmortytest.adapter.CharacterAdapter
import com.example.rickandmortytest.consants.ConstansString
import org.json.JSONObject

class RequestCharacter {

     fun requestCharacter(count:IntRange, context: Context,characterAdapter: CharacterAdapter)  {
        for (i in count){
            val url = "${ConstansString.URL.value}$i"
            val queue = Volley.newRequestQueue(context)
            var request = StringRequest(
                Request.Method.GET,
                url, { result -> parseRequest(result, characterAdapter) }, { error -> Log.d("MyLog", "Error: $error") }
            )
            queue.add(request)
        }
    }

     private fun parseRequest(result:String, characterAdapter: CharacterAdapter){
        val jsonObject = JSONObject(result)
        characterAdapter.addCharacter(
            Character(
                name = jsonObject.getString("name").toString(),
                gender = jsonObject.getString("gender").toString(),
                location = jsonObject.getJSONObject("location").getString("name").toString(),
                status = jsonObject.getString("status").toString(),
                characterImageUrl = jsonObject.getString("image").toString()
            )
        )
    }
}