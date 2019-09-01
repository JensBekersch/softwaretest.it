package it.softwaretest.app.ws.io.entity.impl;

import it.softwaretest.app.ws.io.entity.SingleDomElementEntityInterface;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "SingleDomElement")
public class SingleDomElementEntity implements SingleDomElementEntityInterface, Serializable {

    private static final long serialVersionUID = -1360001092291519833L;

    @Id
    @GeneratedValue
    private long id;

    private String elementType;

    private String elementId;

    private String elementClass;

    private String elementXPath;

    public SingleDomElementEntity() {}

    public SingleDomElementEntity(String elementType, String elementId, String elementClass, String elementXPath) {
        this.elementType = elementType;
        this.elementId = elementId;
        this.elementClass = elementClass;
        this.elementXPath = elementXPath;
    }

    public long getId() {
        return id;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getElementClass() {
        return elementClass;
    }

    public void setElementClass(String elementClass) {
        this.elementClass = elementClass;
    }

    public String getElementXPath() {
        return elementXPath;
    }

    public void setElementXPath(String elementXPath) {
        this.elementXPath = elementXPath;
    }
}
