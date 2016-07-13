package cn.buer.util.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/**
 * 
* @ClassName: HttpClientUtil
* @Description: httpclicent工具类
* @author WangBuEr
* @date 2016年3月30日 下午1:36:39
*
 */
public class HttpClientUtil {
	/**
	 * 根据服务获取服务相应内容
	 * @param url
	 * @return
	 * @ 
	 */
	public static String  getServiceResponseAsString(String url)  {
		return getServiceResponseAsString(url, null);
	}
	/**
	 * 根据服务获取服务相应内容
	 * @param url
	 * @param params
	 * @return
	 * @ 
	 */
	public static String getServiceResponseAsString(String url, Map<String, String> params)  {
		return getServiceResponseAsString(url, params, null, 60*1000);
	}
	/**
	 * 根据服务获取服务相应内容
	 * @param url
	 * @param params
	 * @param heads
	 * @param timeOut
	 * @return
	 * @ 
	 */
	public static String getServiceResponseAsString(String url, Map<String, String> params, Map<String, String> heads, Integer timeOut)  {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			URIBuilder uri = new URIBuilder();
			uri.setPath(url);
			if(params != null){
				for(Map.Entry<String, String> entry : params.entrySet()){
					uri.addParameter(entry.getKey(), entry.getValue());
				}
			}
			HttpGet httpGet = new HttpGet(uri.build());
			if(heads != null){
				for(Map.Entry<String, String> entry : heads.entrySet()){
					httpGet.addHeader(entry.getKey(), entry.getValue());
				}
			}
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut).setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
			httpGet.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			try {
				if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
					HttpEntity entity = response.getEntity();
					return EntityUtils.toString(entity);
				}else{
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	/**
	 * 根据服务获取服务相应内容
	 * @param url
	 * @return
	 * @ 
	 */
	public static String  postServiceResponseAsString(String url)  {
		return postServiceResponseAsString(url, null,"UTF-8");
	}
	/**
	 * 根据服务获取服务相应内容
	 * @param url
	 * @param params
	 * @return
	 * @ 
	 */
	public static String postServiceResponseAsString(String url, Map<String, String> params,String charset)  {
		return postServiceResponseAsString(url, params, null,charset,60*1000);
	}
	/**
	 * Http request ：Post
	 *
	 * @param url
	 * @param params
	 * @param heads
	 * @param timeOut (Millisecond)
	 * @return String of request result
	 */
	public static String postServiceResponseAsString(String url, Map<String, String> params, Map<String, String> heads, String charset,Integer timeOut)
			  {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			List<NameValuePair> pairs = null;
			if(params != null){
				pairs = new ArrayList<NameValuePair>(params.size());
				for(Map.Entry<String, String> entry : params.entrySet()){
					String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
				}
			}
			HttpPost post = new HttpPost(url);
			if(pairs != null && pairs.size() > 0){
				post.setEntity(new UrlEncodedFormEntity(pairs,charset));
            }
			if(heads != null){
				for(Map.Entry<String, String> entry : heads.entrySet()){
					post.addHeader(entry.getKey(), entry.getValue());
				}
			}
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut).setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
			post.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(post);
			try {
				if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
					HttpEntity entity = response.getEntity();
					return EntityUtils.toString(entity);
				}else{
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 根据服务获取服务相应内容
	 * @param url
	 * @param params
	 * @return
	 * @ 
	 */
	public static String postJsonServiceResponseAsString(String url, String jsonData,String charset)  {
		return postJsonServiceResponseAsString(url, jsonData, null,charset,60*1000);
	}
	/**
	 * Http request ：Post
	 *
	 * @param url
	 * @param params
	 * @param heads
	 * @param timeOut (Millisecond)
	 * @return String of request result
	 */
	public static String postJsonServiceResponseAsString(String url,String jsonData, Map<String, String> heads, String charset,Integer timeOut)
			  {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			
			HttpPost post = new HttpPost(url);
			StringEntity postJsonEntity = new StringEntity(jsonData.toString(),charset);//解决中文乱码问题    
			postJsonEntity.setContentEncoding(charset);    
			postJsonEntity.setContentType("application/json");    
            post.setEntity(postJsonEntity);    
			if(heads != null){
				for(Map.Entry<String, String> entry : heads.entrySet()){
					post.addHeader(entry.getKey(), entry.getValue());
				}
			}
			RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(timeOut).setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
			post.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(post);
			try {
				if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
					HttpEntity entity = response.getEntity();
					return EntityUtils.toString(entity);
				}else{
					return null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
