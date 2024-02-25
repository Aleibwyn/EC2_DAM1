package pe.edu.idat.ec2_dam1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.edu.idat.ec2_dam1.databinding.ItemListaBinding

class AdapterLista(private var listItem: ArrayList<Libro>) : RecyclerView.Adapter<AdapterLista.ViewHolder>() {
    inner class ViewHolder(val binding: ItemListaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding  = ItemListaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvTituloLibro.text = listItem[position].titulo
        holder.binding.tvDescripcion.text = listItem[position].descripcion
        holder.binding.tvFechaPublicacion.text = listItem[position].publicacion
    }

}