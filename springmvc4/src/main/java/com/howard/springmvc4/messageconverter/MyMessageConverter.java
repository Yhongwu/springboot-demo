package com.howard.springmvc4.messageconverter;

import com.howard.springmvc4.domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 自定义内容转换
 * 继承AbstractHttpMessageConverter接口实现自定义的MessageConverter
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj>{

    public MyMessageConverter() {
        //自定义新的媒体类型
        super(new MediaType("application", "x-wisely", Charset.forName("UTF-8")));
    }

    /**
     * 只处理DemoObj类型的数据
     * @param aClass
     * @return
     */
    @Override
    protected boolean supports(Class<?> aClass) {
        return DemoObj.class.isAssignableFrom(aClass);
    }

    /**
     * 处理请求的数据
     * @param aClass
     * @param httpInputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String tmp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        //按“-”格式处理参数
        String[] tmpArr = tmp.split("-");
        return new DemoObj(new Long(tmpArr[0]),tmpArr[1]);


    }

    /**
     * 处理后只在原内容前加上“hello”
     * @param demoObj
     * @param httpOutputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(DemoObj demoObj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello:" + demoObj.getId()+"-"+demoObj.getName();
        httpOutputMessage.getBody().write(out.getBytes());
    }





}
