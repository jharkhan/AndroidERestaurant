package fr.isen.corre.androiderestaurant.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.corre.androiderestaurant.CategoryActivity
import fr.isen.corre.androiderestaurant.R
import fr.isen.corre.androiderestaurant.basket.Basket
import fr.isen.corre.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.corre.androiderestaurant.network.Dish
import kotlin.math.max

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var currentDish: Dish? = null
    private var itemCount = 1F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentDish = intent.getSerializableExtra(CategoryActivity.SelectedItem) as? Dish
        setupDetails()
        observeClick()
        refreshShopButton()
    }
    private fun setupDetails() {
        binding.titleSelectedItem.text = currentDish?.name
        binding.ingredients.text = currentDish?.ingredients?.joinToString(", ") { it.name }
        currentDish?.let {
            binding.viewPager.adapter = ViewPagerAdapter(this, it.images)
        }
    }

    private fun observeClick() {
        binding.buttonShop.setOnClickListener {
            currentDish?.let { dish ->
                val basket = Basket.getBasket(this)
                basket.addItem(dish, itemCount.toInt())
                basket.save(this)
            }
        }
        binding.buttonLess.setOnClickListener {
            itemCount = max(1f, itemCount - 1)
            refreshShopButton()
        }
        binding.buttonMore.setOnClickListener {
            itemCount++
            refreshShopButton()
        }
    }

    private fun refreshShopButton() {
        currentDish?.let { dish ->
            val price: Float = dish.prices.first().price.toFloat()
            val total = price * itemCount
            binding.buttonShop.text = "${getString(R.string.total)} $total €"
            binding.quantity.text = itemCount.toInt().toString()
        }
    }
}