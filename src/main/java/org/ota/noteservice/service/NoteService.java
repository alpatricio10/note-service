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

    public Note getNote(Long id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                return note;
            }
        }
        return null;
    }

    public Note updateNote(Long id, Note note) {
        Note noteToUpdate = getNote(id);
        if (noteToUpdate == null) {
            // add exception here
            return null;
        }

        int index = notes.indexOf(noteToUpdate);
        noteToUpdate.setTitle(note.getTitle());
        noteToUpdate.setBody(note.getBody());
        notes.set(index, noteToUpdate);

        return noteToUpdate;
    }

    public void deleteNote(Long id) {
        notes.removeIf(note -> note.getId().equals(id));
    }
}
