

class Book {
    private final String title;
    private final int year;
    private final int pages;

    public Book(String title, int year, int pages) {
        this.title = title;
        this.year = year;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return title + " (" + year + "), " + pages + " pages";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && pages == book.pages && title.equals(book.title);
    }

    @Override
    public int hashCode() {
        return 31 * title.hashCode() + year + pages;
    }
}