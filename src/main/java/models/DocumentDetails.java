package models;

import java.io.File;
import java.util.HashMap;

public class DocumentDetails {

    private String contactId;
    private String dueDate;
    private String expDate;
    private String documentType;
    private String assignee;
    private String assignment;
    private String status;
    private String details;
    private File fileToUpload;

    public String getAssignee() {
        return assignee;
    }

    public String getContactId() {
        return contactId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getAssignment() {
        return assignment;
    }

    public String getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public File getFileToUpload() {
        return fileToUpload;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setFileToUpload(File fileToUpload) {
        this.fileToUpload = fileToUpload;
    }

    public DocumentDetails(HashMap<String, String> document) {
        this.contactId = document.get("contactId");
        this.dueDate = document.get("dueDate");
        this.assignee = document.get("assignee");
        this.documentType = document.get("documentType");
        this.assignment = document.get("assignment");
        this.status = document.get("status");
        // this.fileToUpload = document.get("fileToUpload");
        this.details = document.get("details");
        this.expDate = document.get("expDate");
    }

    public String forReview() {
        StringBuffer document = new StringBuffer();
        String statusToShow = status;
        String assignmentToShow = assignment;
        String expDateToShow = expDate;

        if (status == null) {
            statusToShow = "Awaiting Upload";
        }

        if (assignment == null) {
            assignmentToShow = "-";
        }

        if (this.expDate == null) {
            expDateToShow = "-";
        }
        return document.append(documentType).append(" ").append(contactId).append(" ").append(assignmentToShow).append(" ").
                append(dueDate).append(" ").append(expDateToShow).append("\n").append(statusToShow).toString();
    }
}

