package pe.edu.idat.ec2_dam1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import pe.edu.idat.ec2_dam1.databinding.ActivityListadoCuestionarioBinding
import android.R

class ListadoCuestionarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadoCuestionarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoCuestionarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listaCuestionario = intent.getSerializableExtra("listaCuestionario") as ArrayList<String>

        val adapter = ArrayAdapter(applicationContext, R.layout.simple_list_item_1, listaCuestionario)
        binding.lvlistadorc.adapter = adapter

    }
}