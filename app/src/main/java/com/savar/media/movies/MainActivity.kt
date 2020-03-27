package ir.airport.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import ir.airport.myapplication.Features.SearchInfoMovie.MovieInfoActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            val i = Intent(this@MainActivity, MovieInfoActivity::class.java)
            startActivity(i)
            finish()
        }, 4000)
    }
}
