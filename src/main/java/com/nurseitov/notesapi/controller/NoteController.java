package com.nurseitov.notesapi.controller;

import com.nurseitov.notesapi.model.Note;
import com.nurseitov.notesapi.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NoteController {

    private NoteService noteService;

    @PostMapping("/create-note")
    public void createNote(@RequestBody Note note){
        noteService.createNote(note.getTitle(), note.getContent());
    }
}
