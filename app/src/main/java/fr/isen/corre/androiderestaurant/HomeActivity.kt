package fr.isen.corre.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import fr.isen.corre.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listenClick()

        Log.d("life cycle", "HomeActivity onCreate")
    }

    private fun listenClick() {
        binding.buttonStarters.setOnClickListener {
            showCategories(EntPlDes.STARTERS)
        }
        binding.buttonMeals.setOnClickListener {
            showCategories(EntPlDes.MEALS)
        }
        binding.buttonDesserts.setOnClickListener {
            showCategories(EntPlDes.DESSERTS)
        }
    }
    private fun showCategories(cast: EntPlDes) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra(HomeActivity.category_type, cast)
        startActivity(intent)
    }
    override fun onDestroy() {
        Log.d("life cycle", "HomeActivity onDestroy")
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("life cycle", "HomeActivity onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life cycle", "HomeActivity onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("life cycle", "HomeActivity onStart")
    }

    override fun onStop() {
        Log.d("life cycle", "HomeActivity onStop")
        super.onStop()
    }

    companion object {
        const val category_type = "category_type"
    }

}