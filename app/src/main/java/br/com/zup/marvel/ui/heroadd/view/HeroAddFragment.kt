package br.com.zup.marvel.ui.heroadd.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.marvel.R
import br.com.zup.marvel.databinding.FragmentHeroAddBinding
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.ui.heroadd.viewmodel.HeroAddViewModel
import br.com.zup.marvel.ui.home.view.HomeActivity
import br.com.zup.marvel.viewstate.ViewState

class HeroAddFragment : Fragment() {
    private lateinit var binding: FragmentHeroAddBinding
    private val viewModel: HeroAddViewModel by lazy {
        ViewModelProvider(this)[HeroAddViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as HomeActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (activity as HomeActivity).supportActionBar?.title = getString(R.string.hero_add)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bvSaveHero.setOnClickListener {
            viewModel.insertHero(
                Marvel(
                   nome = binding.etHeroNameAdd.text.toString(),
                    detalhe = binding.etHeroDetailAdd.text.toString()
                )
            )
        }

        initObserver()
    }

    private fun initObserver() {
        viewModel.marvelAddState.observe(this.viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    Toast.makeText(
                        context,
                        "Filme cadastrado com sucesso!",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is ViewState.Error -> {
                    Toast.makeText(
                        context,
                        "${it.throwable.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }
}