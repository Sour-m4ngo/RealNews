package com.example.realnews.Bean;

import java.util.ArrayList;
import java.util.List;

public class NewsDetails {
    private int status;
    private String msg;
    private Result result;
     class Result{
        private String channel;
        private String num;
        private ArrayList<Detail> list;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public ArrayList<Detail> getList() {
            return list;
        }

        public void setList(ArrayList<Detail> list) {
            this.list = list;
        }
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
    public String title(){
         Result result = this.result;
         Detail detail1 = result.getList().get(0);
         Detail detail2 = result.getList().get(1);
         String title1 = detail1.getTitle();
         String title2 = detail2.getTitle();
         return title1+" "+title2;
    }

    public ArrayList<Detail> getdata(){
        Result result = this.result;
        ArrayList<Detail> ResultList = result.getList();
        return ResultList;
    }
}
/*
    {
    "status": 0,
    "msg": "ok",
    "result": {
        "channel": "头条",
        "num": "10",
        "list": [
            {
                "title": "中国开闸放水27天解救越南旱灾",
                "time": "2016-03-16 07:23",
                "src": "中国网",
                "category": "mil",
                "pic": "http://api.jisuapi.com/news/upload/20160316/105123_31442.jpg",
                "content": "<p class="\"art_t\"">　　原标题：防总：应越南请求 中方启动澜沧江水电站水量应急调度</p><p class="\"art_t\"">　　记者从国家防总获悉，应越南社会主义共和国请求，我方启动澜沧江梯级水电站水量应急调度，缓解湄公河流域严重旱情。3月15日8时，澜沧江景洪水电站下泄流量已加大至2190立方米每秒，标志着应越方请求，由我方实施的澜沧江梯级水电站水量应急调度正式启动。</p>",
                "url": "http://mil.sina.cn/zgjq/2016-03-16/detail-ifxqhmve9235380.d.html?vt=4&pos=108",
                "weburl": "http://mil.news.sina.com.cn/china/2016-03-16/doc-ifxqhmve9235380.shtml"
            }
        ]
    }
}
*/
