package Exceptions.Phonebook;

import java.io.FileWriter;
import java.util.Scanner;

/*
Напишите приложение, которое будет запрашивать у пользователя следующие данные, разделенные пробелом:
Фамилия Имя Отчество номертелефона

Форматы данных:
фамилия, имя, отчество - строки
номертелефона - целое беззнаковое число без форматирования

Ввод всех элементов через пробел

Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида

<Фамилия><Имя><Отчество><номер_телефона>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

Не забудьте закрыть соединение с файлом.

При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.
*/

public class PhoneBook {

    public static Boolean isAlphabetic(String input) {
        return input.matches("[a-zA-Zа-яА-Я]+");
    }

    public static Boolean isDigit(String input) {
        return input.matches("[0-9]+");
    }

    public static String[] entryParser(String input) throws ParserException {
        if (input.contains("  ")) {
            throw new ParserInvalidDataFormatException("Введено более одного пробела подряд");
        }

        String[] result = input.split(" ");
        if (result.length != 4) {
            throw new ParserInvalidDataFormatException("Некорректное количество данных.");
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i].isBlank()) {
                throw new ParserInvalidDataFormatException("Некорректное количество данных.");
            }
        }

        if (!isAlphabetic(result[0])) {
            throw new ParserInvalidNameException("Фамилия содержит недопустимые символы.");
        }
        if (!isAlphabetic(result[1])) {
            throw new ParserInvalidNameException("Имя содержит недопустимые символы.");
        }
        if (!isAlphabetic(result[2])) {
            throw new ParserInvalidNameException("Отчество содержит недопустимые символы.");
        }
        if (!isDigit(result[3])) {
            throw new ParserInvalidPhoneException("Телефон содержит недопустимые символы.");
        }
        if (result[3].length() > 18) {
            throw new ParserInvalidPhoneException("Слишком длинный номер телефона.");
        }
        if (result[3].length() < 11) {
            throw new ParserInvalidPhoneException("Слишком короткий номер телефона.");
        }
        return result;
    }

    public static void main(String[] args) {

        String[] entry = new String[4];

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите фамилию, имя, отчетство и номер телефона, разделенные пробелами.");
            System.out.println("ФИО могут могут содержать только буквы. Номер телефона - только цифры, без знаков.");
            entry = entryParser(scanner.nextLine());
        } catch (ParserException e) {
            System.out.println("Возникла ошибка при обработке введенной строки.");
            e.printStackTrace();
        }

        if (entry != null) {
            System.out.println("работаем");
            System.out.println(String.join(" ", entry));
            try (FileWriter writer = new FileWriter(entry[0], true)) {
                writer.write(String.join(" ", entry) + "\n");
            } catch (Exception e) {
                System.out.println("Возникла ошибка при записи данных.");
                e.printStackTrace();
            }
        }
    }

}
