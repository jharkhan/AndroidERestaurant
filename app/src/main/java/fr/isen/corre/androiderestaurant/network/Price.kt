package fr.isen.corre.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Price(@SerializedName("name_fr") val price: String): Serializable {
}