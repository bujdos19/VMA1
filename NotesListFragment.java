package com.example.notesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NotesListFragment extends Fragment {
    private NoteAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        AppDatabase db = AppDatabase.getInstance(getActivity());
        List<Note> notes = db.noteDao().getAllNotes();

        adapter = new NoteAdapter(notes);
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.fab).setOnClickListener(v -> {
            // Navigácia na pridanie poznámky
        });

        return view;
    }
}
