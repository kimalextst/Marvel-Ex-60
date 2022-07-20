package br.com.zup.marvel.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import br.com.zup.marvel.MARVEL_KEY
import br.com.zup.marvel.R
import br.com.zup.marvel.databinding.ActivityDetalheBinding
import br.com.zup.marvel.domain.model.Marvel

class DetalheActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalheBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalheBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.detalhe_name)

        recuperarMarvel()
    }

    private fun recuperarMarvel(){
        val marvelHeroi = intent.getParcelableExtra<Marvel>(MARVEL_KEY)

        if (marvelHeroi != null){
            exibirInformacoes(marvelHeroi)
        }
    }

    private fun exibirInformacoes(marvel: Marvel){
        binding.ivHeroiDetalhe.setImageResource(marvel.imagem)
        binding.tvNomeHeroiDetalhe.text = marvel.nome
        binding.tvHeroiDetalhe.text = marvel.detalhe
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}