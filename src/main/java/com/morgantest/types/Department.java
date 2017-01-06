//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.10 at 01:52:14 AM CST 
//


package com.morgantest.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for department.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="department">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="hr"/>
 *     &lt;enumeration value="fin"/>
 *     &lt;enumeration value="it"/>
 *     &lt;enumeration value="operations"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "department", namespace = "http://www.morgantest.com/types/")
@XmlEnum
public enum Department {

    @XmlEnumValue("hr")
    HR("hr"),
    @XmlEnumValue("fin")
    FIN("fin"),
    @XmlEnumValue("it")
    IT("it"),
    @XmlEnumValue("operations")
    OPERATIONS("operations");
    private final String value;

    Department(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Department fromValue(String v) {
        for (Department c: Department.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
