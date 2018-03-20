package com.example.sheetal.hp;

/**
 * Created by sheetal on 3/20/2018.
 */

public class Data {
    public String name;
    public String adds;
    public String pre;
    public String pv;
    public String sn;
    public String vi;

    public Data(String name, String adds, String pre, String pv,String sn,String vi) {
        this.name = name;
        this.adds = adds;
        this.pre = pre;
        this.pv = pv;
        this.sn=sn;
        this.vi=vi;
    }

    public Data() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdds() {
        return adds;
    }

    public void setAdds(String adds) {
        this.adds = adds;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getsn(){return sn;}

    public String setsn(){return sn; }

    public String getVi(){return vi;}
    public String setVi(){return vi;}
}
