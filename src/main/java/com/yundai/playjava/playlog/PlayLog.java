package com.yundai.playjava.playlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yundai on 2015/7/8.
 */
public class PlayLog {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(PlayLog.class);
        logger.info("Play Log!");
    }
}
