package org.example.notesapi.service;

import org.example.notesapi.exception.NoteNotFoundException;
import org.example.notesapi.records.NoteRequest;
import org.example.notesapi.repository.NoteRepository;
import org.example.notesapi.entity.Note;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;
    @InjectMocks
    private NoteService noteService;

    @Test
    void getNoteById_when_Missing_throws_NotFoundException() {
        when(noteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoteNotFoundException.class, () -> noteService.getNoteById(99L));
    }

    @Test
    void deleteNoteById_throws_NotFoundException() {
        when(noteRepository.existsById(99L)).thenReturn(false);//set up

        assertThrows(NoteNotFoundException.class, () -> noteService.deleteNoteById(99L));
        //act + check

        verify(noteRepository, never()).deleteById(any());
    }

    @Test
    void updateNote_whenMissing_throwsNotFound() {
        when(noteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoteNotFoundException.class , () -> noteService.updateNote(99L, new NoteRequest("title", "content")));
        verify(noteRepository, never()).save(any());
    }

    @Test
    void updateNote_whenExists_changesTitleAndContent() {
        // 1. Set up: a note that already exists
        Note existing = new Note();
        existing.setTitle("old title");
        existing.setContent("old content");

        when(noteRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(noteRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        // 2. Act: update it
        Note result = noteService.updateNote(1L, new NoteRequest("new title", "new content"));

        // 3. Check: the values changed
        assertEquals("new title", result.getTitle());
        assertEquals("new content", result.getContent());
    }

    @Test
    void deleteNote_whenExists_deletesNote() {
        when(noteRepository.existsById(1L)).thenReturn(true);//setting up the situation

        noteService.deleteNoteById(1L); //acting on the situation

        verify(noteRepository).deleteById(1L); //verify
    }

    @Test
    void createNote_savesTitleAndContent() {
        // set up: when(noteRepository.save(any())) ... ???
        when(noteRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        // act: call noteService.createNote(...) ???
        var result = noteService.saveNote(new NoteRequest("new title", "new content"));

        // check: assertEquals(...)
        assertEquals("new title", result.getTitle());
        assertEquals("new content", result.getContent());
    }
}
