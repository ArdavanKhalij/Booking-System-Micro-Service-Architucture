import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import java.util.Date
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class SystemService (DATE: Date, PT: PropertyType, NAME: String, CITY: String, COUNTRY: String, CATEGORY: Int, searchActor: ActorRef) extends Actor with ActorLogging {
  if (DATE != null) {
    if (PT != NON) {
      if (NAME == "null") {
        if (CITY == "null") {
          if (COUNTRY == "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for ${PT.toString}s with availability on ${DATE.toString}...")
              Thread.sleep(100)
              println("#######################################################################################################")
              Thread.sleep(1000)
              searchActor ! SendProperty(DATE, PT, self)
            }
          }
        }
      }
    }
    if (PT == NON) {
      if (NAME != "null") {
        if (CITY == "null") {
          if (COUNTRY == "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for properties with name $NAME , and availability on ${DATE.toString}...")
              Thread.sleep(100)
              println("#######################################################################################################")
              Thread.sleep(1000)
              searchActor ! SendPropertyName(DATE, NAME, self)
            }
          }
        }
      }
    }
    if (PT == NON) {
      if (NAME == "null") {
        if (CITY == "null") {
          if (COUNTRY == "null") {
            if (CATEGORY != 0) {
              println(s"Start Searching for ${CATEGORY} star properties with availability on ${DATE.toString}...")
              Thread.sleep(100)
              println("#######################################################################################################")
              Thread.sleep(1000)
              searchActor ! SendPropertyCategory(DATE, CATEGORY, self)
            }
          }
        }
      }
    }
    if (PT == NON) {
      if (NAME == "null") {
        if (CITY != "null") {
          if (COUNTRY != "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for properties in ${CITY}, $COUNTRY, with availability on ${DATE.toString}...")
              Thread.sleep(100)
              println("#######################################################################################################")
              Thread.sleep(1000)
              searchActor ! SendPropertyCountryCity(DATE, COUNTRY, CITY, self)
            }
          }
        }
      }
    }
    if (PT == NON) {
      if (NAME != "null") {
        if (CITY != "null") {
          if (COUNTRY != "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for properties in ${CITY}, $COUNTRY, with name $NAME and with availability on ${DATE.toString}...")
              Thread.sleep(100)
              println("#######################################################################################################")
              Thread.sleep(1000)
              searchActor ! SendPropertyCountryCityName(DATE, COUNTRY, CITY, NAME, self)
            }
          }
        }
      }
    }
    if (PT == NON) {
      if (NAME == "null") {
        if (CITY != "null") {
          if (COUNTRY != "null") {
            if (CATEGORY != 0) {
              println(s"Start Searching for properties in ${CITY}, $COUNTRY, and with availability on ${DATE.toString}...")
              Thread.sleep(100)
              println("#######################################################################################################")
              Thread.sleep(1000)
              searchActor ! SendPropertyCountryCityCategory(DATE, COUNTRY, CITY, CATEGORY, self)
            }
          }
        }
      }
    }
    if (PT == NON) {
      if (NAME != "null") {
        if (CITY == "null") {
          if (COUNTRY == "null") {
            if (CATEGORY != 0) {
              println(s"Start Searching for $PT with name, $NAME, and with availability on ${DATE.toString}...")
              Thread.sleep(100)
              println("#######################################################################################################")
              Thread.sleep(1000)
              searchActor ! SendPropertyNameCategory(DATE, CATEGORY, NAME, self)
            }
          }
        }
      }
    }
    if (PT != NON) {
      if (NAME != "null") {
        if (CITY == "null") {
          if (COUNTRY == "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for $CATEGORY star $PT and with availability on ${DATE.toString}...")
              Thread.sleep(100)
              println("#######################################################################################################")
              Thread.sleep(1000)
              searchActor ! SendPropertyTypeName(DATE, PT, NAME, self)
            }
          }
        }
      }
    }
    if (PT != NON) {
      if (NAME == "null") {
        if (CITY != "null") {
          if (COUNTRY != "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for $PT in $CITY, $COUNTRY, and with availability on ${DATE.toString}...")
              Thread.sleep(100)
              println("#######################################################################################################")
              Thread.sleep(1000)
              searchActor ! SendPropertyTypeCountryCity(DATE, PT, COUNTRY, CITY, self)
            }
          }
        }
      }
    }
    if (PT != NON) {
      if (NAME != "null") {
        if (CITY != "null") {
          if (COUNTRY != "null") {
            if (CATEGORY == 0) {
              println(s"Start Searching for $PT in $CITY, $COUNTRY, with name $NAME and with availability on ${DATE.toString}...")
              Thread.sleep(100)
              println("#######################################################################################################")
              Thread.sleep(1000)
              searchActor ! SendPropertyTypeCountryCityName(DATE, PT, COUNTRY, CITY, NAME, self)
            }
          }
        }
      }
    }
  }
  override def receive: Receive = {
    case AvailableProperties(availableProperties) => {
      var i = 1
      availableProperties.foreach { X =>
        Thread.sleep(200)
        println(s"$i- ${X.name} ${X.category.toString} star ${X.PropertyType.toString} with id number ${X.id} in ${X.city}, ${X.country} is available on ${DATE} for ${X.price.toString} euros.")
        i = i + 1
      }
      println("")
    }
    case _ =>
  }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class Search extends Actor{
  var PROPERTIES = List(
    property(1, "Avenue Louise", Hotel, 4, "Belgium", "Brussels", List(), 70),
    property(2, "Azadi", Hotel, 5, "Iran", "Tehran", List(), 99),
    property(3, "Arora", Resort, 4, "Turkey", "Istanbul", List(), 24),
    property(4, "Diamond", Apartment, 3, "Belgium", "Brussels", List(), 24),
    property(5, "Hilton", Hotel, 5, "Iran", "Tehran", List(), 24),
    property(6, "Dedeman", Resort, 5, "Turkey", "Antalyia", List(), 24),
    property(7, "LUX", Apartment, 4, "USA", "Las Vegas", List(), 24),
    property(8, "SIDE", Resort, 3, "UK", "London", List(), 24),
    property(9, "Khalifa", Hotel, 5, "UAE", "Dubai", List(), 24),
    property(10, "Airbnb", Apartment, 4, "Belgium", "Brussels", List(), 24),
    property(11, "Airbnb", Apartment, 5, "Belgium", "Brussels", List(), 24),
    property(12, "Ferdowsi", Resort, 4, "Iran", "Mashhad", List(), 24),
    property(13, "Pasargad", Hotel, 5, "Iran", "Kish", List(), 24),
    property(14, "Homa", Hotel, 5, "Iran", "Tehran", List(), 24),
    property(15, "Vista hill", Resort, 3, "Turkey", "Antalyia", List(), 24),
    property(16, "LUXA", Apartment, 4, "USA", "Las Vegas", List(), 24),
    property(17, "SID", Resort, 3, "UK", "London", List(), 24),
    property(18, "ARAM", Resort, 5, "UAE", "Dubai", List(), 24),
    property(19, "Kroonlaan", Hotel, 4, "Belgium", "Brussels", List(), 24),
    property(20, "Espinas", Hotel, 5, "Iran", "Tehran", List(), 24),
    property(21, "Cornel", Resort, 4, "Turkey", "Istanbul", List(), 24),
    property(22, "Gold", Apartment, 3, "Belgium", "Brussels", List(), 24),
    property(23, "Taksim", Hotel, 5, "Iran", "Istanbul", List(), 24),
    property(24, "Abbasi", Resort, 5, "Iran", "Isfahan", List(), 24),
    property(25, "Eram", Apartment, 4, "Iran", "Shiraz", List(), 24)
  )
  override def receive: Receive = {
    case SendProperty(date, propertyType, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.PropertyType == propertyType){
            APS.availableProperties = APS.availableProperties :+ AvailableProperty(X.id, X.name, X.PropertyType,
              X.category, X.country, X.city, X.Price)
          }
        }
      }
      replyTo ! APS
    }
    case SendPropertyName(date, name, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.name == name){
            APS.availableProperties = APS.availableProperties :+ AvailableProperty(X.id, X.name, X.PropertyType,
              X.category, X.country, X.city, X.Price)
          }
        }
      }
      replyTo ! APS
    }
    case SendPropertyCategory(date, category, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.category == category){
            APS.availableProperties = APS.availableProperties :+ AvailableProperty(X.id, X.name, X.PropertyType,
              X.category, X.country, X.city, X.Price)
          }
        }
      }
      replyTo ! APS
    }
    case SendPropertyCountryCity(date, country, city, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.country == country){
            if (X.city == city){
              APS.availableProperties = APS.availableProperties :+ AvailableProperty(X.id, X.name, X.PropertyType,
                X.category, X.country, X.city, X.Price)
            }
          }
        }
      }
      replyTo ! APS
    }
    case SendPropertyCountryCityName(date, country, city, name, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.country == country){
            if (X.city == city){
              if (X.name == name){
                APS.availableProperties = APS.availableProperties :+ AvailableProperty(X.id, X.name, X.PropertyType,
                  X.category, X.country, X.city, X.Price)
              }
            }
          }
        }
      }
      replyTo ! APS
    }
    case SendPropertyCountryCityCategory(date, country, city, category, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.country == country){
            if (X.city == city){
              if (X.category == category){
                APS.availableProperties = APS.availableProperties :+ AvailableProperty(X.id, X.name, X.PropertyType,
                  X.category, X.country, X.city, X.Price)
              }
            }
          }
        }
      }
      replyTo ! APS
    }
    case SendPropertyNameCategory(date, category, name, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.category == category){
            if (X.name == name) {
              APS.availableProperties = APS.availableProperties :+ AvailableProperty(X.id, X.name, X.PropertyType,
                X.category, X.country, X.city, X.Price)
            }
          }
        }
      }
      replyTo ! APS
    }
    case SendPropertyTypeName(date, propertyType, name, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.PropertyType == propertyType){
            if (X.name == name) {
              APS.availableProperties = APS.availableProperties :+ AvailableProperty(X.id, X.name, X.PropertyType,
                X.category, X.country, X.city, X.Price)
            }
          }
        }
      }
      replyTo ! APS
    }
    case SendPropertyTypeCountryCity(date, propertyType, country, city, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.PropertyType == propertyType){
            if (X.country == country){
              if (X.city == city){
                APS.availableProperties = APS.availableProperties :+ AvailableProperty(X.id, X.name, X.PropertyType,
                  X.category, X.country, X.city, X.Price)
              }
            }
          }
        }
      }
      replyTo ! APS
    }
    case SendPropertyTypeCountryCityName(date, propertyType, country, city, name, replyTo) => {
      var APS = AvailableProperties(List())
      PROPERTIES.foreach {  X =>
        if (!X.NotAvailable.contains(date)){
          if (X.PropertyType == propertyType){
            if (X.country == country){
              if (X.city == city){
                if (X.name == name){
                  APS.availableProperties = APS.availableProperties :+ AvailableProperty(X.id, X.name, X.PropertyType,
                    X.category, X.country, X.city, X.Price)
                }
              }
            }
          }
        }
      }
      replyTo ! APS
    }
    case _ =>
  }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//class Client(CLIENT: client, PROPERTY: List[property], searchActor: ActorRef) extends Actor with ActorLogging {
//  var returnProperties: List[property] = List()
//  override def receive: Receive = {
//    case String =>
//  }
//}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////