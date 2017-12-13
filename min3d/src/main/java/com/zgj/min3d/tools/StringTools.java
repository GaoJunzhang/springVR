package com.zgj.min3d.tools;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;
import java.util.UUID;

/**
 * Created by alexis on 2017/5/10.
 */
public class StringTools {
    static public String getRandomString(int length) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(36);// [0,62)
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    static public boolean isStringEmpty(String str) {
        return (str == null || str.trim().length() <= 0);
    }

    static public String convertToXml(Object obj) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> T xmlStringToObject(String xml, Class<T> type) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }

    static public <T> T jsonStringToObject(String str, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(str, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    static public String convertToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String sha1(String str) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    public static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    public static String timeStampToString(Timestamp date) {
        if (date == null)
            return "";
        return dateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String dateToString(Timestamp date, String format) {
        if (date == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String dateToString(Date date) {
        return dateToString(date, "yyyyMMddHHmmss");
    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Timestamp stringToTimestamp(String time) {
        return stringToTimestamp(time, "yyyyMMddHHmmss");
    }

    public static Timestamp stringToTimestamp(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Timestamp timestamp = null;
        try {
            timestamp = new Timestamp(sdf.parse(time).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    public static String numberScale(Double num, int len) {
        String val = "";
        try {
            BigDecimal f = new BigDecimal(num);
            val = f.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * class_name:StringTools
     * param: format 日期类型(yyyy-MM-dd),str 日期字符串
     * describe: 字符串转日期
     * creat_user: ZhangGaoJun@zhanggj@seeyoo.cn
     * creat_date: 2017/11/14
     * creat_time: 9:07
     **/
    public static Date strToDate(String format, String str) {
        Date date = null;
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);//小写的mm表示的是分钟
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
