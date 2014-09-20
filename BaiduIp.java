package two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
/**
 * 
 * @author zyp
　* @Description 利用百度地图获得周围的吉祥馄饨店铺   
 *				但是由于百度地图会将吉祥馄饨面等店铺输出  
 *				所以在这里最好使用经纬度定位
 * @created Apr 6, 2014 10:03:11 AM
　* @History 
 * @version v1.0
 */
public class BaiduIp {
	
	
	public  List ipGetCoor(String ip){
		StringBuffer document = new StringBuffer();
		try{			
			//http://api.map.baidu.com/location/ip?ak=E4805d16520de693a3fe707cdc962045&ip=202.198.16.3&coor=bd09ll
//			342c0bced1035142422dc6dce4a863e0
			String ipUrl="http://api.map.baidu.com/location/ip?ak=342c0bced1035142422dc6dce4a863e0&ip=IP&coor=bd09ll".replace("IP", ip);
			URL url = new URL(ipUrl);
			
			//远程url
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null)
				document.append(line + " ");
				reader.close();
				}catch(MalformedURLException e) {
					e.printStackTrace(); 	
					}catch(IOException e){
						e.printStackTrace(); 		
						}
		
		String xmlDoc = document.toString();
//		System.out.println("xmlDoc="+xmlDoc);
		  //创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
        InputSource source = new InputSource(read);
        //创建一个新的SAXBuilder
        SAXBuilder sb = new SAXBuilder();
        
        String shop=null;
        List shops = new ArrayList();
        try {
            //通过输入源构造一个Document
            Document doc = sb.build(source);
            //取的根元素
            Element root = doc.getRootElement();
            System.out.println("address:"+root.getAttributeValue("address"));
//            System.out.println("message:"+root.getAttributeValue("message"));
//            System.out.println("PlaceSearchResponse="+root.getName());//输出根元素的名称（测试）
////         
//            System.out.println("rootTest="+root.getAttribute("message"));//输出根元素的名称（测试）
            //得到根元素所有子元素的集合
            List jiedian = root.getChildren();
            
            Element et = null;
//            et = (Element) jiedian.get(2);
//            String name=et.getAttributeValue("name");
            for(int i=0;i<jiedian.size();i++){
                et = (Element) jiedian.get(i);//循环依次得到子元素
                
//                if(et.getAttributeValue("inputindex").equals("1")){
//                    et.setAttribute("name","1");
                }
//                et.setAttribute("age","15");
//            System.out.println("name===:"+et.getAttributeValue("name"));
//            System.out.println("address:"+et.getAttributeValue("address"));
            
            /**//*
             * 如要取<result>下的第一个子元素的名称
             * 即距离就近到店到名称
             * String dian
             */
           
            et = (Element) jiedian.get(2);
            List zjiedian = et.getChildren();
            
            for(int j=0;j<zjiedian.size();j++){
                Element xet = (Element) zjiedian.get(j);
//                shop=xet.getChildText("name");
//                shops.add(shop);
                System.out.println(xet.getChildText("x"));
                
            }
//                System.out.println("shops="+shops);
        } catch (JDOMException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        } catch (IOException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return shops;
	}
	 public static void main(String args[])
	 {
		BaiduIp bi=new BaiduIp();
		bi.ipGetCoor("124.93.200.218");
	    }
}
