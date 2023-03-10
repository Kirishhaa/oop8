package org.example.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.structures.Patient;
import org.example.structures.PatientArray;

import java.io.*;
import java.util.ArrayList;

public class IO {

    public static String DEFAULT_FILE = "defaultFile.txt";
    public static String BINARY_FILE = "binaryFile.dat";
    public static String JSON_FILE = "jsonFile.json";

    public void saveToDefaultFile(PatientArray patientArray, String fileName) {
        try (PrintWriter pw = new PrintWriter(fileName)) {
            pw.println(patientArray);
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
        }
    }

    public PatientArray readFromDefaultFile(String fileName) {
        PatientArray patientArray = new PatientArray();
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line=bf.readLine())!=null){
                patientArray.add(Patient.parsePatient(line));
            }
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        }
        return patientArray;
    }

    public PatientArray readFromBinaryFile(String fileName) {
        PatientArray patientArray = new PatientArray();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            patientArray = (PatientArray) ois.readObject();
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        } catch (ClassNotFoundException e) {
            System.out.println("CLASS NOT FOUND EXCEPTION");
        }
        return patientArray;
    }

    public void saveToBinaryFile(PatientArray patientArray, String fileName) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(patientArray);
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        }
    }

    public void saveToJSONFile(PatientArray patientArray, String fileName){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(fileName), patientArray.getArrayListPatient());
        } catch (IOException e) {
            System.out.println("IO EXCEPTION");
        }
    }

    public PatientArray readFromJSONFile(String fileName){
        ObjectMapper objectMapper = new ObjectMapper();
        PatientArray patients = new PatientArray();
        try {
            ArrayList<Patient> list = objectMapper.readValue(
                    new File(fileName), new TypeReference<>(){});
            patients = new PatientArray(list);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO EXCEPTION");
        }
        return patients;
    }
}