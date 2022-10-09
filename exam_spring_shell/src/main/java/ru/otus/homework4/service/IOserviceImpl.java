package ru.otus.homework4.service;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class IOserviceImpl implements IOservice {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public int readInt() {
        return Integer.parseInt(sc.nextLine());
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
