package com.example.myas.myasyntest.base;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2015-12-18.
 */
public abstract class WebServiceClient {
    private static final String NAME_SPACE = "http://tempuri.org/";

    protected abstract String getServiceUrl();

    public SoapObject getData(String method, Map<String, Object> params) {

        SoapObject soap = null;

        String strNameSpace = NAME_SPACE;
        String strMethodName = method;
        String strUrl = getServiceUrl();
        String soapAction = strNameSpace.endsWith("/") ? (strNameSpace + strMethodName)
                : (strNameSpace + "/" + strMethodName);

        try {
            SoapObject request = buildRequest(method, params);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.bodyOut = request;
            envelope.encodingStyle = SoapSerializationEnvelope.ENC;

            HttpTransportSE ht = new HttpTransportSE(strUrl);

            ht.call(soapAction, envelope);
            if (!envelope.getResponse().equals(null)) {
                // response = (SoapPrimitive) envelope.getResponse();
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                soap =soapObject;
            }

        } catch (Exception e) {
            Log.e("WebServiceClient", e.toString());
        }

        return soap;
    }

    private SoapObject buildRequest(String arg1, Map<String, Object> arg2) {
        SoapObject request = new SoapObject(NAME_SPACE, arg1);
        if (arg2 != null && !arg2.isEmpty()) {
            Iterator<Map.Entry<String, Object>> it = arg2.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) it.next();
                String key = (String) entry.getKey();
                Object value = entry.getValue();
                request.addProperty(key, value);
                Log.d("webservice-getdata:" + key, String.valueOf(value));
            }
        }

        return request;
    }
}
