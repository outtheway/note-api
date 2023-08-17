package com.nurseitov.notesapi.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.nurseitov.notesapi.model.Note;
import com.nurseitov.notesapi.repo.NoteRepo;
import com.nurseitov.notesapi.service.NoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class NoteServiceImplTest {

    @Mock
    private NoteRepo noteRepo; // Мок репозитория для заметок

    @InjectMocks
    private NoteServiceImpl noteService; // Тестируемый сервис

    @Test
    public void testCreateNote() {
        // Вызов метода createNote
        noteService.createNote("Test Note", "This is a test note content.");

        // Проверяем, что метод save был вызван один раз
        verify(noteRepo, times(1)).save(any(Note.class));
    }

    @Test
    public void testGetNote() {
        // Создаем тестовую заметку
        Note testNote = new Note();
        testNote.setId(1L);
        testNote.setTitle("Test Note");
        testNote.setContent("This is a test note content.");

        // Указываем, что при вызове метода noteRepo.findById(1L) нужно вернуть тестовую заметку
        when(noteRepo.findById(1L)).thenReturn(Optional.of(testNote));

        // Вызываем тестируемый метод
        Note resultNote = noteService.getNote(1L);

        // Проверяем, что результат совпадает с тестовой заметкой
        assertEquals("Test Note", resultNote.getTitle());
        assertEquals("This is a test note content.", resultNote.getContent());
    }

    @Test
    public void testDeleteNote() {
        // Вызов метода deleteNote
        noteService.deleteNote(1L);

        // Проверяем, что метод deleteById был вызван один раз с аргументом 1L
        verify(noteRepo, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateNote() {
        // Создаем тестовую заметку
        Note testNote = new Note();
        testNote.setId(1L);

        // Указываем, что при вызове метода noteRepo.findById(1L) нужно вернуть тестовую заметку
        when(noteRepo.findById(1L)).thenReturn(Optional.of(testNote));

        // Вызов метода updateNote
        noteService.updateNote(1L, "Updated Title", "Updated content.");

        // Проверяем, что метод save был вызван один раз
        verify(noteRepo, times(1)).save(any(Note.class));

        // Проверяем, что заметка была обновлена
        assertEquals("Updated Title", testNote.getTitle());
        assertEquals("Updated content.", testNote.getContent());
    }
}
