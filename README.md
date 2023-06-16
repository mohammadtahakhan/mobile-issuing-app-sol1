# Mobile phone testing distribution system 
Note: Required tools are maven and jdk8,
Solution can be tested by following ways after cloning the code:
  1.	Run mvn clean install command, and run mvn test
  2.	Run the all unit test cases written in MobilePhoneBookingServiceTest
  3.	One happy path scenario is covered in main method as well, MobilePhoneBookingTestWithMain
 
 Flow diagram for “Mobile Phone Testing Distribution System”  is given bellow.

![image](https://github.com/mohammadtahakhan/mobile-issuing-app-sol1/assets/54814747/6c113d7e-8562-4435-873b-1f5242814065)
    

This solution uses the Singleton pattern for the MobilePhoneInventory class to ensure there is only one instance of the inventory. This class is used as database to hold the list of mobiles.
The MobilePhoneBookingService class provides methods to book and return phones based on their model names and the person booking the phone. This class is designed to work as faced for different operations i.e issue and return and this class is having other methods to interact with MobilePhoneInventory class to fetch the information.

The MobilePhone class represents each mobile phone, and the BookedBy and MobileModel classes are entity classes that hold information about the person booking the phone and the mobile phone model, respectively.

Class diagram describe the class relationships is given below:

![image](https://github.com/mohammadtahakhan/mobile-issuing-app-sol1/assets/54814747/1334766e-6393-4523-a213-a8d8cda0f54f)
    

Sequence diagram for “Mobile Phone Testing Distribution System” is given below
![image](https://github.com/mohammadtahakhan/mobile-issuing-app-sol1/assets/54814747/bca72080-fbff-4e84-b476-11690f2bc2be)

