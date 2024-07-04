package com.example.notesappmvvmkotlin.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesappmvvmkotlin.Model.Notes
import com.example.notesappmvvmkotlin.R
import com.example.notesappmvvmkotlin.ViewModel.NotesViewModel
import com.example.notesappmvvmkotlin.databinding.FragmentHomeBinding
import com.example.notesappmvvmkotlin.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

     lateinit var binding: FragmentHomeBinding
     val viewModel: NotesViewModel by viewModels()
      var oldMyNotes = arrayListOf<Notes>()
     lateinit var adapter: NotesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)

         val staggeredGridLayoutManager=
             StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
             binding.rcvAllNotes.layoutManager=staggeredGridLayoutManager

        //get all notes
        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            // binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter=NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter
        }

        //filter high
        binding.filterHigh.setOnClickListener{

            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                //  binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter=NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }
        //filter all notes
        binding.allNotes.setOnClickListener{
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                //  binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter=NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }

        //filter low
        binding.filterLow.setOnClickListener{
            viewModel.getlowNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                //   binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter=NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }

        //filter medium
        binding.filterMedium.setOnClickListener{
            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                //   binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter=NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }
        }


        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)

        val item=menu.findItem(R.id.app_bar_search)
        val searchView=item.actionView as SearchView
        searchView.queryHint="Enter Notes Here....."
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
              return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun NotesFiltering(p0: String?) {
        val newFilteredList= arrayListOf<Notes>()

        for(i in oldMyNotes){
         if(i.title.contains(p0!!) || i.subTitile.contains(p0!!) )
         {
             newFilteredList.add(i)
         }
        }
        adapter.filtering(newFilteredList)
    }
}