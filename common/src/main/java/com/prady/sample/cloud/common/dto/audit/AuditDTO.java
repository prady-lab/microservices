/**
 *
 */

package com.prady.sample.cloud.common.dto.audit;

import java.util.Date;

/**
 * @author Prady
 */
public class AuditDTO {

    private String id;

    private String module;
    private String action;

    private String actionPerformedBy;
    private Date actionPerformedDate;

    private String ipAddress;
    private String hostName;

    private Object additionalData;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the module
     */
    public String getModule() {
        return module;
    }

    /**
     * @param module the module to set
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the actionPerformedBy
     */
    public String getActionPerformedBy() {
        return actionPerformedBy;
    }

    /**
     * @param actionPerformedBy the actionPerformedBy to set
     */
    public void setActionPerformedBy(String actionPerformedBy) {
        this.actionPerformedBy = actionPerformedBy;
    }

    /**
     * @return the actionPerformedDate
     */
    public Date getActionPerformedDate() {
        return actionPerformedDate;
    }

    /**
     * @param actionPerformedDate the actionPerformedDate to set
     */
    public void setActionPerformedDate(Date actionPerformedDate) {
        this.actionPerformedDate = actionPerformedDate;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * @return the additionalData
     */
    public Object getAdditionalData() {
        return additionalData;
    }

    /**
     * @param additionalData the additionalData to set
     */
    public void setAdditionalData(Object additionalData) {
        this.additionalData = additionalData;
    }

}
