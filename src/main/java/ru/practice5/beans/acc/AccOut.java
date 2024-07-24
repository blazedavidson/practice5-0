package ru.practice5.beans.acc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccOut {
    private String message;
    private AccContent data;
    public AccOut() {
        data = new AccContent();
    }
}
