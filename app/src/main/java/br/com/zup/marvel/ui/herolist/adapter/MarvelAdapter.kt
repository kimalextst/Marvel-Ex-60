package br.com.zup.marvel.ui.herolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.marvel.databinding.MarvelItemBinding
import br.com.zup.marvel.domain.model.Marvel

class MarvelAdapter(
    private var listaHerois: MutableList<Marvel>,
    private val clickHeroi: (heroi: Marvel) -> Unit
) : RecyclerView.Adapter<MarvelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MarvelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val marvelHeroi = listaHerois[position]
        holder.adicionarInformacoesView(marvelHeroi)
        holder.binding.cvItemLista.setOnClickListener {
            clickHeroi(marvelHeroi)
        }
    }

    override fun getItemCount() = listaHerois.size

    fun updateListHeroes(newList: List<Marvel>) {
        if (listaHerois.size == 0) {
            listaHerois = newList as MutableList<Marvel>
        } else {
            listaHerois.addAll(newList)
        }
        notifyDataSetChanged()
    }

    fun getLista() = this.listaHerois

    class ViewHolder(val binding: MarvelItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun adicionarInformacoesView(heroi: Marvel) {
            binding.ivHeroi.setImageResource(heroi.imagem)
            binding.tvNomeHeroi.text = heroi.nome
        }
    }
}