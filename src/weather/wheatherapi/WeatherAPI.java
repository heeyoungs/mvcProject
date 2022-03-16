package weather.wheatherapi;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import weather.vo.Location;
import weather.vo.Weather;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherAPI {

    public Weather useApi(Location location) {
        Weather weather = null;
        String apiURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
        String authKey = "1Ou5DpC%2BjcgNQCgURIpkipq3SS4NlnYpaLc3pa5q9Cryxj5Vq2SkqDkhMAPX%2BK9tDfKqxundj2nsDIEAwpORKg%3D%3D";
        try {
            weather = use(location.getLocationCode(),apiURL,authKey,location.getX(), location.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather;
    }

    private Weather use(Long locationCode, String apiURL,String authKey, String x, String y) throws Exception {
        Weather weather = new Weather();

        Date time = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format2 = new SimpleDateFormat("HH");

        int hour = Integer.parseInt(format2.format(time));
        String bt = null;

        if (hour <= 3) {
            bt = "0000";
        } else if (hour <= 6) {
            bt = "0300";
        } else if (hour <= 9) {
            bt = "0600";
        } else if (hour <= 12) {
            bt = "0900";
        } else if (hour <= 15) {
            bt = "1200";
        } else if (hour <= 18) {
            bt = "1500";
        } else if (hour <= 21) {
            bt = "1800";
        } else if (hour <= 24) {
            bt = "2100";
        }
        String baseDate = format1.format(time);
        String baseTime = bt;

        StringBuilder urlBuilder = new StringBuilder(apiURL);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + authKey);
        urlBuilder.append("&" + URLEncoder.encode("numOfRows=10", "UTF-8"));    // 숫자 표
        urlBuilder.append("&" + URLEncoder.encode("pageNo=1", "UTF-8"));    // 페이지 수
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(x, "UTF-8")); //경도
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(y, "UTF-8")); //위도

        URL url = new URL(urlBuilder.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + connection.getResponseCode());
        BufferedReader br;
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        connection.disconnect();
        String result = sb.toString();

        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(result));
        Document document = db.parse(is);

        try {
            document.getDocumentElement().normalize();
            System.out.println("Root Element: " + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("item");
            System.out.println("-----------------------");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String category = element.getElementsByTagName("category").item(0).getTextContent();
                    double value = Double.parseDouble(element.getElementsByTagName("obsrValue").item(0).getTextContent());

                    WeatherValue weatherValue = WeatherValue.valueOf(category);
                    switch (weatherValue) {
                        case PTY:
                            weather.setPTY(value);
                            break;
                        case REH:
                            weather.setREH(value);
                            break;
                        case RN1:
                            weather.setRN1(value);
                            break;
                        case T1H:
                            weather.setT1H(value);
                            break;
                        case UUU:
                            weather.setUUU(value);
                            break;
                        case VEC:
                            weather.setVEC(value);
                            break;
                        case VVV:
                            weather.setVVV(value);
                            break;
                        case WSD:
                            weather.setWSD(value);
                            break;
                        default:
                            throw new XMLParseException();
                    }
                }
            }
            weather.setLocationCode(locationCode);
        } catch (XMLParseException e) {
            e.printStackTrace();
        }

        return weather;

    }

    private enum WeatherValue {
        PTY, REH, RN1, T1H, UUU, VEC, VVV, WSD
    }

//    enum WeatherBaseTime {
//        _0000, _0300, _0600, _0900, _1200, _1500, _1800, _2100
//    }

}
