package com.tstore.msg;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class MsgTest {
     /*短信测试代码*/
    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAIdmSJshoHjl27", "Rtxydu9q2lGjMKGkIOpal0CeyTWvMW");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("SignName", "ITBooking");
        request.putQueryParameter("TemplateCode", "SMS_161890189");
        request.putQueryParameter("TemplateParam", "{\"code\":\"123456\"}");
        request.putQueryParameter("PhoneNumbers", "18820148139");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
