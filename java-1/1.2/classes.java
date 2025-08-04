
// самолет 
class Airplane extends Vehicle implements HasWheels, HasPropeller, HasWings, CargoTransport {

}

// вертолет
class Helicopter extends Vehicle implements HasWheels, HasPropeller, CargoTransport {

}

// катер
class Boat extends Vehicle implements HasPropeller, CargoTransport {

}

// танкер
class Tanker extends Vehicle implements HasPropeller, CargoTransport {

}

// грузовик
class Truck extends Vehicle implements HasWheels, CargoTransport {

}

// такси
class Taxi extends Vehicle implements HasWheels {
}