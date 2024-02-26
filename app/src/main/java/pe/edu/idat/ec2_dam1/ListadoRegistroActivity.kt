package pe.edu.idat.ec2_dam1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import pe.edu.idat.ec2_dam1.databinding.ActivityListadoRegistroBinding

class ListadoRegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadoRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}