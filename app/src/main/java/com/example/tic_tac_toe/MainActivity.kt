package com.example.tic_tac_toe

import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    //1st create the variables
    //2nd step is to assign to the views
    lateinit var b1:Button
    lateinit var b2:Button
    lateinit var b3:Button
    lateinit var b4:Button
    lateinit var b5:Button
    lateinit var b6:Button
    lateinit var b7:Button
    lateinit var b8:Button
    lateinit var b9:Button
    lateinit var tv_text:TextView


    private var player1 = 0       //x
    var player2 = 1       //O
    var activePlayer = player1
    lateinit var filledPos : IntArray
    var gameActive = true

//    var gameActive = true

    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_text = findViewById(R.id.player_turn_text)
        b1 = findViewById(R.id.t1)
        b2 = findViewById(R.id.t2)
        b3 = findViewById(R.id.t3)
        b4 = findViewById(R.id.t4)
        b5 = findViewById(R.id.t5)
        b6 = findViewById(R.id.t6)
        b7 = findViewById(R.id.t7)
        b8 = findViewById(R.id.t8)
        b9 = findViewById(R.id.t9)
        filledPos = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)


    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun PlayerMove(view: View){

        if (!gameActive)
            return

        var btnClicked = findViewById<Button>(view.id)
//        btnClicked.text = "o"
        var ClickedTag = Integer.parseInt(btnClicked.tag.toString())
        if(filledPos[ClickedTag]!=-1)
            return

        filledPos[ClickedTag] = activePlayer

        if(activePlayer==player1){
            btnClicked.text = "o"
            activePlayer = player2
            tv_text.text = "Player 2 Turn"
            btnClicked.setTextColor(Color.BLACK)
            btnClicked.backgroundTintList = getColorStateList(R.color.my_color)

        }else{
            btnClicked.text = "x"
            activePlayer = player1
            tv_text.text = "Player 1 Turn"
            btnClicked.setTextColor(Color.WHITE)
            btnClicked.backgroundTintList = getColorStateList(R.color.black)
        }
        playerWon()


    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun playerWon(){
        var winsPos = arrayOf(intArrayOf(0,1,2), intArrayOf(3,4,5), intArrayOf(6,7,8),//Horizontal
                              intArrayOf(0,3,6), intArrayOf(1,4,7), intArrayOf(2,5,8),//vertical
                              intArrayOf(0,4,8), intArrayOf(2,4,6) //diagonal
        )
        for(i in winsPos.indices){
            var _val0 = winsPos[i][0]
            var _val1 = winsPos[i][1]
            var _val2 = winsPos[i][2]

            if(filledPos[_val0] == filledPos[_val1] && filledPos[_val1]==filledPos[_val2]) {
                if (filledPos[_val0] != -1) {
                    gameActive = false
    //                gameActive = false
                    if (filledPos[_val0] == player1) {
                        gameStatus("Player 1 is the winner")
//                        tv_text.text = "Player 1 is the winner"
                    } else {
                        gameStatus("Player 2 is the Winner")
//                        tv_text.text = "Player 2 is the Winner"
                    }
                    return
                }
            }

        }
        onDraw()


    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun onDraw(){
        var filledPlaces = 0
        for (i in filledPos.indices){
            if(filledPos[i]==-1){
                filledPlaces++
            }
        }
        if(filledPlaces==0){
            gameStatus("Match Draw")
        }
        //
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun gameStatus(s: String) {
        AlertDialog.Builder(this)
            .setTitle("Game Over")
            .setMessage(s)
            .setPositiveButton("Restart",DialogInterface.OnClickListener { dialog, which -> restart() })
            .show()
    }
    //implement the reset functionality by implementing the alert dialog
    @RequiresApi(Build.VERSION_CODES.M)
    private fun restart(){
        filledPos = intArrayOf(-1,-1,-1,-1,-1,-1,-1,-1,-1)
        activePlayer = player1
        gameActive = true
        tv_text.text = "Player 1 Turn"
        b1.text = ""
        b2.text = ""
        b3.text = ""
        b4.text = ""
        b5.text = ""
        b6.text = ""
        b7.text = ""
        b8.text = ""
        b9.text = ""
        b1.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b2.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b3.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b4.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b5.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b6.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b7.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b8.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)
        b9.backgroundTintList = getColorStateList(com.google.android.material.R.color.design_default_color_primary)


    }


}