package com.example.autosgallery.Dialog;

public class OtherId {
    // bu sınıf mesajlaşma kısmı için oluşturulde
    // kendi profilimizdeyken başka ilan verenin id sine nesne oluşturmadan heryerden ulaşabilmek için static tanımlama
    public static String otherId;

    public static String getOtherId() {
        return otherId;
    }

    public static void setOtherId(String otherId) {
        OtherId.otherId = otherId;
    }
}
