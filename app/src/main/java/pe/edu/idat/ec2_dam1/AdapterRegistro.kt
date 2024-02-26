package pe.edu.idat.ec2_dam1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterRegistro(private val registros: ArrayList<String>) : RecyclerView.Adapter<AdapterRegistro.RegistroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_registro, parent, false)
        return RegistroViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RegistroViewHolder, position: Int) {
        val registro = registros[position]
        holder.bind(registro)
    }

    override fun getItemCount(): Int {
        return registros.size
    }

    inner class RegistroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewDni: TextView = itemView.findViewById(R.id.textViewDni)
        private val textViewNombreyApellidos: TextView = itemView.findViewById(R.id.textViewNombreyApellidos)
        private val textViewGenero: TextView = itemView.findViewById(R.id.textViewGenero)
        private val textViewHobbies: TextView = itemView.findViewById(R.id.textViewHobbies)
        private val textViewEmail: TextView = itemView.findViewById(R.id.textViewEmail)
        private val textViewPassword: TextView = itemView.findViewById(R.id.textViewPassword)


        fun bind(registro: String) {
            val datos = registro.split(", ")

            textViewDni.text = datos[0]
            textViewNombreyApellidos.text = datos[1]
            textViewGenero.text = datos[2]
            textViewHobbies.text = datos[3]
            textViewEmail.text = datos[4]
            textViewPassword.text = datos[5]
        }

    }
}