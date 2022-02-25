package fr.isen.corre.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import fr.isen.corre.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.corre.androiderestaurant.network.Dish

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var currentDish: Dish? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentDish = intent.getSerializableExtra(CategoryActivity.SelectedItem) as? Dish
        setupTitle()
    }
    fun setupTitle() {
        binding.titleSelectedItem.text = currentDish?.name
    }
}