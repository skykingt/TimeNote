package com.example.administrator.timenote.Manager.UserManager;


import android.os.Message;
import android.util.Base64DataException;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.timenote.Model.BeanUserInformation;
import com.example.administrator.timenote.Ui.MainActivity;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.annotation.Target;
import java.util.List;

import static com.example.administrator.timenote.Model.BeanUserInformation.tryLoginUser;

/**
 * Created by Sprou on 2018/5/26.
 */

public class UserLogin {
    // 命名空间
    String nameSpace = "http://tempuri.org/";
    // 调用的方法名称
    String methodName = "LoadUser";
    // EndPoint
    String endPoint = "http://39.108.124.121:5818/WebService1.asmx";
    // SOAP Action
    String soapAction = "http://tempuri.org//LoadUser/";

    // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);

    HttpTransportSE transport = new HttpTransportSE(endPoint);

    //创建子线程并引用webservice层的LoadUser方法
    public void getRemoteInfo(String userid) {
        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("useremail", userid);
        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        (new MarshalBase64()).register(envelope);
        // 等价于envelope.bodyOut = rpc;
        //envelope.setOutputSoapObject(rpc);
        transport.debug = false;
        //Message msg = MainActivity.myHandler.obtainMessage();
        BeanUserInformation u = new BeanUserInformation();
        try {
            transport.call(soapAction, envelope);
//            if (envelope.getResponse() == null) throw new Base64DataException("没有该用户");

            SoapObject result = (SoapObject) envelope.getResponse();

            u.setUseremail(result.getProperty(2).toString());
            System.out.println(u.getUseremail());
            u.setUserpassword(result.getProperty(1).toString());
            System.out.println(u.getUserpassword());
            //tryLoginUser = u;
            //msg.what = 1;
           //msg.obj = tryLoginUser;
           // MainActivity.myHandler.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
           // msg.what = 0;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        BeanUserInformation.tryLoginUser=u;
    }
    public static void main(String[] args){
        UserLogin userLogin = new UserLogin();
        userLogin.getRemoteInfo("306273815@qq.com");
        System.out.println(BeanUserInformation.tryLoginUser.getUseremail());
        System.out.println(BeanUserInformation.tryLoginUser.getUserpassword());
    }
}