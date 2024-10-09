package org.example;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File casetas = new File("Archivos/casetas.txt");
        File f = new File("Archivos/feria.xml");
        File fj = new File("Archivos/feria.json");


        List<CasetaFeria> list = new ArrayList<>();
        Feria feria = new Feria(list);

        //Este try es para hacer una lista con las casetas
        try(BufferedReader bf = new BufferedReader(new FileReader(casetas))) {

            String linea = bf.readLine();
            int id=1;

            while(linea!=null){
                String lineaSeparada[] = linea.split(" - ");
                feria.add(new CasetaFeria(id,lineaSeparada[0],lineaSeparada[1],Integer.parseInt(lineaSeparada[2]),lineaSeparada[3]));
                id++;
                linea= bf.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Menu que le saldra al usuario
        System.out.println("Menu: ");
        System.out.println("1- Marshalling a XML");
        System.out.println("2- UnMarshalling de XML");
        System.out.println("3- Mostrar caseta nº \"X\"");
        System.out.println("4- Marshalling a JSON");
        System.out.println("5- UnMarshalling de JSON");
        System.out.println("6- Mostrar caseta nº \"X\" de JSON");
        System.out.println("7- Salir de Menú");
        int i;
        do{
            System.out.println("Seleccione una opcion");
            i=sc.nextInt();
            switch (i){
                case 1:
                    marshallingXML(feria,f);
                    break;
                case 2:
                    System.out.println(unmarshallingXML(f).toString());
                    break;
                case 3:
                    casetaPorID(unmarshallingXML(f));
                    break;
                case 4:
                    hacerJSON(feria,fj);
                    break;
                case 5:
                    System.out.println(unmarshallJSON(fj).toString());
                    break;
                case 6:
                    buscarDesdeJSON(unmarshallJSON(fj));
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no Valida");
                    break;
            }
        }while(i!=7);
    }
    //
    public static void marshallingXML(Feria lista,File f){
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Feria.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            //Creamos Marshaller y hacemos que el XML sea legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Hacer el XML
            marshaller.marshal(lista,f);
            System.out.println("XML hecho");
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
    public static Feria unmarshallingXML(File f){
        /*try(BufferedReader bf = new BufferedReader(new FileReader(f))){
            String linea=bf.readLine();
            while(linea!=null){
                System.out.println(linea);
                linea= bf.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
        Feria feria = null;
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Feria.class);

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            feria = (Feria) unmarshaller.unmarshal(f);

        } catch (JAXBException e) {

        }
        return feria;
    }
    public static void casetaPorID(Feria feria){
        Scanner sc = new Scanner(System.in);

        System.out.println("Dime el ID de la caseta que quieres");
        int i = sc.nextInt();

        for (CasetaFeria f : feria.getCasetas()){
            if (f.getId()==i){
                System.out.println(f.toString());
            }
        }
    }
    public static void hacerJSON(Feria feria,File json){
        try {

            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.writeValue(json,feria);

            System.out.println("Archivo JSON hecho");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Feria unmarshallJSON(File f){
        Feria feria=null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            feria = objectMapper.readValue(f,Feria.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return feria;
    }
    public static void buscarDesdeJSON(Feria feria){
        Scanner sc = new Scanner(System.in);

        System.out.println("Dime el ID de la caseta que quieres");
        int i = sc.nextInt();

        for (CasetaFeria f : feria.getCasetas()){
            if (f.getId()==i){
                System.out.println(f.toString());
            }
        }
    }
}
