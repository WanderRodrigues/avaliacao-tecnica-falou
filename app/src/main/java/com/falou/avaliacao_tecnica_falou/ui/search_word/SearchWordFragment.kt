package com.falou.avaliacao_tecnica_falou.ui.search_word

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.falou.avaliacao_tecnica_falou.databinding.FragmentSearchWordBinding
import com.falou.avaliacao_tecnica_falou.model.Language
import com.falou.avaliacao_tecnica_falou.utils.CustomSpinnerAdapter
import org.koin.android.ext.android.inject

class SearchWordFragment : Fragment() {


    lateinit var binding : FragmentSearchWordBinding

    private val viewModel : SearchWordViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return FragmentSearchWordBinding.inflate(layoutInflater).also {
            binding = it
            binding.viewModel = viewModel
            it.lifecycleOwner = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

        viewModel.languages.observe(viewLifecycleOwner){
            val spinnerAdapter = CustomSpinnerAdapter(requireContext(), it)
            binding.spinner.adapter = spinnerAdapter
        }

    }
    private fun initListeners(){

        binding.editWord.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                    viewModel.setIsVisibleButton(s.isNotEmpty())

            }
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {/*na*/}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {/*na*/}
        })

        binding.onCLickSearch = View.OnClickListener {
            findNavController().navigate(
                SearchWordFragmentDirections
                    .actionNavigationSearchWordToNavigationDictionary(
                        binding.editWord.text.toString(),
                        binding.spinner.selectedItem.let { it as Language }.code
                    )
            )
        }
    }

}