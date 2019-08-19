package com.vortexbird.vorteam.domain;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Entity
@Table(name = "zcode_entity_audit_event", schema = "public")
public class ZcodeEntityAuditEvent implements java.io.Serializable {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String action;
    private Integer commitVersion;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String entityId;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String entityType;
    private String entityValue;
    private String modifiedBy;
    private Date modifiedDate;

    public ZcodeEntityAuditEvent() {
    }

    public ZcodeEntityAuditEvent(Long id, String action, Integer commitVersion,
        String entityId, String entityType, String entityValue,
        String modifiedBy, Date modifiedDate) {
        this.id = id;
        this.action = action;
        this.commitVersion = commitVersion;
        this.entityId = entityId;
        this.entityType = entityType;
        this.entityValue = entityValue;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "action", nullable = false)
    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Column(name = "commit_version")
    public Integer getCommitVersion() {
        return this.commitVersion;
    }

    public void setCommitVersion(Integer commitVersion) {
        this.commitVersion = commitVersion;
    }

    @Column(name = "entity_id", nullable = false)
    public String getEntityId() {
        return this.entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    @Column(name = "entity_type", nullable = false)
    public String getEntityType() {
        return this.entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    @Column(name = "entity_value")
    public String getEntityValue() {
        return this.entityValue;
    }

    public void setEntityValue(String entityValue) {
        this.entityValue = entityValue;
    }

    @Column(name = "modified_by")
    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Column(name = "modified_date")
    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
