package of.mobile.datacontrol;

import of.mobile.entities.TasksEntity;
import of.mobile.json.helper.JsonArrayToTasksArray;
import of.mobile.uri.AnthonyURI;
import of.mobile.util.RestCallerUtil;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class TaskDC {
    private TasksEntity[] allTasks = null;

    public TaskDC() {
        super();
    }

    public void refresh() {
        getAllTasks();
    }

    public TasksEntity[] getAllTasks() {
        String restURI = AnthonyURI.GetAllTask();
        RestCallerUtil rcu = new RestCallerUtil();
        String jsonArrayAsString = rcu.invokeREAD(restURI);
        TasksEntity[] tasks = JsonArrayToTasksArray.getTasksArray(jsonArrayAsString);
        allTasks = tasks;

        return allTasks;
    }

    public void setAllTasks(TasksEntity[] allTasks) {
        this.allTasks = allTasks;
    }

    public void createTask() {
        //Save newly created tasks
        StringBuffer woNo = new StringBuffer();
        StringBuffer subject = new StringBuffer();
        StringBuffer payload = new StringBuffer();

        try {
            woNo.append(AdfmfJavaUtilities.getELValue("#{viewScope.BarcodeBean.barcodeResult}").toString());
        } catch (Exception ex) {
            return;
        }

        try {
            subject.append(AdfmfJavaUtilities.getELValue("#{viewScope.Subject}").toString());
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
        AdfmfJavaUtilities.setELValue("#{viewScope.Subject}", response);
    }
}

