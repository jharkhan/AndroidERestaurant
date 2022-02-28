package fr.isen.corre.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.corre.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.corre.androiderestaurant.details.DetailsActivity
import fr.isen.corre.androiderestaurant.network.Dish
import fr.isen.corre.androiderestaurant.network.MenuResult
import fr.isen.corre.androiderestaurant.network.NetworkConstants
import org.json.JSONObject

enum class EntPlDes {
    STARTERS, MEALS, DESSERTS;
    companion object {
        fun getResourceId(cast: EntPlDes): Int {
            return when (cast) {
                STARTERS -> R.string.starters
                MEALS -> R.string.meals
                DESSERTS -> R.string.desserts
            }
        }
        fun getCategoryTitle(type: EntPlDes): String {
            return when (type) {
                STARTERS -> "Entrées"
                MEALS -> "Plats"
                DESSERTS -> "Desserts"
            }
        }
    }
}

class CategoryActivity : BaseActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var category: EntPlDes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        category = intent.getSerializableExtra(HomeActivity.category_type) as? EntPlDes ?: EntPlDes.STARTERS
        categoryTitle()
        makeRequest()

        Log.d("life cycle", "CategoryActivity onCreate")
    }

    private fun categoryTitle() {
        binding.textCategory.text = getString(EntPlDes.getResourceId(category))
    }

    private fun setupList(items: List<Dish>) {
        binding.listDishes.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter(items) { item ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra(CategoryActivity.SelectedItem, item)
            startActivity(intent)
        }
        binding.listDishes.adapter = adapter
    }

    private fun makeRequest() {
        val queue = Volley.newRequestQueue(this)
        val url = NetworkConstants.BASE_URL+NetworkConstants.MENU
        val parameters = JSONObject()
        parameters.put(NetworkConstants.KEY_SHOP, NetworkConstants.SHOP)
        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            parameters,
            {
                Log.d("tag", "$it")
                val data = GsonBuilder().create().fromJson(it.toString(), MenuResult::class.java)
                val items = data.data.firstOrNull() {
                    it.name == EntPlDes.getCategoryTitle(category)
                }?.items
                if (items != null) {
                    setupList(items)
                }
            },
            {
                Log.d("tag", "$it")
            })
        queue.add(request)
    }

    companion object {
        const val SelectedItem = "Selected Item"
    }

}