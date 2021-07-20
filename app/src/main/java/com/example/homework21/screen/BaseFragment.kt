package com.example.homework21.screen

import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.homework21.databinding.ErrorDialogLayoutBinding
import com.example.homework21.extension.init

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T


abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: Inflate<VB>,
) : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null) {
            _binding = inflate(inflater, container, false)
            start()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun start()

    fun showErrorDialog(message:String){
        val dialog = Dialog(requireContext())
        val dialogBinding = ErrorDialogLayoutBinding.inflate(layoutInflater)
        dialog.init(dialogBinding.root)
        dialogBinding.tvDescription.text = message
        dialogBinding.btnClose.setOnClickListener {
            dialog.cancel()
        }
        dialog.show()
    }

    fun hasCameraPermission() = ActivityCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

    fun hasWriteExternalStoragePermission() = ActivityCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    fun requestPermissions(reques:ActivityResultLauncher<Array<String>>){
        reques.launch(
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        )
    }

}