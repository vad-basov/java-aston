import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Alice", List.of(
                        new Book("Book A", 2005, 250),
                        new Book("Book B", 1998, 300),
                        new Book("Book C", 2010, 150),
                        new Book("Book D", 2003, 400),
                        new Book("Book E", 2001, 200)
                )),
                new Student("Bob", List.of(
                        new Book("Book F", 2002, 350),
                        new Book("Book G", 2007, 180),
                        new Book("Book C", 2010, 150),
                        new Book("Book H", 1999, 500),
                        new Book("Book I", 2004, 220)
                )),
                new Student("Charlie", List.of(
                        new Book("Book J", 2008, 280),
                        new Book("Book K", 2012, 190),
                        new Book("Book L", 2006, 320),
                        new Book("Book M", 2001, 210),
                        new Book("Book N", 2003, 240)
                )),
                new Student("David", List.of(
                        new Book("Book O", 2009, 270),
                        new Book("Book P", 2015, 160),
                        new Book("Book Q", 2004, 290),
                        new Book("Book R", 2002, 230),
                        new Book("Book S", 2007, 200)
                ))
        );

        Optional<Integer> result = students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted((b1, b2) -> Integer.compare(b1.getPages(), b2.getPages()))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst();

        result.ifPresent(year -> System.out.println("First year found: " + year));
    }
}