package HW4;
//2. Реализуйте очередь с помощью LinkedList со следующими методами:
//enqueue() - помещает элемент в конец очереди,
//dequeue() - возвращает первый элемент из очереди и удаляет его,
//first() - возвращает первый элемент из очереди, не удаляя.

import java.util.LinkedList;

public class MyQueue {
    LinkedList queue = new LinkedList();

    void enqueue(Object object){
        queue.add(object);
    }

    Object dequeue(){
        return (Object)queue.poll();
    }

    Object first(){
        return (Object)queue.getFirst();
    }

    int getSize(){
        return queue.size();
    }
}
