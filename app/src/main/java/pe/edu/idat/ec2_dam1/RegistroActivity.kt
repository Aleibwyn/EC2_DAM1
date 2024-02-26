package pe.edu.idat.ec2_dam1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import pe.edu.idat.ec2_dam1.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private val datosRegistrados: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        estadosCheckBox()
        listarDatos()
    }

    private fun estadosCheckBox() {
        binding.cbOtro.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.cbDeporte.isEnabled = false
                binding.cbPintura.isEnabled = false
                binding.etHobby.isEnabled = true
            } else {
                binding.cbDeporte.isEnabled = true
                binding.cbPintura.isEnabled = true
                binding.etHobby.isEnabled = false
                binding.etHobby.text.clear()
            }
        }
    }
    private fun listarDatos(){
        binding.btnAcceder.setOnClickListener {
            if (validarCampos()) {
                Toast.makeText(this, "Datos validados correctamente", Toast.LENGTH_SHORT).show()
                val opListadoRegistro = Intent(this, ListadoRegistroActivity::class.java)
                startActivity(opListadoRegistro)
            }
        }
    }

    private fun validarCampos(): Boolean {
        val dni = binding.etDni.text.toString().trim()
        val nombre = binding.etNombre.text.toString().trim()
        val apellidos = binding.etApellidos.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        val genHombre = binding.rbHombre
        val genMujer = binding.rbMujer
        val hobDeporte = binding.cbDeporte
        val hobPintura = binding.cbPintura
        val hobOtro = binding.cbOtro
        val hobby = binding.etHobby.text.toString().trim()

        if (dni.isEmpty() || nombre.isEmpty() || apellidos.isEmpty() ||
            email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos obligatorios", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!genHombre.isChecked && !genMujer.isChecked) {
            Toast.makeText(this, "Por favor, seleccione al menos un genero", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!hobDeporte.isChecked && !hobPintura.isChecked && !hobOtro.isChecked) {
            Toast.makeText(this, "Por favor, seleccione al menos un hobby", Toast.LENGTH_SHORT).show()
            return false
        }

        if (hobOtro.isChecked) {

            if (hobby.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese un hobby", Toast.LENGTH_SHORT).show()
                return false
            }

        }
        return true
    }

}