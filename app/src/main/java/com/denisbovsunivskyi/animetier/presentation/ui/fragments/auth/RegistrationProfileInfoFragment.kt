package com.denisbovsunivskyi.animetier.presentation.ui.fragments.auth

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.denisbovsunivskyi.animetier.core.fragment.BaseBindingFragment
import com.denisbovsunivskyi.animetier.databinding.FragmentRegistrationProfileInfoBinding
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.auth.RegistrationProfileActions
import com.denisbovsunivskyi.animetier.presentation.ui.viewmodels.auth.RegistrationProfileViewModel
import com.denisbovsunivskyi.animetier.presentation.utils.clearFiles
import com.denisbovsunivskyi.animetier.presentation.utils.constatns.ERROR_TAG
import com.denisbovsunivskyi.animetier.presentation.utils.extentions.loadNewImageFromUri
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationProfileInfoFragment :
    BaseBindingFragment<FragmentRegistrationProfileInfoBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegistrationProfileInfoBinding
        get() = FragmentRegistrationProfileInfoBinding::inflate
    private val registrationProfileViewModel by viewModels<RegistrationProfileViewModel>()
    override fun init() {
        initClearFocusInputs()
    }

    override fun initViewModels() {
        binding.model = registrationProfileViewModel.registrationProfileModel
        registrationProfileViewModel.getEventLiveData().observe(viewLifecycleOwner) { state ->
            val event = state.contentIfNotHandled
            event?.let {
                binding.registerBtn.isEnabled = true
                when (event) {
                    is RegistrationProfileActions.Success.RegistrationSuccess -> {
                        openHomeScreen()
                    }
                    is RegistrationProfileActions.Failed.RegistrationFailed ->{
                        context?.let {
                            Toast.makeText(it, event.message.asString(it), Toast.LENGTH_SHORT).show()
                        }
                    }
                    is RegistrationProfileActions.Failed.NoUserCredentialsAvailable -> {
                        context?.let {
                            Toast.makeText(it, event.message.asString(it), Toast.LENGTH_SHORT).show()
                            openAuthScreen()
                        }
                    }
                    is RegistrationProfileActions.Failed.NickNameIsEmpty ->{
                        context?.let {
                            Toast.makeText(it, event.message.asString(it), Toast.LENGTH_SHORT).show()
                        }
                    }
                    is RegistrationProfileActions.Loading ->{
                    }
                }
            }
        }
    }
    override fun initListeners() {
        binding.profileImage.setOnClickListener {
            startLoadImage()
        }
        binding.registerBtn.setOnClickListener {
            registrationProfileViewModel.validateFields()
        }
    }

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            // Use the returned uri.
            result.uriContent?.let { handleCropResult(it) }
            result.getUriFilePath(requireContext(), false)
        } else {
            // An error occurred.
            val exception = result.error
            Log.i(ERROR_TAG, exception.toString())
        }
        clearFiles(requireContext().cacheDir)
    }

    private fun handleCropResult(uriContent: Uri) {
        registrationProfileViewModel.registrationProfileModel.photo = uriContent
        binding.profileImage.loadNewImageFromUri(uriContent)
    }

    private fun startLoadImage() {
        val cropImageOptions = CropImageOptions(
            imageSourceIncludeCamera = true,
            imageSourceIncludeGallery = true,
            aspectRatioX = 4,
            aspectRatioY = 4,
            fixAspectRatio = true,
            guidelines = CropImageView.Guidelines.ON,
            cropShape = CropImageView.CropShape.OVAL,
            cornerShape = CropImageView.CropCornerShape.OVAL
        )
        val cropImageContractOptions = CropImageContractOptions(null, cropImageOptions)
        cropImage.launch(
            cropImageContractOptions
        )
    }
    private fun initClearFocusInputs() {
        binding.editNickName.setTargetForCleanFocus(binding.inputNickName)
        binding.editNickName.setNextTargetView(binding.editAbout)
        binding.editAbout.setTargetForCleanFocus(binding.inputAbout)

    }
    private fun openAuthScreen() {
        findNavController().navigate(RegistrationProfileInfoFragmentDirections.actionGlobalAuth())
    }
    private fun openHomeScreen(){
        findNavController().navigate(RegistrationProfileInfoFragmentDirections.actionRegistrationProfileInfoFragmentToHomeList())
    }
}