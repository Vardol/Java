package HeapSort;

//3. Реализовать алгоритм пирамидальной сортировки (HeapSort).


//Не до конца понял сортировку - подсмотрел алгоритмы в гугле. Но в итоге работает.

import java.util.Random;

public class HeapSort {

    public static void heapSort(int array[]){

        // Построение кучи (перегруппируем массив)
        for (int i = array.length / 2 - 1; i >= 0; i--)
            heapify(array, array.length, i);

        // Один за другим извлекаем элементы из кучи   
        for (int i = array.length - 1; i>=0; i--)
        {
            // Перемещаем текущий корень в конец
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Вызываем процедуру heapify на уменьшенной куче
            heapify(array, i, 0);
        }
    }

    // Процедура для преобразования в двоичную кучу поддерева с корневым узлом i, что является индексом в array[]. n - размер кучи
    static void heapify(int array[], int size, int i)
    {
        int largest = i; // Инициализируем наибольший элемент как корень
        int left = 2*i + 1; // левый = 2*i + 1
        int right = 2*i + 2; // правый = 2*i + 2

           // Если левый дочерний элемент больше корня
        if (left < size && array[left] > array[largest])
            largest = left;

          // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (right < size && array[right] > array[largest])
            largest = right;
       // Если самый большой элемент не корень
        if (largest != i)
        {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

          // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(array, size, largest);
        }
    }


    public static void main(String args[])
    {

        //создаем массив и заполняем случайными числами
        Random random = new Random();
        int arrayLength = utils.readNatural("Введите длину массива - ");
        int[] array = new int[arrayLength];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(-100, 100);
        }

        //выводим сгенерированный массив
        System.out.println("\nСгенерированный массив:");
        String result = "";
        for (int i = 0; i < array.length; i++) {
            result = result.concat(String.valueOf(array[i]));
            result = result.concat(", ");
        }
        System.out.println(result);

        heapSort(array);

        //выводим отсортированный массив
        System.out.println("\nОтсротированный массив:");
        result = "";
        for (int i = 0; i < array.length; i++) {
            result = result.concat(String.valueOf(array[i]));
            result = result.concat(", ");
        }
        System.out.println(result);
    }
}
