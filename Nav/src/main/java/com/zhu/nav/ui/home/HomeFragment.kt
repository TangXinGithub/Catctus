package com.zhu.nav.ui.home

import actsJSON
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.norbsoft.typefacehelper.TypefaceHelper
import com.ramotion.circlemenu.CircleMenuView
import com.zhu.daomengkj.Global
import com.zhu.nav.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : androidx.fragment.app.Fragment() {

    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
/*使用root 获得 不然null */
        root.daomeng_login.setOnClickListener {
            edit_account.text?.let {
                daomeng_pass?.let {
                    if (edit_account.text.isNotBlank() && daomeng_pass.text.isNotBlank()) {
                        val sharedPreference =
                          Global.context.getSharedPreferences("daomengKJ", Context.MODE_PRIVATE)
                        val editor = sharedPreference.edit()
                        editor.putString("username", edit_account.text.toString())
                        editor.putString("password", daomeng_pass.text.toString())
                        editor.apply()
                        Toast.makeText(Global.context, "保存成功！用户名${edit_account.text}和密码${daomeng_pass.text}", Toast.LENGTH_SHORT).show()
                        Toast.makeText(Global.context,"登录中！  保存中",Toast.LENGTH_SHORT).show()
                        com.zhu.daomengkj.App(Global.context, MutableLiveData<actsJSON>()).login()//登录保存token
                    } else Toast.makeText(
                      Global.context,
                        "输入为blank:`${daomeng_login}+${daomeng_pass}`",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }

        val sharedPreference =
          Global.context.getSharedPreferences("daomengKJ", Context.MODE_PRIVATE)
        sharedPreference?.let {
            root.edit_account.setText(it.getString("username",""))
            root.daomeng_pass.setText(it.getString("password",""))
        }

        val menu = root.circle_menu
        menu.eventListener = object : CircleMenuView.EventListener() {
            /*    override fun onMenuOpenAnimationStart(view: CircleMenuView) {
                    Log.d("D", "onMenuOpenAnimationStart")
                }

                override fun onMenuOpenAnimationEnd(view: CircleMenuView) {
                    Log.d("D", "onMenuOpenAnimationEnd")
                }

                override fun onMenuCloseAnimationStart(view: CircleMenuView) {
                    Log.d("D", "onMenuCloseAnimationStart")
                }

                override fun onMenuCloseAnimationEnd(view: CircleMenuView) {
                    Log.d("D", "onMenuCloseAnimationEnd")
                }*/

            override fun onButtonClickAnimationStart(view: CircleMenuView, index: Int) {
                Log.d("D", "onButtonClickAnimationStart| index: $index")
            }

            override fun onButtonClickAnimationEnd(view: CircleMenuView, index: Int) {
                Log.d("D", "onButtonClickAnimationEnd| index: $index")
            }
        }



        if (Global.typeface) TypefaceHelper.typeface(root)//应用字体


        return root
    }
}