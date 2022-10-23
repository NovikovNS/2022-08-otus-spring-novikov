package ru.otus.homework7.service;


import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class IOServiceImpl implements IOService {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public long readLong() {
        return Long.parseLong(sc.nextLine());
    }

    @Override
    public String readString() {
        return sc.nextLine();
    }

    @Override
    public void outputString(String text) {
        System.out.println(text);
    }
}
