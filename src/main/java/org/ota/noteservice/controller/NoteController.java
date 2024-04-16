package org.ota.noteservice.controller;

import lombok.AllArgsConstructor;
import org.ota.noteservice.data.NotesResponse;
import org.ota.noteservice.data.Note;
import org.ota.noteservice.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        Note createdNote = noteService.addNote(note);
        return ResponseEntity.ok(createdNote);
    }

    @GetMapping
    public ResponseEntity<NotesResponse> getNotes() {
        NotesResponse response = noteService.getNotes();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getNote(@PathVariable("noteId") Long noteId) {
        Note note = noteService.getNote(noteId);
        return ResponseEntity.ok(note);
    }

    @PatchMapping("/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable("noteId") Long noteId, @RequestBody Note note) {
        Note updatedNote = noteService.updateNote(noteId, note);
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable("noteId") Long noteId) {
        noteService.deleteNote(noteId);
        return ResponseEntity.noContent().build();
    }
}
