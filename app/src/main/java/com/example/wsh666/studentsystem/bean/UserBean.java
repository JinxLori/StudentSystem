package com.example.wsh666.studentsystem.bean;
import java.io.Serializable;

/**
 * Created by wsh666 on 2018/8/23.
 * へ　　　　　  ／|
 　　/＼7　　　 ∠＿/
 　 /　│　　 ／　／
 　│　Z ＿,＜　／　　 /`ヽ
 　│　　　　　ヽ　　 /　　〉
 　 Y　　　　　`　   /　　/
 　ｲ●　､　●　　⊂⊃〈　　/
 　()　 へ　　　　|　＼〈
 　　>ｰ ､_　 ィ　 │ ／／
 　 / へ　　 /　ﾉ＜| ＼＼
 　 ヽ_ﾉ　　(_／　 │／／
 　　7　　　　　　　|／
 　　＞―r￣￣`ｰ―＿
 */
public class UserBean implements Serializable{
    private int imageId;
    private String username;
    private String password;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
