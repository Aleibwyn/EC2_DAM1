package pe.edu.idat.ec2_dam1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import pe.edu.idat.ec2_dam1.comunes.AppMensaje
import pe.edu.idat.ec2_dam1.comunes.TipoMensaje
import pe.edu.idat.ec2_dam1.databinding.ActivityFormularioCuestionarioBinding

class FormularioCuestionarioActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFormularioCuestionarioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioCuestionarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnResolver.setOnClickListener(this)
        binding.rgPregunta3.setOnCheckedChangeListener{group: RadioGroup, checkedId: Int ->
            actualizarEstadoPregunta4(checkedId)
        }

    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnResolver -> {
                // Validar el formulario
                val formularioValido = validarFormulario()

                if (formularioValido) {
                    // Si el formulario es válido, actualizar el estado de la pregunta
                    actualizarEstadoPregunta4(binding.rgPregunta3.checkedRadioButtonId)
                    // Luego, iniciar la actividad para mostrar el listado
                    startActivity(Intent(applicationContext, ListadoCuestionarioActivity::class.java))
                }
            }
        }
    }

    // VALIDACION DE FORMULARIO

    private fun validarFormulario(): Boolean {
        var respuesta = false
        if (!validarSintomas()){
            AppMensaje.enviarMensaje(
                binding.root,
                "Debe seleccionar al menos un síntoma",
                TipoMensaje.ERROR)
        }else if (!validarFiebreAlta()){
            AppMensaje.enviarMensaje(
                binding.root,
                "Debe seleccionar si tiene fiebre alta",
                TipoMensaje.ERROR)
        }else if (!validarViveSolo()){
            AppMensaje.enviarMensaje(
                binding.root,
                "Debe seleccionar si vive solo",
                TipoMensaje.ERROR)
        }else if (!validarServicios()){
            AppMensaje.enviarMensaje(
                binding.root,
                "Debe seleccionar almenos un servicio",
                TipoMensaje.ERROR)
        }else if (binding.rbPregunta3si.isChecked && !validarViveconAdulto()){
            AppMensaje.enviarMensaje(
                binding.root,
                "Debe seleccionar si vive con un Adulto",
                TipoMensaje.ERROR)
        }else {
            respuesta = true
        }
        return respuesta
    }

    // VALIDACIONES
    private fun validarSintomas(): Boolean {
        var respuesta = false
        if (binding.cbSintoma1.isChecked ||
            binding.cbSintoma2.isChecked ||
            binding.cbSintoma3.isChecked ||
            binding.cbSintoma4.isChecked ||
            binding.cbSintoma5.isChecked ||
            binding.cbSintoma6.isChecked) {
            respuesta = true
        }
        return respuesta
    }
    private fun validarFiebreAlta(): Boolean{
        var respuesta = true
        if(binding.rgPregunta2.checkedRadioButtonId == -1){
            respuesta = false
        }
        return respuesta
    }

    private fun validarViveSolo(): Boolean{
        var respuesta = true
        if(binding.rgPregunta3.checkedRadioButtonId == -1){
            respuesta = false
        }
        return respuesta
    }

    private fun validarViveconAdulto(): Boolean{
        var respuesta = true
        if(binding.rgPregunta4.checkedRadioButtonId == -1){
            respuesta = false
        }
        return respuesta
    }
    private fun actualizarEstadoPregunta4(checkedId: Int) {
        if (checkedId == R.id.rbPregunta3no) {
            // Si la respuesta a la pregunta 3 es "No", deshabilitar las opciones de la pregunta 4
            binding.rbPregunta4si.isEnabled = false
            binding.rbPregunta4no.isEnabled = false
            // Además, desmarcar cualquier opción seleccionada en la pregunta 2
            binding.rgPregunta4.clearCheck()
        } else {
            // Si la respuesta a la pregunta 3 no es "No", habilitar las opciones de la pregunta 4
            binding.rbPregunta4si.isEnabled = true
            binding.rbPregunta4no.isEnabled = true
        }
    }

    private fun validarServicios(): Boolean {
        var respuesta = false
        if (binding.cbServicio1.isChecked ||
            binding.cbServicio2.isChecked ||
            binding.cbServicio3.isChecked ||
            binding.cbServicio4.isChecked){
            respuesta = true
        }
        return respuesta
    }




}

