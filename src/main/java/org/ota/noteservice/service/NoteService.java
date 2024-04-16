package org.ota.noteservice.service;

import org.ota.noteservice.data.Note;
import org.ota.noteservice.data.NotesResponse;
import org.ota.noteservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    // in-memory array, contains all the notes being stored
    private List<Note> notes = new ArrayList<>();

    // a simple counter to keep track of the note identifiers
    private Long nextId = 1L;

    private static final String NOTE_NOT_FOUND_EXCEPTION = "Note not found";

    public Note addNote(Note note) throws IOException {
        String title = note.getTitle();
        if (title == null || title.isEmpty()) {
            throw new IOException("Title must not be empty");
        }

        String body = note.getBody();
        if (body == null || body.isEmpty()) {
            throw new IOException("Body must not be empty");
        }

        note.setId(nextId++);
        notes.add(note);
        return note;
    }

    public NotesResponse getNotes() {
        NotesResponse response = new NotesResponse();
        response.setNotes(notes);

        return response;
    }

    public Note getNote(Long id) throws NotFoundException {
        Optional<Note> note = notes.stream().filter(n -> n.getId().equals(id)).findFirst();
        if (note.isEmpty()) {
            throw new NotFoundException(NOTE_NOT_FOUND_EXCEPTION);
        }

        return note.get();
    }

    public Note updateNote(Long id, Note note) throws NotFoundException, IOException {
        Note noteToUpdate = getNote(id);
        if (noteToUpdate == null) {
            throw new NotFoundException(NOTE_NOT_FOUND_EXCEPTION);
        }

        String title = note.getTitle();
        if (title == null || title.isEmpty()) {
            throw new IOException("Title must not be empty");
        }

        String body = note.getBody();
        if (body == null || body.isEmpty()) {
            throw new IOException("Body must not be empty");
        }

        int index = notes.indexOf(noteToUpdate);
        noteToUpdate.setTitle(note.getTitle());
        noteToUpdate.setBody(note.getBody());
        notes.set(index, noteToUpdate);

        return noteToUpdate;
    }

    public void deleteNote(Long id) throws NotFoundException {
        Note note = getNote(id);
        if (note == null) {
            throw new NotFoundException(NOTE_NOT_FOUND_EXCEPTION);
        }

        notes.remove(note);
    }
}
