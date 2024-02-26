package pe.edu.idat.ec2_dam1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.ec2_dam1.databinding.ActivityListadoRegistroBinding

class ListadoRegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadoRegistroBinding
    private lateinit var registroAdapter: AdapterRegistro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listaRegistros = intent.getStringArrayListExtra("registros") ?: arrayListOf()

        registroAdapter = AdapterRegistro(listaRegistros)

        binding.recyclerViewRegistros.apply {
            layoutManager = LinearLayoutManager(this@ListadoRegistroActivity)
            adapter = registroAdapter
        }
    }
}