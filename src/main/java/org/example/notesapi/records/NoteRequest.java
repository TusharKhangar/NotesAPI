package org.example.notesapi.records;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record  NoteRequest(@Size(max = 200, message="title must be at most 200 characters")@NotBlank(message = "title is required") String title,
                          @Size(max = 10000, message = "content must be at most 10000 characters") @NotBlank(message = "content is required") String content){
}

