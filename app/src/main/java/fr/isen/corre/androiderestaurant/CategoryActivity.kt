package fr.isen.corre.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.corre.androiderestaurant.databinding.ActivityMealsBinding

enum class EntPlDes {
    STARTERS, MEALS, DESSERTS;
    companion object {
        fun getRessourceId(cast: EntPlDes): Int {
            return when (cast) {
                STARTERS -> R.string.starters
                MEALS -> R.string.meals
                DESSERTS -> R.string.desserts
            }
        }
    }
}
class MealsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealsBinding
    private lateinit var category: EntPlDes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        category = intent.getSerializableExtra(HomeActivity.category_type) as? EntPlDes ?: EntPlDes.STARTERS
        mealsTitle()
    }
    private fun mealsTitle() {
        binding.textMeals.text = getString(EntPlDes.getRessourceId(category))
    }
}