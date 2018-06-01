package com.lenta;

        /*
        Программа принимает входные параметры:
        -алкод
        Программа выводит готовую АМ
        Программа копирует АМ в буфер обмена пользователя
         */

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class AMmaker {

    public static void main(String[] args) {

        String version = "1.0";
        String input;
        String amVersion = "22";
        String n = "N";
        BigInteger alcode;
        String alcodeBase36;
        String numberOfApplication = "1NKN31105001";
        String numberOfMark = "000056";
        String cryptoCode = "NQQMS5VP4HTF5SB46ZSQQJD8BNJP891";

        String rez;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();


        while (true) {

            System.out.println("Введите алкод");

            try {
                input = reader.readLine();
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
                continue;
            }

            if ("q".equals(input) | "quit".equals(input)) break;

            if ("v".equals(input) | "ver".equals(input) | "version".equals(input) ) {
                System.out.println(version);
                continue;
            }

            if ("h".equals(input) | "help".equals(input) | "р".equals(input) | "рудз".equals(input)) {
                System.out.println("Программа формирует АМ на основе алкода. " +
                        "Написана Бузиной А. в июне 2018 года, " +
                        "предназначена исключительно для использования" +
                        " в тестовой среде компании Лента. " +
                        "V1.0");
                continue;
            }

            try {
                /* добавляем к 16 ведущим нулям алкод в 36-й системе счисления
                и обрезаем строку до 16 символов*/

                alcode = new BigInteger(input);
                alcodeBase36 = "0000000000000000"; //16 нулей
                alcodeBase36 += alcode.toString(36);
                if (alcodeBase36.length() > 32) {
                    System.out.println("Слишком длинный алкод");
                    continue;
                }
                alcodeBase36 = alcodeBase36.substring(alcodeBase36.length() - 16);

                rez = amVersion /*версия ПС ЕГАИС*/
                        + n /*буква N*/
                        + alcodeBase36 /*с ведущими нулями*/
                        + numberOfApplication /*номер и дата заявки на печать*/
                        + numberOfMark /*номер марки в заявке на печать*/
                        + cryptoCode /*защитная последовательность*/;

                clipboard.setContents(new StringSelection(rez), null); //в буфер обмена
                System.out.println("АМ (скопирована в буфер обмена):");
                System.out.println(rez);

            } catch (NumberFormatException e) {
                System.out.println("Ошибка чтения алкода");
            }
        }
    }
}
