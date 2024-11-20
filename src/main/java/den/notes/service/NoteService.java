package den.notes.service;

import den.notes.model.Note;
import den.notes.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {

        private NoteRepository noteRepository;

        public List<Note> getAll() {
            return noteRepository.findAll();
        }

        public Note getById(int id) {
            return noteRepository.findNoteById(id);
        }

        public Note save(Note note) {
            return noteRepository.save(note);
        }

        public Note update(Note note) {
            return noteRepository.save(note);
        }

        public void delete(Note note) {
            noteRepository.delete(note);
        }
}
