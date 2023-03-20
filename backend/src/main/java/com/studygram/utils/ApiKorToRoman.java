package com.studygram.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static org.apache.el.util.MessageFactory.get;

public class ApiKorToRoman {
    private String clientId = "pkKJhJjNV9d03_Rivs_1";
    private String clientSecret = "qCPUQknrYV";

    public String getKeyword(String jsonResponse) throws ParseException {
        /* Example
        {"aResult":[{"sFirstName":"\ud64d","aItems":[{"name":"Hong Gildong","score":"99"},{"name":"Hong Kildong","score":"96"}, ...]}
         */
        String keyword = null;
        JSONParser jsonParser = new JSONParser();
        // String -> Object parsing
        Object object = jsonParser.parse(jsonResponse);
        // Object -> JSONObject parsing
        JSONObject jsonObject = (JSONObject) object;
        // JSONObject -> JSONArray
        JSONArray jsonArray = (JSONArray) jsonObject.get("aResult");
        if(jsonArray.size() > 0) {
            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
            JSONArray jsonArray1 = (JSONArray) jsonObject1.get("aItems");
            if(jsonArray1.size() > 0) {
                JSONObject jsonObject2 = (JSONObject) jsonArray1.get(0);
                String tmp = (String)jsonObject2.get("name");
                keyword = tmp.replace(" ", "_");
            }
        }

        return keyword;
    }

    public String convertLang(String keyword) throws ParseException {
        String text = keyword;
        try{
            text = URLEncoder.encode(text, "UTF-8");
        } catch(UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }
        String apiURL = "https://openapi.naver.com/v1/krdict/romanization?query="+ text;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        System.out.println("네이버 한글인명로마변환 API 결과 " +responseBody);
        return getKeyword(responseBody);
    }

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
