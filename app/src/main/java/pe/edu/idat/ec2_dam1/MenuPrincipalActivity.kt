package pe.edu.idat.ec2_dam1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pe.edu.idat.ec2_dam1.databinding.ActivityMenuPrincipalBinding

class MenuPrincipalActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFormulario.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnFormulario -> startActivity(Intent(applicationContext,
                    FormularioCuestionarioActivity::class.java))
                }
    }
}