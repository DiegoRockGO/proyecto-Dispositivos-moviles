package com.elkiplangat.barcodescanner

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController


private const val PERMISSIONS_REQUEST_CODE = 10
private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.CAMERA)
class PermissionsFragment : Fragment() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasPermissions(requireContext())) {

            requestPermissions(PERMISSIONS_REQUIRED, PERMISSIONS_REQUEST_CODE)
        } else {

            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container).navigate(
                R.id.action_permissionsFragment_to_cameraFragment)
            view?.findNavController()?.navigate(
                R.id.action_permissionsFragment_to_cameraFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (PackageManager.PERMISSION_GRANTED == grantResults.firstOrNull()) {

                Toast.makeText(context, "PERMISO ACEPTADO", Toast.LENGTH_LONG).show()
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_container).navigate(
                    R.id.action_permissionsFragment_to_cameraFragment)
            } else {
                Toast.makeText(context, "PERMISO DENEGADO", Toast.LENGTH_LONG).show()
            }
        }
    }
    companion object {


        fun hasPermissions(context: Context) = PERMISSIONS_REQUIRED.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }  }
}