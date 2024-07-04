package com.example.notesappmvvmkotlin.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesappmvvmkotlin.Model.Notes
import com.example.notesappmvvmkotlin.ViewModel.NotesViewModel
import com.example.notesappmvvmkotlin.databinding.FragmentCreateNotesBinding
import java.util.*
//import android.R

class CreateNotesFragment : Fragment() {

    lateinit var binding:FragmentCreateNotesBinding
    var priority: String="1"
    val viewModel:NotesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater,container,false)
        binding.pGreen.setImageResource(com.example.notesappmvvmkotlin.R.drawable.baseline_done_24)

         binding.pGreen.setOnClickListener{
             priority="1"
         binding.pGreen.setImageResource(com.example.notesappmvvmkotlin.R.drawable.baseline_done_24)
             binding.pYellow.setImageResource(0)
             binding.pRed.setImageResource(0)
         }
        binding.pYellow.setOnClickListener{
            priority="2"
          binding.pYellow.setImageResource(com.example.notesappmvvmkotlin.R.drawable.baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.pRed.setOnClickListener{
            priority="3"
             binding.pRed.setImageResource( com.example.notesappmvvmkotlin.R.drawable.baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }



        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it:View?){
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubTitle.text.toString()
        val notes = binding.editNotes.text.toString()

        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.time)

        val data = Notes(
            null, title, subTitle, notes, notesDate.toString(), priority.toString()
        )
       viewModel.addNotes(data)
        Toast.makeText(requireContext(),"Notes Created Successfully",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(com.example.notesappmvvmkotlin.R.id.action_createNotesFragment_to_homeFragment)
    }
}