package ru.practice5.beans.acc;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
public class AccContent {
    private ArrayList<String> accountId;
    public AccContent() {
        this.accountId = new ArrayList<>();
    }
    public void setAccId(String id) {
        this.accountId.add(id);
    }
}
