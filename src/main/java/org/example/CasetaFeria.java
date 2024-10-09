package org.example;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="caseta")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "id", "nombre", "titular", "aforo", "tipoCaseta" })
public class CasetaFeria {
    @XmlAttribute(name="id")
    private int id;
    @XmlElement(name="nombre")
    private String nombre;
    @XmlElement(name="titular")
    private String titular;
    @XmlElement(name="aforo")
    private int aforo;
    @XmlElement(name="tipoCaseta")
    private String tipoCaseta;

    public CasetaFeria(int id, String nombre, String titular, int aforo, String tipoCaseta) {
        this.id = id;
        this.nombre = nombre;
        this.titular = titular;
        this.aforo = aforo;
        this.tipoCaseta = tipoCaseta;
    }
    public CasetaFeria(){}

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTitular() {
        return titular;
    }

    public int getAforo() {
        return aforo;
    }

    public String getTipoCaseta() {
        return tipoCaseta;
    }

    @Override
    public String toString() {
        return "CasetaFeria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", titular='" + titular + '\'' +
                ", aforo=" + aforo +
                ", tipoCaseta='" + tipoCaseta + '\'' +
                '}';
    }
}
