package com.backyardigans.doggiecare.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.backyardigans.doggiecare.R
import com.backyardigans.doggiecare.databinding.FragmentDetailsBinding
import com.backyardigans.doggiecare.viewModel.ProfileViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by activityViewModels()
    private lateinit var pic : String
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val userNick = arguments?.getString("userNick").toString()
        val userMail = arguments?.getString("userMail").toString()


        binding.detalleBotonMore.setOnClickListener {
            val directions = DetailsFragmentDirections.actionDetailsFragmentToContactPopUpFragment(
                userNick, userMail, pic
            )
            findNavController().navigate(directions)
        }
        Glide.with(requireContext()).load(arguments?.getString("urlImage"))
            .transform(CenterCrop(), RoundedCorners(40)).placeholder(R.drawable.ic_icon_perrito)
            .into(binding.detalleFotoMascota)
        binding.detalleNombreMascota.text =
            arguments?.getString("animalName") + " ● " + arguments?.getString("animalAge")
        binding.detalleDuenioMascota.text = userNick
        binding.detalleUsuarioMascota.text = userMail
        binding.detalleDescripcionMascota.text = arguments?.getString("description")
        profileViewModel.userPhotoProfile.observe(viewLifecycleOwner, {
pic = it.userPic
            Glide.with(view?.context!!).load(it.userPic)
                .transform( CenterCrop(), CircleCrop()).error(R.drawable.ic_maleowner)
                .into(binding.detalleFotoUsuario)
        })

        profileViewModel.updatePhotoProfile(userMail)
        return binding.root
    }
}