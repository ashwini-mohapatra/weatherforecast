package com.ashwini.weatherforecast.Screens

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.ashwini.weatherforecast.Model.Free.CurrentForecast
import com.ashwini.weatherforecast.R
import com.ashwini.weatherforecast.WeatherAPI
import retrofit2.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    lateinit var link:String
    lateinit var card:CardView
    lateinit var t1: TextView
    lateinit var t2: TextView
    lateinit var t3: TextView
    lateinit var t4: TextView
    lateinit var t5: TextView
    lateinit var t6: TextView
    lateinit var t7: TextView
    lateinit var t8: TextView
    lateinit var t9: TextView
    lateinit var t10: TextView
    lateinit var t11: TextView
    lateinit var t12: TextView
    lateinit var t13: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View= inflater.inflate(R.layout.fragment_home, container, false)


        var loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        var okHttpClient:OkHttpClient=OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val e1:EditText=v.findViewById(R.id.editTextTextPersonName)
        val b1:Button=v.findViewById(R.id.button)

        t1=v.findViewById(R.id.latitude);
        t2=v.findViewById(R.id.longitude);
        t3=v.findViewById(R.id.sky);
        t4=v.findViewById(R.id.temperature);
        t5=v.findViewById(R.id.feelslike);
        t6=v.findViewById(R.id.temperaturerange);
        t7=v.findViewById(R.id.pressure);
        t8=v.findViewById(R.id.humidity);
        t9=v.findViewById(R.id.wind1);
        t10=v.findViewById(R.id.wind2);
        t11=v.findViewById(R.id.clouds);
        t12=v.findViewById(R.id.sunrise);
        t13=v.findViewById(R.id.sunset);

        card=v.findViewById(R.id.card)

        //This uses Open Weather API

        b1.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var s1:String=e1.text.toString()
                link= "https://api.openweathermap.org/data/2.5/";
                val service: WeatherAPI
                val retrofit= Retrofit.Builder().baseUrl(link).addConverterFactory(
                    GsonConverterFactory.create()).client(okHttpClient).build()
                service=retrofit.create(WeatherAPI::class.java)
                var call=service.getCurrentWeather(s1,"9c385d813f4dbdd0ae2b011dd2fd7caf")
                call.enqueue(object: Callback<CurrentForecast>{
                    override fun onFailure(call: Call<CurrentForecast>, t: Throwable) {
                        Log.i("Response","Failure")
                        Toast.makeText(activity,"Failure to Fetch API",Toast.LENGTH_SHORT).show()
                    }

                    @RequiresApi(Build.VERSION_CODES.O)
                    override fun onResponse(call: Call<CurrentForecast>, response: Response<CurrentForecast>) {
                        if(response.body()!!.cod ==200){
                            Log.i("Response","Success")
                            val apiModel0: CurrentForecast? =response.body()


                            t1.setText(apiModel0?.coord?.lat?.toBigDecimal()?.toPlainString())
                            t2.setText(apiModel0?.coord?.lon?.toBigDecimal()?.toPlainString())
                            t3.setText(apiModel0?.weather?.get(0)?.main)

                            var tmean:Double= apiModel0!!.main.temp
                            var tmeanc:Double=tmean-273.15
                            t4.setText(String.format("%.2f",tmeanc))

                            var tfeel:Double= apiModel0!!.main.feels_like
                            var tfeelc:Double=tfeel-273.15
                            t5.setText(String.format("%.2f",tfeelc))

                            var x1:Double=apiModel0?.main?.temp_min
                            var x2:Double=apiModel0?.main?.temp_max
                            x1-=273.15
                            x2-=273.15
                            val x:String=String.format("%.2f",x1)+"-"+String.format("%.2f",x2)
                            t6.setText(x)

                            val pressure:String=apiModel0?.main?.pressure.toBigDecimal()?.toPlainString()+ " hPa"
                            t7.setText(pressure)

                            val hint:String=apiModel0?.main?.hint.toBigDecimal()?.toPlainString()+ " %"
                            t8.setText(hint)

                            val speed:String="Speed: "+apiModel0?.wind?.speed?.toBigDecimal()?.toPlainString()+" m/s"
                            t9.setText(speed)

                            val degree:String="Degree: "+apiModel0?.wind?.deg?.toBigDecimal()?.toPlainString()+" deg."
                            t10.setText(degree)

                            val cloud:String=apiModel0?.clouds?.all.toBigDecimal()?.toPlainString()+ " %"
                            t11.setText(cloud)

                            val sunrise:String=java.time.format.DateTimeFormatter.ISO_INSTANT
                                .format(java.time.Instant.ofEpochSecond(apiModel0?.sys?.sunrise))
                            var sunrise1:String=sunrise.replace("T","",true)
                            var sunrise2=sunrise1.replace("Z","",true)
                            t12.setText(sunrise2)

                            val sunset:String=java.time.format.DateTimeFormatter.ISO_INSTANT
                                .format(java.time.Instant.ofEpochSecond(apiModel0?.sys?.sunset))
                            var sunset1=sunrise.replace("T","",true)
                            var sunset2=sunset1.replace("Z","",true)
                            t13.setText(sunset2)
                        }
                    }


                })
            }

        })
        return v
    }
}
