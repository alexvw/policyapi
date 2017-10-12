package com.idw.policymanager.adaptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * @author avanderwoude
 */

public class PolicyEngineBaseAdaptor{
	

	/*
	 * this function sends the request via httpClient and returns the response data as utf8
	 * is as simple and straightforward as possible. If you require extra functionality, override with extra	 
	 * 
	 * @param request the http Get string request
	 * 
	 * @return String the data returned from the http Get request. This response is not parsed beyond utf8
	 */
	public static String sendGETRequest(String request) throws Exception {
		try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //TODO debug
            //logger.info("request = " + request);
            HttpGet httpGet = new HttpGet(request);
            //TODO implement headers for more versatility 
            httpGet.addHeader("content-type", "application/json");
            httpGet.addHeader("charset", "UTF-8");
            HttpResponse response = httpClient.execute(httpGet);
            //logger.info("result = " + response.getStatusLine());
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            httpClient.close();
            return result;
        }
        catch(Exception e){
            System.err.println(e);
            throw new Exception("Exception occurred while sending request" + e.getMessage());
        }
	}
	
	/*
	 * this function sends the request via httpClient and returns the response data as utf8
	 * is as simple and straightforward as possible. If you require extra functionality, override with extra	 
	 * 
	 * @param url the http POST url
	 * @param body the object to include in the body as JSON
	 * 
	 * @return String the data returned from the http Get request. This response is not parsed beyond utf8
	 */
	public static String sendPOSTRequest(String url, Object body) throws Exception {
		//attempt to convert object to json
		ObjectMapper mapper = new ObjectMapper();
		
		//Object to JSON in String
		String bodyAsJSON = "";
		try {
			bodyAsJSON = mapper.writeValueAsString(body);
		} catch (JsonProcessingException e){
            System.err.println(e);
            throw new Exception("Exception occurred while attempting to json object" + e.getMessage());
		}
		
		//build and send post request
		try{            
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            //attach body
            StringEntity entity = new StringEntity(bodyAsJSON);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            //make request
            CloseableHttpResponse response = client.execute(httpPost);
            //make sure is 200
            if (response.getStatusLine().getStatusCode() != 200)
            	throw new Exception("Exception occurred while sending request, received response "+response.getStatusLine().getStatusCode());
            //get response
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            client.close();
            return result;
        }
        catch(Exception e){
            System.err.println(e);
            throw new Exception("Exception occurred while sending request" + e.getMessage());
        }
	}
	
	/*
	 * Super useful code to split a URL query with custom delimiter
	 */
	public static Map<String, List<String>> splitURLQuery(String query, String delimiter) throws UnsupportedEncodingException {
		final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
		final String[] pairs = query.split(delimiter);
		//logger.info("Splitting "+query +" by "+delimiter);
			for (String pair : pairs) {
				final int idx = pair.indexOf("=");
				final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
				if (!query_pairs.containsKey(key)) {
					query_pairs.put(key, new LinkedList<String>());
				}
				final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
				query_pairs.get(key).add(value);
			}
			return query_pairs;
		}
	
	/*
	 * another super useful util class to parse complex properties strings - embed variables in the string
	 * I use it to do things like [[variable]] in properties files
	 */
	public static String replaceValues(final String template,
		    final Map<String, String> values, String patternString){
			//logger.info("Trying to replace "+ template +" / "+values);
		    final StringBuffer sb = new StringBuffer();
		    final Pattern pattern =
		        Pattern.compile(patternString, Pattern.DOTALL);
		    final Matcher matcher = pattern.matcher(template);
		    while(matcher.find()){
		        final String key = matcher.group(1);
		        String replacement = values.get(key);
		        
		        //logger.info("key/replacement: "+key +"/"+replacement);
		        
		        if(replacement == null){
		            /*throw new IllegalArgumentException(
		               "Missing Key: "
		                + key);*/
		        	//To allow for optional attributes, don't throw exception if key is missing.
		        	//instead, replace it with a blank string. This way, you can do things like [[key1]][[key2]] and it will grab one, both, or none of those
		        	replacement = "";
		        }
		        matcher.appendReplacement(sb, replacement);
		    }
		    matcher.appendTail(sb);
		    return sb.toString();
		}
	
	/*
	 * handy dynamic jackson mapping using reflectology
	 * awfully loose tho, be careful with this
	 */
	public static Object parseObjectFromJSON(String JSON, Object object) throws InstantiationException, IllegalAccessException, JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		//infer class type
		Class theClass = object.getClass();
		//JSON from String to Object
		object = mapper.readValue(JSON, theClass);
		
		return object;
	}

}
