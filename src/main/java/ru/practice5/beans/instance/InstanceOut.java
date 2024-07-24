package ru.practice5.beans.instance;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InstanceOut {
    private String message;
    private InstanceData data;
    public InstanceOut() {
        data = new InstanceData();
    }
}