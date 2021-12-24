package com.learn.meme.util;

import com.learn.meme.model.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

public class HtmlGenerator {

    public static String generateMailPass(User user) throws IOException {
        File fileHtml=new File("templates\\formEmail\\PasswordEmail.html");
        Document document= Jsoup.parse(fileHtml,"utf-8");
        Element body = document.body();

        Element passEle = body.getElementById("pass-id");
        passEle.appendText(user.getPassword());

        Element usernameEle = body.getElementById("pass-id");
        usernameEle.appendText(user.getFullname());

        return document.toString();
    }
}
