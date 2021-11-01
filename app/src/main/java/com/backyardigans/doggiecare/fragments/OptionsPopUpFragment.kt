package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.FragmentPopupOptionsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class OptionsPopUpFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentPopupOptionsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPopupOptionsBinding.inflate(inflater, container, false)

        if (arguments?.getString("previous").equals("post")) {
            binding.popupoptions1.text=requireContext().getString(R.string.editar_publicacion)
            binding.popupoptions2.text= requireContext().getString(R.string.eliminar_publicacion)

            binding.popupoptions1.setOnClickListener {
                findNavController().navigate(R.id.action_optionsPopUpFragment_to_addFragment)
                //todo editar publicación
            }

            binding.popupoptions2.setOnClickListener {
                Toast.makeText(activity, "Publicación eliminada", Toast.LENGTH_SHORT).show()
                //todo eliminar publicación
            }


        } else if (arguments?.getString("previous").equals("add") or arguments?.getString("previous").equals("editProfile") ) {
            binding.popupoptions1.text=requireContext().getString(R.string.camara)
            binding.popupoptions2.text= requireContext().getString(R.string.galeria)
                //todo if camara y galeria
        } else if (arguments?.getString("previous").equals("block") or arguments?.getString("previous").equals("report") or arguments?.getString("previous").equals("verification")) {
            binding.popupoptions1.text=requireContext().getString(R.string.proseguir)
            binding.popupoptions2.text= requireContext().getString(R.string.abortar)
                //todo bloquear verificar y reportar
        }




        return binding.root
    }
}