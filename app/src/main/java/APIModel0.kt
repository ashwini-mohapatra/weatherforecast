
import com.google.gson.annotations.SerializedName

data class APIModel0(
    @SerializedName("city")
    val city: City,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: ArrayList<APIModel>,
    @SerializedName("message")
    val message: Double
)