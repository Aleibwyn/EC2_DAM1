package pe.edu.idat.ec2_dam1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import pe.edu.idat.ec2_dam1.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private val listaRegistros: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.etHobby.isVisible = false
        estadosCheckBox()
        listarDatos()
    }

    private fun estadosCheckBox() {

        binding.cbOtro.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.cbDeporte.isEnabled = false
                binding.cbPintura.isEnabled = false
                binding.etHobby.isVisible = true
            } else {
                binding.cbDeporte.isEnabled = true
                binding.cbPintura.isEnabled = true
                binding.etHobby.isVisible = false
                binding.etHobby.text.clear()
            }
        }

        binding.cbDeporte.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.cbOtro.isEnabled = false
                binding.cbPintura.isEnabled = false
            } else {
                binding.cbOtro.isEnabled = true
                binding.cbPintura.isEnabled = true
            }
        }

        binding.cbPintura.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.cbDeporte.isEnabled = false
                binding.cbOtro.isEnabled = false
            } else {
                binding.cbDeporte.isEnabled = true
                binding.cbOtro.isEnabled = true
            }
        }
    }

    private fun limpiarCampos(){
        binding.etDni.text.clear()
        binding.etNombre.text.clear()
        binding.etApellidos.text.clear()
        binding.etEmail.text.clear()
        binding.etPassword.text.clear()
        binding.etHobby.text.clear()

        binding.cbDeporte.isChecked = false
        binding.cbPintura.isChecked = false
        binding.cbOtro.isChecked = false

        binding.cbDeporte.isEnabled = true
        binding.cbPintura.isEnabled = true
        binding.cbOtro.isEnabled = true

        binding.etHobby.isVisible = false

        binding.rbHombre.isChecked = false
        binding.rbMujer.isChecked = false
    }

    private fun listarDatos(){
        binding.btnAcceder.setOnClickListener {
            if (validarCampos()) {
                limpiarCampos()
                Toast.makeText(this, "Datos registrados correctamente", Toast.LENGTH_SHORT).show()
                val opListadoRegistro = Intent(this, ListadoRegistroActivity::class.java)
                opListadoRegistro.putExtra("registros", listaRegistros)
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

        if (dni.length != 8) {
            Toast.makeText(this, "El DNI debe tener exactamente 8 dígitos", Toast.LENGTH_SHORT).show()
            return false
        }

        val nombreRegex = Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+$")
        if (!nombre.matches(nombreRegex)) {
            Toast.makeText(this, "El nombre solo debe contener letras", Toast.LENGTH_SHORT).show()
            return false
        }

        val apellidosRegex = Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ]+( [a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*$")
        if (!apellidos.matches(apellidosRegex)) {
            Toast.makeText(this, "Los apellidos solo deben contener letras", Toast.LENGTH_SHORT).show()
            return false
        }

        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+\$")
        if (!email.matches(emailRegex)) {
            Toast.makeText(this, "Por favor, ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show()
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

        val genero = if (genHombre.isChecked) "Hombre" else "Mujer"
        val hobbies = mutableListOf<String>()
        if (hobDeporte.isChecked) hobbies.add("Deporte")
        if (hobPintura.isChecked) hobbies.add("Pintura")
        if (hobOtro.isChecked) hobbies.add(hobby)

        val passwordHidden = "*".repeat(password.length)

        val registro = "$dni, $nombre $apellidos, $genero, ${hobbies.joinToString(", ")}, $email, $passwordHidden"
        listaRegistros.add(registro)

        return true
    }

}