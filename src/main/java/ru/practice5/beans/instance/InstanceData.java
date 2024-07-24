package ru.practice5.beans.instance;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
@Getter
@Setter
public class InstanceData {
    private String instanceId;
    private ArrayList<String> registerId;
    private ArrayList<String> supplementaryAgreementId;
    public InstanceData() { this.registerId = new ArrayList<>();this.supplementaryAgreementId = new ArrayList<>();}
    public void setAgrId(String argId) {
        this.supplementaryAgreementId.add(argId);
    }
    public void setRegId(String argId) {
        this.registerId.add(argId);
    }
}