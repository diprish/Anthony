package of.mobile.entities;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class TasksEntity {
    private String createdOn;
    private String description;
    private String failureType;
    private String id;
    private String serviceType;
    private String siteContact;
    private String sitePhone;
    private String subject;
    private String updated;
    private String woNo;
    private String createdBy;
    private String company;
    private String findings;
    private String workPerformed;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);


    public TasksEntity() {
        super();
    }

    public void setCreatedBy(String createdBy) {
        String oldCreatedBy = this.createdBy;
        this.createdBy = createdBy;
        _propertyChangeSupport.firePropertyChange("createdBy", oldCreatedBy, createdBy);
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCompany(String company) {
        String oldCompany = this.company;
        this.company = company;
        _propertyChangeSupport.firePropertyChange("company", oldCompany, company);
    }

    public String getCompany() {
        return company;
    }

    public void setFindings(String findings) {
        String oldFindings = this.findings;
        this.findings = findings;
        _propertyChangeSupport.firePropertyChange("findings", oldFindings, findings);
    }

    public String getFindings() {
        return findings;
    }

    public void setWorkPerformed(String workPerformed) {
        String oldWorkPerformed = this.workPerformed;
        this.workPerformed = workPerformed;
        _propertyChangeSupport.firePropertyChange("workPerformed", oldWorkPerformed, workPerformed);
    }

    public String getWorkPerformed() {
        return workPerformed;
    }

    public void setCreatedOn(String createdOn) {
        String oldCreatedOn = this.createdOn;
        this.createdOn = createdOn;
        _propertyChangeSupport.firePropertyChange("createdOn", oldCreatedOn, createdOn);
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setDescription(String description) {
        String oldDescription = this.description;
        this.description = description;
        _propertyChangeSupport.firePropertyChange("description", oldDescription, description);
    }

    public String getDescription() {
        return description;
    }

    public void setFailureType(String failureType) {
        String oldFailureType = this.failureType;
        this.failureType = failureType;
        _propertyChangeSupport.firePropertyChange("failureType", oldFailureType, failureType);
    }

    public String getFailureType() {
        return failureType;
    }

    public void setId(String id) {
        String oldId = this.id;
        this.id = id;
        _propertyChangeSupport.firePropertyChange("id", oldId, id);
    }

    public String getId() {
        return id;
    }

    public void setServiceType(String serviceType) {
        String oldServiceType = this.serviceType;
        this.serviceType = serviceType;
        _propertyChangeSupport.firePropertyChange("serviceType", oldServiceType, serviceType);
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setSiteContact(String siteContact) {
        String oldSiteContact = this.siteContact;
        this.siteContact = siteContact;
        _propertyChangeSupport.firePropertyChange("siteContact", oldSiteContact, siteContact);
    }

    public String getSiteContact() {
        return siteContact;
    }

    public void setSitePhone(String sitePhone) {
        String oldSitePhone = this.sitePhone;
        this.sitePhone = sitePhone;
        _propertyChangeSupport.firePropertyChange("sitePhone", oldSitePhone, sitePhone);
    }

    public String getSitePhone() {
        return sitePhone;
    }

    public void setSubject(String subject) {
        String oldSubject = this.subject;
        this.subject = subject;
        _propertyChangeSupport.firePropertyChange("subject", oldSubject, subject);
    }

    public String getSubject() {
        return subject;
    }

    public void setUpdated(String updated) {
        String oldUpdated = this.updated;
        this.updated = updated;
        _propertyChangeSupport.firePropertyChange("updated", oldUpdated, updated);
    }

    public String getUpdated() {
        return updated;
    }

    public void setWoNo(String woNo) {
        String oldWoNo = this.woNo;
        this.woNo = woNo;
        _propertyChangeSupport.firePropertyChange("woNo", oldWoNo, woNo);
    }

    public String getWoNo() {
        return woNo;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}
