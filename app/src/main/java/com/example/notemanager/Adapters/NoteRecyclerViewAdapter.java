package com.example.notemanager.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notemanager.Entities.Note;
import com.example.notemanager.R;

import java.util.ArrayList;
import java.util.List;

public class NoteRecyclerViewAdapter extends ListAdapter<Note, NoteRecyclerViewAdapter.NoteViewHolder> {
    //    private List<Note> mNotes = new ArrayList<>();
    private OnItemClickListener listener;

    public NoteRecyclerViewAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDescription().equals(newItem.getDescription())
                    && oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
        return new NoteViewHolder(itemView);
    }

//    public void setNotes(List<Note> notes) {
//        this.mNotes = notes;
//        notifyDataSetChanged();
//    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.title.setText(currentNote.getTitle());
        holder.description.setText(currentNote.getDescription());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));
    }

//    @Override
//    public int getItemCount() {
//        return mNotes.size();
//    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;
        private final TextView priority;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.note_title);
            description = itemView.findViewById(R.id.note_description);
            priority = itemView.findViewById(R.id.note_priority);

            itemView.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}
