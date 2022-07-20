package br.com.zup.marvel.ui.herolist.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.marvel.*
import br.com.zup.marvel.databinding.FragmentHeroListBinding
import br.com.zup.marvel.domain.model.Marvel
import br.com.zup.marvel.ui.detail.DetalheActivity
import br.com.zup.marvel.ui.herolist.adapter.MarvelAdapter
import br.com.zup.marvel.ui.herolist.viewmodel.HeroViewModel
import br.com.zup.marvel.ui.home.view.HomeActivity
import br.com.zup.marvel.viewstate.ViewState

class HeroListFragment : Fragment() {

    private lateinit var binding : FragmentHeroListBinding

    private val viewModel: HeroViewModel by lazy {
        ViewModelProvider(this)[HeroViewModel::class.java]
    }

    private val adapter: MarvelAdapter by lazy {
        MarvelAdapter(arrayListOf(), this::irParaDetalhe)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as HomeActivity).supportActionBar?.title = getString(R.string.hero_list)
        initObserver()
        setUpRvHeroList()
        viewModel.getHeroList()
    }

    private fun initObserver() {
        viewModel.marvelListState.observe(this.viewLifecycleOwner) {

            when (it) {
                is ViewState.Success -> {
                    adapter.updateListHeroes(it.data)
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

    private fun setUpRvHeroList() {
        binding.rvHerois.adapter = adapter
        binding.rvHerois.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun irParaDetalhe(heroi: Marvel) {
        val intent = Intent(requireContext(), DetalheActivity::class.java).apply {
            putExtra(MARVEL_KEY, heroi)
        }
        startActivity(intent)
    }
}