package com.example.sheetal.hp;

public class blog {
    private String ChildName;
    private String ChildDOB;
    private String ChildFatherName;
    private String UserId;

    public blog() {
    }

    public blog(String child_name, String child_dob, String child_father_name) {
    }

    public blog(String childName, String childDOB, String childFatherName, String userId) {
        ChildName = childName;
        ChildDOB = childDOB;
        ChildFatherName = childFatherName;
        UserId = userId;
    }

    public String getChildName() {
        return ChildName;
    }

    public void setChildName(String childName) {
        ChildName = childName;
    }

    public String getChildDOB() {
        return ChildDOB;
    }

    public void setChildDOB(String childDOB) {
        ChildDOB = childDOB;
    }

    public String getChildFatherName() {
        return ChildFatherName;
    }

    public void setChildFatherName(String childFatherName) {
        ChildFatherName = childFatherName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
