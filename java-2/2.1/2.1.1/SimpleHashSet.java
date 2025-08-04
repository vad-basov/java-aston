import java.util.Objects;

public class SimpleHashSet<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Node<E>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public SimpleHashSet() {
        buckets = (Node<E>[]) new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    private static class Node<E> {
        final E data;
        Node<E> next;

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public boolean add(E element) {
        if (size >= buckets.length * LOAD_FACTOR) {
            resize();
        }

        int index = getIndex(element);
        Node<E> current = buckets[index];

        while (current != null) {
            if (Objects.equals(element, current.data)) {
                return false;
            }
            current = current.next;
        }

        buckets[index] = new Node<>(element, buckets[index]);
        size++;
        return true;
    }

    public boolean remove(Object element) {
        int index = getIndex((E) element);
        Node<E> current = buckets[index];
        Node<E> prev = null;

        while (current != null) {
            if (Objects.equals(element, current.data)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    private int getIndex(E element) {
        if (element == null) {
            return 0;
        }
        return (element.hashCode() & 0x7FFFFFFF) % buckets.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<E>[] oldBuckets = buckets;
        buckets = (Node<E>[]) new Node[oldBuckets.length * 2];
        size = 0;

        for (Node<E> node : oldBuckets) {
            while (node != null) {
                add(node.data);
                node = node.next;
            }
        }
    }
}