package pe.edu.idat.ec2_dam1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.edu.idat.ec2_dam1.databinding.ActivityMenuPrincipalBinding

class MenuPrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistro.setOnClickListener {
            val opRegistro = Intent(this, RegistroActivity::class.java)
            startActivity(opRegistro)
        }

        binding.btnFormulario.setOnClickListener {
            val opFormulario = Intent(this, FormularioCuestionarioActivity::class.java)
            startActivity(opFormulario)
        }

        binding.btnListado.setOnClickListener {
            val opListado = Intent(this, ListadoActivity::class.java)
            startActivity(opListado)
        }
    }
}