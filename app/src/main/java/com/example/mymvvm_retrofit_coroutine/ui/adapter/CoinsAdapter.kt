package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.mymvvm_retrofit_coroutine.R
import com.example.mymvvm_retrofit_coroutine.databinding.AdapterCoinBinding
import com.example.mymvvm_retrofit_coroutine.util.ValidationUtil

class CoinAdapter(private val context: Context,) : RecyclerView.Adapter<MainViewHolder>() {

    var movieList = mutableListOf<Coin>()

    fun setMovies(movies: List<Coin>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCoinBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    @SuppressLint("MissingInflatedId")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val movie = movieList[position]
        if (ValidationUtil.validateMovie(movie)) {
            holder.binding.tvId.text = movie.id
            holder.binding.tvName.text = movie.name
            holder.binding.tvDetail.setOnClickListener(View.OnClickListener {
                val dialogView = LayoutInflater.from(context).inflate(R.layout.layout_screen, null)
                val txtId = dialogView.findViewById<TextView>(R.id.txtID)
                txtId.text = "Id : "+movie.id
                val txtName = dialogView.findViewById<TextView>(R.id.txtName)
                txtName.text = "Name : "+movie.name
                val txtSymbol = dialogView.findViewById<TextView>(R.id.txtSymbol)
                txtSymbol.text = "Symbol : "+movie.symbol
                val txtRank = dialogView.findViewById<TextView>(R.id.txtRank)
                txtRank.text = "Rank : "+movie.rank
                val txtIsNew = dialogView.findViewById<TextView>(R.id.txtIsNew)
                txtIsNew.text = "is_new : "+movie.is_new
                val txtIsActive = dialogView.findViewById<TextView>(R.id.txtIsActive)
                txtIsActive.text = "is_active : "+movie.is_active
                val txtType = dialogView.findViewById<TextView>(R.id.txtType)
                txtType.text = "Type : "+movie.type

                val builder = AlertDialog.Builder(context)
                    .setTitle("Detail")
                    .setView(dialogView)
                    .setPositiveButton("OK") { dialog, _ ->






                        dialog.dismiss()
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }

                builder.create().show()
            })
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MainViewHolder(val binding: AdapterCoinBinding) : RecyclerView.ViewHolder(binding.root) {

}