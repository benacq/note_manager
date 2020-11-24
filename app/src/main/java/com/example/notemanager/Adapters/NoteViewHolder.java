package com.example.notemanager.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanager.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView title;
    private final TextView description;
    private final TextView priority;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.note_title);
        description = itemView.findViewById(R.id.note_description);
        priority = itemView.findViewById(R.id.note_priority);

//        itemView.setOnClickListener(v -> {
//            if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
//                listener.onItemClick(mNotes.get(getAdapterPosition()));
//            }
//        });
    }

    public void bind(String title, String description, int priority) {
        this.title.setText(title);
        this.description.setText(description);
        this.priority.setText(String.valueOf(priority));
    }

    static NoteViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_item, parent, false);
        return new NoteViewHolder(view);
    }
}
