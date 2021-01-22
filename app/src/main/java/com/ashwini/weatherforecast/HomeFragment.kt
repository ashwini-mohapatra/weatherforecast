package com.ashwini.weatherforecast

import APIModel0
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView : RecyclerView
    lateinit var link:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View= inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView=v.findViewById(R.id.recyclerview)
        linearLayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = linearLayoutManager

        var loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        var okHttpClient:OkHttpClient=OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val e1:EditText=v.findViewById(R.id.editTextTextPersonName)
        val b1:Button=v.findViewById(R.id.button)
        b1.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                var s1:String=e1.text.toString()
                link= "https://api.openweathermap.org/data/2.5/";
                val service: WeatherAPI
                val retrofit= Retrofit.Builder().baseUrl(link).addConverterFactory(
                    GsonConverterFactory.create()).client(okHttpClient).build()
                service=retrofit.create(WeatherAPI::class.java)
                var call=service.getForecast(s1,"9c385d813f4dbdd0ae2b011dd2fd7caf")
                call.enqueue(object: Callback<APIModel0>{
                    override fun onFailure(call: Call<APIModel0>, t: Throwable) {
                        Log.i("Response","Failure")
                        Toast.makeText(activity,"Failure to Fetch API",Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<APIModel0>, response: Response<APIModel0>) {
                        if(response.isSuccessful){
                            Log.i("Response","Success")
                            val apiModel0: APIModel0? =response.body()
                            Toast.makeText(activity,"API Fetch Successful",Toast.LENGTH_LONG).show()
                        }
                    }


                })
            }

        })
        return v
    }
}