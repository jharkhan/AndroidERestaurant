package fr.isen.corre.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.corre.androiderestaurant.databinding.CellMainBinding

class ListDishesAdapter(val items: List<String>): RecyclerView.Adapter<ListDishesAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(binding: CellMainBinding): RecyclerView.ViewHolder(binding.root) {
        // mapper le contenu de la cellule
        val dishTitle: TextView = binding.titleCategory
        // on crée le view model

    }

    override fun onCreateViewHolder(viewParent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CellMainBinding.inflate(LayoutInflater.from(viewParent.context), viewParent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, position: Int) {
        val item = items[position]
        viewHolder.dishTitle.text = item
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}