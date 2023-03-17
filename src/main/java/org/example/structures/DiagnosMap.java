package org.example.structures;

import java.util.*;
import java.util.stream.Collectors;

public class DiagnosMap {
    private Map<String, Integer> map;

    public DiagnosMap(int size) {
        map = new HashMap<>(size);
    }

    private DiagnosMap(Map<String, Integer> map){
        this.map = map;
    }

    public static DiagnosMap parseDiagnosMap(PatientArray patients) {
        DiagnosMap map = new DiagnosMap(patients.size());
        for (int i = 0; i < patients.nPatients(); i++) {
            map.add(patients.get(i).getDiagnos());
        }
        return map;
    }

    public DiagnosMap sort() {
        return new DiagnosMap(map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                )));
    }

    public void add(String diagnos) {
        if(map.containsKey(diagnos)) map.put(diagnos, map.get(diagnos)+1);
        else map.put(diagnos, 1);
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            builder.append("diagnos = ")
                    .append(entry.getKey())
                    .append("  amount = ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return builder.toString();
    }
}

