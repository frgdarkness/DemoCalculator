package com.example.calculatordemo1

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.WindowManager
import android.view.inputmethod.BaseInputConnection
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), KeyboardFragment.OnFragmentInteractionListener{


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showKeyboard()
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        edtCalNow.requestFocus()
        edtCalNow.showSoftInputOnFocus = false
    }

    fun showKeyboard(){
        var ft = supportFragmentManager.beginTransaction()
        var fragment = KeyboardFragment()
        ft.replace(R.id.frameInput,fragment,"KEYBOARD")
        ft.commit()
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClickButton(str: String) {
        //var str: String = txtCalNow.text.toString()
        //txtCalNow.setText(str)
        when(str){
            "b"->{
                var textFieldInputConnection = BaseInputConnection(edtCalNow, true)
                textFieldInputConnection.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_DEL))
            }

            "d"->{
                edtCalNow.setText("")
                txtCalPrev.setText("")
            }

            "="->{
                var str1 = edtCalNow.text.toString()
                var str2:String =""
                // lay chuoi tu EditText nen can doi ki tu ',' thanh '.' (de khi tinh ep kieu Double ko bi loi)
                for(i in str1)
                    if(i==',')
                        str2 += '.'
                    else
                        str2 += i
                //Toast.makeText(this,str2,Toast.LENGTH_LONG).show()

                if(checkError(str1))//kiem tra format
                    getResult(str2)
                else
                    edtCalNow.setText("Error")
            }
            else->{
                edtCalNow.append(str)
            }
        }
    }

    fun getResult(s: String){
        var checkNegative = false   //kiem tra so am
        var str:String = ""

        if(s[0]=='-') {
            checkNegative = true
            str = s.substring(1,s.length)
        }else
            str = s

        var listNum = ArrayList<String>()   //luu cac so
        var listOpe = ArrayList<Char>()     //luu cac phep toan
        var pos = 0;                        //luu vi tri cua ki tu dau tien cua 1 so trong chuoi str
        var checkDouble = false             //kiem tra co dung so double ko
        for(i in str.indices){
            if(str[i]=='+' || str[i]=='-' || str[i]=='*' || str[i]=='/' || str[i]=='%') {
                listOpe.add(str[i])
                var temp = str.substring(pos,i)
                listNum.add(temp)
                pos=i+1                     //set lai vi tri ki tu dau cua so moi
            }
        }
        if(checkNegative)
            listOpe.add(0,'-')
        else
            listOpe.add(0,'+')
        listNum.add(str.substring(pos,str.length))//so cuoi cung trong str
        //Toast.makeText(this,"Num: "+listNum.toString()+" - Operator: "+listOpe.toString(),Toast.LENGTH_SHORT).show()
        calcu(listNum,listOpe)
        Log.d("Demo","call calcu1")
    }

    fun getSum(a: Double, b:Double):Double{return a+b}
    fun getMinus(a: Double, b:Double):Double{return a-b}
    fun getDivice(a: Double, b:Double):Double{return a/b}
    fun getMulti(a: Double, b:Double)=a*b
    fun getMod(a: Double, b:Double)=a%b

    fun calcu(listNum: ArrayList<String>, listOpe: ArrayList<Char>){
        var df:DecimalFormat = DecimalFormat("###.####")
        var listNum1: ArrayList<Double> = ArrayList()
        for(i in listNum){
            listNum1.add(i.toDouble())
        }
        var listOpe1:ArrayList<Char> = ArrayList()
        listOpe1.addAll(listOpe)
        //Toast.makeText(this,listNum1.toString(),Toast.LENGTH_LONG).show()

        Log.d("Demo",listNum1.toString())
        //var check=true
        while(true){
            var check=1
            for(i in listOpe1.indices){
                if(listOpe1[i]=='*' || listOpe1[i]=='/' || listOpe1[i]=='%'){
                    check=0
                    var temp:Double=0.0
                    if(listOpe1[i]=='*')
                        temp = getMulti(listNum1[i-1],listNum1[i])
                    if(listOpe1[i]=='/')
                        temp = getDivice(listNum1[i-1],listNum1[i])
                    if(listOpe1[i]=='%')
                        temp = getMod(listNum1[i-1],listNum1[i])
                    listNum1.removeAt(i-1)
                    listNum1.removeAt(i-1)
                    listNum1.add(i-1,temp)
                    listOpe1.removeAt(i)
                    break
                }
            }
            if(check==1) break
        }
        //txtMain1.text = listNum1.toString()+" - "+listOpe1.toString()
        //Toast.makeText(this,listNum1.toString(),Toast.LENGTH_LONG).show()
        var result:Double = 0.0
        for(i in listNum1.indices){
            if(listOpe1[i]=='+')
                result += listNum1[i]
            else
                result -= listNum1[i]
        }
        //txtMain2.text = "kq: "+result
        txtCalPrev.text = edtCalNow.text
        edtCalNow.setText(""+df.format(result))
    }

    fun checkError(str: String):Boolean{
        var listMath = listOf('+','-','*','/','%')
        for(i in str.indices){
            if(str[i] in listMath && str[i+1] in listMath )
                return false
        }
        return true
    }

}
