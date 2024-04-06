package oy.tol.tra;


public class StackImplementation<E> implements StackInterface<E> {

   private Object [] itemArray;//栈空间
   private int capacity =10;//容量
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;

   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
      // TODO: call the constructor with size parameter with default size of 10.

         this(10);
   }

   /** TODO: Implement so that
    * - if the size is less than 2, throw StackAllocationException
    * - if the allocation of the array throws with Java exception,
    *   throw StackAllocationException.
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {
      if (capacity < 2) {
         throw new StackAllocationException("Size should be at least 2.");
      }
      this.capacity = capacity;
      this.itemArray = new Object[capacity];

   }

   @Override
   public int capacity() {
      // TODO: Implement this
      return capacity;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      // TODO: Implement this
      if (element == null) {
         throw new NullPointerException("Element cannot be null.");
      }

      if (currentIndex == capacity - 1) {
         // 满栈 增容
         int newCapacity = capacity * 2;
         Object[] newArray = new Object[newCapacity];
         System.arraycopy(itemArray, 0, newArray, 0, capacity);
         itemArray = newArray;
         capacity = newCapacity;
      }

      currentIndex++;
      itemArray[currentIndex] = element;

   }


   @Override
   public E pop() throws StackIsEmptyException {
      if (isEmpty()) {
         throw new StackIsEmptyException("Cannot pop from an empty stack.");
      }

      E element = (E) itemArray[currentIndex];
      itemArray[currentIndex] = null;
      currentIndex--;

      return element;
   }

   @Override
   public E peek() throws StackIsEmptyException {
      if (isEmpty()) {
         throw new StackIsEmptyException("Cannot peek an empty stack.");
      }

      return (E) itemArray[currentIndex];
   }

   @Override
   public int size() {
      // TODO: Implement this
      return currentIndex + 1;
   }

   @Override
   public void clear() {
      // TODO: Implement this
      for (int i = 0; i <= currentIndex; i++) {
         itemArray[i] = null;
      }
      currentIndex = -1; // 重置currentIndex
   }

   @Override
   public boolean isEmpty() {
      // TODO: Implement this
      return currentIndex== -1;
   }

   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder builder = new StringBuilder("[");
      for (var index = 0; index <= currentIndex; index++) {
         builder.append(itemArray[index].toString());
         if (index < currentIndex) {
            builder.append(", ");
         }
      }
      builder.append("]");
      return builder.toString();
   }
}

