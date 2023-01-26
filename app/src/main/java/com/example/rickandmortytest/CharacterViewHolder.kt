package com.example.rickandmortytest

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item.view.*

class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(character: Character){
        with(itemView){
            character.run {
                nameCharacter.text = name
                genderCharacter.text = gender
                locationCharacter.text = location
                statusCharacter.text = status
                if (status == "Alive") {
                    statusCharacter.setBackgroundResource(R.drawable.status_bar_green)
                    statusCharacter.setTextColor(Color.parseColor("#319F16"))
                }
                else if (status == "Dead") {
                    statusCharacter.setBackgroundResource(R.drawable.status_bar_red)
                    statusCharacter.setTextColor(Color.parseColor("#E93800"))
                }
                else if (status == "Unknown") {
                    statusCharacter.setBackgroundResource(R.drawable.status_bar_gray)
                    statusCharacter.setTextColor(Color.parseColor("#A0A0A0"))
                }
                Glide.with(context).load(characterImageUrl).into(imageCharacter)
                imageCharacter.clipToOutline = true

            }
        }
    }
}