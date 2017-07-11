package com.sw.base.util;

import com.sw.base.exception.BusinessException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.sql.Blob;
import java.util.Calendar;
import java.util.Date;

import static com.sw.base.util.DateHelper.today;

public class StringUtils {
    /**
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
     * 例如：HelloWorld->HELLO_WORLD
     *
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String toUnderScoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * 将下划线分隔的字符串转换为驼峰式。如果转换前的字符串为空，则返回null</br>
     * 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String toHumpName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return null;
        } else if (!name.contains("_")) {
            // 不含下划线，仅将字母小写
            return name.toLowerCase();
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 标准的年龄的现实方式
     *
     * @param birthDay
     * @return
     */
    public static String getStandardAgeFromBirthday(Date birthDay) {
        String s = "";
        if (birthDay != null) {
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar1.setTime(today());
            calendar2.setTime(birthDay);
            Integer currentYear = calendar1.get(Calendar.YEAR);
            Integer currentMonth = calendar1.get(Calendar.MONTH);
            Integer currentDay = calendar1.get(Calendar.DAY_OF_MONTH);

            Integer myYear = currentYear - calendar2.get(Calendar.YEAR);
            Integer myMonth = currentMonth - calendar2.get(Calendar.MONTH);
            Integer myDay = currentDay - calendar2.get(Calendar.DAY_OF_MONTH);


            if(myDay < 0){
                myDay = myDay + 30;
                myMonth --;
            }
            if(myMonth < 0){
                myMonth = myMonth + 12;
                myYear --;
            }
            if(myYear <= 0){
                if(myMonth == 0){
                    s = s + myDay + "天";
                }else{
                    s = s + myMonth + "月" + myDay + "天";
                }
            } else{
                s = myYear + "岁";
                if( "0天".equals(s)){
                    s = "1天";
                }
            }
        }
        return s;
    }

    /**
     * InputStream转换为byte[]
     *
     * @param is
     * @return byte[]
     */
    public static byte[] getBytes(InputStream is) throws IOException {

        int len;
        int size = 1024;
        byte[] buf;

        if (is instanceof ByteArrayInputStream) {
            size = is.available();
            buf = new byte[size];
            len = is.read(buf, 0, size);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[size];
            while ((len = is.read(buf, 0, size)) != -1)
                bos.write(buf, 0, len);
            buf = bos.toByteArray();
        }
        return buf;
    }

    /**
     * InputStream转换为StreamingOutput
     *
     * @param in
     * @return StreamingOutput
     */
    public static StreamingOutput getStreamingOutput(final InputStream in) throws IOException {
        StreamingOutput stream = new StreamingOutput() {
            public void write(OutputStream out) throws IOException, WebApplicationException {
                try {
                    int read = 0;
                    byte[] bytes = new byte[1024];
                    while ((read = in.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                } catch (Exception e) {
                    throw new BusinessException("文件流读取失败: " + e.getMessage(), e);
                }
            }
        };
        return stream;
    }

    /**
     * 将Blob转换为StreamingOutput
     *
     * @param blob
     * @return
     * @throws RuntimeException
     */
    public static StreamingOutput getStreamingOutputByBlob(Blob blob) throws RuntimeException{
        StreamingOutput streamingOutput = null;
        try {
            streamingOutput = StringUtils.getStreamingOutput(blob.getBinaryStream());
        } catch (Exception e) {
            throw new BusinessException("读取数据流失败", e);
        }
        return streamingOutput;
    }


}
