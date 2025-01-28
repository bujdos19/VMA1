package com.example.notesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class AddNoteFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);

        EditText titleEditText = view.findViewById(R.id.editTextTitle);
        EditText descriptionEditText = view.findViewById(R.id.editTextDescription);
        Button saveButton = view.findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String description = descriptionEditText.getText().toString();

            if (!title.isEmpty() && !description.isEmpty()) {
                Note newNote = new Note(title, description);
                AppDatabase db = AppDatabase.getInstance(getActivity());
                db.noteDao().insert(newNote);

                // Vrátenie sa na zoznam poznámok
                Navigation.findNavController(view).navigate(R.id.action_addNoteFragment_to_notesListFragment);
            }
        });

        return view;
    }
}
