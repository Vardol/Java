package MyHash;

import java.util.Random; // импортирую для теста класса в main


// ЗАДАНИЕ.
// Реализовать класс работающий по принципу HashMap.
// Включая внутренний массив узлов с индексацией по хешу и описание узла. Добавить в класс методы:
// Object put(Integer key , Integer value) добавить элемент
// Object get(Integer key) получить значение соответствующее ключу
// Object remove(Integer key) удалить элемент с соответствующем ключём
// Object replays(Integer key, Integer v) заменить значение
// int size() количество элементов
// boolean containsKey(Integer key) проверка наличия ключа и значения
// boolean containsValue(Integer v)



public class MyHash {

    // сделал класс Node вложенным и приватным, т.к. мне кажется, что обращение к
    // классу извне структуры данных может вызвать нештатные ситуации
    private class Node {
        Integer value, key;
        int hash;
        Node next;
    }

    // сделал размер массива переменной, потому что сделал длину массива динамической
    // если количество элементов в массиве становится вдвое больше числа нод, то количество нод увеличивается вдвое
    // имеющиеся элементы при этом перераспределяются по новому массиву заново
    // плюс чуть поменял нейминг переменных на более очевидный
    
    //описание полей класа
    // убрал ноду Head, т.к. она не используется
    // private Node head;

    private int arraySize = 4; // поля сделал приватными, т.к. манипуляции с ними извне могут сломать структуру.
    private int size = 0;
    private Node[] nodes = new Node[arraySize];


    //Сделал геттеры для переменных содержащих сведения о размере массива и количестве элементов
    public int getArraySize() {
        return arraySize;
    }

    public int getSize() {
        return size;
    }


    public Object put(int v, int k) {
        Node newNode = new Node();
        newNode.value = v;
        newNode.key = k;
        newNode.hash = newNode.key.hashCode() % arraySize;
        if (nodes[newNode.hash] != null) {
            Node tempNode = nodes[newNode.hash];
            while (tempNode != null) {
                if (tempNode.key == k) {
                    return tempNode.value;
                }
                tempNode = tempNode.next;
            }
            newNode.next = nodes[newNode.hash];
        }
        nodes[newNode.hash] = newNode;
        this.size += 1; // прибавляем размер, при успешном добавлении
        if (checkResizeNeeded()) {
            this.resize(); // запускаем увеличение длины массива нодов, если оно стало необходимо.
                           // Используемые методы описаны ниже.
        }
        return null;
    }

    public Object get(Integer key) {
        // добавил предварительную проверку размера. Если структура пуста - нет смысла искать
        if (this.size == 0) {
            return null;
        }
        int index = key.hashCode() % arraySize;
        if (nodes[index] != null) {
            Node tempNode = nodes[index];
            while (tempNode != null) {
                if (tempNode.key == key) {
                    return tempNode.value;
                }
                tempNode = tempNode.next;
            }
        }
        return null;
    }

    // счел необходимым добавить метод, который возвращает первый ключ, соответствующий значению
    public Object findValue(int value) {
        if (this.size == 0) {
            return null;
        }
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                Node tempNode = nodes[i];
                while (tempNode != null) {
                    if (tempNode.value == value) {
                        return tempNode.key;
                    }
                    tempNode = tempNode.next;
                }
            }
        }
        return null;
    }

    public Object remove(Integer key) {
        int index = key.hashCode() % arraySize;
        if (nodes[index] != null) {
            Node tempNode = nodes[index];
            if (tempNode.key == key && tempNode.next == null) {
                nodes[index] = null;
                this.size -= 1; // уменьшение размера при успешном удалении объекта
                return tempNode.value;
            }
            if (tempNode.key == key && tempNode.next != null) {
                nodes[index] = tempNode.next;
                this.size -= 1; // уменьшение размера при успешном удалении объекта
                return tempNode.value;
            }
            while (tempNode.next != null) {
                if (tempNode.next.key == key) {
                    Integer removedValue = tempNode.next.value;
                    tempNode.next = tempNode.next.next;
                    this.size -= 1; // уменьшение размера при успешном удалении объекта
                    return removedValue;
                }
                tempNode = tempNode.next;
            }
        }
        return null;
    }

    public Object replace(Integer key, Integer v) {
        int index = key.hashCode() % arraySize;
        if (nodes[index] != null) {
            Node tempNode = nodes[index];
            while (tempNode != null) {
                if (tempNode.key == key) {
                    Integer replecedValue = tempNode.value;
                    tempNode.value = v;
                    return replecedValue;
                }
                tempNode = tempNode.next;
            }
        }
        return null;
    }

    // поскольку поиск по ключу фактически уже реализован в get(int key) - просто
    // отталкиваемся от результатоввыполнения этого метода
    public boolean containsKey(Integer key) {
        if (this.get(key) != null) {
            return true;
        } else {
            return false;
        }
    }

    // логика полностью аналогичная поиску по ключу. Просто хэш мы рассчитать не можем, поэтому пробегаем по всему массиву.
    // в начале также добавил проверку на наличие элементов в структуре.
    public boolean containsValue(int v) {
        if (this.size == 0) {
            return false;
        }
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                Node tempNode = nodes[i];
                while (tempNode != null) {
                    if (tempNode.value == v) {
                        return true;
                    }
                    tempNode = tempNode.next;
                }
            }
        }
        return false;
    }

    // вспомогательный внутренний метод, сравнивает количество элементов с размером массива нодов
    // если элементов стало больше чем нодов в 2 раза, то возвращает true
    private boolean checkResizeNeeded() {
        if (this.size > this.arraySize * 2) {
            return true;
        } else {
            return false;
        }
    }

    // внутренний метод увеличивающий массив нодов вдвое с перераспределением всех элементов в новый массив
    // должен использоваться в методах класса, где происходит увеличение числа элементов
    // Сейчас используется в методе put()
    private boolean resize() {
        if (checkResizeNeeded()) { //перепроверяем необходимость увеличения массива
            Node[] oldNodes = this.nodes; // переносим текущий массив нодов во временный
            int counter = 0; // счетчик перенесенных элементов
            int oldSize = this.size; //сохраняем себе количество элементов
            this.nodes = new Node[this.arraySize * 2]; // создаем себе новый массив нодов вдвое большего размера
            this.arraySize *= 2; // обновляем зачение длины массива, для корректной работы всех методов
            for (int i = 0; i < oldNodes.length; i++) { // пробегаем по всему старому массиву нодов, перекладывая элементы в новый
                if (oldNodes[i] != null) {
                    Node tempNode = oldNodes[i];
                    while (tempNode != null) {
                        if (this.put(tempNode.value, tempNode.key) == null) { //переносим элемент, убеждаемся что перенос прошел успешно
                            this.size--; // метод put() увеличил size на 1, перложив элемент. Но это не новый элемент,
                                         // мы его просто переложили. Поэтому мы уменьшаем size оратно на 1.
                            counter++; // увеличиваем счетчик перенесенных элементов
                        }
                        tempNode = tempNode.next;
                    }
                }
            }

            // если мы успешно перенесли все элементы, то счетчик перенесенных элементов counter будет равен количеству элементов size
            // и они будут равны количеству элементов на начало переноса - oldSize
            if (counter == this.size && oldSize == counter) {
                return true; // если мы убедились в корректном переносе всех элементов, то возвращаем true
            } else {
                this.nodes = oldNodes; // если что-то пошло не так, то мы возвращаем наш старый массив нодов
                this.arraySize /= 2; // и возвращаем параметры размера массива для работы методов
                this.size = oldSize; // и ставим старое значение количества элементов
                return false; // и возвращаем в методе false. Хотя надо бы кидать исключение.
            }

        } else {
            return false; // если ресайз вообще не требовался, возвращаем false
        }
    }

    // вспомогательный метод, чтобы отображать структуру, для проверки работы ресайза.
    public void showStructure() {
        System.out.println("Размер массива нодов - " + this.arraySize);
        for (int i = 0; i < nodes.length; i++) {
            int counter = 0;
            if (nodes[i] != null) {
                counter++;
                Node tempNode = nodes[i];
                while (tempNode.next != null) {
                    counter++;
                    tempNode = tempNode.next;
                }
            }
            System.out.println("Нода " + i + " хранит " + counter + " значений.");
        }
    }


    // статичный метод main для тестирования класса.
    // конечно его тут быть не должно - поставил, просто чтобы было проще проверить работоспособность
    public static void main(String[] args) {

        MyHash myHash = new MyHash();
        Random rand = new Random();

        System.out.println("size = " + myHash.getSize());
        myHash.showStructure();
        System.out.println("");
        for (int i = 0; i < 7; i++) {
            myHash.put(rand.nextInt(10000), i);
        }

        System.out.println("size = " + myHash.getSize());
        myHash.showStructure();
        System.out.println("");

        for (int i = 10; i < 18; i++) {
            myHash.put(rand.nextInt(10000), i);
        }

        System.out.println("size = " + myHash.getSize());
        myHash.showStructure();
        System.out.println("");

        for (int i = 20; i < 30; i++) {
            myHash.put(rand.nextInt(10000), i);
        }

        System.out.println("size = " + myHash.getSize());
        myHash.showStructure();
        System.out.println("");

        System.out.println(myHash.containsKey(10));
        System.out.println(myHash.get(10));
        myHash.replace(10, 2318);
        System.out.println(myHash.get(10));
        System.out.println(myHash.containsValue(2318));
        System.out.println(myHash.findValue(2318));
        myHash.remove(10);
        System.out.println(myHash.containsKey(10));
        System.out.println(myHash.containsValue(2318));
    }
}
