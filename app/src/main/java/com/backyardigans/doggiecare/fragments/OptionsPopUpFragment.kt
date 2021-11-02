package com.backyardigans.doggiecare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
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
            binding.popupoptions1.text = requireContext().getString(R.string.editar_publicacion)
            binding.popupoptions2.text = requireContext().getString(R.string.eliminar_publicacion)

            binding.popupoptions1.setOnClickListener {
                findNavController().navigate(R.id.action_optionsPopUpFragment_to_addFragment)
                //todo editar publicación
            }

            binding.popupoptions2.setOnClickListener {
                Toast.makeText(activity, "Publicación eliminada", Toast.LENGTH_SHORT).show()
                //todo eliminar publicación
            }


        } else if (arguments?.getString("previous")
                .equals("add") or arguments?.getString("previous").equals("editProfile")
        ) {
            binding.popupoptions1.text = requireContext().getString(R.string.camara)
            binding.popupoptions2.text = requireContext().getString(R.string.galeria)

            binding.popupoptions1.setOnClickListener {
                setFragmentResult("requestKey", bundleOf("camorgal" to "cam"))
                findNavController().popBackStack()
            }

            binding.popupoptions2.setOnClickListener {
                setFragmentResult("requestKey", bundleOf("camorgal" to "gal"))
                findNavController().popBackStack()
            }
        } else if (arguments?.getString("previous")
                .equals("block") or arguments?.getString("previous")
                .equals("report") or arguments?.getString("previous").equals("verification")
        ) {
            binding.popupoptions1.text = requireContext().getString(R.string.proseguir)
            binding.popupoptions2.text = requireContext().getString(R.string.abortar)

            binding.popupoptions1.setOnClickListener {
                Toast.makeText(activity, "Su solicitud ha sido enviada", Toast.LENGTH_SHORT).show()
                //todo bloquear abortar verificar
            }

            binding.popupoptions2.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        return binding.root
    }
}