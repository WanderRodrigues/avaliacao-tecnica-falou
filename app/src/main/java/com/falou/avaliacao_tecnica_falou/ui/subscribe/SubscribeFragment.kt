package com.falou.avaliacao_tecnica_falou.ui.subscribe

import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView.BufferType
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.falou.avaliacao_tecnica_falou.R
import com.falou.avaliacao_tecnica_falou.databinding.FragmentSubscribeBinding


class SubscribeFragment : Fragment() {

    lateinit var binding : FragmentSubscribeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return FragmentSubscribeBinding.inflate(layoutInflater).also {
            binding = it
            it.lifecycleOwner = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()
    }
    private fun initView(){
        val spam_1 = "<font color='#5BD6FD'>unlimited</font>"
        val spam_2 = "<font color='#5BD6FD'>all features</font>"
        val title_subscribe = getString(R.string.title_subscribe, spam_1, spam_2 )
        binding.titleSubscribe.text = Html.fromHtml(title_subscribe)
    }
    private fun initListeners() {

        binding.onCLickSubscribe = View.OnClickListener {
            activity?.finish()
        }

    }


}