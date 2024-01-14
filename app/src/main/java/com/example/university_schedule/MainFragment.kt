package com.example.university_schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.example.university_schedule.databinding.FragmentMainBinding

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
                loginMethod.accessLogin(binding.edEmail.text.toString(), binding.edEmail.text.toString(), requireContext()){
                    if (it.contains("OK")){
                        findNavController().navigate(R.id.action_mainFragment_to_listDayOfWeekFragment)
                    } else Toast.makeText(requireContext(), "Неправильные данные", Toast.LENGTH_SHORT).show()
                }
            }
            else Toast.makeText(requireContext(), "Вообще все ифы не прошел", Toast.LENGTH_SHORT).show()
        }
    }
}