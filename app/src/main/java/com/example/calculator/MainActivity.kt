package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tv:TextView?=null
    var lastnumeric:Boolean=false
    var lastdot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv=findViewById(R.id.tvinput)



    }
    fun ondigit(view: View)
    {Toast.makeText(this,"button clicked", Toast.LENGTH_SHORT).show()
      tv?.append((view as Button).text)//view does not have text property so we take view as button first then we use text
        lastnumeric=true
        lastdot=false
    }
    fun onclear(view:View)
    {
        tv?.text=""
    }
    fun ondecimal(view:View)
    {
        if(lastnumeric && !lastdot)
        {
            tv?.append(".")
            lastnumeric=false
            lastdot=true
        }
    }
    fun onoperator(view:View) {
        tv?.text?.let {

            if (lastnumeric && !isoperatoradded(it.toString())) {
                tv?.append((view as Button).text)
                lastnumeric=false
                lastdot=false
            }
        }
    }
fun onequal(view:View)
{
    if(lastnumeric){
        var tvvalue=tv?.text.toString()
        var prefix=""
        try{
            if(tvvalue.startsWith("-"))
            {
                prefix="-"
                tvvalue=tvvalue.substring(1)//we get rid from first entry
            }
            if(tvvalue.contains("-"))
            {

                val splitvalue=tvvalue.split("-")
                var one=splitvalue[0] //99
                var two=splitvalue[1] //1
                if(prefix.isNotEmpty())
                {one= prefix +one}
                var result=one.toDouble() -two.toDouble()
                tv?.text=removezeroafterdot(result.toString())

            }
            else  if(tvvalue.contains("+"))
            {

                val splitvalue=tvvalue.split("+")
                var one=splitvalue[0] //99
                var two=splitvalue[1] //1
                if(prefix.isNotEmpty())
                {one= prefix +one}
                var result=one.toDouble() +two.toDouble()
                tv?.text=removezeroafterdot(result.toString())

            }
            else if(tvvalue.contains("*"))
            {

                val splitvalue=tvvalue.split("*")
                var one=splitvalue[0] //99
                var two=splitvalue[1] //1
                if(prefix.isNotEmpty())
                {one= prefix +one}
                var result=one.toDouble() *two.toDouble()
                tv?.text=removezeroafterdot(result.toString())
            }
            else if(tvvalue.contains("/"))
            {

                val splitvalue=tvvalue.split("/")
                var one=splitvalue[0] //99
                var two=splitvalue[1] //1
                if(prefix.isNotEmpty())
                {one= prefix +one}
                var result=one.toDouble() /two.toDouble()
                tv?.text=removezeroafterdot(result.toString())

            }




        }catch(e:ArithmeticException){
            e.printStackTrace()
        }
    }
}
private fun removezeroafterdot(result1:String):String{
    var value=result1
    if(result1.contains(".0"))
    {
        value=result1.substring(0,result1.length-2)

    }
    return value
}
    private fun isoperatoradded(value:String):Boolean
    {
        return if(value.startsWith("-")){
            false
        }
        else{
            value.contains("/") ||value.contains("*")||value.contains("+")
                    ||value.contains("-")
        }
    }
}