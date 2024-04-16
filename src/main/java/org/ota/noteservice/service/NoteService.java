package org.ota.noteservice.service;

import org.ota.noteservice.data.Note;
import org.ota.noteservice.data.NotesResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    private static final List<Note> notes = new ArrayList<>();
    private static Long nextId = 1L;

    public Note addNote(Note note) {
        note.setId(nextId++);
        notes.add(note);
        return note;
    }

    public NotesResponse getNotes() {
        NotesResponse response = new NotesResponse();
        response.setNotes(notes);

        return response;
    }
}
