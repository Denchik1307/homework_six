package den.notes.controller;

import den.notes.model.Note;
import den.notes.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final NoteRepository noteRepository;

    @GetMapping
    public ResponseEntity<List<Note>> getNotes() {
        return ResponseEntity.ok(noteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNote(@PathVariable long id) {
        return noteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note createdNote = noteRepository.save(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable long id, @RequestBody Note noteDetails) {
        return noteRepository.findById(id)
                .map(note -> {
                    note.setTitle(noteDetails.getTitle()); // Assuming Note has a setTitle method
                    note.setContent(noteDetails.getContent()); // Assuming Note has a setContent method
                    Note updatedNote = noteRepository.save(note);
                    return ResponseEntity.ok(updatedNote);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteNote(@PathVariable long id) {
        return noteRepository.findById(id)
                .map(note -> {
                    noteRepository.delete(note);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
