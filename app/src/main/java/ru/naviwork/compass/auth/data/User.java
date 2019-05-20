package ru.naviwork.compass.auth.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("_entityName")
    @Expose
    private String entityName;
    @SerializedName("_instanceName")
    @Expose
    private String instanceName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("loginLowerCase")
    @Expose
    private String loginLowerCase;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("changePasswordAtNextLogon")
    @Expose
    private Boolean changePasswordAtNextLogon;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("facebookId")
    @Expose
    private String facebookId;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("passwordEncryption")
    @Expose
    private String passwordEncryption;
    @SerializedName("name")
    @Expose
    private String name;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginLowerCase() {
        return loginLowerCase;
    }

    public void setLoginLowerCase(String loginLowerCase) {
        this.loginLowerCase = loginLowerCase;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getChangePasswordAtNextLogon() {
        return changePasswordAtNextLogon;
    }

    public void setChangePasswordAtNextLogon(Boolean changePasswordAtNextLogon) {
        this.changePasswordAtNextLogon = changePasswordAtNextLogon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getPasswordEncryption() {
        return passwordEncryption;
    }

    public void setPasswordEncryption(String passwordEncryption) {
        this.passwordEncryption = passwordEncryption;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "entityName='" + entityName + '\'' +
                ", instanceName='" + instanceName + '\'' +
                ", id='" + id + '\'' +
                ", loginLowerCase='" + loginLowerCase + '\'' +
                ", login='" + login + '\'' +
                ", changePasswordAtNextLogon=" + changePasswordAtNextLogon +
                ", email='" + email + '\'' +
                ", facebookId='" + facebookId + '\'' +
                ", active=" + active +
                ", version=" + version +
                ", passwordEncryption='" + passwordEncryption + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
