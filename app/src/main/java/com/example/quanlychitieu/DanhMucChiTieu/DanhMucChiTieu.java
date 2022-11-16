package com.example.quanlychitieu.DanhMucChiTieu;

public class DanhMucChiTieu {
    private int thuchiId;
    private String note;
    private int money;
    private int trangthai_thuchi;


    public DanhMucChiTieu(int thuchiId, String note, int money, int trangthai_thuchi) {
        this.thuchiId = thuchiId;
        this.note = note;
        this.money = money;
        this.trangthai_thuchi = trangthai_thuchi;
    }

    public int getThuchiId() {
        return thuchiId;
    }

    public void setThuchiId(int thuchiId) {
        this.thuchiId = thuchiId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTrangthai_thuchi() {
        return trangthai_thuchi;
    }

    public void setTrangthai_thuchi(int trangthai_thuchi) {
        this.trangthai_thuchi = trangthai_thuchi;
    }
}
