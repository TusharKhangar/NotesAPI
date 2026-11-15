package org.example.notesapi.records;

import jakarta.validation.constraints.NotBlank;

public record  NoteRequest(@NotBlank(message = "title is required") String title,
                           @NotBlank(message = "Content is required") String content){
}

