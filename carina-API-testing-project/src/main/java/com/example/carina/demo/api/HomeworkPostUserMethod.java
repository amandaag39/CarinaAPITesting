package com.example.carina.demo.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;
import org.skyscreamer.jsonassert.JSONCompareMode;

@Endpoint(url = "${base_url}/users/add", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/users/_post/homework-rq.json")
@ResponseTemplatePath(path = "api/users/_post/homework-rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class HomeworkPostUserMethod extends AbstractApiMethodV2 {

    public HomeworkPostUserMethod() {
        super("api/users/_post/homework-rq.json","api/users/_post/homework-rs.json", "api/users/homework-user.properties");
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
    }
}
