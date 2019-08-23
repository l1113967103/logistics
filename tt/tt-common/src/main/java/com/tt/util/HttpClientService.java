package com.tt.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HttpClientService {
	@Autowired
	private CloseableHttpClient httpClient;
	@Autowired
	private RequestConfig requestConfig;
	
	/**
	 * 思想:让其他程序员调用时方便.
	 * 问题:	1.参数几个?
	 *		2.返回值是什么?
	 *		3.如何实现?
	 *  解决问题:
	 *	get请求:http://www.jt.com?id=1&name=tomcat....
	 *		url:用户指定的请求地址
	 *		Map<String,String> 接收用户参数 字符串类型
	 *             字符集: 避免出现乱码问题  
	 *	返回值: 使用json数据返回
	 *	
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 */
	
	public String doGet(String url,Map<String,String> params,String charset){
		String result = null;
		//1.判断字符集是否为null 如果为null指定默认字符集
		if(StringUtils.isEmpty(charset)) {
			charset = "UTF-8";
		}
		
		/**
		 * 2.检查用户是否传递参数.
		 * 	url:http://www.jt.com?key=value&key2=value2
		 * 	如果用户传递了参数.则进行url拼接
		 *      如果没传 则直接发起get请求即可.
		 */
		if(params != null) {
			//2.1拼接用户参数key-value格式
			url = url + "?";
			for (Map.Entry<String,String> entry :params.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				//www.jt.com?key=value&key2=value2&
				url =url+key+"="+value+"&";
			}
			//url:www.jt.com?id=1&name=tomcat  需要去除多余的&符
			url = url.substring(0,url.length()-1);
		}
		
		//3.准备请求对象 发起GET请求
		HttpGet httpGet = new HttpGet(url);
		//标识请求超时时间!!!!
		httpGet.setConfig(requestConfig);
		
		try {
			//4.发起http请求
			CloseableHttpResponse httpResponse = 
					httpClient.execute(httpGet);
			
			if(httpResponse.getStatusLine().getStatusCode() == 200) {
				//标识程序执行正确
				result = EntityUtils.toString(httpResponse.getEntity(),charset);
			}else {
				//如果状态码不是200 则表示程序执行失败
				System.out.println("请求远程数据时失败.请检查url是否正确!!");
				throw new RuntimeException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return result;
	}

	public String doGet(String url){

		return doGet(url, null, null);
	}

	public String doGet(String url,Map<String,String> params){

		return doGet(url, params, null);
	}

	public String doGet(String url,String charset){

		return doGet(url, null, charset);
	}

	//实现httpClient POST提交
	public String doPost(String url,Map<String,String> params,String charset){
		String result = null;

		//1.定义请求类型
		HttpPost post = new HttpPost(url);
		post.setConfig(requestConfig);  	//定义超时时间

		//2.判断字符集是否为null
		if(StringUtils.isEmpty(charset)){

			charset = "UTF-8";
		}

		//3.判断用户是否传递参数
		if(params !=null){
			//3.2准备List集合信息
			List<NameValuePair> parameters = 
					new ArrayList<>();

			//3.3将数据封装到List集合中
			for (Map.Entry<String,String> entry : params.entrySet()) {

				parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}

			//3.1模拟表单提交
			try {
				UrlEncodedFormEntity formEntity = 
						new UrlEncodedFormEntity(parameters,charset); //采用u8编码

				//3.4将实体对象封装到请求对象中
				post.setEntity(formEntity);
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
		}

		//4.发送请求
		try {
			CloseableHttpResponse response = 
					httpClient.execute(post);

			//4.1判断返回值状态
			if(response.getStatusLine().getStatusCode() == 200) {

				//4.2表示请求成功
				result = EntityUtils.toString(response.getEntity(),charset);
			}else{
				System.out.println("获取状态码信息:"+response.getStatusLine().getStatusCode());
				throw new RuntimeException();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}



	public String doPost(String url){

		return doPost(url, null, null);
	}

	public String doPost(String url,Map<String,String> params){

		return doPost(url, params, null);
	}

	public String doPost(String url,String charset){

		return doPost(url, null, charset);
	}
}
