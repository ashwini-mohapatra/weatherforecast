package com.ashwini.weatherforecast.Authentication

import android.animation.Animator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.ashwini.weatherforecast.Screens.HomeFragment
import com.ashwini.weatherforecast.R
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability

class SplashFragment : Fragment() {

    val appUpdateManager: AppUpdateManager? = activity?.let { AppUpdateManagerFactory.create(it) }
    private val listener: InstallStateUpdatedListener? = InstallStateUpdatedListener { installState ->
        if (installState.installStatus() == com.google.android.play.core.install.model.InstallStatus.DOWNLOADED) {
            // After the update is downloaded, show a notification
            // and request user confirmation to restart the app.
            Log.d(TAG, "An update has been downloaded")
            Toast.makeText(activity,"Update Downloaded",Toast.LENGTH_SHORT).show()
        }
    }

    val MY_REQUEST_CODE:Int=100
    val TAG="Update"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_splash, container, false)

        val i1:ImageView=view.findViewById(R.id.imageView)
        i1.setAlpha(0f)

        i1.animate().alpha(1f).setDuration(3000).setListener(object: Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
//                // Returns an intent object that you use to check for an update.
//                listener?.let { appUpdateManager?.registerListener(it) }
//                val appUpdateInfoTask = appUpdateManager?.appUpdateInfo
//                // Checks that the platform will allow the specified type of update.
//                Log.d(TAG, "Checking for updates")
//                appUpdateInfoTask?.addOnSuccessListener { appUpdateInfo ->
//                    if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
//                            && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
//                        // Request the update.
//                        Log.d(TAG, "Update available")
//                        activity?.let { appUpdateManager?.startUpdateFlowForResult(appUpdateInfo,AppUpdateType.IMMEDIATE, it,MY_REQUEST_CODE) }
//                    } else {
//                        Log.d(TAG, "No Update available")
//                        listener?.let { appUpdateManager?.unregisterListener(it) }
                        activity!!.supportFragmentManager.beginTransaction().replace(R.id.mainframe,
                            HomeFragment()
                        ).commit()
//                    }
//              }

            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
        return view
    }
    override fun onResume() {
        super.onResume()
        appUpdateManager
                ?.appUpdateInfo
            ?.addOnSuccessListener { appUpdateInfo ->
                if (appUpdateInfo.updateAvailability()
                        == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                ) {
                    // If an in-app update is already running, resume the update.
                    activity?.let {
                        appUpdateManager?.startUpdateFlowForResult(
                                appUpdateInfo,
                                AppUpdateType.IMMEDIATE,
                                it,
                                MY_REQUEST_CODE
                        )
                    };
                }
            }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    Log.d(TAG, "" + "Result Ok")
                    //  handle user's approval }
                }
                Activity.RESULT_CANCELED -> {
                    {
//if you want to request the update again just call checkUpdate()
                    }
                    Log.d(TAG, "" + "Result Cancelled")
                    //  handle user's rejection  }
                }
                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
                    //if you want to request the update again just call checkUpdate()
                    Log.d(TAG, "" + "Update Failure")
                    //  handle update failure
                }
            }
        }
    }
}