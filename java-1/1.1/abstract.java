//абстрактный класс животные
abstract class Animal {
    // код класса
}

// абстрактный класс млекопитающие
abstract class Mammal extends Animal {
    // есть позвоночник
    public boolean hasVertebrae() {
        return true;
    }

}