package of.mobile;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class BarcodeBean {
    private String barcodeError = "";
    private String barcodeResult = "";
    private String barcodeFormat = "";
    private String barcodeCancelled = "";

    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public BarcodeBean() {
        super();
    }

    public void scanBarcode(ActionEvent event) {
        // Our AMX page includes a small JavaScript function which wraps the Cordova
        // barcode scanning function in a manner that makes it more suitable for invocation
        // from Java bean code. This is the function we are invoking below:
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureId(),
                                                                  "scanBarcodeFromJavaBean", new Object[] { });
    }

    public void setBarcodeFormat(String barcodeFormat) {
        String oldBarcodeFormat = this.barcodeFormat;
        this.barcodeFormat = barcodeFormat;
        propertyChangeSupport.firePropertyChange("barcodeFormat", oldBarcodeFormat, barcodeFormat);
    }

    public String getBarcodeFormat() {
        return barcodeFormat;
    }

    public void setBarcodeCancelled(String barcodeCancelled) {
        String oldBarcodeCancelled = this.barcodeCancelled;
        this.barcodeCancelled = barcodeCancelled;
        propertyChangeSupport.firePropertyChange("barcodeCancelled", oldBarcodeCancelled, barcodeCancelled);
    }

    public String getBarcodeCancelled() {
        return barcodeCancelled;
    }

    public void setBarcodeResult(String barcodeResult) {
        String oldBarcodeResult = this.barcodeResult;
        this.barcodeResult = barcodeResult;
        propertyChangeSupport.firePropertyChange("barcodeResult", oldBarcodeResult, barcodeResult);
    }

    public String getBarcodeResult() {
        return barcodeResult;
    }

    public void setBarcodeError(String barcodeError) {
        String oldBarcodeError = this.barcodeError;
        this.barcodeError = barcodeError;
        propertyChangeSupport.firePropertyChange("barcodeError", oldBarcodeError, barcodeError);
    }

    public String getBarcodeError() {
        return barcodeError;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

}

