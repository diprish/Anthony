package of.mobile.datacontrol;

import java.util.logging.Level;

import of.mobile.entities.TasksEntity;
import of.mobile.json.helper.JsonArrayToTasksArray;
import of.mobile.uri.AnthonyURI;
import of.mobile.util.RestCallerUtil;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.logging.Trace;

public class TaskDC {
    private TasksEntity[] allTasks = null;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);
    private String user = "Arup";

    public TaskDC() {
        super();
    }

    public void refreshTasks() {
        String restURI = AnthonyURI.GetAllTask();
        RestCallerUtil rcu = new RestCallerUtil();
        String jsonArrayAsString = rcu.invokeREAD(restURI);
        TasksEntity[] tasks = JsonArrayToTasksArray.getTasksArray(jsonArrayAsString);
        allTasks = tasks;
    }

    public void getTask() {
        String id = AdfmfJavaUtilities.getELValue("#{pageFlowScope.taskId}").toString();
        for (TasksEntity task : allTasks) {
            if (task.getId().equals(id)) {
                //Set Values
                AdfmfJavaUtilities.setELValue("#{applicationScope.BarcodeBean.barcodeResult}", task.getWoNo());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.subject}", task.getSubject());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceType}", task.getServiceType());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.description}", task.getDescription());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.failureType}", task.getFailureType());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.company}", task.getCompany());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.findings}", task.getFindings());
                AdfmfJavaUtilities.setELValue("#{pageFlowScope.workPerformed}", task.getWorkPerformed());
                break;
            }
        }
    }

    public void clearValues() {
        AdfmfJavaUtilities.setELValue("#{applicationScope.BarcodeBean.barcodeResult}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.subject}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.serviceType}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.description}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.failureType}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.company}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.findings}", null);
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.workPerformed}", null);
    }

    public void saveTask() {
        StringBuffer payload = new StringBuffer();
        payload.append("{");
        //Task ID
        try {
            StringBuffer taskId = new StringBuffer();
            taskId.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.taskId}").toString());
            payload.append("\"id\":\"" + taskId + "\",");
        } catch (Exception ex) {
            return;
        }

        //WO No
        try {
            StringBuffer woNo = new StringBuffer();
            woNo.append(AdfmfJavaUtilities.getELValue("#{applicationScope.BarcodeBean.barcodeResult}").toString());
            payload.append("\"wo_no\":\"" + woNo + "\",");
        } catch (Exception ex) {
            return;
        }

        //Subject
        try {
            StringBuffer subject = new StringBuffer();
            subject.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.subject}"));
            payload.append("\"subject\":\"" + subject + "\",");
        } catch (Exception ex) {
            return;
        }

        //Description
        try {
            StringBuffer description = new StringBuffer();
            description.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.description}"));
            payload.append("\"description\":\"" + description + "\",");
        } catch (Exception ex) {
            Trace.log("description field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

        //Service Type
        try {
            StringBuffer serviceType = new StringBuffer();
            serviceType.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.serviceType}"));
            payload.append("\"service_type\":\"" + serviceType + "\",");
        } catch (Exception ex) {
            Trace.log("service_type field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

        //Failure Type
        try {
            StringBuffer failureType = new StringBuffer();
            failureType.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.failureType}"));
            payload.append("\"failure_type\":\"" + failureType + "\",");
        } catch (Exception ex) {
            Trace.log("failure_type field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

        //Company
        try {
            StringBuffer company = new StringBuffer();
            company.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.company}"));
            payload.append("\"company\":\"" + company + "\",");
        } catch (Exception ex) {
            Trace.log("company field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

        //Findings
        try {
            StringBuffer findings = new StringBuffer();
            findings.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.findings}"));
            payload.append("\"findings\":\"" + findings + "\",");
        } catch (Exception ex) {
            Trace.log("findings field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

        //Work Performed
        try {
            StringBuffer workPerformed = new StringBuffer();
            workPerformed.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.workPerformed}"));
            payload.append("\"work_performed\":\"" + workPerformed + "\",");
        } catch (Exception ex) {
            Trace.log("work_performed field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }
        payload.append("\"updated_by\":\"" + user + "\",");
        /*
         * Save all input fields
         */
        payload.deleteCharAt(payload.lastIndexOf(","));
        payload.append("}");

        String restURI = AnthonyURI.GetAllTask();
        RestCallerUtil rcu = new RestCallerUtil();
        String response = rcu.invokeUPDATE(restURI, payload.toString());
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.Response}", response);
        refreshTasks();
        clearValues();
    }

    public TasksEntity[] getAllTasks() {
        refreshTasks();
        return allTasks;
    }

    public void setAllTasks(TasksEntity[] allTasks) {
        this.allTasks = allTasks;
    }

    public void createAndSaveTask() {
        //Save newly created tasks
        StringBuffer payload = new StringBuffer();

        payload.append("{");
        //WO No
        try {
            StringBuffer woNo = new StringBuffer();
            woNo.append(AdfmfJavaUtilities.getELValue("#{applicationScope.BarcodeBean.barcodeResult}").toString());
            payload.append("\"wo_no\":\"" + woNo + "\",");
        } catch (Exception ex) {
            return;
        }

        //Subject
        try {
            StringBuffer subject = new StringBuffer();
            subject.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.subject}"));
            payload.append("\"subject\":\"" + subject + "\",");
        } catch (Exception ex) {
            return;
        }

        //Description

        try {
            StringBuffer description = new StringBuffer();
            description.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.description}"));
            payload.append("\"description\":\"" + description + "\",");
        } catch (Exception ex) {
            Trace.log("description field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

        //Failure Type
        try {
            StringBuffer failureType = new StringBuffer();
            failureType.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.failureType}"));
            payload.append("\"failure_type\":\"" + failureType + "\",");
        } catch (Exception ex) {
            Trace.log("failureType field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

        //Service Type
        try {
            StringBuffer serviceType = new StringBuffer();
            serviceType.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.serviceType}"));
            payload.append("\"service_type\":\"" + serviceType + "\",");
        } catch (Exception ex) {
            Trace.log("serviceType field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

        //Company
        try {
            StringBuffer company = new StringBuffer();
            company.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.company}"));
            payload.append("\"company\":\"" + company + "\",");
        } catch (Exception ex) {
            Trace.log("company field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

        //Findings
        try {
            StringBuffer findings = new StringBuffer();
            findings.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.findings}"));
            payload.append("\"findings\":\"" + findings + "\",");
        } catch (Exception ex) {
            Trace.log("findings field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

        //Work Performed
        try {
            StringBuffer workPerformed = new StringBuffer();
            workPerformed.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.workPerformed}"));
            payload.append("\"work_performed\":\"" + workPerformed + "\",");
        } catch (Exception ex) {
            Trace.log("work_performed field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

        payload.append("\"created_by\":\"" + user + "\",");

        /*
         * Save all input fields
         */
        payload.deleteCharAt(payload.lastIndexOf(","));
        payload.append("}");

        String restURI = AnthonyURI.GetAllTask();
        RestCallerUtil rcu = new RestCallerUtil();
        String response = rcu.invokeCREATE(restURI, payload.toString());
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.Response}", response);
        refreshTasks();
        clearValues();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}

