package edu.iest.androidwear

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import edu.iest.androidwear.databinding.ActivityRelojBinding

class MainActivity : Activity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityRelojBinding
    private var textoSeleccionado: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRelojBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvSaludo.text="Holis"
        binding.bnCambio.text="Enviar"

        val adaptador = ArrayAdapter.createFromResource(this,R.array.misOpciones,android.R.layout.simple_spinner_item)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spOpciones.adapter=adaptador
        binding.spOpciones.onItemSelectedListener=this

        binding.bnCambio.setOnClickListener {
            val alerta = AlertDialog.Builder(this)
            alerta.setTitle("Atencion").setMessage("Quiere enviar el saludo $textoSeleccionado")
                .setCancelable(false)
                .setPositiveButton("OK",DialogInterface.OnClickListener{
                    dialogInterface, i ->
                    //aqui el codigo
                    binding.tvSaludo.text=textoSeleccionado
                })
                .setNegativeButton("Cancelar",DialogInterface.OnClickListener{
                    dialogInterface, i ->
                    Toast.makeText(this,"Una lastima :(", Toast.LENGTH_SHORT).show()
                }).show()
        }

    }

    //La vista del adaptador
    override fun onItemSelected(vistaPadre: AdapterView<*>?, vistaRow: View?, position: Int, idVista: Long) {
        textoSeleccionado= vistaPadre?.getItemAtPosition(position).toString()
        Toast.makeText(this,"Elegiste $textoSeleccionado",Toast.LENGTH_LONG).show()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}