package pe.edu.idat.ec2_dam1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioGroup
import pe.edu.idat.ec2_dam1.comunes.AppMensaje
import pe.edu.idat.ec2_dam1.comunes.TipoMensaje
import pe.edu.idat.ec2_dam1.databinding.ActivityFormularioCuestionarioBinding

class FormularioCuestionarioActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFormularioCuestionarioBinding
    private var listaServicios = ArrayList<String>()
    private var listaSintomas = ArrayList<String>()
    private var listaCuestionario = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioCuestionarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnResolver.setOnClickListener(this)
        binding.rgPregunta3.setOnCheckedChangeListener{group: RadioGroup, checkedId: Int ->
            actualizarEstadoPregunta4(checkedId)
        }
        binding.cbSintoma1.setOnClickListener(this)
        binding.cbSintoma2.setOnClickListener(this)
        binding.cbSintoma3.setOnClickListener(this)
        binding.cbSintoma4.setOnClickListener(this)
        binding.cbSintoma5.setOnClickListener(this)
        binding.cbSintoma6.setOnClickListener(this)
        binding.cbServicio1.setOnClickListener(this)
        binding.cbServicio2.setOnClickListener(this)
        binding.cbServicio3.setOnClickListener(this)
        binding.cbServicio4.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        if (v is CheckBox) {
            agregarQuitarSintoma(v)
            agregarQuitarServicios(v)
        } else {
            when (v.id) {
                R.id.btnResolver -> {
                    resolverCuestionario(v)
                    startActivity(Intent(applicationContext, ListadoCuestionarioActivity::class.java)
                        .apply {
                            putExtra("listaSintomas", listaSintomas)
                            putExtra("listaServicios", listaServicios)
                            putExtra("listaCuestionario", listaCuestionario)
                        })
                }
            }
        }
    }
    // FUNCION LISTA DE CUESTIONARIO
    fun resolverCuestionario(v: View){
        if(validarFormulario()){
            val infoCuestionario =
                    obtenerSintomas() + "\n" +
                    obtenerFiebreAlta() + "\n" +
                    obtenerViveSolo() + "\n" +
                    obtenerViveconAdulto() + "\n" +
                    obtenerServicios()
            //listaSintomas.add(infoCuestionario)
            listaCuestionario.add(infoCuestionario)
            AppMensaje.enviarMensaje(
                binding.root,
                "Cuestionario resuelto con éxito",
                TipoMensaje.SUCCESSFULL)
            setearControles()
        }
    }

    // LIMPIEZA DE CONTROLES
    private fun setearControles() {
        binding.cbSintoma1.isChecked = false
        binding.cbSintoma2.isChecked = false
        binding.cbSintoma3.isChecked = false
        binding.cbSintoma4.isChecked = false
        binding.cbSintoma5.isChecked = false
        binding.cbSintoma6.isChecked = false
        binding.rgPregunta2.clearCheck()
        binding.rgPregunta3.clearCheck()
        binding.rgPregunta4.clearCheck()
        binding.cbServicio1.isChecked = false
        binding.cbServicio2.isChecked = false
        binding.cbServicio3.isChecked = false
        binding.cbServicio4.isChecked = false
    }

    // AGREGAR O QUITAR SELECCION DE CHECKBOXS
    private fun agregarQuitarSintoma(v: CheckBox) {
        if(v.isChecked){
            when(v.id){
                R.id.cbSintoma1 -> listaSintomas.add(v.text.toString())
                R.id.cbSintoma2 -> listaSintomas.add(v.text.toString())
                R.id.cbSintoma3 -> listaSintomas.add(v.text.toString())
                R.id.cbSintoma4 -> listaSintomas.add(v.text.toString())
                R.id.cbSintoma5 -> listaSintomas.add(v.text.toString())
                R.id.cbSintoma6 -> listaSintomas.add(v.text.toString())
            }
        }else{
            when(v.id){
                R.id.cbSintoma1 -> listaSintomas.remove(v.text.toString())
                R.id.cbSintoma2 -> listaSintomas.remove(v.text.toString())
                R.id.cbSintoma3 -> listaSintomas.remove(v.text.toString())
                R.id.cbSintoma4 -> listaSintomas.remove(v.text.toString())
                R.id.cbSintoma5 -> listaSintomas.remove(v.text.toString())
                R.id.cbSintoma6 -> listaSintomas.remove(v.text.toString())
            }
        }
    }

    private fun agregarQuitarServicios(v: CheckBox) {
        if(v.isChecked){
            when(v.id){
                R.id.cbServicio1 -> listaServicios.add(v.text.toString())
                R.id.cbServicio2 -> listaServicios.add(v.text.toString())
                R.id.cbServicio3 -> listaServicios.add(v.text.toString())
                R.id.cbServicio4 -> listaServicios.add(v.text.toString())
            }
        }else{
            when(v.id){
                R.id.cbServicio1 -> listaServicios.remove(v.text.toString())
                R.id.cbServicio2 -> listaServicios.remove(v.text.toString())
                R.id.cbServicio3 -> listaServicios.remove(v.text.toString())
                R.id.cbServicio4 -> listaServicios.remove(v.text.toString())
            }
        }
    }

    // OBTENCION DE DATOS

    fun obtenerSintomas():String{
        var sintomas = ""
        for ((index, sint)in listaSintomas.withIndex()){
            sintomas += sint
            if (index < listaSintomas.size - 1){
                sintomas += ", "
            }
        }
        return sintomas
    }

    fun obtenerServicios():String{
        var servicios = ""
        for ((index, serv)in listaServicios.withIndex()){
            servicios += serv
            if (index < listaServicios.size - 1){
                servicios += ", "
            }
        }
        return servicios
    }

    fun obtenerFiebreAlta():String{
        var fiebreAlta = ""
        when(binding.rgPregunta2.checkedRadioButtonId){
            R.id.rbPregunta2si -> {
                fiebreAlta = binding.rbPregunta2si.text.toString()
            }
            R.id.rbPregunta2no -> {
                fiebreAlta = binding.rbPregunta2no.text.toString()
            }
        }
        return fiebreAlta
    }

    fun obtenerViveSolo():String{
        var viveSolo = ""
        when(binding.rgPregunta3.checkedRadioButtonId){
            R.id.rbPregunta3si -> {
                viveSolo = binding.rbPregunta3si.text.toString()
            }
            R.id.rbPregunta3no -> {
                viveSolo = binding.rbPregunta3no.text.toString()
            }
        }
        return viveSolo
    }

    fun obtenerViveconAdulto(): String {
        var viveconAdulto = ""
        if (binding.rgPregunta4.isEnabled) {
            when (binding.rgPregunta4.checkedRadioButtonId) {
                R.id.rbPregunta4si -> {
                    viveconAdulto = binding.rbPregunta4si.text.toString()
                }
                R.id.rbPregunta4no -> {
                    viveconAdulto = binding.rbPregunta4no.text.toString()
                }
            }
        }
        return viveconAdulto
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

