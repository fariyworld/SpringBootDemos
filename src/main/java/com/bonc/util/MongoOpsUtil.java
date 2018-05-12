package com.bonc.util;

import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @Description:
 *
 * Created by mace on 8:47 2018/4/11.
 */
public class MongoOpsUtil {

    /**
     * @Description: 当前系统时间 --> ObjectId
     * @return: org.bson.types.ObjectId
     * Created by mace on 2018/4/11 9:01.
     */
    public static ObjectId time2Id(){

        return new ObjectId();
    }

    /**
     * description: 当前系统时间 --> ObjectId --> String
     * <br /><br />
     * create by mace on 2018/5/12 19:18.
     * @param
     * @return: java.lang.String
     */
    public static String ObjectId2Str(){

        return new ObjectId().toString();
    }

    /**
     * @Description: date --> ObjectId
     * @param date  指定date
     * @return: org.bson.types.ObjectId
     * Created by mace on 2018/4/11 9:03.
     */
    public static ObjectId time2Id(Date date){

        return new ObjectId(date);
    }

    /**
     * @Description: 把MongoDB中的 ObjectId(_id) ---> java.util.Date
     * objectid 前八位是时间戳 16进制 转化为 10进制(s) * 1000(ms)
     * @param id  :  _id ObjectId
     * @return: java.util.Date
     * Created by mace on 2018/4/11 8:57.
     */
    public static Date id2time(String id){

        return new Date(Long.parseLong(id.substring(0, 8), 16) * 1000);
    }


}
