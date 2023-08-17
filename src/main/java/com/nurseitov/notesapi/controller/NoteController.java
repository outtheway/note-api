package com.nurseitov.notesapi.controller;

import com.nurseitov.notesapi.dto.NoteDTO;
import com.nurseitov.notesapi.model.Note;
import com.nurseitov.notesapi.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class NoteController {

    private NoteService noteService;

    @PostMapping("/create-note")
    public void createNote(@RequestBody NoteDTO note){
        noteService.createNote(note.getTitle(), note.getContent());
    }

    @GetMapping("/get-note/{id}")
    public Note getNote(@PathVariable Long id) {
        return noteService.getNote(id);
    }
}
