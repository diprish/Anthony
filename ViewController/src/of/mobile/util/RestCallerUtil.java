package of.mobile.util;

import java.util.logging.Level;

import oracle.adfmf.util.logging.Trace;

import oracle.maf.api.dc.ws.rest.RestServiceAdapter;
import oracle.maf.api.dc.ws.rest.RestServiceAdapterFactory;

public class RestCallerUtil {
    public RestCallerUtil() {
        super();
    }

    //GET
    public String invokeREAD(String requestURI) {
        return this.invokeRestRequest(RestServiceAdapter.REQUEST_TYPE_GET, requestURI, "");
    }

    //POST
    public String invokeUPDATE(String requestURI, String payload) {

        return this.invokeRestRequest(RestServiceAdapter.REQUEST_TYPE_POST, requestURI, payload);
    }

    //PUT
    public String invokeCREATE(String requestURI, String payload) {
        return this.invokeRestRequest(RestServiceAdapter.REQUEST_TYPE_PUT, requestURI, payload);
    }


    //DELETE
    public String invokeDELETE(String requestURI) {
        return this.invokeRestRequest(RestServiceAdapter.REQUEST_TYPE_POST, requestURI, "");
    }

    /**
     * Method that handles the boilerplate code for obtaining and configuring a service
     * adapter instance.
     *
     * @param httpMethod GET, POST, PUT, DELETE
     * @param requestURI the URI to appends to the base REST URL. URIs are expected to start with "/"
     * @return
     */
    private String invokeRestRequest(String httpMethod, String requestURI, String payload) {

        String restPayload = "";

        RestServiceAdapter restServiceAdapter = RestServiceAdapterFactory.newFactory().createRestServiceAdapter();
        restServiceAdapter.clearRequestProperties();
        restServiceAdapter.setConnectionName("restapi");
        restServiceAdapter.setRequestMethod(httpMethod);
        restServiceAdapter.addRequestProperty("Content-Type", "application/json");
        restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
        restServiceAdapter.setRequestURI(requestURI);
        restServiceAdapter.setRetryLimit(0);

        String response = "";

        //set payload if there is payload passed with the request
        if (payload != null) {
            //send with empty payload
            restPayload = payload;
        }

        try {
            response = (String) restServiceAdapter.send(restPayload);
        } catch (Exception e) {
            //log error
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "invokeRestRequest",
                      "Invoke of REST Resource failed for " + httpMethod + " to " + requestURI);
            Trace.log("REST_JSON", Level.SEVERE, this.getClass(), "invokeRestRequest", e.getLocalizedMessage());
        }
        return response;
    };
}
