
package xml.saveData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="timeToDestination" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="localTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataType", propOrder = {
    "timeToDestination",
    "localTime"
})
public class DataType {

    @XmlElement(required = true)
    protected String timeToDestination;
    @XmlElement(required = true)
    protected String localTime;

    /**
     * Gets the value of the timeToDestination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeToDestination() {
        return timeToDestination;
    }

    /**
     * Sets the value of the timeToDestination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeToDestination(String value) {
        this.timeToDestination = value;
    }

    /**
     * Gets the value of the localTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalTime() {
        return localTime;
    }

    /**
     * Sets the value of the localTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalTime(String value) {
        this.localTime = value;
    }

}
