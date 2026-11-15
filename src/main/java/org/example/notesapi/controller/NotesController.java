package org.example.notesapi.controller;

import jakarta.validation.Valid;
import org.example.notesapi.exception.NoteNotFoundException;
import org.example.notesapi.records.NoteRequest;
import org.example.notesapi.repository.NoteRepository;
import org.example.notesapi.service.NoteService;
import org.example.notesapi.entity.Note;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NotesController {

    private final NoteService noteService;


    public NotesController(NoteService noteService, NoteRepository noteRepository) {
        this.noteService = noteService;
    }

    @GetMapping()
    public List<Note> getAll() {
        return noteService.getAllNotes();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@Valid @RequestBody NoteRequest note) {
        return noteService.saveNote(note);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK   )
    public Note getNote(@PathVariable long id){
        return noteService.getNoteById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable long id) throws NoteNotFoundException {
        noteService.deleteNoteById(id);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable long id, @Valid @RequestBody NoteRequest note) {
        return noteService.updateNote(id,note);
    }
}
