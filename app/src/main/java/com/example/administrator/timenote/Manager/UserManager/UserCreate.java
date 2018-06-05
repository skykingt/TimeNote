package com.example.administrator.timenote.Manager.UserManager;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Sprou on 2018/5/28.
 */

public class UserCreate {
    public String getRemoteInfo(String userid, String userpwd, String username) {
        // 命名空间
        String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        String methodName = "CreateUser ";
        // EndPoint
        String endPoint = "http://39.108.124.121:5818/WebService1.asmx";
        // SOAP Action
        String soapAction = "http://tempuri.org//CreateUser/";
        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("useremail", userid);
        rpc.addProperty("userpwd", userpwd);
        rpc.addProperty("username", username);
        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        (new MarshalBase64()).register(envelope);
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE transport = new HttpTransportSE(endPoint);
        transport.debug = true;
        String result = "false";
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            if (envelope.getResponse()!=null) {
                System.out.println(envelope.getResponse());
                result = envelope.getResponse().toString();
                System.out.print(result);
                return result;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }
    public static void main(String[] args){
        UserCreate userCreate = new UserCreate();
        String sign = userCreate.getRemoteInfo("1191583108@qq.com","12345678","goldenboy");
        System.out.print(sign);
    }
}