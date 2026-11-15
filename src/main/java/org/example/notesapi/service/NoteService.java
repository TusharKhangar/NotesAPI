package org.example.notesapi.service;

import jakarta.validation.Valid;
import org.example.notesapi.exception.NoteNotFoundException;
import org.example.notesapi.records.NoteRequest;
import org.example.notesapi.repository.NoteRepository;
import org.example.notesapi.entity.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note saveNote(NoteRequest note) {
        Note noteEntity = new Note();
        noteEntity.setContent(note.content());
        noteEntity.setTitle(note.title());
        return noteRepository.save(noteEntity);
    }
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
    public Note getNoteById(long id) {return noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));}
    public void deleteNoteById(long id) {
        if(!noteRepository.existsById(id)) {
            throw new NoteNotFoundException(id);
        }
        noteRepository.deleteById(id);
    }
    public boolean existById(long id) {noteRepository.existsById(id);
        return true;
    }

    public Note updateNote(long id, @Valid NoteRequest note) {
        var noteEntity = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id));
        noteEntity.setContent(note.content());
        noteEntity.setTitle(note.title());
        return noteRepository.save(noteEntity);
    }
}
