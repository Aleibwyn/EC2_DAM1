package pe.edu.idat.ec2_dam1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import pe.edu.idat.ec2_dam1.databinding.ActivityMenuPrincipalBinding

class MenuPrincipalActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMenuPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistro.setOnClickListener(this)
        binding.btnFormulario.setOnClickListener(this)
        binding.btnListado.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnRegistro -> startActivity(
                Intent(
                    applicationContext,
                    RegistroActivity::class.java
                )
            )
            R.id.btnFormulario -> startActivity(
                Intent(
                    applicationContext,
                    FormularioCuestionarioActivity::class.java
                )
            )
            R.id.btnListado -> startActivity(
                Intent(
                    applicationContext,
                    ListadoActivity::class.java
                )
            )
        }
    }


}