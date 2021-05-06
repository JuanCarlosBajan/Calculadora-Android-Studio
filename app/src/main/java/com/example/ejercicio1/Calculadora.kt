package com.example.ejercicio1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import java.util.*

class Calculadora : AppCompatActivity() {

    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var btn5: Button
    lateinit var btn6: Button
    lateinit var btn7: Button
    lateinit var btn8: Button
    lateinit var btn9: Button
    lateinit var btn0: Button
    lateinit var btnmas : Button
    lateinit var btnmenos: Button
    lateinit var btnpor: Button
    lateinit var btndividido: Button
    lateinit var btnigual: Button
    lateinit var btndel : Button
    lateinit var textvalue : TextView
    lateinit var  fullexp : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        var stack = Stack<String>()
        var number = ""
        var recentequal = false

        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btnmas = findViewById(R.id.btnmas)
        btnmenos = findViewById(R.id.btnmenos)
        btnpor = findViewById(R.id.btnpor)
        btndividido = findViewById(R.id.btndividido)
        btndel = findViewById(R.id.btndel)
        btnigual = findViewById(R.id.btnigual)
        textvalue = findViewById(R.id.value)
        fullexp = findViewById(R.id.fullexp)



        btn0.setOnClickListener {
            if(recentequal){
                number = ""
                recentequal = false
            }

            number += "0"
            textvalue.text = number
        }
        btn1.setOnClickListener{
            if(recentequal){
                number = ""
                recentequal = false
            }
            number += "1"
            textvalue.text = number
        }
        btn2.setOnClickListener{
            if(recentequal){
                number = ""
                recentequal = false
            }
            number += "2"
            textvalue.text = number
        }
        btn3.setOnClickListener{
            if(recentequal){
                number = ""
                recentequal = false
            }
            number += "3"
            textvalue.text = number
        }
        btn4.setOnClickListener{
            if(recentequal){
                number = ""
                recentequal = false
            }
            number += "4"
            textvalue.text = number
        }
        btn5.setOnClickListener{
            if(recentequal){
                number = ""
                recentequal = false
            }
            number += "5"
            textvalue.text = number
        }
        btn6.setOnClickListener{
            if(recentequal){
                number = ""
                recentequal = false
            }
            number += "6"
            textvalue.text = number
        }
        btn7.setOnClickListener{
            if(recentequal){
                number = ""
                recentequal = false
            }
            number += "7"
            textvalue.text = number
        }
        btn8.setOnClickListener{
            if(recentequal){
                number = ""
                recentequal = false
            }
            number += "8"
            textvalue.text = number
        }
        btn9.setOnClickListener{
            if(recentequal){
                number = ""
                recentequal = false
            }
            number += "9"
            textvalue.text = number
        }
        btnmas.setOnClickListener{
            if (stack.size == 0 || !stack.peek().intOrString() && stack.get(stack.size -2).intOrString() && number != ""){
            stack.add(number)
            stack.add("+")
            number = ""
            textvalue.text = number
            fullexp.text = unify(stack)}else if (!stack.peek().intOrString()){
                stack.pop()
                stack.add("+")
                fullexp.text = unify(stack)}
        }
        btnmenos.setOnClickListener{
            if (stack.size == 0 || !stack.peek().intOrString() && stack.get(stack.size -2).intOrString() && number != ""){
            stack.add(number)
            stack.add("-")
            number = ""
            textvalue.text = number
            fullexp.text = unify(stack)} else if (!stack.peek().intOrString()){
                stack.pop()
                stack.add("-")}
        }
        btnpor.setOnClickListener{
            if (stack.size == 0 || !stack.peek().intOrString() && stack.get(stack.size -2).intOrString() && number != ""){
            stack.add(number)
            stack.add("*")
            number = ""
            textvalue.text = number
            fullexp.text = unify(stack)} else if (!stack.peek().intOrString()){
                stack.pop()
                stack.add("*")}
        }
        btndividido.setOnClickListener{
            if (stack.size == 0 || !stack.peek().intOrString() && stack.get(stack.size -2).intOrString() && number != ""){
            stack.add(number)
            stack.add("/")
            number = ""
            textvalue.text = number
            fullexp.text = unify(stack)} else if (!stack.peek().intOrString()){
                stack.pop()
                stack.add("/")}
        }
        btndel.setOnClickListener{
            if (number != ""){
                number = number.substring(0,number.length-1)
                textvalue.text = number
            }
        }
        btnigual.setOnClickListener{
            if (number == ""){
                stack.push("0")
            } else{stack.push(number)}
            number = evaluate(infixToPostfix(stack)).toString()
            fullexp.text = ""
            textvalue.text = number
            stack = Stack<String>()
            recentequal = true
        }
    }

    private fun unify(s: Stack<String>): String {

        var expression = ""

        for (i in 1..s.size){
            expression += s.get(i-1)
            expression += " "
        }

        return expression
    }

    private fun String.intOrString(): Boolean{
        return when(toIntOrNull()) {
            null -> false
            else -> true
        }
    }

    private fun Prec(ch:String): Int {
        return when (ch){
            "+"->1
            "-"->1
            "*"->2
            "/"->2
            else -> -1
        }
    }

    private fun infixToPostfix(s:Stack<String>):Stack<String>{
        var res = Stack<String>()
        var opts = Stack<String>()

        for (i in 0 until s.size) {
            if(s.get(i).intOrString()){
                res.push(s.get(i))
            } else {
                while (!opts.isEmpty() && Prec(s.get(i)) <= Prec(opts.peek())){
                    res.push(opts.pop())
                }
                opts.push(s.get(i))
            }
        }

        while (!opts.isEmpty()){
            res.push(opts.pop())
        }
        return res
    }

    private fun evaluate(s:Stack<String>):Int{
        var st = Stack<String>()
        for (i in 0 until s.size) {
            if(s.get(i).intOrString()){
                st.push(s.get(i))
            } else{
                var x = st.pop().toInt()
                var y = st.pop().toInt()

                when(s.get(i)){
                    "+"->st.push((y+x).toString())
                    "-"->st.push((y-x).toString())
                    "*"->st.push((y*x).toString())
                    "/"->st.push((y/x).toString())
                }
            }
        }
        return st.pop().toInt()
    }

}