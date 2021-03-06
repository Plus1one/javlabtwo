package com.company;
import java.util.ArrayList;
import java.util.List;

/*
Алгоритм: Чтобы избавиться от скобок, записываем выражение так: сначала числа,
          с которыми совершают операцию, затем сам оператор.
          Это получается с помощью записи операторов и скобок в стек и добавлении их после.
          Приоритет попадания знаков в строчку определяется в стеке.
          Когда мы получаем + или - в строке, переносим из стека все знаки, что нам встретятся в строку.
          Переноисм знаки, пока сверху не окажется другой символ.
          Если же там сразу был другой символ, кладем сверху.
          Если добавляем * или /, кладем сверху.
          Когда встречаем закрывающую скобку, все переносим в строку до открывающей.
          Ее уничтожаем, а закрывающую больше не используем.
          Таким образом избавляемся от скобок.
          Это все происходит в методе reloading().
          После в методе count() работаем с измененной строкой.
          Добавляем все в стек до первого оператора. Вынимаем из него последние два числа
          и совершаем операцию. Записываем результат обратно в стек. Происходит это все, пока строка не закончится.
          В конце концов в стеке окажется одно число - ответ.

 */
public class Main {


    public static void main(String[] args) {
        //String m = "1+2";
        //ArrayList <String> list = new ArrayList<>();
        ////list = AA.reloading(m, list);
        //int i = 0;
        //while(i < m.length()) {
        //    char c = m.charAt(i);
        //    String sym = "" + c;
        //    list.add(sym);
        //    System.out.println(list.get(i));
        //    i++;
        //}
        //System.out.println(BD.reloading(m, list));
        //System.out.println(BD.reloading("6+1*11*(5+1-2)"));
        //System.out.println(BD.reloading("6+1*11*(5+1-2)"));
        String str = "6+1*11*(5+1-2*4-2+17)/7";
        System.out.println(BD.count(BD.reloading(str)));
    }
}
