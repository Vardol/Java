package HW2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggedBubbleSort {

    static void swapValueInIntArray(int[] array, int firstIndex, int secondIndex){
        if (firstIndex >= array.length || secondIndex >= array.length || firstIndex < 0 || secondIndex < 0){return;} //прекращаем выполнение функции, если индексы не входят в длину массива
        int swapValue = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = swapValue;
    }

    public static void main(String[] args) throws IOException{
        // создаем объекты логгера

        Logger logger = Logger.getLogger(LoggedBubbleSort.class.getName());
        FileHandler fileHandler = new FileHandler("log.log");
        logger.addHandler(fileHandler);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);

        //создаем массив и заполняем случайными числами
        Random random = new Random();
        int arrayLength = utils.readNatural("Введите длину массива - ");
        int[] array = new int[arrayLength];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(-100, 100);
        }
        
        //логгируем начальный массив
        logger.log(Level.INFO, "Generated array: " + Arrays.toString(array));

        //сортировка
        for (int i = 0; i < array.length - 1; i++) {
            int maxValueIndex = i;
            for (int j = i+1; j < array.length; j++) {
                if (array[j] > array[maxValueIndex]){
                    maxValueIndex = j;
                }
            }

            //логгируем, если были изменения
            if (i != maxValueIndex) {
                swapValueInIntArray(array, i, maxValueIndex);
                logger.log(Level.INFO, Arrays.toString(array));
            }
        }
    }


}
