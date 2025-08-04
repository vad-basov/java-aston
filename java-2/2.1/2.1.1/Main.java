public class Main {
    public static void main(String[] args) {
        SimpleHashSet<String> set = new SimpleHashSet<>();

        // Добавление элементов
        System.out.println(set.add("Apple"));
        System.out.println(set.add("Banana"));
        System.out.println(set.add("Apple"));

        // Удаление элементов
        System.out.println(set.remove("Banana"));
        System.out.println(set.remove("Grape"));

        System.out.println("Size: " + set.size());
    }
}