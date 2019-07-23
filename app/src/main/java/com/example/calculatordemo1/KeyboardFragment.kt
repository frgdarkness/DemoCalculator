package com.example.calculatordemo1

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_keyboard.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [KeyboardFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [KeyboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class KeyboardFragment : Fragment() , View.OnClickListener {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    //lateinit var btnDel:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_keyboard, container, false)
        // Inflate the layout for this fragment

        var btnNum1 = view.findViewById<Button>(R.id.btnNum1)
        var btnNum2 = view.findViewById<Button>(R.id.btnNum2)
        var btnNum3 = view.findViewById<Button>(R.id.btnNum3)
        var btnNum4 = view.findViewById<Button>(R.id.btnNum4)
        var btnNum5 = view.findViewById<Button>(R.id.btnNum5)
        var btnNum6 = view.findViewById<Button>(R.id.btnNum6)
        var btnNum7 = view.findViewById<Button>(R.id.btnNum7)
        var btnNum8 = view.findViewById<Button>(R.id.btnNum8)
        var btnNum9 = view.findViewById<Button>(R.id.btnNum9)
        var btnNum0 = view.findViewById<Button>(R.id.btnNum0)
        var btnDevice = view.findViewById<Button>(R.id.btnDevice)
        var btnMulti = view.findViewById<Button>(R.id.btnMulti)
        var btnMinus = view.findViewById<Button>(R.id.btnMinus)
        var btnSum = view.findViewById<Button>(R.id.btnSum)
        var btnDel = view.findViewById<Button>(R.id.btnDelete)
        var btnDot = view.findViewById<Button>(R.id.btnDot)
        var btnBackspace = view.findViewById<Button>(R.id.btnBackspace)
        var btnMod = view.findViewById<Button>(R.id.btnMod)
        var btnResult = view.findViewById<Button>(R.id.btnResult)

        btnNum1.setOnClickListener(View.OnClickListener { listener?.onClickButton("1")})
        btnNum2.setOnClickListener(View.OnClickListener { listener?.onClickButton("2") })
        btnNum3.setOnClickListener(View.OnClickListener { listener?.onClickButton("3") })
        btnNum4.setOnClickListener(View.OnClickListener { listener?.onClickButton("4") })
        btnNum5.setOnClickListener(View.OnClickListener { listener?.onClickButton("5") })
        btnNum6.setOnClickListener(View.OnClickListener { listener?.onClickButton("6") })
        btnNum7.setOnClickListener(View.OnClickListener { listener?.onClickButton("7") })
        btnNum8.setOnClickListener(View.OnClickListener { listener?.onClickButton("8") })
        btnNum9.setOnClickListener(View.OnClickListener { listener?.onClickButton("9") })
        btnNum0.setOnClickListener(View.OnClickListener { listener?.onClickButton("0") })
        btnDevice.setOnClickListener(View.OnClickListener { listener?.onClickButton("/") })
        btnMulti.setOnClickListener(View.OnClickListener { listener?.onClickButton("*") })
        btnMinus.setOnClickListener(View.OnClickListener { listener?.onClickButton("-") })
        btnSum.setOnClickListener(View.OnClickListener { listener?.onClickButton("+") })
        btnDot.setOnClickListener(View.OnClickListener { listener?.onClickButton(".") })
        //btnLeftBracket.setOnClickListener(View.OnClickListener { setEventClick('(') })
        //btnRightBracket.setOnClickListener(View.OnClickListener { setEventClick(')') })
        btnMod.setOnClickListener(View.OnClickListener { listener?.onClickButton("%") })
        btnDel.setOnClickListener(View.OnClickListener { listener?.onClickButton("d") })
        btnBackspace.setOnClickListener(View.OnClickListener { listener?.onClickButton("b") })
        btnResult.setOnClickListener(View.OnClickListener { listener?.onClickButton("=") })


        return view
    }

    //fun sum()

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */

    override fun onClick(p0: View?) {
    }

//    fun setEventClick(c: Char){
//        if(c=='d') {
//            if (btnDel.text == "AC")
//                listener?.onClickButton("dAC")
//            else
//                listener?.onClickButton("dCE")
//        }else if(c=='=')
//            btnDel.text = "AC"
//        else
//            btnDel.text = "CE"
//        if(c!='d')
//            listener?.onClickButton(c+"")
//    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
        fun onClickButton(str: String)
    }


}
