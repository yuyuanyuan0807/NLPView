import java.io.BufferedInputStream; 
import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.ByteArrayOutputStream; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.OutputStream; 
import java.io.OutputStreamWriter; 
import java.io.PrintWriter; 
import java.io.Writer; 
import java.net.HttpURLConnection; 
import java.net.MalformedURLException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.net.URL; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Properties; 
import java.util.Set; 
import java.util.regex.Pattern;

public class openUrl { 
private static ServerSocket server; 
private final static int port = 80; 

public static void main(String[] args) { 
try { 
server = new ServerSocket(port); 
System.out.println("server port: " + port + " start..."); 
} catch (Throwable t) { 
t.printStackTrace(); 
return; 
} 
nextServerThreadStart(); 
} 

private static Thread nextServerThreadStart() { 
Thread t = new Thread(new Runnable() { 
public void run() { 
Socket socket = null; 
try { 
socket = server.accept(); 
} catch (Throwable t) { 
t.printStackTrace(); 
} finally { 
nextServerThreadStart(); 
} 

if (socket != null) { 
// System.out.println(socket.getPort()); 
Thread t = sendThreadStart(socket); 
while (t.isAlive()) { 
try { 
Thread.sleep(100); 
} catch (InterruptedException e) { 
// TODO Auto-generated catch block 
e.printStackTrace(); 
} 
} 

try { 
socket.close(); 
} catch (IOException e) { 
// TODO Auto-generated catch block 
e.printStackTrace(); 
} 
} 
} 
}); 
t.start(); 
return t; 
} 

private static Thread sendThreadStart(final Socket socket) { 
Thread t = new Thread(new Runnable() { 
public void run() { 
BufferedReader is = null; 
OutputStream os = null; 
try { 
socket.setSoTimeout(20000); 
Map<String, String> paramters = getParamters(socket); 
String encoding = paramters.get("encoding"); 
String url = paramters.get("url"); 
String content = getURLContent(url, encoding); 
if (content == null) 
return; 

if (content.indexOf("<base ") == -1) { 
content = content.replaceFirst("<head>", "<head><base href=\"" + url+"\" />"); 
} 

Writer out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), encoding)); 
out.write(content); 
out.flush(); 
} catch (Exception e) { 
e.printStackTrace(); 
} finally { 
/* 
 * if (out != null){ try { out.close(); } catch (Exception 
 * e) {} } 
 */ 
/* 
 * if (is != null) { try { is.close(); } catch (Exception e) 
 * { } } 
 */ 

if (os != null) { 
try { 
os.close(); 
} catch (Exception e) { 
} 
} 
} 

} 
}); 
t.start(); 
return t; 
} 

private static String gethead(final Socket socket) throws Exception { 
InputStream reader = socket.getInputStream(); 
int available; 
ByteArrayOutputStream data = new ByteArrayOutputStream(); 
while ((available = reader.available()) > 0) { 
byte[] b = new byte[available]; 
reader.read(b); 
data.write(b); 
} 
// System.out.println(data.toString()); 
return data.toString(); 
} 

private static Map<String, String> getParamters(final Socket socket) throws Exception { 
Map<String, String> map = new HashMap<String, String>(); 
String head = gethead(socket); 
if (head.split(" ").length < 2) 
return map; 
String GETStr = head.split(" ")[1]; 
String[] split = GETStr.replaceFirst("%\\?", "").split("[&?]+"); 
for (String str : split) { 
int i = str.indexOf("="); 
if (i >= 1 && i <= str.length() - 2) { 
map.put(str.substring(0, i), str.substring(i + 1)); 
} 
} 
return map; 
} 

public static String getURLContent(String url, String encoding) { 
if (url == null || "".equals(url.trim())) 
return null; 

StringBuffer content = new StringBuffer(); 
try { 
// 新建URL对象 
URL u = new URL(url); 
InputStream in = new BufferedInputStream(u.openStream()); 
InputStreamReader theHTML = new InputStreamReader(in, encoding != null ? encoding : "gb2312"); 
int c; 
while ((c = theHTML.read()) != -1) { 
content.append((char) c); 
} 
} 
// 处理异常 
catch (MalformedURLException e) { 
System.err.println(e); 
} catch (IOException e) { 
System.err.println(e); 
} 
return content.toString(); 
} 

public static String Html2Text(String inputString) {
	   //过滤html标签
	   String htmlStr = inputString; // 含html标签的字符串
	   String textStr = "";
	   java.util.regex.Pattern p_script;
	   java.util.regex.Matcher m_script;
	   java.util.regex.Pattern p_style;
	   java.util.regex.Matcher m_style;
	   java.util.regex.Pattern p_html;
	   java.util.regex.Matcher m_html;
	   java.util.regex.Pattern p_cont1;
	   java.util.regex.Matcher m_cont1;
	   java.util.regex.Pattern p_cont2;
	   java.util.regex.Matcher m_cont2;

	   try {
	    String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
	    // }
	    String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
	    // }
	    String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

	    String regEx_cont1 = "[\\d+\\s*`~!@#$%^&*\\(\\)\\+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘：”“’_]"; // 定义HTML标签的正则表达式

	    String regEx_cont2 = "[\\w[^\\W]*]"; // 定义HTML标签的正则表达式[a-zA-Z]

	    p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
	    m_script = p_script.matcher(htmlStr);
	    htmlStr = m_script.replaceAll(""); // 过滤script标签

	    p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
	    m_style = p_style.matcher(htmlStr);
	    htmlStr = m_style.replaceAll(""); // 过滤style标签

	    p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
	    m_html = p_html.matcher(htmlStr);
	    htmlStr = m_html.replaceAll(""); // 过滤html标签

	    p_cont1 = Pattern.compile(regEx_cont1, Pattern.CASE_INSENSITIVE);
	    m_cont1 = p_cont1.matcher(htmlStr);
	    htmlStr = m_cont1.replaceAll(""); // 过滤其它标签

	    p_cont2 = Pattern.compile(regEx_cont2, Pattern.CASE_INSENSITIVE);
	    m_cont2 = p_cont2.matcher(htmlStr);
	    htmlStr = m_cont2.replaceAll(""); // 过滤html标签

	    textStr = htmlStr;

	   } catch (Exception e) {
	    System.err.println("Html2Text: " + e.getMessage());
	   }
	   return textStr;// 返回文本字符串
	}

} 

