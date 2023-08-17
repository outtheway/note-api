package com.nurseitov.notesapi.service;

import com.nurseitov.notesapi.model.Note;

public interface NoteService {
    void createNote(String title, String content);

    Note getNote(Long id);

    void deleteNote(Long id);

    void updateNote(Long id, String title, String content);
}
