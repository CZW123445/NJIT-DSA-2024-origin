 package oy.tol.tra;

import java.util.Arrays;

    public class QueueImplementation<T> implements QueueInterface<T> {

        private static final int DEFAULT_CAPACITY = 10;
        private T[] elements;
        private int size;
        private int capacity;
        private int head; // Points to the front of the queue
        private int tail; // Points to the next available position at the end of the queue

        @SuppressWarnings("unchecked")
        public QueueImplementation(int capacity) {
            this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY;
            this.elements = (T[]) new Object[this.capacity];
            this.size = 0;
            this.head = 0;
            this.tail = 0;
        }

        public QueueImplementation() {
            this(DEFAULT_CAPACITY);
        }

        @Override
        public void enqueue(T element) {
            if (element == null) {
                throw new NullPointerException("Cannot add null element to the queue.");
            }
            if (size == capacity) {
                reallocate();
            }
            elements[tail] = element;
            tail = (tail + 1) % capacity;
            size++;
        }

        @Override
        public T dequeue() {
            if (isEmpty()) {
                throw new QueueIsEmptyException("Queue is empty, cannot dequeue.");
            }
            T element = elements[head];
            elements[head] = null; // Clear the reference
            head = (head + 1) % capacity;
            size--;
            return element;
        }

        @Override
        public T element() {
            if (isEmpty()) {
                throw new QueueIsEmptyException("Queue is empty, cannot retrieve element.");
            }
            return elements[head];
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public void clear() {
            for (int i = 0; i < size; i++) {
                elements[(head + i) % capacity] = null;
            }
            size = 0;
            head = 0;
            tail = 0;
        }

        @Override
        public int capacity() {
            return capacity;
        }

        @SuppressWarnings("unchecked")
        private void reallocate() {
            capacity *= 2;
            T[] newArray = (T[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                newArray[i] = elements[(head + i) % (size)];
            }
            elements = newArray;
            head = 0;
            tail = size;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                sb.append(elements[(head + i) % capacity]);
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }
