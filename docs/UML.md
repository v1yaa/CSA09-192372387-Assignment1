# UML (Mermaid)
```mermaid
classDiagram
 Vehicle <|-- TwoWheeler
 Vehicle <|-- Car
 Car <|-- ElectricCar
 Vehicle <|-- EBike
 Car <|-- DeliveryVan
 Chargeable <|.. ElectricCar
 Pass <|.. MonthlyPass
 Pass <|.. OneDayPass
 ParkingService --> ParkingFacility
 ParkingService --> ParkingTicket
 ParkingService --> PricingService
 ParkingTicket --> Vehicle
```
