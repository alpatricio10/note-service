package org.ota.noteservice.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NotesResponse {
    List<Note> notes;
}
