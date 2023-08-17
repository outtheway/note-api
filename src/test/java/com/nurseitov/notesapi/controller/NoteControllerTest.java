package com.nurseitov.notesapi.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nurseitov.notesapi.controller.NoteController;
import com.nurseitov.notesapi.dto.NoteDTO;
import com.nurseitov.notesapi.model.Note;
import com.nurseitov.notesapi.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService; // Мок сервиса

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCreateNote() throws Exception {
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setTitle("Test Note");
        noteDTO.setContent("This is a test note content.");

        mockMvc.perform(post("/create-note")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(noteDTO)))
                .andExpect(status().isOk());

        verify(noteService, times(1)).createNote(eq("Test Note"), eq("This is a test note content."));
    }

    @Test
    public void testGetNote() throws Exception {
        Note testNote = new Note();
        testNote.setId(1L);
        testNote.setTitle("Test Note");
        testNote.setContent("This is a test note content.");

        when(noteService.getNote(1L)).thenReturn(testNote);

        mockMvc.perform(get("/get-note/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Note"))
                .andExpect(jsonPath("$.content").value("This is a test note content."));
    }

    @Test
    public void testDeleteNote() throws Exception {
        mockMvc.perform(delete("/delete-note/1"))
                .andExpect(status().isOk());

        verify(noteService, times(1)).deleteNote(1L);
    }

    @Test
    public void testUpdateNote() throws Exception {
        NoteDTO updatedNoteDTO = new NoteDTO();
        updatedNoteDTO.setTitle("Updated Note");
        updatedNoteDTO.setContent("Updated content.");

        mockMvc.perform(put("/update-note/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedNoteDTO)))
                .andExpect(status().isOk());

        verify(noteService, times(1)).updateNote(eq(1L), eq("Updated Note"), eq("Updated content."));
    }
}