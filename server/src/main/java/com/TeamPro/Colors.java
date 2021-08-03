package com.TeamPro;

/**
 * Methods for add color to a string
 */
public class Colors {
    /**
     * Print a message to the console in yellow
     * @param msg
     */
    public static String toYellow(String msg){
        return "\033[93m" + msg + "\033[00m";
    }
    /**
     * Print a message to the console in red
     * @param msg
     */
    public static String toRed(String msg){
        return "\033[91m" + msg + "\033[00m";
    }
    /**
     * Print a message to the console in green
     * @param msg
     */
    public static String toGreen(String msg){
        return "\033[92m" + msg + "\033[00m";
    }
    /**
     * Print a message to the console in blue
     * @param msg
     */
    public static String toBlue(String msg){
        return "\033[94m" + msg + "\033[00m";
    }
}