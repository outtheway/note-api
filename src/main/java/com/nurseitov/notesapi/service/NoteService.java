package com.nurseitov.notesapi.service;

import com.nurseitov.notesapi.model.Note;

import java.util.Optional;

public interface NoteService {
    void createNote(String title, String content);

    Optional<Note> getNote(Long id);

    void deleteNote(Long id);
}
