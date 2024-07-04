package com.example.notesappmvvmkotlin.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappmvvmkotlin.Model.Notes
import com.example.notesappmvvmkotlin.R
import com.example.notesappmvvmkotlin.databinding.ItemNotesBinding
import com.example.notesappmvvmkotlin.ui.Fragments.HomeFragmentDirections

class NotesAdapter(val requireContext: Context,var notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>(){

        fun filtering(newFilteredList: ArrayList<Notes>) {
           notesList=newFilteredList
            notifyDataSetChanged()
        }

    class notesViewHolder(val binding:ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
      val data = notesList[position]
        holder.binding.notesTitle.text = data.title
        holder.binding.notesSubTitle.text = data.subTitile
        holder.binding.notesDate.text = data.date

        when(data.priority)
        {
           "1"->{
               holder.binding.ViewPriority.setBackgroundResource(R.drawable.green_dot)
           }
            "2"->{
                holder.binding.ViewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3"->{
                holder.binding.ViewPriority.setBackgroundResource(R.drawable.red_dot)
            }


        }

        holder.binding.root.setOnClickListener(){
            val action=HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount()=notesList.size
}