package oy.tol.tra;

class StackImplementation<E> implements StackInterface<E> {
    private Object[] itemArray;
    private int capacity;

    private static final int DEFAULT_STACK_SIZE = 10;
    private int currentIndex=-1;

   
    public StackImplementation() throws StackAllocationException {
        this(DEFAULT_STACK_SIZE);
    }

    public StackImplementation(int capacity) throws StackAllocationException {
        if (capacity < 2) {
            throw new StackAllocationException("Capacity should be at least 2");
        }
        try {
            itemArray = new Object[capacity];
        } catch (Exception e) {
            throw new StackAllocationException("Cannot allocate room for the internal array");
        }
        this.capacity = capacity;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void push(E element) throws StackAllocationException, NullPointerException {

        if(element==null){
            throw new NullPointerException("can not be push");
        }
        if (currentIndex+1>=capacity){
            int newCapacity = capacity*2;
            Object[] newArray;
            try{
                newArray = new  Object[newCapacity];
                for (int i = 0; i < itemArray.length; i++){
                    newArray[i] = itemArray[i];
                }
                itemArray = newArray;
                capacity = newCapacity;

            }catch (Exception e){
                throw new StackAllocationException("cannot ");
            }
        }
        itemArray[++currentIndex] = element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() throws StackIsEmptyException {
        if (isEmpty()) {
            throw new StackIsEmptyException("Stack is empty");
        }
        E poppedElement = (E) itemArray[currentIndex]; // Decrement size before retrieving element
        itemArray[currentIndex] = null; // Prevent memory leak
        currentIndex--;
        return poppedElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() throws StackIsEmptyException {
        if (isEmpty()) {
            throw new StackIsEmptyException("Stack is empty");
        }
        return (E) itemArray[currentIndex]; // Peek the top element without removing it
    }

    @Override
    public int size() {
        return currentIndex+1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < currentIndex; i++) {
            itemArray[i] = null; // Clear references to elements
        }
        currentIndex = -1; // Reset size
    }

    @Override
    public boolean isEmpty() {
        return currentIndex==-1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < currentIndex+1; i++) {
            builder.append(itemArray[i]);
            if (i < currentIndex) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
