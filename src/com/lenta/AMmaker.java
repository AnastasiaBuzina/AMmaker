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
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class AMmaker {

    public static void main(String[] args) {

        String version = "1.1";
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

            System.out.println("Введите алкод или нажмите \"b\", чтобы вставить из буфера обмена");

            try {
                input = reader.readLine();
            } catch (IOException e) {
                System.out.println("Ошибка ввода");
                continue;
            }

            if ("q".equals(input) | "й".equals(input)) break;

            if ("v".equals(input) | "м".equals(input) ) {
                System.out.println("Версия " + version);
                continue;
            }

            if ("h".equals(input) | "р".equals(input)) {
                System.out.println("Программа формирует АМ на основе алкода. " +
                        "Предназначена для использования" +
                        " в тестовой среде компании Лента. " +
                        "Бузина А., 2018г.");
                System.out.println("Горячие клавиши:\n" +
                        "b - вставка алкода из буфера\n" +
                        "v - версия\n" +
                        "h - справка\n" +
                        "q - выход");
                System.out.println("Добавлено в V1.1:\n" +
                        "чтение алкода из системного буфера обмена");
                continue;
            }

            if ("b".equals(input) | "и".equals(input)) {
                try {
                    input = (String) clipboard.getContents(null).getTransferData(DataFlavor.stringFlavor);
                    System.out.println("Введён алкод");
                    System.out.println(input);
                } catch (UnsupportedFlavorException e) {
                    System.out.println("ошибка вставки из буфера UnsupportedFlavorException (загуглить!)");
                } catch (IOException e) {
                    System.out.println("ошибка вставки из буфера (IOException)");
                } /*catch (ClassNotFoundException e) {
                    System.out.println("ошибка чтения алкода ");
                    e.printStackTrace();
                }*/
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
