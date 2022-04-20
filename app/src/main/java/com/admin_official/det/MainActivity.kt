package com.admin_official.det

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.admin_official.det.databinding.ActivityMainBinding
import com.admin_official.det.databinding.SettingsDialogBinding

var DAILY_LIMIT = 100
var MONTHLY_LIMIT = 3000
var YEARLY_LIMIT = 36000

const val DAILY_LIMIT_KEY = "daily_limit_key"
const val MONTHLY_LIMIT_KEY = "monthly_limit_key"
const val YEARLY_LIMIT_KEY = "yearly_limit_key"
const val APP_SHARED_PREFERENCES_KEY = "app_shared_preferences"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel.updateLimits()

        setSupportActionBar(binding.mainToolbar)

        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_settings -> openSettingsDialog()
            R.id.menu_history -> findNavController(R.id.main_navigation).navigate(R.id.action_frag_main_to_frag_history)
        }

        return super.onOptionsItemSelected(item)
    }

    fun openSettingsDialog() {
        val binding = SettingsDialogBinding.inflate(layoutInflater)
        binding.dailyLimit.setText(DAILY_LIMIT.toString())
        binding.monthlyLimit.setText(MONTHLY_LIMIT.toString())
        binding.yearlyLimit.setText(YEARLY_LIMIT.toString())

        val dialog = AlertDialog.Builder(this).setView(binding.root).create()

        binding.save.setOnClickListener {
            onSaveClicked(binding, dialog)
            dialog.dismiss()
        }

        dialog.setCanceledOnTouchOutside(true)
//        dialog.setTitle("Settings")
        dialog.show()
    }

    fun onSaveClicked(binding: SettingsDialogBinding, dialog: Dialog) {
        if(binding.dailyLimit.text.equals(DAILY_LIMIT) && binding.monthlyLimit.text.equals(MONTHLY_LIMIT)
            && binding.yearlyLimit.text.equals(YEARLY_LIMIT)) return

        if(binding.dailyLimit.text.isEmpty() || binding.monthlyLimit.text.isEmpty() || binding.yearlyLimit.text.isEmpty()) return

        with(getSharedPreferences(APP_SHARED_PREFERENCES_KEY, 0).edit()) {
            putInt(DAILY_LIMIT_KEY, binding.dailyLimit.text.toString().toInt())
            putInt(MONTHLY_LIMIT_KEY, binding.monthlyLimit.text.toString().toInt())
            putInt(YEARLY_LIMIT_KEY, binding.yearlyLimit.text.toString().toInt())
            apply()
        }

        viewModel.updateLimits()
    }


}