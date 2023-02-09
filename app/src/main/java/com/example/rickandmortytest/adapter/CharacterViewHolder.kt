package com.example.rickandmortytest.adapter

import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortytest.R
import kotlinx.android.synthetic.main.item.view.*

class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(character: Character){
        with(itemView){
            character.run {
                nameCharacter.text = name
                genderCharacter.text = gender
                locationCharacter.text = location
                statusCharacter.text = status
                when (status.toString()){
                    "Alive" -> {
                        statusCharacter.setBackgroundResource(R.drawable.status_bar_green)
                        statusCharacter.setTextColor(Color.parseColor("#319F16"))
                        imageCharacter.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(1f)})
                    }
                    "Dead" -> {
                        statusCharacter.setBackgroundResource(R.drawable.status_bar_red)
                        statusCharacter.setTextColor(Color.parseColor("#E93800"))
                        imageCharacter.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)})
                    }
                    "unknown" -> {
                        statusCharacter.setBackgroundResource(R.drawable.status_bar_gray)
                        statusCharacter.setTextColor(Color.parseColor("#A0A0A0"))
                        imageCharacter.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(1f)})
                    }
                }
                Glide.with(context).load(characterImageUrl).into(imageCharacter)
                imageCharacter.clipToOutline = true




            }
        }
    }
}