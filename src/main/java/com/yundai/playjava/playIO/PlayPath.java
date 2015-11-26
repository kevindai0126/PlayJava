package com.yundai.playjava.playio;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yundai on 11/25/15.
 */
public class PlayPath {

    public static void main(String[] args) {
        Path listing = Paths.get("/usr/bin/zip");

        System.out.println(listing.getFileName());
        System.out.println(listing.getNameCount());
        System.out.println(listing.getParent());
        System.out.println(listing.getRoot());
        System.out.println(listing.subpath(0, 2));
    }
}
