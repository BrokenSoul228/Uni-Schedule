package com.example.university_schedule

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.example.university_schedule.databinding.FragmentMainBinding
import com.example.university_schedule.dto.User
import com.google.android.material.textfield.TextInputLayout

class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginMethod = RetrofitLogin()
        binding.serverId.setOnClickListener{
            showAlertWithEditText(requireContext())
        }
        binding.singIn.setOnClickListener {
            if (binding.edEmail.text?.isEmpty() == true) {
                binding.textInputLayout2.error = "Empty login"

                binding.textInputLayout2.editText?.doAfterTextChanged {
                    if (binding.textInputLayout2.editText?.text.isNullOrEmpty()) {
                        showErrorMessage(binding.textInputLayout2, "Empty")
                    } else {
                        binding.textInputLayout2.error = null
                    }
                }
            } else if (binding.edPassword.text?.isEmpty() == true) {
                binding.textInputLayout3.error = "Empty password"
                binding.textInputLayout3.editText?.doAfterTextChanged {
                    if (binding.textInputLayout3.editText?.text.isNullOrEmpty()) {
                        showErrorMessage(binding.textInputLayout3, "Empty")
                    } else {
                        binding.textInputLayout3.error = null
                    }
                }
            } else if (binding.edEmail.text?.isNotEmpty() == true && binding.edPassword.text?.isNotEmpty()== true) {
                val user = User(binding.edEmail.text.toString(), binding.edEmail.text.toString())
                loginMethod.accessLogin(binding.edEmail.text.toString(), binding.edPassword.text.toString(), requireContext()){
                    findNavController().navigate(R.id.action_mainFragment_to_listDayOfWeekFragment)
//                    when(it){
//                        "1" -> {shared(it)
//                        findNavController().navigate(R.id.action_mainFragment_to_listDayOfWeekFragment)}
//                        "2" -> {shared(it)
//                            findNavController().navigate(R.id.action_mainFragment_to_listDayOfWeekFragment)}
//                        "3" -> {shared(it)
//                            findNavController().navigate(R.id.action_mainFragment_to_listDayOfWeekFragment)}
//                        else ->{ Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()}
//                    }
                }
            }
            else Toast.makeText(requireContext(), "Вообще все ифы не прошел", Toast.LENGTH_SHORT).show()
        }
    }

    fun showAlertWithEditText(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Введите адрес")
        val inflater = LayoutInflater.from(context)
        val inputLayout = inflater.inflate(R.layout.edit_text_view, null) as TextInputLayout
        builder.setView(inputLayout)
        val input = inputLayout.editText?.text
        builder.setPositiveButton("OK") { dialog, which ->
            binding.serverId.text = input
            val enteredText = input
            saveAddressServer(enteredText.toString())
        }

        // Установка кнопки "Отмена" для закрытия диалогового окна
        builder.setNegativeButton("Отмена") { dialog, which ->
            dialog.cancel()
        }

        // Отображение диалогового окна
        builder.show()
    }

    fun saveAddressServer(text : String){
        val shared = context?.getSharedPreferences("ServerId", Context.MODE_PRIVATE)
        val editor = shared?.edit()?.putString("serverId", text)?.apply()
    }

    fun shared(ID : String){
        val shared = context?.getSharedPreferences("UserId", Context.MODE_PRIVATE)
        val editor = shared?.edit()?.putString("ID", ID)?.apply()
    }
}