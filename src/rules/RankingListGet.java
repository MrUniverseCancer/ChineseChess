package rules;

import java.util.ArrayList;

public class RankingListGet
{
    public static ArrayList<ArrayList<Object>> getRankingList()
    {
        ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
        ArrayList<Object> temp = new ArrayList<Object>();
        temp.add("张三");
        temp.add(100);
        data.add(temp);
        temp = new ArrayList<Object>();
        temp.add("李四");
        temp.add(90);
        data.add(temp);
        temp = new ArrayList<Object>();
        temp.add("王五");
        temp.add(80);
        data.add(temp);
        temp = new ArrayList<Object>();
        temp.add("赵六");
        temp.add(70);
        data.add(temp);
        temp = new ArrayList<Object>();
        temp.add("钱七");
        temp.add(60);
        data.add(temp);
        // 给出更多例子
        temp = new ArrayList<Object>();
        temp.add("孙八");
        temp.add(50);
        data.add(temp);
        temp = new ArrayList<Object>();
        temp.add("周九");
        temp.add(40);
        data.add(temp);
        temp = new ArrayList<Object>();
        temp.add("吴十");
        temp = new ArrayList<Object>();
        temp.add("孙八");
        temp.add(50);
        data.add(temp);
        temp = new ArrayList<Object>();
        temp.add("周九");
        temp.add(40);
        data.add(temp);
        temp = new ArrayList<Object>();
        temp.add("吴十");
        temp.add(30);
        data.add(temp);
        temp = new ArrayList<Object>();
        temp.add("孙八");
        temp.add(50);
        data.add(temp);
        temp = new ArrayList<Object>();
        temp.add("周九");
        temp.add(40);
        data.add(temp);
        temp = new ArrayList<Object>();
        temp.add("吴十");
        temp.add(30);
        data.add(temp);

        return data;
    }
}
