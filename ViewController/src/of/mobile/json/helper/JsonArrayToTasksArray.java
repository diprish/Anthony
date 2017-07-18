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
                try {
                    entity.setCreatedOn(obj.getString("created"));
                } catch (JSONException jex) {
                    Trace.log("created field value not found", Level.INFO, JsonArrayToTasksArray.class, "getTasksArray",
                              "Parsing of REST response failed: " + jex.getLocalizedMessage());
                }

                try {
                    entity.setDescription(obj.getString("description"));
                } catch (JSONException jex) {
                    Trace.log("description field value not found", Level.INFO, JsonArrayToTasksArray.class,
                              "getTasksArray", "Parsing of REST response failed: " + jex.getLocalizedMessage());
                }

                try {
                    entity.setFailureType(obj.getString("failure_type"));
                } catch (JSONException jex) {
                    Trace.log("failure_type field value not found", Level.INFO, JsonArrayToTasksArray.class,
                              "getTasksArray", "Parsing of REST response failed: " + jex.getLocalizedMessage());
                }

                try {
                    entity.setId(obj.getString("id"));
                } catch (JSONException jex) {
                    Trace.log("id field value not found", Level.INFO, JsonArrayToTasksArray.class, "getTasksArray",
                              "Parsing of REST response failed: " + jex.getLocalizedMessage());
                }

                try {
                    entity.setServiceType(obj.getString("service_type"));
                } catch (JSONException jex) {
                    Trace.log("service_type field value not found", Level.INFO, JsonArrayToTasksArray.class, "getTasksArray",
                              "Parsing of REST response failed: " + jex.getLocalizedMessage());
                }

                try {
                    entity.setSiteContact(obj.getString("site_contact"));
                } catch (JSONException jex) {
                    Trace.log("site_contact field value not found", Level.INFO, JsonArrayToTasksArray.class, "getTasksArray",
                              "Parsing of REST response failed: " + jex.getLocalizedMessage());
                }

                try {
                    entity.setSitePhone(obj.getString("site_phone"));
                } catch (JSONException jex) {
                    Trace.log("site_phone field value not found", Level.INFO, JsonArrayToTasksArray.class, "getTasksArray",
                              "Parsing of REST response failed: " + jex.getLocalizedMessage());
                }

                try {
                    entity.setSubject(obj.getString("subject"));
                } catch (JSONException jex) {
                    Trace.log("subject field value not found", Level.INFO, JsonArrayToTasksArray.class, "getTasksArray",
                              "Parsing of REST response failed: " + jex.getLocalizedMessage());
                }

                try {
                    entity.setUpdated(obj.getString("updated"));
                } catch (JSONException jex) {
                    Trace.log("updated field value not found", Level.INFO, JsonArrayToTasksArray.class, "getTasksArray",
                              "Parsing of REST response failed: " + jex.getLocalizedMessage());
                }

                try {
                    entity.setWoNo(obj.getString("wo_no"));
                } catch (JSONException jex) {
                    Trace.log("wo_no field value not found", Level.INFO, JsonArrayToTasksArray.class, "getTasksArray",
                              "Parsing of REST response failed: " + jex.getLocalizedMessage());
                }
                taskList.add(entity);
            }
        } catch (Exception e) {
            Trace.log("JSONArray_to_JavaArray", Level.SEVERE, JsonArrayToTasksArray.class, "getTasksArray",
                      "Parsing of REST response failed: " + e.getLocalizedMessage());
        }
        return taskList.toArray(new TasksEntity[0]);
    }

}
