package fr.isen.corre.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.corre.androiderestaurant.databinding.CellMainBinding
import fr.isen.corre.androiderestaurant.network.Dish

class ItemAdapter(val items: List<Dish>, val itemClickListener: (Dish) -> Unit): RecyclerView.Adapter<ItemAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(binding: CellMainBinding): RecyclerView.ViewHolder(binding.root) {
        val dishTitle: TextView = binding.titleDish
        val price: TextView = binding.price
        val image: ImageView = binding.imageView
        val layout: CardView = binding.root
    }

    override fun onCreateViewHolder(viewParent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CellMainBinding.inflate(LayoutInflater.from(viewParent.context), viewParent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: CategoryViewHolder, position: Int) {
        val item = items[position]
        viewHolder.dishTitle.text = item.name
        viewHolder.price.text = "${item.prices.first().price} €"
        Picasso.get()
            .load(item.getThumbnailURL())
            .placeholder(R.drawable.loading)
            .into(viewHolder.image)
        viewHolder.layout.setOnClickListener {
            itemClickListener.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}