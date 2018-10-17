package com.roger.makecoffee.objects.define;

import com.roger.makecoffee.user.UserManager;

public class UserInformation {
    private String userUid;
    private String name;
    private String email;
    private String image;

    public UserInformation() {
    }

    public void initialUserInformation() {
        userUid = UserManager.getInstance().getUserUid();
        name = UserManager.getInstance().getUserName();
        email = UserManager.getInstance().getUserEmail();
        image = UserManager.getInstance().getUserPhotoUrl();
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
