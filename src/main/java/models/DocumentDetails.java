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
        String expDate="-";

        if (status == null) {
            status = "Awaiting Upload";
        }

        if (assignment == null) {
            assignment = "-";
        }

        if (this.expDate != null) {
            this.expDate = expDate;
        }
        return document.append(documentType).append(" ").append(contactId).append(" ").append(assignment).append(" ").
                append(dueDate).append(" ").append(expDate).append("\n").append(status).toString();
    }
}

