package fr.isen.corre.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Plat(@SerializedName("name_fr") val name: String): Serializable {
}