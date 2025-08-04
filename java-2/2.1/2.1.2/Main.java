public class Main {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        // Добавление элементов
        list.add("Apple");
        list.add("Banana");
        list.add(1, "Orange");

        // Получение элементов
        System.out.println(list.get(0));
        System.out.println(list.get(1));

        // Удаление
        list.remove(0);
        list.remove("Banana");

        // Размер списка
        System.out.println("Size: " + list.size());
    }
}