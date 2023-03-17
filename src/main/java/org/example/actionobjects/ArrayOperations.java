package org.example.actionobjects;

import org.example.structures.DiagnosMap;
import org.example.structures.Patient;
import org.example.structures.PatientArray;
import org.example.structures.Range;

import java.util.ArrayList;

public class ArrayOperations {

    public void sortByMedNo(PatientArray patientArray) {
        for (int i = patientArray.nPatients() - 1; i > 1; i--) {
            for (int j = 0; j < i; j++) {
                if (patientArray.get(j).getMedCardNo() > patientArray.get(j + 1).getMedCardNo()) {
                    Patient temp = patientArray.get(j);
                    patientArray.insert(j, patientArray.get(j + 1));
                    patientArray.insert(j + 1, temp);
                }
            }
        }
    }

    public PatientArray filter(PatientArray patients, String diagnos) {
        PatientArray newArray = new PatientArray();
        for (int i = 0; i < patients.nPatients(); i++)
            if (patients.get(i).getDiagnos().equals(diagnos))
                newArray.add(patients.get(i));
        return newArray;
    }

    public PatientArray filter(PatientArray patients, Range range) {
        PatientArray newArray = new PatientArray();
        for (int i = 0; i < patients.nPatients(); i++)
            if (range.contains(patients.get(i).getMedCardNo()))
                newArray.add(patients.get(i));
        return newArray;
    }

    public PatientArray filter(PatientArray patients, long phoneNumber) {
        char fst = String.valueOf(phoneNumber).charAt(0);
        PatientArray newArray = new PatientArray();
        for (int i = 0; i < patients.nPatients(); i++) {
            String s = String.valueOf(patients.get(i).getPhonenumber());
            if (s.charAt(0) == fst) newArray.add(patients.get(i));
        }
        return newArray;
    }

    public PatientArray filter(PatientArray patientArray){
        ArrayList<Patient> list = new ArrayList<>(
                patientArray.getArrayListPatient().stream().distinct().toList());
        return new PatientArray(list);
    }

    public DiagnosMap filterSortedMap(PatientArray patients) {
        return DiagnosMap.parseDiagnosMap(patients).sort();
    }

    public DiagnosMap filterMap(PatientArray patientArray){
        return DiagnosMap.parseDiagnosMap(patientArray);
    }
}
