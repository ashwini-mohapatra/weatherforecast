package com.ashwini.weatherforecast

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
import androidx.cardview.widget.CardView
import com.ashwini.weatherforecast.Model.Free.CurrentForecast
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

        b1.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var s1:String=e1.text.toString()
                link= "https://api.openweathermap.org/data/2.5/";
                val service: WeatherAPI
                val retrofit= Retrofit.Builder().baseUrl(link).addConverterFactory(
                    GsonConverterFactory.create()).client(okHttpClient).build()
                service=retrofit.create(WeatherAPI::class.java)
                var call=service.getForecast(s1,"9c385d813f4dbdd0ae2b011dd2fd7caf")
                call.enqueue(object: Callback<CurrentForecast>{
                    override fun onFailure(call: Call<CurrentForecast>, t: Throwable) {
                        Log.i("Response","Failure")
                        Toast.makeText(activity,"Failure to Fetch API",Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<CurrentForecast>, response: Response<CurrentForecast>) {
                        if(response.body()!!.cod ==200){
                            Log.i("Response","Success")
                            val apiModel0: CurrentForecast? =response.body()
                            Toast.makeText(activity,"API Fetch Successful",Toast.LENGTH_LONG).show()

                        }
                    }


                })
            }

        })
        return v
    }
}
