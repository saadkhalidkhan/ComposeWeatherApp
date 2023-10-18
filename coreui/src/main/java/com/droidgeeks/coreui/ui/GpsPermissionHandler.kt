package com.droidgeeks.coreui.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import com.droidgeeks.coreui.R

import com.droidgeeks.coreui.ui.theme.CellStroke

@Composable
fun EnableGPS(
    context: Context,
    permissions: Array<String>,
    onAllowed: (Boolean) -> Unit,
) {
    val openDialog = remember { mutableStateOf(true) }
    var requestPermission by remember { mutableStateOf(false) }

    if (openDialog.value) {
        GPSPermissionDialog() {
            if (it) {
                requestPermission = true
            }else{
                onAllowed.invoke(false)
                openDialog.value = false
            }
        }
    }

    val appSettingsLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            if (permissions.all {
                    ContextCompat.checkSelfPermission(
                        context,
                        it
                    ) == PackageManager.PERMISSION_GRANTED
                }) {
                if (isLocationEnabled(context)) {
                    onAllowed.invoke(true)
                    openDialog.value=false
                } else {
                    openDialog.value = true
                }
            }
        }
    LaunchedEffect(key1 = requestPermission) {
        if(requestPermission){
            appSettingsLauncher.launch(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))

        }
        requestPermission = false
    }


}

private fun isLocationEnabled(context: Context): Boolean {
    val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
}

@Composable
fun GPSPermissionDialog(onDismiss: (Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = {
            onDismiss.invoke(false)
        },
        title = {
            Text(text = "GPS is Disabled")
        },
        text = {
            Text(text = stringResource(id = R.string.gps_is_disable_please_enable_gps_to_fetch_your_location))
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismiss.invoke(true)
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss.invoke(false)
                }
            ) {
                Text("Dismiss")
            }
        },
        backgroundColor = CellStroke
    )
}