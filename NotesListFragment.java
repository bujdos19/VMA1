package com.example.notesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NotesListFragment extends Fragment {
    private NoteAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);

        // Nastavenie RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Načítanie poznámok z databázy
        AppDatabase db = AppDatabase.getInstance(getActivity());
        List<Note> notes = db.noteDao().getAllNotes();

        // Nastavenie adaptéra pre RecyclerView
        adapter = new NoteAdapter(notes);
        recyclerView.setAdapter(adapter);

        // Akcia pre FloatingActionButton (FAB) – navigácia na obrazovku pridania poznámky
        view.findViewById(R.id.fab).setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_notesListFragment_to_addNoteFragment);
        });

        return view;
    }
}
