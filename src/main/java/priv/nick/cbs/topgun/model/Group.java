package priv.nick.cbs.topgun.model;

import priv.nick.cbs.topgun.model.base.BaseEntity;

public class Group extends BaseEntity {
    private Long id;

    private String name;

    private String code;

    private String contactName;

    private String contactPhone;

    private String contactEmail;

    private Boolean multiProperty;

    private Integer numberOfProperty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    public Boolean getMultiProperty() {
        return multiProperty;
    }

    public void setMultiProperty(Boolean multiProperty) {
        this.multiProperty = multiProperty;
    }

    public Integer getNumberOfProperty() {
        return numberOfProperty;
    }

    public void setNumberOfProperty(Integer numberOfProperty) {
        this.numberOfProperty = numberOfProperty;
    }
}