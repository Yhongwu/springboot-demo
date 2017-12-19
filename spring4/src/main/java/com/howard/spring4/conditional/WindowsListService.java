package com.howard.spring4.conditional;

/**
 * Created by hongwu on 2017/12/19.
 */
public class WindowsListService implements  ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
