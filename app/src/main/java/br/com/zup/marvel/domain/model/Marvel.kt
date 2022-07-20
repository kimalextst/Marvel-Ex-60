package br.com.zup.marvel.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "heroes")
data class Marvel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod_filme")
    var codHero : Int = 1,

    var imagem: Int = 0,

    @ColumnInfo(name = "nome")
    var nome: String,

    @ColumnInfo(name = "detalhe")
    var detalhe: String
) : Parcelable