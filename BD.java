package com.company;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BD {
    /**
     *
     * @param str искомое выражение
     * @return Идем по строке и считываем символы. В зависимости от них попадаем в нужный кейс, где проходит проверка корректности строки. К примеру: проверяем, не являются ли оператор или закрывающая скобка первыми символами. Если да возвращаем Фолз, иначе Тру
     */
    public static boolean check(String str)
    {
        int open = 0;
        int close = 0;
        for(int pos = 0; pos<str.length(); pos++)
        {
            switch (str.charAt(pos)) {
                case '+':
                case '-':
                case '*':
                case '/':
                    if(pos == 0 || pos == str.length() - 1)
                    {
                        return false;
                    }
                    if(str.charAt(pos++) == '+' || str.charAt(pos++) == '-' || str.charAt(pos++) == '*' || str.charAt(pos++) == '/')
                    {
                        return false;
                    }
                case '(':
                    open++;
                    if(str.charAt(pos++) == '+' || str.charAt(pos++) == '-' || str.charAt(pos++) == '*' || str.charAt(pos++) == '/' || str.charAt(pos++) == ')')
                    {
                        return false;
                    }
                    if(pos == str.length() - 1)
                    {
                        return false;
                    }
                    pos++;
                case ')':
                    close++;
                    if(str.charAt(pos--) == '+' || str.charAt(pos--) == '-' || str.charAt(pos--) == '*' || str.charAt(pos--) == '/' || str.charAt(pos--) == '(')
                    {
                        return false;
                    }
                    if(pos == 0)
                    {
                        return false;
                    }
                    pos++;
                default:
                    if(str.charAt(pos) >= '0' && str.charAt(pos) <= '9')
                    {
                        if(str.charAt(pos++) == '(' || str.charAt(pos--) == ')')
                            return false;
                        pos++;
                    }
                    else return false;
            }
        }
        if(close != open)
        {
            return false;
        }
        return true;
    }

    /**
     *
     * @param ch символ, приоритет которого нужно определить
     * @return Возвращаем 0, если это цифра, 3, если умножение или деление, 2, если плюс или минус, 1, если открывающая скобка, -1, если закрывающая скобка
     */
    private static int Type(char ch) {
        if (ch == '*' || ch == '/')
            return 3;
        else if (ch == '+' || ch == '-')
            return 2;
        else if (ch == '(')
            return 1;
        else if (ch == ')')
            return -1;
        return 0;
    }

    /**
     *
     * @param text искомое выражение
     * @return Проверяем выражение на корректность. Идем по строке и в зависимости от типа добавляем символ в нужный контейнер.
     * Если символ - цифра, то отправляем его в результирующую строку str.
     * Если открывающая скобка или оператор - в стек чаров
     * Закрывающую не добавляем никуда. Она символизирует о переносе элементов из стека в строку, согласно алгоритму, описанному в комментарии Мейна
     * В конце метода отправляем оставшиеся элементы стека в строку, получая новый вид нашего выражения, которое и возвращаем.
     * Также, вставляем пробелы между числами, чтобы не запутаться при извлечении в стек в следующем методе.
     */
    public static String reloading(String text) {
        boolean reallytemp = check(text);
        if(reallytemp)
            throw new RuntimeException("Выражение некорректно! ");
        Stack<Character> temp = new Stack<Character>();
        String str = "";
        for(int pos = 0; pos < text.length(); pos++) {
            char sym = text.charAt(pos);
            int type = Type(sym);

            if (type == 0)
                str += text.charAt(pos);
            if (type == 1) {
                temp.push(text.charAt(pos));
            }
            if (type > 1) {
                str += ' ';
                while (!temp.empty()) {
                    if (Type(temp.peek()) >= type)
                        str += temp.pop();
                    else break;
                }
                temp.push(text.charAt(pos));
            }
            if (type == -1) {
                str += ' ';
                while (Type(temp.peek()) != 1)
                    str += temp.pop();
                temp.pop();
            }
        }
        while (!temp.empty())
            str += temp.pop();
        return str;
    }

    /**
     *
     * @param str новый вид нашего выражения
     * @return Идем по видоизмененной строке и переносим элементы строки в новый стек, попутно совершая операции с числами
     * Пока число переноисм в стек.
     * Если оператор, то вытаскиваем последние два числа в переменные и совершаем операцию в зависимости от оператора.
     * Таким образом в стеке останется одно число, которое мы возвращаем. Это и есть решение вражения.
     */
    public static double count(String str) {
        String sign = "";
        Stack<Double> temp = new Stack<Double>();
        for(int pos = 0;pos < str.length(); pos++) {
            if (str.charAt(pos) == ' ')
            {
                continue;
            }
            if (Type(str.charAt(pos)) == 0) {
                while (str.charAt(pos) != ' ' && Type(str.charAt(pos)) == 0) {
                    sign += str.charAt(pos++);
                    if (pos == str.length()) break;
                }
                temp.push(Double.parseDouble(sign));
                sign = "";
            }
            if (Type(str.charAt(pos)) > 1) {
                double curr1 = temp.pop();
                double curr2 = temp.pop();

                if (str.charAt(pos) == '+')

                    temp.push(curr2 + curr1);

                if (str.charAt(pos) == '-')

                    temp.push(curr2 - curr1);

                if (str.charAt(pos) == '*')

                    temp.push(curr2 * curr1);

                if (str.charAt(pos) == '/')

                    temp.push(curr2 / curr1);
            }
        }
        return temp.pop();
    }
}
