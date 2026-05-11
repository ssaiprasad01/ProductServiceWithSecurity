package com.lambda.Lambdaintegration.security;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtAuthorizer
        implements RequestHandler<Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> handleRequest(
            Map<String, Object> input,
            Context context) {

        String token =
                (String) input.get("authorizationToken");

        // Read token from environment variable
        String expectedToken =
                System.getenv("AUTH_TOKEN");

        String effect = "Deny";

        if (token != null &&
                token.equals("Bearer " + expectedToken)) {

            effect = "Allow";
        }

        String methodArn =
                (String) input.get("methodArn");

        Map<String, Object> response =
                new HashMap<>();

        Map<String, Object> policyDocument =
                new HashMap<>();

        policyDocument.put("Version", "2012-10-17");

        Map<String, String> statement =
                new HashMap<>();

        statement.put("Action", "execute-api:Invoke");
        statement.put("Effect", effect);
        statement.put("Resource", methodArn);

        policyDocument.put(
                "Statement",
                List.of(statement)
        );

        response.put("principalId", "saiprasad");
        response.put("policyDocument", policyDocument);

        return response;
    }
}