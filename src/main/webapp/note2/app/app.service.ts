import {Injectable} from '@angular/core'
import {Note} from "./note/note.model";

@Injectable()
export class NoteService {
    private note: Note = new Note();

    setNote(note: Note) {
        this.note = note;
    }

    getNote() {
        return this.note;
    }

    setId(id: number) {
        this.note = new Note();
        this.note.id = id;
    }

    getId() {
        return this.note.id;
    }

    isNewNote() {
        return !this.note.title;
    }
}