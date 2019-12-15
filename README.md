# Vending Machine

A console program that imitate the Vending Machine, receives the standard input and generate the standard output
## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.
```
1. git clone https://github.com/shankar96/VendingMachine.git
2. mvn clean compile
3. mvn test
4. mvn exec:exec
``` 
### Prerequisites

What things you need to install the software and how to install them

```
java and mvn installed
```

### Installing

```
1. mvn package
2. java -jar target/VendingMachine-1.0-SNAPSHOT.jar
```





## Running the tests

There are three basic tests to check running state of vending machine
### Successfull purchase on fullpaid

purchase successfull on inserting same required coins as item price

```
insert coins 10JPY 10JPY 100JPY
purchase canned coins 
   successfull ✔
returned coins 0
```

### return change success and successfull purchage

return change will happen if amount is more than item value if there is sufficient change

```
insert coins 10JPY 10JPY 10JPY 100JPY
purchase canned coins
   successfull ✔
returned coins 10
```

### return all change success without purchase no change

return all amount will happen if amount is more than item value if there is insufficient change

```
insert coins 10JPY 10JPY 10JPY 100JPY
purchase canned coins
   successfull ✔
returned coins 130
```

```
insert coins 10JPY 10JPY 10JPY
purchase canned coins
   successfull ✔
returned coins 20
```

## Deployment

Additional notes about how to deploy this on a live system
copy generated jar files to use anywhere

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Shankar Kumar Chaudhary** - *Initial work* - [Shanky](https://github.com/shankar96)

See also the list of [contributors](https://github.com/shankar96/VendingMachine/graphs/contributors) who participated in this project.


