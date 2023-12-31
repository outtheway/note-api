package com.nurseitov.notesapi.service;

import com.nurseitov.notesapi.model.Note;
import com.nurseitov.notesapi.repo.NoteRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService{
    private NoteRepo noteRepo;
    @Override
    public void createNote(String title, String content) {
        Note note = new Note();

        note.setTitle(title);
        note.setContent(content);
        noteRepo.save(note);
    }

    @Override
    public Note getNote(Long id) {
        Note note = noteRepo.findById(id).orElse(null);
        return note;
    }

    @Override
    public void deleteNote(Long id) {
        noteRepo.deleteById(id);
    }

    @Override
    public void updateNote(Long id, String title, String content) {
        Note note = noteRepo.findById(id).orElse(null);

        note.setTitle(title);
        note.setContent(content);
        noteRepo.save(note);
    }
}
