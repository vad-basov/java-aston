// кошка
class Cat extends Mammal implements FurBearing {
    @Override
    public boolean hasFur() {
        return true;
    }
}

// медведь
class Bear extends Mammal implements FurBearing {
    @Override
    public boolean hasFur() {
        return true;
    }
}

// кит
class Whale extends Mammal implements WaterDweller {
    @Override
    public boolean livesInWater() {
        return true;
    }
}

// рыба
class Fish extends Animal implements WaterDweller {
    @Override
    public boolean livesInWater() {
        return true;
    }
}