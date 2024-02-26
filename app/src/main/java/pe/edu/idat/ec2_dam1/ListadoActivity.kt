package pe.edu.idat.ec2_dam1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import pe.edu.idat.ec2_dam1.databinding.ActivityListadoBinding


class ListadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvListaLibros.layoutManager = LinearLayoutManager(applicationContext)
        binding.rvListaLibros.adapter = AdapterLista(listadoLibros())
    }

    fun listadoLibros(): ArrayList<Libro> {
        return arrayListOf(
            Libro(
                "Gray's Anatomy",
                "Un tratado anatómico escrito por Henry Gray",
                "24/11/1858"
            ),
            Libro(
                "Principios de Anatomía y Fisiología",
                "Un libro de texto de medicina escrito por Gerard J. Tortora y Bryan H. Derrickson",
                "01/01/1980"
            ),
            Libro(
                "Harrison's Principles of Internal Medicine",
                "Un libro de medicina interna, escrito por Anthony S. Fauci, et al.",
                "01/01/1950"
            ),
            Libro(
                "The Merck Manual of Diagnosis and Therapy",
                "Un manual médico para profesionales de la salud, publicado por Merck & Co.",
                "01/01/1899"
            ),
            Libro(
                "Robbins and Cotran Pathologic Basis of Disease",
                "Un libro de texto de patología médica, escrito por Vinay Kumar, Abul K. Abbas y Jon C. Aster",
                "01/01/1957"
            ),
            Libro(
                "Williams Obstetrics",
                "Un libro de texto sobre obstetricia, escrito por F. Gary Cunningham, et al.",
                "01/01/1903"
            ),
            Libro(
                "Netter's Atlas of Human Anatomy",
                "Un atlas de anatomía humana, ilustrado por Frank H. Netter",
                "01/01/1989"
            ),
            Libro(
                "Current Medical Diagnosis and Treatment",
                "Un libro de referencia médica, editado por Maxine A. Papadakis y Stephen J. McPhee",
                "01/01/1965"
            ),
            Libro(
                "Guyton and Hall Textbook of Medical Physiology",
                "Un libro de texto de fisiología médica, escrito por John E. Hall",
                "01/01/1956"
            ),
            Libro(
                "The Principles and Practice of Medicine",
                "Un libro de medicina interna, escrito por William Osler",
                "01/01/1892"
            )
        )
    }
}