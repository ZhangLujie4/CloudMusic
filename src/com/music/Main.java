package com.music;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	static public String SendGet(String aurl){
		// 定义一个字符串用来存储网页内容
		String url = null;
        if(aurl.substring(0, 5).equals("https"))
        {
        	url = aurl.replace("https", "http");
        }
        else{
        	url = aurl;
        }
		String result = "";
		// 定义一个缓冲字符输入流
		BufferedReader in = null;
		try
		{
			// 将string转成url对象
			URL realUrl = new URL(url);
			// 初始化一个链接到那个url的连接
			URLConnection connection = realUrl.openConnection();
			// 开始实际的连接
			connection.connect();
			// 初始化 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			// 用来临时存储抓取到的每一行的数据
			String line;
			while ((line = in.readLine()) != null)
			{
				// 遍历抓取到的每一行并将其存储到result里面
				result += line;
			}
		} catch (Exception e)
		{
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally来关闭输入流
		finally
		{
			try
			{
				if (in != null)
				{
					in.close();
				}
			} catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	static public String[] RegexString(String targetStr, String patternStr)
	{
		// 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容 // 相当于埋好了陷阱匹配的地方就会掉下去
		Pattern pattern = Pattern.compile(patternStr);
		// 定义一个matcher用来做匹配

		Matcher matcher = pattern.matcher(targetStr);
		// 如果找到了
		if (matcher.find())
		{
			// 打印出结果
			String line = matcher.group(0);
			String[] fenline = line.split("\""+"},"+"\"klyric\"");
			String lyric = fenline[0];
			String[] lyr = lyric.split("\\"+"\\n");
			return lyr;
		}

		return null;
	}
	
	static public String RegexMp3(String targetStr, String patternStr)
	{
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(targetStr);
		if(matcher.find()){
			String line = matcher.group(0);
			String[] m4a = line.split("\"");
			return m4a[2];
		}
		return null;
	}
	
	static public String RegexCBMp3(String targetStr, String patternStr)
	{
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(targetStr);
		if(matcher.find()){
			String line = matcher.group(0);
			String[] m4a = line.split("\"");
			return m4a[1];
		}
		return null;
	}
	
	static public String Regexname(String targetStr, String patternStr)
	{
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(targetStr);
		if(matcher.find()){
			String line = matcher.group(0);
			String[] name = line.split("\"");
			return name[5];
		}
		return null;
		
	}
	
	
}
