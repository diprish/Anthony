package of.mobile.datacontrol;

import of.mobile.entities.TasksEntity;
import of.mobile.json.helper.JsonArrayToTasksArray;
import of.mobile.uri.AnthonyURI;
import of.mobile.util.RestCallerUtil;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class TaskDC {
    private TasksEntity[] allTasks = null;
    private TasksEntity editableTask = null;
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

    public TasksEntity[] getAllTasks() {
        refreshTasks();
        return allTasks;
    }

    public void setAllTasks(TasksEntity[] allTasks) {
        this.allTasks = allTasks;
    }

    public void setEditableTask(TasksEntity editableTask) {
        TasksEntity oldEditableTask = this.editableTask;
        this.editableTask = editableTask;
        _propertyChangeSupport.firePropertyChange("editableTask", oldEditableTask, editableTask);
    }

    public TasksEntity getEditableTask() {
        return editableTask;
    }

    public void createAndSaveTask() {
        //Save newly created tasks
        StringBuffer woNo = new StringBuffer();
        StringBuffer subject = new StringBuffer();
        StringBuffer payload = new StringBuffer();

        try {
            woNo.append(AdfmfJavaUtilities.getELValue("#{applicationScope.BarcodeBean.barcodeResult}").toString());
        } catch (Exception ex) {
            return;
        }

        try {
            subject.append(AdfmfJavaUtilities.getELValue("#{pageFlowScope.subject}").toString());
        } catch (Exception ex) {
            return;
        }

        /*
         * Save all input fields
         */
        payload.append("{");
        payload.append("\"subject\":\"" + subject + "\",");
        payload.append("\"wo_no\":\"" + woNo + "\",");
        payload.deleteCharAt(payload.lastIndexOf(","));
        payload.append("}");

        String restURI = AnthonyURI.GetAllTask();
        RestCallerUtil rcu = new RestCallerUtil();
        String response = rcu.invokeCREATE(restURI, payload.toString());
        AdfmfJavaUtilities.setELValue("#{viewScope.Response}", response);
        refreshTasks();
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }
}

