package of.mobile.json.helper;

import java.util.ArrayList;
import java.util.logging.Level;

import of.mobile.entities.TasksEntity;

import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;
import oracle.adfmf.util.logging.Trace;

public class JsonArrayToTasksArray {
    public JsonArrayToTasksArray() {
        super();
    }

    public static TasksEntity[] getTasksArray(String jsonArrayAsString) {
        ArrayList<TasksEntity> taskList = new ArrayList<TasksEntity>();
        try {
            JSONArray jsonArryObj = new JSONArray(jsonArrayAsString);
            for (int i = 0; i < jsonArryObj.length(); i++) {
                TasksEntity entity = new TasksEntity();
                JSONObject obj = jsonArryObj.getJSONObject(i);
                entity.setCreatedOn(obj.getString("created"));
                entity.setDescription(obj.getString("description"));
                entity.setFailureType(obj.getString("failure_type"));
                entity.setId(obj.getString("id"));
                entity.setServiceType(obj.getString("service_type"));
                entity.setSiteContact(obj.getString("site_contact"));
                entity.setSitePhone(obj.getString("site_phone"));
                entity.setSubject(obj.getString("subject"));
                entity.setUpdated(obj.getString("updated"));
                entity.setWoNo(obj.getString("wo_no"));
                taskList.add(entity);
            }
        } catch (JSONException e) {
            Trace.log("JSONArray_to_JavaArray", Level.SEVERE, JsonArrayToTasksArray.class, "getTasksArray",
                      "Parsing of REST response failed: " + e.getLocalizedMessage());
        }
        return taskList.toArray(new TasksEntity[0]);
    }

}
