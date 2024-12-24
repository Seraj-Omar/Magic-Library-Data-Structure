package magicallibrary;
import java.util.*;

class MyStack<T> {
    private T[] stack;
    private int size;
    private static final int cap = 10;

    public MyStack() {
        stack = (T[]) new Object[cap];
        size = 0;
    }

    public void push(T element) {
        ensureCapacity();
        stack[size++] = element;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T element = stack[--size];
        stack[size] = null;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == stack.length) {
            T[] temp = (T[]) new Object[stack.length * 2];
            for (int i = 0; i < stack.length; i++) {
                temp[i] = stack[i];
            }
            stack = temp;
        }
    }

    public void print() {
        printRecursive(0);
        System.out.println();
    }

    private void printRecursive(int index) {
        if (index >= size) {
            return;
        }
        System.out.print(stack[index]);
        if (index < size - 1) {
            System.out.print(", ");
        }
        printRecursive(index + 1);
    }
}

class MyArrayList<T> {
    private T[] a;
    private int size;
    private static final int cap = 10;

    public MyArrayList() {
        a = (T[]) new Object[cap];
        size = 0;
    }

    public void add(T element) {
        ensureCapacity();
        a[size++] = element;
    }

    public T get(int index) {
        checkIndex(index);
        return a[index];
    }

    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            a[i] = a[i + 1];
        }
        a[--size] = null;
    }

    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (a[i].equals(value)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == a.length) {
            T[] temp = (T[]) new Object[size * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = a[i];
            }
            a = temp;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void print() {
        printRecursive(0);
        System.out.println();
    }

    private void printRecursive(int index) {
        if (index >= size) {
            return;
        }
        System.out.print(a[index]);
        if (index < size - 1) {
            System.out.print(", ");
        }
        printRecursive(index + 1);
    }
}

class MyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        return getRecursive(head, index, 0);
    }

    private T getRecursive(Node<T> node, int targetIndex, int currentIndex) {
        if (currentIndex == targetIndex) {
            return node.data;
        }
        return getRecursive(node.next, targetIndex, currentIndex + 1);
    }

    public void remove(int index) {
        checkIndex(index);
        head = removeRecursively(head, index, 0);
        size--;
    }

    private Node<T> removeRecursively(Node<T> node, int i, int curr) {
        if (curr == i) {
            return node.next;
        }
        node.next = removeRecursively(node.next, i, curr + 1);
        return node;
    }

    public boolean remove(T value) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(value)) {
            head = head.next;
            size--;
            if (head == null) {
                tail = null;
            }
            return true;
        }
        return removeRecursively(head, value);
    }

    private boolean removeRecursively(Node<T> node, T value) {
        if (node.next == null) {
            return false;
        }
        if (node.next.data.equals(value)) {
            node.next = node.next.next;
            if (node.next == null) {
                tail = node;
            }
            size--;
            return true;
        }
        return removeRecursively(node.next, value);
    }

    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void print() {
        printRecursive(head);
        System.out.println();
    }

    private void printRecursive(Node<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data);
        if (node.next != null) {
            System.out.print(", ");
        }
        printRecursive(node.next);
    }
}
class MyCircularQueue<T> {
    private T[] queue;
    private int front;
    private int rear;
    private int size;
    private static final int cap = 10;

    public MyCircularQueue() {
        queue = (T[]) new Object[cap];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(T element) {
        ensureCapacity();
        rear = (rear + 1) % queue.length;
        queue[rear] = element;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T element = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        size--;
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (!isFull())
            return;

        T[] temp = (T[]) new Object[queue.length * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[(front + i) % queue.length];
        }

        queue = temp;
        front = 0;
        rear = size - 1;
    }

    public void print() {
        printRecursive(0);
        System.out.println();
    }

    private void printRecursive(int index) {
        if (index >= size) {
            return;
        }
        System.out.print(queue[(front + index) % queue.length]);
        if (index < size - 1) {
            System.out.print(", ");
        }
        printRecursive(index + 1);
    }
}

class MagicalLibrary {
    
    MyArrayList<String> catalog = new MyArrayList<>();
    MyLinkedList<String> borrowedBooks = new MyLinkedList<>();
    MyStack<String> searchHistory = new MyStack<>();
    MyCircularQueue<String> requestQueue = new MyCircularQueue<>();
    

    MyArrayList<String> adventureBooks = new MyArrayList<>();
    MyArrayList<String> mysteryBooks = new MyArrayList<>();
    MyArrayList<String> fantasyBooks = new MyArrayList<>();
    MyArrayList<String> historicalBooks = new MyArrayList<>();
    
    MyArrayList<String> elementalControlBooks = new MyArrayList<>();
    MyArrayList<String> healingBooks = new MyArrayList<>();
    MyArrayList<String> enchantmentBooks = new MyArrayList<>();
    
    public void addBookToCatalog(String book, String category, String Property) {
        catalog.add(book);
        switch (Property.toLowerCase()) {
            case "adventure":
                adventureBooks.add(book);
                break;
            case "mystery":
                mysteryBooks.add(book);
                break;
            case "fantasy":
                fantasyBooks.add(book);
                break;
            case "historical":
                historicalBooks.add(book);
                break;
            case "elemental control":
                elementalControlBooks.add(book);
                break;
            case "healing":
                healingBooks.add(book);
                break;
            case "enchantment":
                enchantmentBooks.add(book);
                break;
        }
        System.out.println("Added to Catalog: " + book);
    }
    
    public void removeBookFromCatalog(String book) {
        if (catalog.remove(book)) 
        {
            removeFromCategory(adventureBooks, book);
            removeFromCategory(mysteryBooks, book);
            removeFromCategory(fantasyBooks, book);
            removeFromCategory(historicalBooks, book);
            removeFromCategory(elementalControlBooks, book);
            removeFromCategory(healingBooks, book);
            removeFromCategory(enchantmentBooks, book);
            System.out.println("Removed from Catalog: " + book);
        } 
        else
            System.out.println("Book not found in Catalog: " + book);
    }

    private void removeFromCategory(MyArrayList<String> category, String book) {
        category.remove(book);
    }

    public void searchBook(String keyword) {
        System.out.println("Searching for: " + keyword);
        boolean found = false;
        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("Found: " + catalog.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found with keyword: " + keyword);
        }
        searchHistory.push(keyword);
    }
    
    public void borrowBook(String book) {
        if (catalog.remove(book)) {
            borrowedBooks.add(book);
            System.out.println("Borrowed: " + book);
        } else {
            System.out.println("Book not available for borrowing: " + book);
        }
    }
    
    public void returnBook(String book) {
        if (borrowedBooks.remove(book)) {
            catalog.add(book);
            System.out.println("Returned: " + book);
        } else {
            System.out.println("Book not found in borrowed list: " + book);
        }
    }
    
    public void addRequest(String request) {
        requestQueue.enqueue(request);
        System.out.println("Added to Request Queue: " + request);
    }
    
    public void processRequest() {
        if (!requestQueue.isEmpty()) {
            String request = requestQueue.dequeue();
            System.out.println("Processing Request: " + request);
        } else {
            System.out.println("No requests in the queue.");
        }
    }
    
    public void displayState() {
        System.out.println("\n----- Magical Library State -----");
        
        System.out.println("Catalog (by Genre):");
        displayCategory("Adventure", adventureBooks);
        System.out.println();
        displayCategory("Mystery", mysteryBooks);
        System.out.println();
        displayCategory("Fantasy", fantasyBooks);
        System.out.println();
        displayCategory("Historical", historicalBooks);
        System.out.println();

        System.out.println("Catalog (by Magical Properties):");
        System.out.println();
        displayCategory("Elemental Control", elementalControlBooks);
        System.out.println();
        displayCategory("Healing", healingBooks);
        System.out.println();
        displayCategory("Enchantment", enchantmentBooks);
        System.out.println();
        
        System.out.print("Borrowed Books: ");
        borrowedBooks.print();
        System.out.println();
        
        System.out.print("Request Queue: ");
        requestQueue.print();
        System.out.println();
        
        System.out.print("Search History: ");
        printSearchHistory();
        System.out.println();
        
        System.out.println("------------------------------------\n");
    }
    
    private void displayCategory(String category, MyArrayList<String> books) {
        System.out.println("Genre/Magical Property: " + category);
        if (books.size() > 0)
            displayBooks(books, 0);
        else
            System.out.println("  No books in this category.");
    }

    private void displayBooks(MyArrayList<String> books, int index) {
        if (index < books.size()) {
            System.out.println("  " + books.get(index));
            displayBooks(books, index + 1);
        }
    }


    public void printSearchHistory() {
        String book="";
        if(!searchHistory.isEmpty())
        {
            book=searchHistory.pop();
            printSearchHistory();
        }
        System.out.print(book+" ");
        System.out.println();
    }
    
    public static void main(String[] args) {
        MagicalLibrary library = new MagicalLibrary();
        System.out.println("Seraj Omar     202310021\n");

        library.addBookToCatalog("The Enchanted Sword of Galladorn", "Genre", "Adventure");
        library.addBookToCatalog("Puss in Boots and the Crystal Cave", "Genre", "Adventure");
        library.addBookToCatalog("The Pirate King's Treasure Map", "Genre", "Adventure");
        library.addBookToCatalog("Voyage to the Edge of the World", "Genre", "Adventure");

        library.addBookToCatalog("The Secret of the Moonlit Forest", "Genre", "Mystery");
        library.addBookToCatalog("The Haunted Castle on the Hill", "Genre", "Mystery");
        library.addBookToCatalog("Whispers from the Wishing Well", "Genre", "Mystery");
        library.addBookToCatalog("The Case of the Missing Spells", "Genre", "Mystery");

        library.addBookToCatalog("The Sorcerer's Guide to Alchemy", "Genre", "Fantasy");
        library.addBookToCatalog("Legend of the Golden Paw", "Genre", "Fantasy");
        library.addBookToCatalog("The Dragon Whisperer's Handbook", "Genre", "Fantasy");
        library.addBookToCatalog("The Tales of Elven Magic", "Genre", "Fantasy");

        library.addBookToCatalog("The Chronicles of the Ancient Kingdoms", "Genre", "Historical");
        library.addBookToCatalog("The Royal Scrolls of Aragonia", "Genre", "Historical");
        library.addBookToCatalog("The Warrior Queen's Legacy", "Genre", "Historical");
        library.addBookToCatalog("The Knights of the Round Table", "Genre", "Historical");

        library.addBookToCatalog("The Fire Master's Grimoire", "Magical Properties", "Elemental Control");
        library.addBookToCatalog("Whispering Winds and Waterfalls", "Magical Properties", "Elemental Control");
        library.addBookToCatalog("The Earth Shaper's Guide", "Magical Properties", "Elemental Control");
        library.addBookToCatalog("Storm-bringer: Secrets of Lightning", "Magical Properties", "Elemental Control");

        library.addBookToCatalog("Elixirs and Remedies for the Brave", "Magical Properties", "Healing");
        library.addBookToCatalog("The Healer's Handbook", "Magical Properties", "Healing");
        library.addBookToCatalog("Potion Brewing for Health and Vitality", "Magical Properties", "Healing");
        library.addBookToCatalog("The Sacred Art of Restoration", "Magical Properties", "Healing");

        library.addBookToCatalog("The Book of Binding and Bewitching", "Magical Properties", "Enchantment");
        library.addBookToCatalog("Enchantments of the Enchanted Isles", "Magical Properties", "Enchantment");
        library.addBookToCatalog("Spells for the Silver Moon", "Magical Properties", "Enchantment");
        library.addBookToCatalog("The Enchanter's Spell-book", "Magical Properties", "Enchantment");


        library.displayState();

        library.searchBook("Crystal");
        library.searchBook("water");
        library.searchBook("Stick");

        library.borrowBook("Puss in Boots and the Crystal Cave");
        library.displayState();

        library.returnBook("Puss in Boots and the Crystal Cave");
        library.displayState();

        library.addRequest("Special request for The Fire Master's Grimoire");
        library.processRequest();
        library.displayState();
    }
}