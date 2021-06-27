package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
     var player =true
     var count_turn=0;
     var boardstatus=Array(3){
        IntArray(3)
      }
    lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board= arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )
        for(i in board) {
            for (button in i) {
                button.setOnClickListener(this)
            }
        }
        initializeboardstatus()
        resetbtn.setOnClickListener {
           player=true
            count_turn=0
            initializeboardstatus()
        }
    }

    private fun initializeboardstatus() {
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                boardstatus[i][j]=-1
            }
        }
        for(i in board)
        {
            for(button in i)
            {
                button.isEnabled=true
                button.text=""
            }
        }
        updatedisplay("Player 1 Turn")
    }

    override fun onClick(v: View) {
        when(v.id)
        {
            R.id.button1->{
                updatevalue(row=0,coloumn=0, player = player)
            }
            R.id.button2->{
                updatevalue(row=0,coloumn=1,player = player)
            }
            R.id.button3->{
                updatevalue(row=0,coloumn=2,player = player)
            }
            R.id.button4->{
                updatevalue(row=1,coloumn=0,player = player)
            }
            R.id.button5->{
                updatevalue(row=1,coloumn=1,player = player)
            }
            R.id.button6->{
                updatevalue(row=1,coloumn=2,player = player)
            }
            R.id.button7->{
                updatevalue(row=2,coloumn=0,player = player)
            }
            R.id.button8->{
                updatevalue(row=2,coloumn=1,player = player)
            }
            R.id.button9->{
                updatevalue(row=2,coloumn=2,player = player)
            }
        }
        player=!player
        count_turn++
        if(player)
        {
            updatedisplay("Player 1 Turn")
        }
        else
        {
            updatedisplay("Player 2 Turn")
        }
        if(count_turn==9)
        {
            updatedisplay("Game Draw")
        }
        checkwinner()
    }

    private fun checkwinner() {
        //horizontal
        for(i in 0..2)
        {
            if(boardstatus[i][0]==boardstatus[i][1]&&boardstatus[i][0]==boardstatus[i][2])
            {
                if(boardstatus[i][0]==1)
                {
                    updatedisplay("Player 1 Winner")
                    break
                }
                else if(boardstatus[i][0]==0)
                {
                    updatedisplay("Player 2 Winner")
                    break
                }
            }
        }
        //vertical
        for(i in 0..2)
        {
            if(boardstatus[0][i]==boardstatus[1][i]&&boardstatus[0][i]==boardstatus[2][i])
            {
                if(boardstatus[0][i]==1)
                {
                    updatedisplay("Player 1 Winner")
                    break
                }
                else if(boardstatus[0][i]==0)
                {
                    updatedisplay("Player 2 Winner")
                    break
                }
            }
        }
        //diagonal 1
        if(boardstatus[0][0]==boardstatus[1][1]&&boardstatus[0][0]==boardstatus[2][2])
        {
            if(boardstatus[0][0]==1)
            {
                updatedisplay("Player 1 Winner")
            }
            else if(boardstatus[0][0]==0)
            {
                updatedisplay("Player 2 Winner")
            }
        }
        //diagonal 2
        if(boardstatus[0][2]==boardstatus[1][1]&&boardstatus[0][2]==boardstatus[2][0])
        {
            if(boardstatus[1][1]==1)
            {
                updatedisplay("Player 1 Winner")
            }
            else if(boardstatus[1][1]==0)
            {
                updatedisplay("Player 2 Winner")
            }
        }
    }

    private fun updatedisplay(s: String) {
        disbtn.text=s
        if(s.contains("Winner"))
        {
            disablebutton()
        }

    }

    private fun disablebutton() {
        for(i in board) {
            for (button in i) {
                button.isEnabled=false
            }
        }
    }

    private fun updatevalue(row: Int, coloumn: Int, player: Boolean) {
             var text = if(player) "x" else "0"
             var value= if(player) 1 else 0
             board[row][coloumn].apply {
                 isEnabled=false
                 setText(text)
             }
        boardstatus[row][coloumn]=value
    }
}
