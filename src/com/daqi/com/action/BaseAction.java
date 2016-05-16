package com.daqi.com.action;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qxinli.utils.JSONUtils;
import com.qxinli.utils.Struts2Utils;
public class BaseAction {
	private static Logger logger = LoggerFactory.getLogger(BaseAction.class);
	
	public static final String RETURN_CODE = "code";
	public static final String MESSAGE = "message";
	
	/**
	 *  request
	 */
	protected HttpServletRequest request = ServletActionContext.getRequest();
	
	/**
	 * response
	 */
	protected HttpServletResponse response = ServletActionContext.getResponse();
	
	/**
	 * 获取session会话
	 * @return
	 */
	protected HttpSession getSession(){
		return request.getSession();
	}
	/**
	 * for 分页
	 * 第 pageIndex 页
	 */
	protected int pageIndex=1;
	
	/**
	 * for 分页
	 * 总共 totalPage 页
	 */
	protected int totalPage;//总共多少页
	
	/**
	 * for 分页
	 * 页面显示数量
	 */
	public int pageSize=10;//页面显示数量
	
	public void out(String data){
		PrintWriter printWriter = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		try{
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.setHeader("Cache-Control", "no-store");
			
			printWriter = response.getWriter();
			printWriter.write(data);
			printWriter.flush();
		}catch(Exception ex){
			
		}finally{
			printWriter.close();
		}
		
	}
	
	
	
	
	
	
	/**
	 * 获取String类型参数值
	 * 
	 * @param paramName
	 *            参数名
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	protected String getString(String paramName, String defaultValue) {
		String value = request.getParameter(paramName);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		} else {
			return value.trim();
		}
	}

	/**
	 * 获取Integer类型参数值
	 * 
	 * @param paramName
	 *            参数名
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	protected Integer getInt(String paramName, Integer defaultValue) {
		String value = getString(paramName, null);
		if (value != null) {
			try {
				return Integer.valueOf(value.trim());
			} catch (Exception e) {
			}
		}
		return defaultValue;
	}

	/**
	 * 获取Long类型参数值
	 */
	protected Long getLong(String paramName, Long defaultValue) {
		String value = getString(paramName, null);
		if (value != null) {
			try {
				return Long.valueOf(value.trim());
			} catch (Exception e) {
			}
		}
		return defaultValue;
	}
	
	/**
	 * 获取Boolean类型参数值
	 * 
	 * @param paramName
	 *            参数名
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	protected Boolean getBoolean(String paramName, Boolean defaultValue) {
		String value = getString(paramName, null);
		if (value != null) {
			try {
				return Integer.valueOf(value) != 0;
			} catch (Exception e) {
			}
			try {
				return Boolean.valueOf(value);
			} catch (Exception e) {
			}
		}
		return defaultValue;
	}
	
	

	

	/**
	 * 输出json字符串
	 */
	protected void renderJson(Map<String, Object> resultMap) {
		String jsonString = JSONUtils.Object2JSON(resultMap);
		Struts2Utils.renderJson(jsonString, new String[0]);
	}
	
	/**
	 * 输出html信息
	 */
	protected void renderHtml(Map<String, Object> resultMap) {
		String html = JSONUtils.Object2JSON(resultMap);
		Struts2Utils.renderHtml(html);
	}
	
	/**
	 * 获取远程ip
	 * @return
	 */
	protected String getRemoteAddress() {  
        String ip = this.request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }
	
	/**
	 * 获取远程设备地址
	 * @param ip
	 * @return
	 */
	public String getMACAddress(String ip) {  
        String str = "";  
        String macAddress = "";  
        try {  
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);  
            InputStreamReader ir = new InputStreamReader(p.getInputStream());  
            LineNumberReader input = new LineNumberReader(ir);  
            for (int i = 1; i < 100; i++) {  
                str = input.readLine();  
                if (str != null) {  
                    if (str.indexOf("MAC Address") > 1) {  
                        macAddress = str.substring(  
                                str.indexOf("MAC Address") + 14, str.length());  
                        break;  
                    }  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace(System.out);  
        }  
        return macAddress;  
    } 
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	

}