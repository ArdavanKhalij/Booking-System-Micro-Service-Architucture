import akka.actor.ActorRef
import java.util.Date
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
sealed trait PropertyAvailability
case object PropertyIsAvailable extends PropertyAvailability
case object PropertyIsNotAvailable extends PropertyAvailability
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
sealed trait PropertyType
case object Hotel extends PropertyType
case object Apartment extends PropertyType
case object Resort extends PropertyType
case object NON extends PropertyType
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
case class client(name: String, age: Int, passport_number: String)
case class property(id: Int, name: String, PropertyType: PropertyType, category: Int, country: String, city: String,
                    var NotAvailable: List[Date], Price: Double)
case class AvailableProperty(id: Int, name: String, PropertyType: PropertyType, category: Int, country: String,
                             city: String, price: Double)
case class AvailableProperties(var availableProperties: List[AvailableProperty])
case class SendProperty(date: Date, propertyType: PropertyType, actorRef: ActorRef)
case class SendPropertyName(date: Date, name: String, actorRef: ActorRef)
case class SendPropertyCategory(date: Date, category: Int, actorRef: ActorRef)
case class SendPropertyCountryCity(date: Date, country: String, city: String, actorRef: ActorRef)
case class SendPropertyCountryCityName(date: Date, country: String, city: String, name: String, actorRef: ActorRef)
case class SendPropertyCountryCityCategory(date: Date, country: String, city: String, category: Int, actorRef: ActorRef)
case class SendPropertyNameCategory(date: Date, category: Int, name: String, actorRef: ActorRef)
case class SendPropertyTypeName(date: Date, propertyType: PropertyType, name: String, actorRef: ActorRef)
case class SendPropertyTypeCountryCity(date: Date, propertyType: PropertyType, country: String, city: String,
                                       actorRef: ActorRef)
case class SendPropertyTypeCountryCityName(date: Date, propertyType: PropertyType, country: String, city: String,
                                           name: String, actorRef: ActorRef)
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////pe