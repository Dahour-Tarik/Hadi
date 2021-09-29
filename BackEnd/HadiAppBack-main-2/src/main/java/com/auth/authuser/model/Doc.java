package com.auth.authuser.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="doc")
public class Doc {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idDoc;
    @Column(name = "doc_name", length = 65, unique = true)
    private String docName;
    @Column(name = "doc_type", length = 65)
    private String docType;
    @Column(name="doc_size")
    private Long docSize;
    @Column(name="creation_date")
    private Date docCreationDate;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] data;

    @ManyToOne
    private Repo repo;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "doc", cascade = CascadeType.ALL)
    private List<History> historyList;

    @OneToMany(mappedBy = "doc", cascade = CascadeType.ALL)
    private List<EmailHistory> email_history;

    public Doc(){

    }

    public Doc(String docName, String docType, Long docSize, Date docCreationDate, byte[] data, Repo repo, Company company) {
        this.docName = docName;
        this.docType = docType;
        this.docSize = docSize;
        this.docCreationDate = docCreationDate;
        this.data = data;
        this.repo = repo;
        this.company = company;
    }


    public Long getDocSize() {
        return docSize;
    }

    public void setDocSize(Long docSize) {
        this.docSize = docSize;
    }

    public Integer getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Integer idDoc) {
        this.idDoc = idDoc;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @JsonBackReference(value="repo-doc")
    public Repo getRepo() {
        return repo;
    }

    public void setRepo(Repo rep) {
        this.repo = rep;
    }

    @JsonBackReference(value="company-doc")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getDocCreationDate() {
        return docCreationDate;
    }

    public void setDocCreationDate(Date docCreationDate) {
        this.docCreationDate = docCreationDate;
    }

    @JsonManagedReference(value="doc-history")
    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public List<EmailHistory> getEmail_history() {
        return email_history;
    }

    public void setEmail_history(List<EmailHistory> email_history) {
        this.email_history = email_history;
    }
}
