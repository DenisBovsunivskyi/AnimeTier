package com.denisbovsunivskyi.animetier.presentation.ui.fragments.auth

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.canhub.cropper.CropImageView
import com.denisbovsunivskyi.animetier.core.fragment.BaseBindingFragment
import com.denisbovsunivskyi.animetier.databinding.FragmentRegistrationProfileInfoBinding
import com.denisbovsunivskyi.animetier.presentation.utils.clearFiles
import com.denisbovsunivskyi.animetier.presentation.utils.constatns.CROP_FILE_NAME_DIR
import com.denisbovsunivskyi.animetier.presentation.utils.constatns.ERROR_TAG
import com.denisbovsunivskyi.animetier.presentation.utils.extentions.loadNewImageFromUri
import java.io.File


class RegistrationProfileInfoFragment :
    BaseBindingFragment<FragmentRegistrationProfileInfoBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegistrationProfileInfoBinding
        get() = FragmentRegistrationProfileInfoBinding::inflate

    override fun init() {

    }

    override fun initListeners() {
        binding.profileImage.setOnClickListener {
            startLoadImage()
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
        val fileTemp = File(activity?.cacheDir, CROP_FILE_NAME_DIR)
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
}