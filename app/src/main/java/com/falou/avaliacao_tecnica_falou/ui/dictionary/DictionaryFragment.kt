package com.falou.avaliacao_tecnica_falou.ui.dictionary

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.falou.avaliacao_tecnica_falou.R
import com.falou.avaliacao_tecnica_falou.databinding.FragmentDictionaryBinding
import com.falou.avaliacao_tecnica_falou.ui.dictionary.adapterSenses.AdapterSenses
import org.koin.android.ext.android.inject
import android.util.Log
import java.io.IOException
import android.media.AudioAttributes





class DictionaryFragment : Fragment() {

    lateinit var binding : FragmentDictionaryBinding
    private val args by navArgs<DictionaryFragmentArgs>()
    private val viewModel : DictionaryViewModel by inject()

    private var mediaPlayer: MediaPlayer? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.checkWord(args.language, args.word.uppercase())

        return FragmentDictionaryBinding.inflate(layoutInflater).also {
            binding = it
            binding.viewModel = viewModel
            it.lifecycleOwner = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservers()
        initListeners()
        initPlayer()
    }

    private fun initView(){
        viewModel.title.observe(viewLifecycleOwner){
            binding.textView9.text = getString(R.string.that_s_it_for_education, it.lowercase())
        }

    }
    private fun initObservers(){

        viewModel.senses.observe(viewLifecycleOwner){ list->
            binding.recyclerDictionary.layoutManager = LinearLayoutManager(context)
            binding.recyclerDictionary.adapter = AdapterSenses(list?: listOf(), requireContext())
        }

    }
    private fun initListeners(){

        binding.onCLickSearch = View.OnClickListener {
            findNavController().navigate(R.id.action_navigation_dictionary_to_navigation_subscribe)
        }
        binding.imgSound.setOnClickListener {
            viewModel.pronunciations.observe(viewLifecycleOwner){
                it.forEach {pronunciation->
                    reproducePronunciation(pronunciation.audioFile?:"")
                }
            }
        }
    }

    private fun initPlayer(){
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
    }

    private fun reproducePronunciation(url: String) {
        try {
            viewModel.setIsPlaying(true)
            mediaPlayer?.setDataSource(url)

            mediaPlayer?.setOnPreparedListener {
                mediaPlayer?.start();
            }
            mediaPlayer?.prepareAsync();

            mediaPlayer?.setOnCompletionListener {
                mediaPlayer?.reset()
                viewModel.setIsPlaying(false)
            }
        } catch (err : IOException) {
            Log.e("Audio Error", err.toString());
        }
    }
}