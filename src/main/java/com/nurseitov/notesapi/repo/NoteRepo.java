package com.nurseitov.notesapi.repo;

import com.nurseitov.notesapi.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note, Long> {
}
