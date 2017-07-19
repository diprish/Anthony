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
    }

    public void saveTask() {
        StringBuffer taskId = new StringBuffer();
        StringBuffer woNo = new StringBuffer();
        StringBuffer subject = new StringBuffer();
        StringBuffer description = new StringBuffer();
        //        StringBuffer serviceType = new StringBuffer();
        //        StringBuffer failureType = new StringBuffer();
        StringBuffer payload = new StringBuffer();

        payload.append("{");

        try {
            taskId.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.taskId}").toString());
            payload.append("\"id\":\"" + taskId + "\",");
        } catch (Exception ex) {
            return;
        }

        try {
            woNo.append(AdfmfJavaUtilities.getELValue("#{applicationScope.BarcodeBean.barcodeResult}").toString());
            payload.append("\"wo_no\":\"" + woNo + "\",");
        } catch (Exception ex) {
            return;
        }

        try {
            subject.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.subject}"));
            payload.append("\"subject\":\"" + subject + "\",");
        } catch (Exception ex) {
            return;
        }

        try {
            description.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.description}"));
            payload.append("\"description\":\"" + description + "\",");
        } catch (Exception ex) {
            Trace.log("description field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

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
        StringBuffer woNo = new StringBuffer();
        StringBuffer subject = new StringBuffer();
        StringBuffer payload = new StringBuffer();
        StringBuffer description = new StringBuffer();

        payload.append("{");

        try {
            woNo.append(AdfmfJavaUtilities.getELValue("#{applicationScope.BarcodeBean.barcodeResult}").toString());
            payload.append("\"wo_no\":\"" + woNo + "\",");
        } catch (Exception ex) {
            return;
        }

        try {
            subject.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.subject}"));
            payload.append("\"subject\":\"" + subject + "\",");
        } catch (Exception ex) {
            return;
        }

        try {
            description.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.description}"));
            payload.append("\"description\":\"" + description + "\",");
        } catch (Exception ex) {
            Trace.log("description field value not found", Level.INFO, TaskDC.class, "createAndSaveTask",
                      ex.getLocalizedMessage());
        }

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

