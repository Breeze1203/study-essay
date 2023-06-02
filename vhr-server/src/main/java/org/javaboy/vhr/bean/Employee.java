package org.javaboy.vhr.bean;

import java.util.Date;

public class Employee {
    private Integer id;

    private String name;

    private String gender;

    private Date birthday;

    private String idCard;

    private String wedlock;

    private Integer nationId;

    private String nativePlace;

    private Integer politicId;

    private String email;

    private String phone;

    private String address;

    private Integer departmentId;

    private Integer jobLevelId;

    private Integer posId;

    private String engageForm;

    private String tipTopdeGree;

    private String specialty;

    private String school;

    private Date begindate;

    private String workState;

    private String workId;

    private Double contractTerm;

    private Date conversionTime;

    private Date notworkdate;

    private Date beginContract;

    private Date endContract;

    private Integer workAge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getidCard() {
        return idCard;
    }

    public void setidCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getWedlock() {
        return wedlock;
    }

    public void setWedlock(String wedlock) {
        this.wedlock = wedlock == null ? null : wedlock.trim();
    }

    public Integer getnationId() {
        return nationId;
    }

    public void setnationId(Integer nationId) {
        this.nationId = nationId;
    }

    public String getnativePlace() {
        return nativePlace;
    }

    public void setnativePlace(String nativePlace) {
        this.nativePlace = nativePlace == null ? null : nativePlace.trim();
    }

    public Integer getpoliticId() {
        return politicId;
    }

    public void setpoliticId(Integer politicId) {
        this.politicId = politicId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getdepartmentId() {
        return departmentId;
    }

    public void setdepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getjobLevelId() {
        return jobLevelId;
    }

    public void setjobLevelId(Integer jobLevelId) {
        this.jobLevelId = jobLevelId;
    }

    public Integer getposId() {
        return posId;
    }

    public void setposId(Integer posId) {
        this.posId = posId;
    }

    public String getengageForm() {
        return engageForm;
    }

    public void setengageForm(String engageForm) {
        this.engageForm = engageForm == null ? null : engageForm.trim();
    }

    public String gettipTopdeGree() {
        return tipTopdeGree;
    }

    public void settipTopdeGree(String tipTopdeGree) {
        this.tipTopdeGree = tipTopdeGree == null ? null : tipTopdeGree.trim();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty == null ? null : specialty.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public String getworkState() {
        return workState;
    }

    public void setworkState(String workState) {
        this.workState = workState == null ? null : workState.trim();
    }

    public String getworkId() {
        return workId;
    }

    public void setworkId(String workId) {
        this.workId = workId == null ? null : workId.trim();
    }

    public Double getcontractTerm() {
        return contractTerm;
    }

    public void setcontractTerm(Double contractTerm) {
        this.contractTerm = contractTerm;
    }

    public Date getconversionTime() {
        return conversionTime;
    }

    public void setconversionTime(Date conversionTime) {
        this.conversionTime = conversionTime;
    }

    public Date getNotworkdate() {
        return notworkdate;
    }

    public void setNotworkdate(Date notworkdate) {
        this.notworkdate = notworkdate;
    }

    public Date getbeginContract() {
        return beginContract;
    }

    public void setbeginContract(Date beginContract) {
        this.beginContract = beginContract;
    }

    public Date getendContract() {
        return endContract;
    }

    public void setendContract(Date endContract) {
        this.endContract = endContract;
    }

    public Integer getworkAge() {
        return workAge;
    }

    public void setworkAge(Integer workAge) {
        this.workAge = workAge;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", idCard='" + idCard + '\'' +
                ", wedlock='" + wedlock + '\'' +
                ", nationId=" + nationId +
                ", nativePlace='" + nativePlace + '\'' +
                ", politicId=" + politicId +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", departmentId=" + departmentId +
                ", jobLevelId=" + jobLevelId +
                ", posId=" + posId +
                ", engageForm='" + engageForm + '\'' +
                ", tipTopdeGree='" + tipTopdeGree + '\'' +
                ", specialty='" + specialty + '\'' +
                ", school='" + school + '\'' +
                ", begindate=" + begindate +
                ", workState='" + workState + '\'' +
                ", workId='" + workId + '\'' +
                ", contractTerm=" + contractTerm +
                ", conversionTime=" + conversionTime +
                ", notworkdate=" + notworkdate +
                ", beginContract=" + beginContract +
                ", endContract=" + endContract +
                ", workAge=" + workAge +
                '}';
    }
}