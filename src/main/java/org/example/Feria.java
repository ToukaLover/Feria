package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="feria")
public class Feria {
    @XmlElement(name="caseta")
    private List<CasetaFeria> casetas;

    public Feria(){}

    public Feria(List<CasetaFeria> casetas) {
        this.casetas = new ArrayList<>();
    }


    public void add(CasetaFeria c){
        casetas.add(c);
    }


    public List<CasetaFeria> getCasetas() {
        return casetas;
    }

    @Override
    public String toString() {
        return "Feria{" +
                "casetas=" + casetas +
                '}';
    }
}
