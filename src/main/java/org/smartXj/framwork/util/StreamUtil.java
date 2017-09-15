package org.smartXj.framwork.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public final  class StreamUtil {
    private static Logger LOGGER= LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入流中获取字符串
     * @param inputStream
     * @return
     */
    public static String getString(InputStream inputStream)
    {
        StringBuilder sb=new StringBuilder();
        try
        {
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line=reader.readLine())!=null)
            {
                sb.append(line);
            }
        }catch (Exception e)
        {
            LOGGER.error("get string failue",e);
            throw  new RuntimeException(e);
        }
        return sb.toString();
    }
}
