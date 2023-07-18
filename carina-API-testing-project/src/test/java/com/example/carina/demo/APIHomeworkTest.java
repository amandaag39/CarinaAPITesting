package com.example.carina.demo;

import com.example.carina.demo.api.GetUserMethods;
import com.example.carina.demo.api.HomeworkDeleteUserMethod;
import com.example.carina.demo.api.HomeworkGetUserMethods;
import com.example.carina.demo.api.HomeworkPostUserMethod;
import com.zebrunner.carina.api.APIMethodPoller;
import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.Logger;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class APIHomeworkTest implements IAbstractTest {


    @Test()
    @MethodOwner(owner = "homework")
    public void testHomeworkCreateUser() throws Exception {
        HomeworkPostUserMethod api = new HomeworkPostUserMethod();

        api.setProperties("api/users/homework-user.properties");

        AtomicInteger counter = new AtomicInteger(0);

        api.callAPIWithRetry()
                .withLogStrategy(APIMethodPoller.LogStrategy.ALL)
                .peek(rs -> counter.getAndIncrement())
                .until(rs -> counter.get() == 1)
                .pollEvery(1, ChronoUnit.SECONDS)
                .stopAfter(10, ChronoUnit.SECONDS)
                .execute();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "homework")
    public void testHomeworkGetUsers() {
        HomeworkGetUserMethods getHomeworkUserMethods = new HomeworkGetUserMethods();
        getHomeworkUserMethods.callAPIExpectSuccess();
        getHomeworkUserMethods.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getHomeworkUserMethods.validateResponseAgainstSchema("api/users/_get/homeworkrs.schema");
    }

    @Test()
    @MethodOwner(owner = "homework")
    public void testHomeworkDeleteUser() {
        HomeworkDeleteUserMethod homeworkDeleteUserMethod = new HomeworkDeleteUserMethod();
        homeworkDeleteUserMethod.callAPIExpectSuccess();
        homeworkDeleteUserMethod.validateResponse();
    }
}
