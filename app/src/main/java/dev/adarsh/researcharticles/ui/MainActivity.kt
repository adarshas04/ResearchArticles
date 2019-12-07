package dev.adarsh.researcharticles.ui

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.adarsh.researcharticles.R
import dev.adarsh.researcharticles.api.Api
import dev.adarsh.researcharticles.model.Paper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val api = Api()
    private val callback = object : Callback<Paper>{
        override fun onFailure(call: Call<Paper>, t: Throwable) {
            progressBar.visibility = View.GONE
            swipeContainer.isRefreshing = false
            Toast.makeText(applicationContext,"Problem in reaching servers",Toast.LENGTH_LONG).show()
            Log.d("MainActivity","Problem in reaching the servers")
        }

        override fun onResponse(call: Call<Paper>, response: Response<Paper>) {
            response?.isSuccessful.let {
                swipeContainer.isRefreshing = false
                progressBar.visibility = View.GONE
                val result = Paper(response?.body()?.response ?: Paper.Response(emptyList(),0.0,0,0))
                pList.adapter=PaperAdapter(result.response.docs)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility = View.VISIBLE
        pList.layoutManager = LinearLayoutManager(this)
        if(isNetworkConnected()){
            api.getPapers(callback)
        }
        else{
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        swipeContainer.setOnRefreshListener {
            api.getPapers(callback)
        }
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
