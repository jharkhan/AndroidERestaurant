package fr.isen.corre.androiderestaurant.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import fr.isen.corre.androiderestaurant.R
import fr.isen.corre.androiderestaurant.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = arguments?.getString(URL)
        if(url?.isNotEmpty() == true) {
            Picasso.get().load(url).placeholder(R.drawable.loading).into(binding.photo)
        }
    }
    companion object {
        fun newInstance(url: String) = DetailsFragment().apply { arguments = Bundle().apply { putString(URL, url) } }
        const val URL = "URL"
    }

}