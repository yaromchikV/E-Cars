package com.yaromchikv.ecars.fragments.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.yaromchikv.ecars.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}